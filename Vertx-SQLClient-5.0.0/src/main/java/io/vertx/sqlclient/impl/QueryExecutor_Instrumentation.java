
package io.vertx.sqlclient.impl;

import com.newrelic.agent.database.ParsedDatabaseStatement;
import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.sqlclient.NRCompletableListener;
import com.newrelic.instrumentation.labs.sqlclient.R2dbcObfuscator;
import com.newrelic.instrumentation.labs.sqlclient.SQLUtils;

import io.vertx.core.internal.PromiseInternal;
import io.vertx.sqlclient.PrepareOptions;
import io.vertx.sqlclient.SqlResult;
import io.vertx.sqlclient.Tuple;
import io.vertx.sqlclient.internal.PreparedStatement;
import io.vertx.sqlclient.internal.command.CommandScheduler;

@Weave(originalName="io.vertx.sqlclient.impl.QueryExecutor")
public abstract class QueryExecutor_Instrumentation<T, R extends SqlResultBase<T>, L extends SqlResult<T>> {

	@Trace(dispatcher = true)
	public void executeSimpleQuery(CommandScheduler scheduler, String sql, boolean autoCommit, boolean singleton,
			PromiseInternal<L> promise) {
		Segment segment = createSegment("SQLClientQuery", sql);
		attachListener(segment, promise);
		Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public void executeExtendedQuery(CommandScheduler scheduler, String sql, PrepareOptions options, boolean autoCommit,
			Tuple arguments, PromiseInternal<L> promise) {
		Segment segment = createSegment("SQLClientExtendedQuery", sql);
		attachListener(segment, promise);
		Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	QueryResultBuilder<T, R, L> executeExtendedQuery(CommandScheduler scheduler, PreparedStatement preparedStatement,
			PrepareOptions options, boolean autoCommit, Tuple values, int fetch, String cursorId, boolean suspended,
			PromiseInternal<L> promise) {
		String sql = preparedStatement.sql();
		Segment segment = createSegment("SQLClientExtendedQuery", sql);
		attachListener(segment, promise);
		return Weaver.callOriginal();
	}

	// Other Methods can be added here as needed
	private Segment createSegment(String segmentName, String sql) {
		ParsedDatabaseStatement parsedStmt = SQLUtils.getParsed(sql);
		String dbType = SocketConnectionBase_Instrumentation.dbTypeContext.get();

		String model = parsedStmt.getModel();
		String operation = parsedStmt.getOperation();

		DatastoreParameters params = null;
		if (model != null && operation != null) {
			params = DatastoreParameters.product(dbType).collection(model).operation(operation).noInstance()
					.databaseName(dbType).slowQuery(sql, R2dbcObfuscator.MYSQL_QUERY_CONVERTER).build();
		}

		Segment segment = NewRelic.getAgent().getTransaction().startSegment(segmentName);
		if (params != null) {
			segment.reportAsExternal(params);
		} else {
			segment.addCustomAttribute("Model", model);
			segment.addCustomAttribute("Operation", operation);
		}

		return segment;
	}

	private void attachListener(Segment segment, PromiseInternal<L> promise) {
		NRCompletableListener<L> listener = new NRCompletableListener<>(segment, null);
		promise.onComplete(listener);
	}
}
