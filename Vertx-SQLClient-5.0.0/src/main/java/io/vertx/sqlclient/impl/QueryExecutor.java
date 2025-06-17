package io.vertx.sqlclient.impl;

import com.newrelic.agent.database.ParsedDatabaseStatement;
import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.sqlclient.NRCompletableListener;
import com.nr.instrumentation.sqlclient.R2dbcObfuscator;
import com.nr.instrumentation.sqlclient.SQLUtils;

import io.vertx.core.internal.PromiseInternal;
import io.vertx.sqlclient.PrepareOptions;
import io.vertx.sqlclient.SqlResult;
import io.vertx.sqlclient.Tuple;
import io.vertx.sqlclient.internal.PreparedStatement;
import io.vertx.sqlclient.internal.command.CommandScheduler;

@Weave
public abstract class QueryExecutor<T, R extends SqlResultBase<T>, L extends SqlResult<T>> {

	@Trace
	public void executeSimpleQuery(CommandScheduler scheduler,String sql, boolean autoCommit,
			boolean singleton, PromiseInternal<L> promise) {
		ParsedDatabaseStatement parsedStmt = SQLUtils.getParsed(sql);
		String dbType = SocketConnectionBase.dbTypeContext.get();

		String model = parsedStmt.getModel();
		String operation = parsedStmt.getOperation();
		
		DatastoreParameters params = null;
		if(model != null && operation != null) {
			params = DatastoreParameters.product(dbType)
				.collection(model)
				.operation(operation)
				.noInstance()
				.databaseName(dbType)
				.slowQuery(sql, R2dbcObfuscator.MYSQL_QUERY_CONVERTER)
				.build();
		}
		Segment segment = NewRelic.getAgent().getTransaction().startSegment("SQLClientQuery");
		if(params != null) {
			segment.reportAsExternal(params);
		} else {
			segment.addCustomAttribute("Model", model);
			segment.addCustomAttribute("Operation", operation);
		}

		NRCompletableListener<L> listener = new NRCompletableListener<>(segment, params);
		promise.onComplete(listener);
		Weaver.callOriginal();
	}

	public void executeExtendedQuery(CommandScheduler scheduler, String sql, PrepareOptions options, boolean autoCommit, Tuple arguments, PromiseInternal<L> promise) {
		/*
		 * TO DO
		 * Add logic to capture query and report as a segment
		 */
		Weaver.callOriginal();
	}
	
	QueryResultBuilder<T, R, L> executeExtendedQuery(CommandScheduler scheduler,
            PreparedStatement preparedStatement,
            PrepareOptions options,
            boolean autoCommit,
            Tuple values,
            int fetch,
            String cursorId,
            boolean suspended,
            PromiseInternal<L> promise) {
		/*
		 * TO DO
		 * Add logic to capture query and report as a segment
		 */
		
		return Weaver.callOriginal();
	}
}
