package com.newrelic.instrumentation.labs.sqlclient;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public class HandlerWrapper<T> implements Handler<AsyncResult<T>> {

	private final Handler<AsyncResult<T>> originalHandler;
	private Token token;
	private Segment segment = null;

	public HandlerWrapper(Handler<AsyncResult<T>> originalHandler, Token token, Segment s) {
		this.originalHandler = originalHandler;
		this.token = token;
		this.segment = s;
	}

	@Override
	@Trace(async = true)
	public void handle(AsyncResult<T> event) {
		System.out.println("HandlerWrapper.handle called with event: " + event);
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] { "Custom", "HandlerWrapper", "handle" });
		try {
			if (token != null) {
				token.linkAndExpire();
				token = null;
			}
			if (originalHandler != null) {
				originalHandler.handle(event);
			}
			if (segment != null) {
				segment.end();
			}

		} catch (Throwable t) {
			AgentBridge.instrumentation.noticeInstrumentationError(t, "HandlerWrapper");
		}
	}
}