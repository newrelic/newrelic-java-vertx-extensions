package com.nr.instrumentation.vertx.reactive;

import org.reactivestreams.Subscriber;

import io.reactivex.FlowableOperator;

public class NRFlowableOperator<T> implements FlowableOperator<T, T> {

	@Override
	public Subscriber<? super T> apply(Subscriber<? super T> subscriber) throws Exception {
		
		return new NRFlowableObserver<T>(subscriber);
	}

}
