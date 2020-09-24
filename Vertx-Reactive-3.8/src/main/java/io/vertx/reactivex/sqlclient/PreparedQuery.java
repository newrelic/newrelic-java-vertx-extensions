package io.vertx.reactivex.sqlclient;

import java.util.List;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.reactive.NRHandlerWrapper;

import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Weave
public abstract class PreparedQuery {

	@Trace
	public io.vertx.reactivex.sqlclient.PreparedQuery batch(List<io.vertx.reactivex.sqlclient.Tuple> argsList, Handler<AsyncResult<io.vertx.reactivex.sqlclient.RowSet>> handler) { 
		NRHandlerWrapper<io.vertx.reactivex.sqlclient.RowSet> wrapper = new NRHandlerWrapper<io.vertx.reactivex.sqlclient.RowSet>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}
	
	@Trace
	public io.vertx.reactivex.sqlclient.PreparedQuery execute(Handler<AsyncResult<io.vertx.reactivex.sqlclient.RowSet>> handler) { 
		NRHandlerWrapper<io.vertx.reactivex.sqlclient.RowSet> wrapper = new NRHandlerWrapper<io.vertx.reactivex.sqlclient.RowSet>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}
	
	@Trace
	public io.vertx.reactivex.sqlclient.PreparedQuery execute(io.vertx.reactivex.sqlclient.Tuple args, Handler<AsyncResult<io.vertx.reactivex.sqlclient.RowSet>> handler) { 
		NRHandlerWrapper<io.vertx.reactivex.sqlclient.RowSet> wrapper = new NRHandlerWrapper<io.vertx.reactivex.sqlclient.RowSet>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}
	
	@Trace
	public Single<io.vertx.reactivex.sqlclient.RowSet> rxBatch(List<io.vertx.reactivex.sqlclient.Tuple> argsList) { 
		return Weaver.callOriginal();
	}
	
	@Trace
	public Single<io.vertx.reactivex.sqlclient.RowSet> rxExecute() { 
		return Weaver.callOriginal();
	}
	
	@Trace
	public Single<io.vertx.reactivex.sqlclient.RowSet> rxExecute(io.vertx.reactivex.sqlclient.Tuple args) { 
		return Weaver.callOriginal();
	}
}
