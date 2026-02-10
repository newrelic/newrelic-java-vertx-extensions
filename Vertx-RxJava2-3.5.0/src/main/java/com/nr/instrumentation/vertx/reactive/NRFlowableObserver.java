package com.nr.instrumentation.vertx.reactive;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;

import io.reactivex.FlowableSubscriber;

public class NRFlowableObserver<T> implements FlowableSubscriber<T>, Subscription {

	private Subscriber<? super T> downstream;
	
	Subscription upstream;
	private Token token = null;
	private static boolean isTransformed = false;
	
	public NRFlowableObserver(Subscriber<? super T> s) {
		downstream = s;
		if(!isTransformed) {
			isTransformed = true;
			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());
		}
	}
	
	
	@Override
	@Trace(async=true)
	public void onNext(T t) {
		if(token != null) {
			token.link();
		}
		downstream.onNext(t);
	}

	@Override
	@Trace(async=true)
	public void onError(Throwable t) {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		downstream.onError(t);
	}

	@Override
	@Trace(async=true)
	public void onComplete() {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		downstream.onComplete();
	}

	@Override
	public void request(long n) {
		upstream.request(n);
	}

	@Override
	public void cancel() {
		upstream.cancel();
	}

	@Override
	public void onSubscribe(Subscription s) {
		if(upstream != null) {
			s.cancel();
		} else {
			upstream = s;
			downstream.onSubscribe(this);
		}
		if(token == null) {
			token = NewRelic.getAgent().getTransaction().getToken();
		}
	}

}
