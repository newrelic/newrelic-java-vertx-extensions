package io.vertx.sqlclient.internal;

import java.util.List;
import java.util.logging.Level;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.agent.database.ParsedDatabaseStatement;
import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.sqlclient.FutureWrapper;
import com.nr.instrumentation.sqlclient.NRCompletableListener;
import com.nr.instrumentation.sqlclient.NRHandlerWrapper;
import com.nr.instrumentation.sqlclient.R2dbcObfuscator;
import com.nr.instrumentation.sqlclient.SQLUtils;

import io.vertx.core.Future;
import io.vertx.core.impl.future.FutureImpl;
import io.vertx.core.internal.PromiseInternal;
import io.vertx.sqlclient.Query;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlResult;
import io.vertx.sqlclient.Tuple;
import io.vertx.sqlclient.impl.SocketConnectionBase;

@Weave
public abstract class SqlClientBase {

	@Trace
	public Query<RowSet<Row>> query(String sql) {
		@SuppressWarnings("rawtypes")
		Query q = Weaver.callOriginal();
		return q;
	}

	@Weave
	private static abstract class QueryImpl<T, R extends SqlResult<T>> {

		protected final String sql = Weaver.callOriginal();

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Trace(dispatcher = true)
		public Future<R> execute() {

			AgentBridge.getAgent().getLogger().log(Level.FINEST,
					"SqlClientBase.QueryImpl.execute called with sql: : {0}", sql);

			// Start the segment immediately before the original call
			Segment segment = NewRelic.getAgent().getTransaction().startSegment("VertxSqlClient/Query");

			DatastoreParameters params = null;

			String dbType = SocketConnectionBase.dbTypeContext.get();

			if (sql != null && dbType != null) {
				ParsedDatabaseStatement parsedStmt = SQLUtils.getParsed(sql);
				params = DatastoreParameters.product(dbType).collection(parsedStmt.getModel())
						.operation(parsedStmt.getOperation()).noInstance().databaseName(dbType)
						.slowQuery(sql, R2dbcObfuscator.MYSQL_QUERY_CONVERTER).build();
			}
	
			Future<R> f = Weaver.callOriginal();
			
            System.out.println("FutureWrapper constructor called with original: " + f);
            return new FutureWrapper<>(f, segment);
		}
	}

	@Weave
	private static abstract class PreparedQueryImpl<T, R extends SqlResult<T>> {

		@Trace(dispatcher = true)
		private void executeBatch(List<Tuple> batch, PromiseInternal<R> promise) {
			Weaver.callOriginal();
		}

		@Trace(dispatcher = true)
		private void execute(Tuple arguments, PromiseInternal<R> promise) {
			Weaver.callOriginal();
		}
	}
}