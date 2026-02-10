package com.newrelic.instrumentation.labs.vertx.rxjava;

import io.reactivex.functions.Action;

public class NRTerminateAction implements Action {

	private NRHolder holder = null;
	
	public NRTerminateAction(NRHolder h) {
		holder = h;
	}
	
	@Override
	public void run() throws Exception {
		if(holder != null) {
			holder.ignoreSegment();
		}
	}

}
