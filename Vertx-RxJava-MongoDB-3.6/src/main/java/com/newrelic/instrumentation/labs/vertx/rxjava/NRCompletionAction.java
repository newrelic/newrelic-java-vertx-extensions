package com.newrelic.instrumentation.labs.vertx.rxjava;

import io.reactivex.functions.Action;

public class NRCompletionAction implements Action {
	
	private NRHolder holder = null;
	
	public NRCompletionAction(NRHolder h) {
		holder = h;
	}

	@Override
	public void run() throws Exception {
		if(holder != null) {
			holder.endSegment();
		}
	}

}
