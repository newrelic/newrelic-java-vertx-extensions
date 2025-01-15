package com.newrelic.instrumentation.labs.vertx.rxjava;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class NRSubscribeConsumer implements Consumer<Disposable> {

	private NRHolder holder = null;
	
	public NRSubscribeConsumer(NRHolder h) {
		holder = h;
	}


	@Override
	public void accept(Disposable t) throws Exception {
		if(holder != null) {
			holder.startSegment();
		}
	}

}
