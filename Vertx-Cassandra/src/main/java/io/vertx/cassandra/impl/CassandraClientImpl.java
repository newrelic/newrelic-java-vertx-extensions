package io.vertx.cassandra.impl;

import java.util.HashMap;

import com.datastax.driver.core.Statement;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.cassandra.CassandraUtils;
import com.nr.instrumentation.vertx.cassandra.NRCompletionWrapper;

import io.vertx.cassandra.CassandraClient;
import io.vertx.cassandra.CassandraRowStream;
import io.vertx.cassandra.ResultSet;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Weave
public abstract class CassandraClientImpl {

	@Trace
	public CassandraClient execute(Statement statement, Handler<AsyncResult<ResultSet>> resultHandler) {
		NRCompletionWrapper<ResultSet> wrapper = new NRCompletionWrapper<ResultSet>(resultHandler, null, NewRelic.getAgent().getTransaction().startSegment("Cassandra-Execute"));
		resultHandler = wrapper;
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		CassandraUtils.addStatement(attributes, statement);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace
	public CassandraClient queryStream(Statement statement, Handler<AsyncResult<CassandraRowStream>> rowStreamHandler) {
		NRCompletionWrapper<CassandraRowStream> wrapper = new NRCompletionWrapper<CassandraRowStream>(rowStreamHandler, null, NewRelic.getAgent().getTransaction().startSegment("Cassandra-Execute"));
		rowStreamHandler = wrapper;
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		CassandraUtils.addStatement(attributes, statement);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
				
		return Weaver.callOriginal();
	}
}
