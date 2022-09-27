package com.newrelic.instrumentation.vertx.mongodb;

import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public class NRAsyncResultHandler<T> implements Handler<AsyncResult<T>> {
	
	private Handler<AsyncResult<T>> delegate = null;
	private Token token = null;
	private Segment segment = null;
	private DatastoreParameters params = null;
	
	public NRAsyncResultHandler(Handler<AsyncResult<T>> d,Segment s,DatastoreParameters p) {
		this.delegate = d;
		token = NewRelic.getAgent().getTransaction().getToken();
		segment = s;
		params = p;
	}



	@Override
	@Trace(async=true)
	public void handle(AsyncResult<T> event) {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		if(segment != null) {
			if(params != null) {
				segment.reportAsExternal(params);
				params = null;
			}
			segment.end();
		} else if(params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
			params = null;
		}
		delegate.handle(event);
	}

}
