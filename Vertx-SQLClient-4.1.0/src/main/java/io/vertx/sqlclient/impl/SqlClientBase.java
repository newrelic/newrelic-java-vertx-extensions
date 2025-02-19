package io.vertx.sqlclient.impl;

import java.util.List;

import com.newrelic.agent.database.ParsedDatabaseStatement;
import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.sqlclient.NRHandlerWrapper;
import com.nr.instrumentation.sqlclient.R2dbcObfuscator;
import com.nr.instrumentation.sqlclient.SQLUtils;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.sqlclient.Query;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlResult;
import io.vertx.sqlclient.Tuple;

@Weave
public abstract class SqlClientBase {

	@Trace
	public Query<RowSet<Row>> query(String sql) {
		Query q = Weaver.callOriginal();
		return q;
	}

	@Weave
	private static abstract class QueryImpl<T, R extends SqlResult<T>> {

		protected final String sql = Weaver.callOriginal();

		@Trace(dispatcher = true)
		public void execute(Handler<AsyncResult<R>> handler) {

			String dbType = SocketConnectionBase.dbTypeContext.get();

			if (!(handler instanceof NRHandlerWrapper)) {

				Segment segment = NewRelic.getAgent().getTransaction().startSegment("Query");
				// String sql = SQLUtils.getSQL();
				DatastoreParameters params = null;
				if (sql != null && dbType != null) {
					// String dbType = "GenericSqlDB"; // SQLUtils.getDBType(getClass());
					ParsedDatabaseStatement parsedStmt = SQLUtils.getParsed(sql);

					params = DatastoreParameters.product(dbType).collection(parsedStmt.getModel())
							.operation(parsedStmt.getOperation()).noInstance().databaseName(dbType)
							.slowQuery(sql, R2dbcObfuscator.MYSQL_QUERY_CONVERTER).build();
				}
				NRHandlerWrapper wrapper = new NRHandlerWrapper(handler, segment, params);
				handler = wrapper;
			}
			Weaver.callOriginal();
		}
	}

	@Weave
	private static abstract class PreparedQueryImpl<T, R extends SqlResult<T>> {

		@Trace(dispatcher = true)
		public void execute(Tuple arguments, Handler<AsyncResult<R>> handler) {
			Weaver.callOriginal();
		}

		@Trace(dispatcher = true)
		public void executeBatch(List<Tuple> batch, Handler<AsyncResult<R>> handler) {
			Weaver.callOriginal();
		}
	}
}
