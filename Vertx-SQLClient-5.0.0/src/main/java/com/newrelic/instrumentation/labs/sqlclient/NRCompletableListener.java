package com.newrelic.instrumentation.labs.sqlclient;

import java.util.logging.Level;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;

import io.vertx.core.Completable;

public class NRCompletableListener<R> implements Completable<R> {

	private Segment segment = null;
	private static boolean isTransformed = false;
	private Token token = null;
	

	public NRCompletableListener(Segment s, DatastoreParameters p) {
		AgentBridge.getAgent().getLogger().log(Level.FINEST,"NRCompletableListener initialised with segment: {0}", s);

		// Get the token right when the listener is created,
		// while still in the context of the original transaction/segment.
		token = NewRelic.getAgent().getTransaction().getToken();
		segment = s;

	

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
			AgentBridge.getAgent().getLogger().log(Level.FINEST,"Token linked and expired in NRCompletableListener complete");
		}

		if (segment != null) {
			segment.end();
			segment = null;
			AgentBridge.getAgent().getLogger().log(Level.FINEST,"Segment ended in NRCompletableListener complete");
		} 

	}

	@Override
	@Trace(async = true) 
	public void succeed(R result) {
		AgentBridge.getAgent().getLogger().log(Level.FINEST, "succeed called");


		if (token != null) {
			// Link to the original context before doing anything else
			token.linkAndExpire();
			token = null;
			AgentBridge.getAgent().getLogger().log(Level.FINEST, "Token linked and expired in NRCompletableListener succeed(result)");
		}

		if (segment != null) {
			
			segment.end();
			segment = null;
			AgentBridge.getAgent().getLogger().log(Level.FINEST,"Segment ended in NRCompletableListener succeed(result)");
		} 

	}

	@Override
	@Trace(async = true) 
	public void succeed() {
		AgentBridge.getAgent().getLogger().log(Level.FINEST, "succeed called");


		if (token != null) {
			// Link to the original context before doing anything else
			token.linkAndExpire();
			token = null;
			System.out.println("Token linked and expired in NRCompletableListener succeed()");
		}

		if (segment != null) {
			
			segment.end();
			segment = null;
			AgentBridge.getAgent().getLogger().log(Level.FINEST, "Segment ended in NRCompletableListener succeed()");
		} 

	}

	@Override
	@Trace(async = true) 
	public void fail(Throwable failure) {
		AgentBridge.getAgent().getLogger().log(Level.FINEST, "fail called");


		if (token != null) {
			// Link to the original context before doing anything else
			token.linkAndExpire();
			token = null;
			AgentBridge.getAgent().getLogger().log(Level.FINEST, "Token linked and expired in NRCompletableListener fail(throwable)");
		}

		if (segment != null) {
			
			segment.end();
			segment = null;
			AgentBridge.getAgent().getLogger().log(Level.FINEST,"Segment ended in NRCompletableListener fail(throwable)" );
		} 

	}

	@Override
	@Trace(async = true) 
	public void fail(String message) {
		AgentBridge.getAgent().getLogger().log(Level.FINEST, "fail called");
	

		if (token != null) {
			// Link to the original context before doing anything else
			token.linkAndExpire();
			token = null;
			AgentBridge.getAgent().getLogger().log(Level.FINEST, "Token linked and expired in NRCompletableListener fail(message)");
		}

		if (segment != null) {
			
			segment.end();
			segment = null;
			AgentBridge.getAgent().getLogger().log(Level.FINEST, "Segment ended in NRCompletableListener fail(message)");
		} 
	}
	
	
}