package io.vertx.ext.jdbc.impl.actions;

import java.sql.Connection;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.jdbcclient.NRResultHandler;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.impl.TaskQueue;

@Weave(type=MatchType.BaseClass)
public abstract class AbstractJDBCAction<T> {

	@Trace(dispatcher=true)
	public void execute(Connection conn, TaskQueue statementsQueue, Handler<AsyncResult<T>> resultHandler) {
		NRResultHandler<T> wrapper = new NRResultHandler<T>(NewRelic.getAgent().getTransaction().getToken(), resultHandler);
		resultHandler = wrapper;
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","JDBCAction",getClass().getSimpleName(),"execute"});
		Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public T execute(Connection conn) {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","JDBCAction",getClass().getSimpleName(),"execute"});
		return Weaver.callOriginal();
	}
}
