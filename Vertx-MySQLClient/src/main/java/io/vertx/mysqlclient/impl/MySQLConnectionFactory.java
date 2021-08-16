package io.vertx.mysqlclient.impl;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.sqlclient.impl.Connection;

@Weave
public class MySQLConnectionFactory {

	@Trace
	public void connect(Handler<AsyncResult<Connection>> handler) {
		Weaver.callOriginal();
	}
}
