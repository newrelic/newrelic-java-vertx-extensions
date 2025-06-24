package com.nr.instrumentation.vertx.redis_client9;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;

import io.vertx.core.AsyncResult;
import io.vertx.core.Expectation;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.redis.client.Response;

public class NRResultHandler<T> implements Future<Response>  {
	
	protected Future<Response>  delegate = null;
	protected Token token = null;
	private static boolean isTransformed = false;
	
	public NRResultHandler(Future<Response>  h, Token t) {
		delegate = h;
		token = t;
		if(!isTransformed) {
			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());
			isTransformed = true;
		}
	}


	@Override
	public boolean isComplete() {
		return delegate.isComplete();
	}

	@Override
	@Trace(async=true)
	public Future<Response> onComplete(Handler<AsyncResult<Response>> handler) {
		AgentBridge.getAgent().getLogger().log(Level.FINEST,
				"LABS: NRResultHandler.onComplete called with handler: " + handler.toString() + " for token: " + token);
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		return delegate.onComplete(handler);
	}

	@Override
	public Response result() {
		AgentBridge.getAgent().getLogger().log(Level.FINEST,
				"LABS: NRResultHandler.result called for token: " + token);
		return delegate.result();
	}

	@Override
	public Throwable cause() {
		AgentBridge.getAgent().getLogger().log(Level.FINEST,
				"LABS: NRResultHandler.cause called for token: " + token);
		return delegate.cause();
	}

	@Override
	public boolean succeeded() {
		AgentBridge.getAgent().getLogger().log(Level.FINEST,
				"LABS: NRResultHandler.succeeded called for token: " + token);
		return delegate.succeeded();
	}

	@Override
	public boolean failed() {
		return delegate.failed();
	}

	@Override
	public <U> Future<U> compose(Function<? super Response, Future<U>> successMapper,
			Function<Throwable, Future<U>> failureMapper) {
		AgentBridge.getAgent().getLogger().log(Level.FINEST,
				"LABS: NRResultHandler.compose called with successMapper: " + successMapper.toString() );
		return delegate.compose(successMapper, failureMapper);
	}

	@Override
	public <U> Future<U> transform(Function<AsyncResult<Response>, Future<U>> mapper) {
	
		return delegate.transform(mapper);
	}

	@Override
	public <U> Future<Response> eventually(Supplier<Future<U>> mapper) {
	
		return delegate.eventually(mapper);
	}

	@Override
	public <U> Future<U> map(Function<? super Response, U> mapper) {
;
		return delegate.map(mapper);
	}

	@Override
	public <V> Future<V> map(V value) {
	
		return delegate.map(value);
	}

	@Override
	public Future<Response> otherwise(Function<Throwable, Response> mapper) {

		return delegate.otherwise(mapper);
	}

	@Override
	public Future<Response> otherwise(Response value) {
	
		return delegate.otherwise(value);
	}

	@Override
	public Future<Response> expecting(Expectation<? super Response> expectation) {
		return delegate.expecting(expectation);
	}

	@Override
	public Future<Response> timeout(long delay, TimeUnit unit) {
		return delegate.timeout(delay, unit);
	}

}
