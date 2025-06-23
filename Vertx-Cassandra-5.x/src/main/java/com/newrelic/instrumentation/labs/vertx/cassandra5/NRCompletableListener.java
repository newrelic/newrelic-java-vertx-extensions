package com.newrelic.instrumentation.labs.vertx.cassandra5;

import java.util.logging.Level;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Trace;

import io.vertx.core.Completable;

public class NRCompletableListener<T> implements Completable<T> {

	private Segment segment = null;
	private Completable<T> delegate = null;

	public NRCompletableListener(Segment s) {

		AgentBridge.getAgent().getLogger().log(Level.FINEST, "NRCompletableListener initialised with segment: {0}", s);
		segment = s;
	}

	@Override
	@Trace(async = true)
	public void complete(T result, Throwable failure) {

		AgentBridge.getAgent().getLogger().log(Level.FINEST, "Complete called");
		if (segment != null) {
			segment.end();
			segment = null;
		}
		if (delegate != null) {

			delegate.complete(result, failure);
		}
	}

	@Override
	@Trace(async = true)
	public void succeed(T result) {
		AgentBridge.getAgent().getLogger().log(Level.FINEST, "Succeed called");
		if (segment != null) {
			segment.end();
			segment = null;
		}
		if (delegate != null) {
			delegate.succeed(result);
		}
	}

	@Override
	@Trace(async = true)
	public void succeed() {
		AgentBridge.getAgent().getLogger().log(Level.FINEST, "Succeed (no result) called");
		if (segment != null) {
			segment.end();
			segment = null;
		}
		if (delegate != null) {
			delegate.succeed();
		}
	}

	@Override
	@Trace(async = true)
	public void fail(Throwable failure) {
		AgentBridge.getAgent().getLogger().log(Level.FINEST, "Fail (Throwable) called");
		if (segment != null) {
			segment.end();
			segment = null;
		}
		if (delegate != null) {
			delegate.fail(failure);
		}
	}

	@Override
	@Trace(async = true)
	public void fail(String message) {
		AgentBridge.getAgent().getLogger().log(Level.FINEST, "Fail (String message) called");
		if (segment != null) {
			segment.end();
			segment = null;
		}
		if (delegate != null) {
			delegate.fail(message);
		}
	}

}
