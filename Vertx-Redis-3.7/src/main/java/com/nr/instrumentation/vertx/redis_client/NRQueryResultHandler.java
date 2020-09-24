package com.nr.instrumentation.vertx.redis_client;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Token;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public class NRQueryResultHandler<T> extends NRResultHandler<T> {
	
	private Segment segment = null;
	
	private DatastoreParameters params = null;
	private static boolean isTransformed = false;
	
	public NRQueryResultHandler(Handler<AsyncResult<T>> h, Segment s,DatastoreParameters p,Token t) {
		super(h,t);
		segment = s;
		params = p;
		
		if(!isTransformed) {
			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());
			isTransformed = true;
		}
	}

	public void handle(AsyncResult<T> event) {
		super.handle(event);
		if(segment != null) {
			if(params != null) {
				segment.reportAsExternal(params);
			}
			segment.end();
			segment = null;
		} else if(params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
	}

}
