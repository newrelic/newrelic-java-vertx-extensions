package com.nr.instrumentation.vertx.reactive;

import io.reactivex.ObservableOperator;
import io.reactivex.Observer;

public class NRObservableOperator<T> implements ObservableOperator<T, T> {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Observer<? super T> apply(Observer<? super T> observer) throws Exception {
		return new NRObservableObserver(observer);
	}

}
