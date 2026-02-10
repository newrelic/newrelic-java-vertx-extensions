package com.nr.instrumentation.vertx.reactive;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOperator;

public class NRMaybeOperator<T> implements MaybeOperator<T, T> {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public MaybeObserver<? super T> apply(MaybeObserver<? super T> observer) throws Exception {
		return new NRMaybeObserver(observer);
	}

}
