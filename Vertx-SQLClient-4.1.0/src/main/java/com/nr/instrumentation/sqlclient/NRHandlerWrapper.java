package com.nr.instrumentation.sqlclient;
import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;

import io.vertx.core.Handler;

public class NRHandlerWrapper<E> implements Handler<E> {
	
	private Handler<E> delegate = null;
	
	private static boolean isTransformed = false;
	
	private Token token = null;
	
	private Segment segment = null;
	
	private DatastoreParameters params = null;
	
	public NRHandlerWrapper(Handler<E> d) {
		this(d,null,null);
	}
	
	public NRHandlerWrapper(Handler<E> d, Segment s, DatastoreParameters p) {
		delegate = d;
		token = NewRelic.getAgent().getTransaction().getToken();
		segment = s;
		params = p;
		if(!isTransformed) {
			isTransformed = true;
			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());
		}
	}

	@Override
	@Trace(async=true)
	public void handle(E event) {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		if(segment != null) {
			if(params != null) {
				segment.reportAsExternal(params);
			}
			segment.end();
			segment = null;
		} else if(params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		if(delegate != null) {
			delegate.handle(event);
		}
	}

}
