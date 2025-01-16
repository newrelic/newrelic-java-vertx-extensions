package io.vertx.rxjava3.core.eventbus;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRCompletionConsumer;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRErrorConsumer;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRHolder;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRSubscribeConsumer;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRTerminateAction;

import io.reactivex.rxjava3.core.Single;
import io.vertx.core.eventbus.DeliveryOptions;

@Weave
public class EventBus {

	@Trace
	public <T> Single<Message<T>> rxRequest(String address, Object message) { 
		Single<io.vertx.rxjava3.core.eventbus.Message<T>> resultSingle = Weaver.callOriginal();
		NRHolder holder = new NRHolder("EventBus", "rxSend", NewRelic.getAgent().getTransaction());
		return resultSingle.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doOnError(new NRErrorConsumer(holder)).doFinally(new NRTerminateAction(holder));
	}
	
	@Trace
	public <T> Single<Message<T>> rxRequest(String address, Object message, DeliveryOptions options) { 
		Single<io.vertx.rxjava3.core.eventbus.Message<T>> resultSingle = Weaver.callOriginal();
		NRHolder holder = new NRHolder("EventBus", "rxSend", NewRelic.getAgent().getTransaction());
		return resultSingle.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doOnError(new NRErrorConsumer(holder)).doFinally(new NRTerminateAction(holder));
	}

}
