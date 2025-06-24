package com.newrelic.instrumentation.labs.sqlclient;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.weaver.Weaver;

import io.vertx.core.AsyncResult;
import io.vertx.core.Completable;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.impl.future.FutureBase;

public  class FutureWrapper<T> extends FutureBase<T> {

	private FutureBase<T> original;
	private Segment segment = null;

	public FutureWrapper(Future<T> original, Segment segment) {
		System.out.println("FutureWrapper constructor called with original: " + original + ", segment: " + segment);
		this.original = (FutureBase<T>) original;
		this.segment = segment;
	}

	@Override
	public boolean isComplete() {
		return original.isComplete();
	}

	@Override
	public Future<T> onComplete(Handler<AsyncResult<T>> handler) {
		System.out.println("FutureWrapper.onComplete called with handler: " + handler);
		try {
			Token token = NewRelic.getAgent().getTransaction().getToken();
			handler = new HandlerWrapper<>(handler, token, segment);

		} catch (Throwable t) {
			AgentBridge.instrumentation.noticeInstrumentationError(t, Weaver.getImplementationTitle());
		}
		return original.onComplete(handler);
	}

	@Override
	public Future<T> onComplete(Completable<? super T> handler) {
		System.out.println("FutureWrapper.onComplete called with Completable handler: " + handler);
		/*
		 * try { Token token = NewRelic.getAgent().getTransaction().getToken(); handler
		 * = new CompletableWrapper<>(handler, token, segment); } catch (Throwable t) {
		 */// AgentBridge.instrumentation.noticeInstrumentationError(t,
			// Weaver.getImplementationTitle());
			// }
		return original.onComplete(handler);
	}

	@Override
	public Future<T> onComplete(Handler<? super T> successHandler, Handler<? super Throwable> failureHandler) {
		System.out.println("FutureWrapper.onComplete called with successHandler: " + successHandler
				+ ", failureHandler: " + failureHandler);
		/*
		 * try { Token token = NewRelic.getAgent().getTransaction().getToken();
		 * successHandler = new HandlerWrapper<>(successHandler, token, segment);
		 * failureHandler = new HandlerWrapper<>(failureHandler, token, segment); }
		 * catch (Throwable t) {
		 * AgentBridge.instrumentation.noticeInstrumentationError(t,
		 * Weaver.getImplementationTitle()); }
		 */
		return original.onComplete(successHandler, failureHandler);
	}

	@Override
	public T result() {
		System.out.println("FutureWrapper.result called");
		return original.result();
	}

	@Override
	public Throwable cause() {
		return original.cause();
	}

	@Override
	public boolean succeeded() {
		System.out.println("FutureWrapper.succeeded called");
		return original.succeeded();
	}

	@Override
	public boolean failed() {
		System.out.println("FutureWrapper.failed called");
		return original.failed();
	}

	@Override
	public <U> Future<U> compose(Function<? super T, Future<U>> successMapper,
			Function<Throwable, Future<U>> failureMapper) {
		System.out.println("FutureWrapper.compose called with successMapper: " + successMapper + ", failureMapper: "
				+ failureMapper);
		return original.compose(successMapper, failureMapper);
	}

	@Override
	public <U> Future<U> transform(Function<AsyncResult<T>, Future<U>> mapper) {
		return original.transform(mapper);
	}

	@Override
	public <U> Future<T> eventually(Supplier<Future<U>> mapper) {
		return original.eventually(mapper);
	}

	@Override
	public <U> Future<U> map(Function<? super T, U> mapper) {
		return original.map(mapper);
	}

	@Override
	public <V> Future<V> map(V value) {
		return original.map(value);
	}

	@Override
	public Future<T> otherwise(Function<Throwable, T> mapper) {
		return original.otherwise(mapper);
	}

	@Override
	public Future<T> otherwise(T value) {
		return original.otherwise(value);
	}

	@Override
	public Future<T> expecting(io.vertx.core.Expectation<? super T> expectation) {
		return original.expecting(expectation);
	}

	@Override
	public Future<T> timeout(long delay, TimeUnit unit) {
		return original.timeout(delay, unit);
	}

	@Override
	public void addListener(Completable<? super T> listener) {
		 original.addListener(listener);
	}

	@Override
	public void removeListener(Completable<? super T> listener) {
		original.removeListener(listener);
		
	}
}