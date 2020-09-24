package io.vertx.reactivex.impl;

import java.util.function.Consumer;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.reactive.NRCompletableOperator;

import io.reactivex.Completable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Weave
public abstract class AsyncResultCompletable {

	@Trace(excludeFromTransactionTrace=true)
	public static Completable toCompletable(Consumer<Handler<AsyncResult<Void>>> subscriptionConsumer) {
		Completable completable = Weaver.callOriginal();
		return completable.lift(new NRCompletableOperator());
	}
	
}
