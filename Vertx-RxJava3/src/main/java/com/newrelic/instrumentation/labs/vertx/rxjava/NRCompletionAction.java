package com.newrelic.instrumentation.labs.vertx.rxjava;

import io.reactivex.rxjava3.functions.Action;

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
