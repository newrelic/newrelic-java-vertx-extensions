package com.nr.instrumentation.vertx.rxjava2;

import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOperator;

public class NRCompletableOperator implements CompletableOperator {

	@Override
	public CompletableObserver apply(CompletableObserver observer) throws Exception {
		return new NRCompletableObserver(observer);
	}

}
