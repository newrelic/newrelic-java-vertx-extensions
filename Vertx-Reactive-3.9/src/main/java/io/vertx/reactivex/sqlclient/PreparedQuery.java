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
public abstract class PreparedQuery<T> extends io.vertx.reactivex.sqlclient.Query<T> {

	
	@Trace
	public void execute(io.vertx.reactivex.sqlclient.Tuple tuple, Handler<AsyncResult<T>> handler) { 
		NRHandlerWrapper<T> wrapper = new NRHandlerWrapper<T>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		Weaver.callOriginal();
	}
	
	@Trace
	public void executeBatch(List<io.vertx.reactivex.sqlclient.Tuple> batch, Handler<AsyncResult<T>> handler) { 
		NRHandlerWrapper<T> wrapper = new NRHandlerWrapper<T>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		Weaver.callOriginal();
	}
	
	@Trace
	public Single<T> rxExecute(io.vertx.reactivex.sqlclient.Tuple tuple) { 
		return Weaver.callOriginal();
	}
	
	@Trace
	public Single<T> rxExecuteBatch(List<io.vertx.reactivex.sqlclient.Tuple> batch) { 
		return Weaver.callOriginal();
	}
}
