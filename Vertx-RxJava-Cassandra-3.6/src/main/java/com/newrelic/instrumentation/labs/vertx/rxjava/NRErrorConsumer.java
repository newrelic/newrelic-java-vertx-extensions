package com.newrelic.instrumentation.labs.vertx.rxjava;

import com.newrelic.api.agent.NewRelic;

import io.reactivex.functions.Consumer;

public class NRErrorConsumer implements Consumer<Throwable> {
	
	private NRHolder holder = null;
	
	public NRErrorConsumer(NRHolder h) {
		holder = h;
	}



	@Override
	public void accept(Throwable t) throws Exception {
		NewRelic.noticeError(t);
		if(holder != null) {
			holder.endSegment();
		}
	}

}
