package com.nr.fit.instrumentation.vertx.rabbitmq;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Token;

public class NRResultHandlerWrapper<T> implements Handler<AsyncResult<T>> {
	
	protected Handler<AsyncResult<T>> delegate = null;
	protected Token token = null;
	protected Segment segment = null;
	protected NRErrorHandlerWrapper<T> companion = null;
	
	public NRResultHandlerWrapper(Handler<AsyncResult<T>> del, Token t, Segment s) {
		delegate = del;
		token = t;
		segment = s;
	}


	@Override
	public void handle(AsyncResult<T> event) {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		if(segment != null) {
			segment.end();
			segment = null;
		}
		if(companion != null && companion.token != null) {
			companion.token.expire();
			companion.token = null;
		}
		delegate.handle(event);
	}


}
