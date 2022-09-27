package com.nr.fit.instrumentation.vertx.rabbitmq;

import io.vertx.core.Handler;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Token;

public class NRErrorHandlerWrapper<T> implements Handler<Throwable> {
	
	protected Handler<Throwable> delegate = null;
	protected Token token = null;
	protected Segment segment = null;
	protected NRResultHandlerWrapper<T> resultcompanion = null;

	public NRErrorHandlerWrapper(Handler<Throwable> del, Token t, Segment s,NRResultHandlerWrapper<T> c) {
		delegate = del;
		token = t;
		segment = s;
		resultcompanion = c;
		if(resultcompanion.companion == null) {
			resultcompanion.companion = this;
		}
	}
	

	@Override
	public void handle(Throwable t) {
		if(t != null) {
			NewRelic.noticeError(t);
		}
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		if(segment != null) {
			segment.end();
			segment = null;
		}
		if(resultcompanion != null && resultcompanion.token != null) {
			resultcompanion.token.expire();
			resultcompanion.token = null;
		}
		if(resultcompanion != null && resultcompanion.segment != null) {
			
			resultcompanion.segment = null;
		}
		delegate.handle(t);
	}
	
	

}
