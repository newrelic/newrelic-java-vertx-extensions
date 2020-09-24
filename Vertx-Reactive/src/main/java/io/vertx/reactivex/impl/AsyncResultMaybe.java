package io.vertx.reactivex.impl;

import java.util.function.Consumer;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.reactive.NRConsumer;

import io.reactivex.Maybe;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Weave
public abstract class AsyncResultMaybe<T> {

	
	@Trace(excludeFromTransactionTrace=true)
	public static <T> Maybe<T> toMaybe(Consumer<Handler<AsyncResult<T>>> subscriptionConsumer) {
		NRConsumer<T> wrapper = new NRConsumer<T>(subscriptionConsumer, NewRelic.getAgent().getTransaction().getToken());
		subscriptionConsumer = wrapper;
		return Weaver.callOriginal();
	}
	
}
