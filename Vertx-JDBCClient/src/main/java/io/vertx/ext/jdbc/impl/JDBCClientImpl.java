package io.vertx.ext.jdbc.impl;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.jdbcclient.NRResultHandler;
import com.nr.instrumentation.vertx.jdbcclient.NRSQLConnectionHandler;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.ext.jdbc.impl.actions.AbstractJDBCAction;
import io.vertx.ext.sql.SQLConnection;

@Weave
public abstract class JDBCClientImpl {

	@Trace
	private void getConnection(Context ctx, Handler<AsyncResult<SQLConnection>> handler) {
		NRSQLConnectionHandler wrapper = new NRSQLConnectionHandler(NewRelic.getAgent().getTransaction().getToken(), handler);
		handler = wrapper;
		Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	private <T> void executeDirect(Context ctx, AbstractJDBCAction<T> action, Handler<AsyncResult<T>> handler) {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","JDBCClient","executeDirect",action.getClass().getSimpleName()});
		NRResultHandler<T> wrapper = new NRResultHandler<T>(NewRelic.getAgent().getTransaction().getToken(), handler);
		handler = wrapper;
		Weaver.callOriginal();
	}
}
