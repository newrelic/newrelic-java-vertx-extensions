package io.vertx.reactivex.impl;

import java.util.function.Consumer;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.reactive.NRSingleOperator;

import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Weave
public abstract class AsyncResultSingle<T> {

	@Trace(excludeFromTransactionTrace=true)
	public static <T> Single<T> toSingle(Consumer<Handler<AsyncResult<T>>> subscriptionConsumer) {
		Single<T> single = Weaver.callOriginal();
		
		return single.lift(new NRSingleOperator<T>());
	}
	
}
