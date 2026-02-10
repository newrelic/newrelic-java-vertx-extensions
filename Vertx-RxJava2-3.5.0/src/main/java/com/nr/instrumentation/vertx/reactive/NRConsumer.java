package com.nr.instrumentation.vertx.reactive;

import java.util.function.Consumer;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public class NRConsumer<T> implements Consumer<Handler<AsyncResult<T>>> {

	private Consumer<Handler<AsyncResult<T>>> delegate = null;
	private Token token = null;
	private static boolean isTransformed = false;

	public NRConsumer(Consumer<Handler<AsyncResult<T>>> d, Token t) {
		delegate = d;
		token = t;
		if(!isTransformed) {
			isTransformed = true;
			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());
		}
	}
	
	@Override
	@Trace(async=true,excludeFromTransactionTrace=true)
	public void accept(Handler<AsyncResult<T>> t) {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		delegate.accept(t);
	}


}
