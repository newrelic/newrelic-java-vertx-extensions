package com.nr.instrumentation.vertx.reactive;

import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOperator;

public class NRCompletableOperator implements CompletableOperator {

	@Override
	public CompletableObserver apply(CompletableObserver observer) throws Exception {
		return new NRCompletableObserver(observer);
	}

}
