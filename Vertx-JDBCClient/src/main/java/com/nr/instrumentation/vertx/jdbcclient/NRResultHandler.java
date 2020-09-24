package com.nr.instrumentation.vertx.jdbcclient;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public class NRResultHandler<T> implements Handler<AsyncResult<T>> {
	
	private Token token = null;
	
	private Handler<AsyncResult<T>> delegate = null;
	
	private static boolean isTransformed = true;


	public NRResultHandler(Token t, Handler<AsyncResult<T>> d) {
		super();
		this.token = t;
		this.delegate = d;
		if(!isTransformed) {
			isTransformed = true;
			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());
		}
	}



	@Override
	@Trace(async=true)
	public void handle(AsyncResult<T> event) {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		if(delegate != null) {
			delegate.handle(event);
		}
	}

}
