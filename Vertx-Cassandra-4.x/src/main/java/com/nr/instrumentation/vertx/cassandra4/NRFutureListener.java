package com.nr.instrumentation.vertx.cassandra4;

import com.newrelic.api.agent.Segment;

import io.vertx.core.impl.future.Listener;

public class NRFutureListener<T> implements Listener<T> {
	
	private Segment segment = null;
	
	public NRFutureListener(Segment s) {
		segment = s;
	}

	@Override
	public void onSuccess(T value) {
		if(segment != null) {
			segment.end();
			segment = null;
		}
	}

	@Override
	public void onFailure(Throwable failure) {
		if(segment != null) {
			segment.end();
			segment = null;
		}
	}

}
