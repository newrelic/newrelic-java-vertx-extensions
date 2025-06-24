package com.nr.instrumentation.vertx.redis_client9;

import java.util.logging.Level;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.redis.client.Response;

public class NRQueryResultHandler<T> extends NRResultHandler<T> {
	
	private Segment segment = null;
	
	private DatastoreParameters params = null;
	private static boolean isTransformed = false;
	
	public NRQueryResultHandler(Future<Response>  h, Segment s,DatastoreParameters p,Token t) {
		super(h,t);
		segment = s;
		params = p;
		
		if(!isTransformed) {
			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());
			isTransformed = true;
		}
	}

	public Future<Response> onComplete(Handler<AsyncResult<Response>> handler) {
		super.onComplete(handler);
		if(segment != null) {
			if(params != null) {
				segment.reportAsExternal(params);
				AgentBridge.getAgent().getLogger().log(Level.FINEST,
						"LABS: Reporting segment as external for command with params: 1 " + params.toString());
			}
			segment.end();
			segment = null;
		} else if(params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
			AgentBridge.getAgent().getLogger().log(Level.FINEST,
					"LABS: Reporting method as external for command with params: 2" + params.toString());
		}
		return delegate;
	}
	
}
