package io.vertx.sqlclient;

import java.util.List;
import java.util.stream.Collector;

import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.sqlclient.NRResultWrapper;
import com.nr.instrumentation.sqlclient.Utils;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Weave(type = MatchType.Interface)
public abstract class SqlClient {


	public SqlClient query(String sql, Handler<AsyncResult<RowSet>> handler) {
		if (!(handler instanceof NRResultWrapper)) {
			NRResultWrapper<RowSet> wrapper = Utils.getWrapper(handler, sql, this);
			handler = wrapper;
		} 
		return Weaver.callOriginal();
	}

	public <R> SqlClient query(String sql, Collector<Row, ?, R> collector, Handler<AsyncResult<SqlResult<R>>> handler) {
		if (!(handler instanceof NRResultWrapper)) {
			NRResultWrapper<SqlResult<R>> wrapper = Utils.getWrapper(handler, sql, this);
			handler = wrapper;
		} 
		return Weaver.callOriginal();
	}

	public SqlClient preparedQuery(String sql, Handler<AsyncResult<RowSet>> handler) {
		if (!(handler instanceof NRResultWrapper)) {
			NRResultWrapper<RowSet> wrapper = Utils.getWrapper(handler, sql, this);
			handler = wrapper;
		} 
		return Weaver.callOriginal();
	}

	public <R> SqlClient preparedQuery(String sql, Collector<Row, ?, R> collector, Handler<AsyncResult<SqlResult<R>>> handler) {
		if (!(handler instanceof NRResultWrapper)) {
			NRResultWrapper<SqlResult<R>> wrapper = Utils.getWrapper(handler, sql, this);
			handler = wrapper;
		} 
		return Weaver.callOriginal();
	}

	public SqlClient preparedQuery(String sql, Tuple arguments, Handler<AsyncResult<RowSet>> handler) {
		if (!(handler instanceof NRResultWrapper)) {
			NRResultWrapper<RowSet> wrapper = Utils.getWrapper(handler, sql, this);
			handler = wrapper;
		} 
		return Weaver.callOriginal();
	}

	public <R> SqlClient preparedQuery(String sql, Tuple arguments, Collector<Row, ?, R> collector, Handler<AsyncResult<SqlResult<R>>> handler) {
		if (!(handler instanceof NRResultWrapper)) {
			NRResultWrapper<SqlResult<R>> wrapper = Utils.getWrapper(handler, sql, this);
			handler = wrapper;
		} 
		return Weaver.callOriginal();
	}

	public SqlClient preparedBatch(String sql, List<Tuple> batch, Handler<AsyncResult<RowSet>> handler) {
		if (!(handler instanceof NRResultWrapper)) {
			NRResultWrapper<RowSet> wrapper = Utils.getWrapper(handler, sql, this);
			handler = wrapper;
		} 
		return Weaver.callOriginal();
	}

	public <R> SqlClient preparedBatch(String sql, List<Tuple> batch, Collector<Row, ?, R> collector, Handler<AsyncResult<SqlResult<R>>> handler) {
		return Weaver.callOriginal();
	}

}
