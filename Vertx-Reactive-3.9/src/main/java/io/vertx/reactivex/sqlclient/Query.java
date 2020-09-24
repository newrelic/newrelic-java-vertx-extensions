package io.vertx.reactivex.sqlclient;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.reactive.NRHandlerWrapper;

import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Weave(type=MatchType.BaseClass)
public abstract class Query<T> {

	public void execute(Handler<AsyncResult<T>> handler) {
		NRHandlerWrapper<T> wrapper = new NRHandlerWrapper<T>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		Weaver.callOriginal();
	}
	
	public Single<T> rxExecute() { 
		return Weaver.callOriginal();
	}
}
