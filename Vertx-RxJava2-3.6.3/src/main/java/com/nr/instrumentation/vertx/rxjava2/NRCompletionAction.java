package com.nr.instrumentation.vertx.rxjava2;

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
