package io.vertx.reactivex.sqlclient;

import java.util.List;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.reactive.NRHandlerWrapper;

import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Weave(type=MatchType.BaseClass)
public abstract class SqlClient {

	@Trace
	public io.vertx.reactivex.sqlclient.SqlClient preparedBatch(String sql, List<io.vertx.reactivex.sqlclient.Tuple> batch, Handler<AsyncResult<io.vertx.reactivex.sqlclient.RowSet>> handler) { 
		NRHandlerWrapper<io.vertx.reactivex.sqlclient.RowSet> wrapper = new NRHandlerWrapper<io.vertx.reactivex.sqlclient.RowSet>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}
	
	@Trace
	public io.vertx.reactivex.sqlclient.SqlClient preparedQuery(String sql, Handler<AsyncResult<io.vertx.reactivex.sqlclient.RowSet>> handler) { 
		NRHandlerWrapper<io.vertx.reactivex.sqlclient.RowSet> wrapper = new NRHandlerWrapper<io.vertx.reactivex.sqlclient.RowSet>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}
	
	@Trace
	public io.vertx.reactivex.sqlclient.SqlClient preparedQuery(String sql, io.vertx.reactivex.sqlclient.Tuple arguments, Handler<AsyncResult<io.vertx.reactivex.sqlclient.RowSet>> handler) { 
		NRHandlerWrapper<io.vertx.reactivex.sqlclient.RowSet> wrapper = new NRHandlerWrapper<io.vertx.reactivex.sqlclient.RowSet>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public io.vertx.reactivex.sqlclient.SqlClient query(String sql, Handler<AsyncResult<io.vertx.reactivex.sqlclient.RowSet>> handler) { 
		NRHandlerWrapper<io.vertx.reactivex.sqlclient.RowSet> wrapper = new NRHandlerWrapper<io.vertx.reactivex.sqlclient.RowSet>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}
	
	@Trace
	public Single<io.vertx.reactivex.sqlclient.RowSet> rxPreparedBatch(String sql, List<io.vertx.reactivex.sqlclient.Tuple> batch) { 
		return Weaver.callOriginal();
	}
	
	@Trace
	public Single<io.vertx.reactivex.sqlclient.RowSet> rxPreparedQuery(String sql) { 
		return Weaver.callOriginal();
	}
	
	@Trace
	public Single<io.vertx.reactivex.sqlclient.RowSet> rxQuery(String sql) { 
		return Weaver.callOriginal();
	}
}
