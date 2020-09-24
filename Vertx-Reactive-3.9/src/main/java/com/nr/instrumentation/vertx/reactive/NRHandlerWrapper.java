package com.nr.instrumentation.vertx.reactive;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public class NRHandlerWrapper<T> implements Handler<AsyncResult<T>> {
	
	private Token token = null;
	private Handler<AsyncResult<T>> delegate = null;
	private static boolean isTransformed = false;
	
	public NRHandlerWrapper(Handler<AsyncResult<T>> d, Token t) {
		delegate = d;
		token = t;
		if(!isTransformed) {
			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());
			isTransformed = true;
		}
	}

	@Trace(async=true, excludeFromTransactionTrace=true)
	public void handle(AsyncResult<T> event) {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		delegate.handle(event);
	}

}
