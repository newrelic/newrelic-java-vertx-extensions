package com.nr.instrumentation.sqlclient;

import java.util.logging.Level;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;

import io.vertx.core.Completable;

public class NRCompletableListener<R> implements Completable<R> {

	private Completable<R> delegate = null;
	private Segment segment = null;
	private static boolean isTransformed = false;
	private Token token = null;
	private DatastoreParameters params = null;

	public NRCompletableListener(Segment s, DatastoreParameters p,  Completable<R> d) {
		AgentBridge.getAgent().getLogger().log(Level.FINEST, "NRCompletableListener initialised with segment: {0}", s);

		// Get the token right when the listener is created,
		// while still in the context of the original transaction/segment.
		token = NewRelic.getAgent().getTransaction().getToken();
		segment = s;
		params = p;
		delegate= d;
	

		if (!isTransformed) {
			isTransformed = true;
			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());
		}
	}

	@Override
	@Trace(async = true) // This is crucial for linking the async operation
	public void complete(R result, Throwable failure) {
		AgentBridge.getAgent().getLogger().log(Level.FINEST, "Complete called");

		if (token != null) {
			// Link to the original context before doing anything else
			token.linkAndExpire();
			token = null;
			System.out.println("Token linked and expired in NRCompletableListener");
		}

		if (segment != null) {
			if (params != null) {
				// Report as external AFTER linking the token and within the segment's context
				segment.reportAsExternal(params);
			}
			segment.end();
			segment = null;
			System.out.println("Segment ended in NRCompletableListener");
		} else if (params != null) {

			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
			System.out.println("Params reported in NRCompletableListener");
		}

		if (delegate != null) {
			delegate.complete(result, failure);
			System.out.println("Delegate completed in NRCompletableListener");
		}
	}
}