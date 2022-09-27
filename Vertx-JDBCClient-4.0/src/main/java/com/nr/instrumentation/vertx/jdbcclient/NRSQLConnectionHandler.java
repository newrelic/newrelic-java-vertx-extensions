package com.nr.instrumentation.vertx.jdbcclient;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.sql.SQLConnection;

public class NRSQLConnectionHandler implements Handler<AsyncResult<SQLConnection>> {
	
	private Token token = null;
	
	private Handler<AsyncResult<SQLConnection>> delegate = null;
	
	private static boolean isTransformed = true;


	public NRSQLConnectionHandler(Token t, Handler<AsyncResult<SQLConnection>> d) {
		super();
		this.token = t;
		this.delegate = d;
		if(!isTransformed) {
			isTransformed = true;
			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());
		}
	}



	@Override
	@Trace(async=true)
	public void handle(AsyncResult<SQLConnection> event) {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		if(delegate != null) {
			delegate.handle(event);
		}
	}

}
