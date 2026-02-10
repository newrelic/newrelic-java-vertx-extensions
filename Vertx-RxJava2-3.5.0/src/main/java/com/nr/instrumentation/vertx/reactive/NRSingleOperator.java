package com.nr.instrumentation.vertx.reactive;

import io.reactivex.SingleObserver;
import io.reactivex.SingleOperator;

public class NRSingleOperator<T> implements SingleOperator<T,T> {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public SingleObserver<? super T> apply(SingleObserver<? super T> observer) throws Exception {
		SingleObserver<? super T> upstream = new NRSingleObserver(observer);
		return upstream;
	}

}
