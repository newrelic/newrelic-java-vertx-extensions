package io.vertx.sqlclient.impl;

import java.util.List;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

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
		Query<RowSet<Row>> q = Weaver.callOriginal();
		return q;
	}

	@Weave
	private static abstract class QueryImpl<T, R extends SqlResult<T>>  {

		@Trace
		public void execute(Handler<AsyncResult<R>> handler) {
			Weaver.callOriginal();
		}
	}

	@Weave
	private static abstract class PreparedQueryImpl<T, R extends SqlResult<T>>  {

		@Trace
		public void execute(Tuple arguments, Handler<AsyncResult<R>> handler) {
			Weaver.callOriginal();
		}


		@Trace
		public void executeBatch(List<Tuple> batch, Handler<AsyncResult<R>> handler) {
			Weaver.callOriginal();
		}
	}
}
