package com.nr.instrumentation.vertx.redis_client9;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public class NRResultHandler<T> implements Handler<AsyncResult<T>> {
	
	protected Handler<AsyncResult<T>> delegate = null;
	protected Token token = null;
	private static boolean isTransformed = false;
	
	public NRResultHandler(Handler<AsyncResult<T>> h, Token t) {
		delegate = h;
		token = t;
		if(!isTransformed) {
			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());
			isTransformed = true;
		}
	}

	@Trace(async=true)
	public void handle(AsyncResult<T> event) {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		delegate.handle(event);
	}

}
