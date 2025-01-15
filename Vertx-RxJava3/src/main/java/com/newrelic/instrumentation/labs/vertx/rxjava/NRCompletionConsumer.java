package com.newrelic.instrumentation.labs.vertx.rxjava;

import io.reactivex.rxjava3.functions.Consumer;

public class NRCompletionConsumer<T> implements Consumer<T> {
	
	private NRHolder holder = null;
	
	public NRCompletionConsumer(NRHolder h) {
		holder = h;
	}

	@Override
	public void accept(T t) throws Exception {
		if(holder != null) {
			holder.endSegment();
		}
	}

}
