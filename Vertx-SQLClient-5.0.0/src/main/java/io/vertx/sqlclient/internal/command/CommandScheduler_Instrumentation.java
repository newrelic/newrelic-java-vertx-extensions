package io.vertx.sqlclient.internal.command;

import java.util.logging.Level;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.agent.database.ParsedDatabaseStatement;
import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.sqlclient.NRCompletableListener;
import com.nr.instrumentation.sqlclient.R2dbcObfuscator;
import com.nr.instrumentation.sqlclient.SQLUtils;

import io.vertx.core.Completable;
import io.vertx.sqlclient.impl.SocketConnectionBase;

@Weave(originalName = "io.vertx.sqlclient.internal.command.CommandScheduler", type = MatchType.Interface)
abstract public class CommandScheduler_Instrumentation {

  public  <R> void schedule(CommandBase<R> cmd, Completable<R> handler) {
        // Log the SQL command
        AgentBridge.getAgent().getLogger().log(Level.FINEST, "SqlClientBase.QueryImpl.execute called with cmd: {0}", cmd);

        // Start the segment immediately before the original call
        Segment segment = NewRelic.getAgent().getTransaction().startSegment("VertxSqlClient/Query");

        DatastoreParameters params = null;

        String dbType = SocketConnectionBase.dbTypeContext.get();
        String sql = null;

        // Check the type of cmd and retrieve the SQL query
        if (cmd instanceof PrepareStatementCommand) {
            PrepareStatementCommand prepareCmd = (PrepareStatementCommand) cmd;
            sql = prepareCmd.sql();
        } else if (cmd instanceof QueryCommandBase) {
            QueryCommandBase<?> queryCmd = (QueryCommandBase<?>) cmd;
            sql = queryCmd.sql();
        }
        // Build DatastoreParameters if SQL and dbType are available
        if (sql != null && dbType != null) {
            ParsedDatabaseStatement parsedStmt = SQLUtils.getParsed(sql);
            params = DatastoreParameters.product(dbType)
                .collection(parsedStmt.getModel())
                .operation(parsedStmt.getOperation())
                .noInstance()
                .databaseName(dbType)
                .slowQuery(sql, R2dbcObfuscator.MYSQL_QUERY_CONVERTER)
                .build();
        }

        // Wrap the handler with NRCompletableListener
        NRCompletableListener<R> listener = new NRCompletableListener<>(segment, params,handler);
        handler = listener;

        // Call the original method
        Weaver.callOriginal();

        // Ensure the segment is ended after the original method completes
    }
}