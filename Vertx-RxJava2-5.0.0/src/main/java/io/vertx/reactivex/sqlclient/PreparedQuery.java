package io.vertx.reactivex.sqlclient;

import java.util.List;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import com.nr.instrumentation.vertx.rxjava2.NRHandlerWrapper;
import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Weave
public abstract class PreparedQuery<T> {

	@Trace
	public io.vertx.core.Future<T> executeBatch(java.util.List<io.vertx.reactivex.sqlclient.Tuple> batch)  {
		io.vertx.core.Future<T> future = Weaver.callOriginal();
		return future;
	}
	
	@Trace
	public void execute(Handler<AsyncResult<io.vertx.reactivex.sqlclient.RowSet>> handler) {
		NRHandlerWrapper<io.vertx.reactivex.sqlclient.RowSet> wrapper = new NRHandlerWrapper<io.vertx.reactivex.sqlclient.RowSet>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		Weaver.callOriginal();
	}
	
	@Trace
	public void execute(io.vertx.reactivex.sqlclient.Tuple args, Handler<AsyncResult<io.vertx.reactivex.sqlclient.RowSet>> handler) {
		NRHandlerWrapper<io.vertx.reactivex.sqlclient.RowSet> wrapper = new NRHandlerWrapper<io.vertx.reactivex.sqlclient.RowSet>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		Weaver.callOriginal();
	}
	
	@Trace
	public Single<io.vertx.reactivex.sqlclient.RowSet> rxExecuteBatch(List<io.vertx.reactivex.sqlclient.Tuple> argsList) {
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
