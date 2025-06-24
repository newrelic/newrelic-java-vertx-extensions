package io.vertx.cassandra.impl;

import java.util.logging.Level;
import java.util.stream.Collector;

import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.Statement;
import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.vertx.cassandra5.NRCompletableListener;

import io.vertx.cassandra.CassandraRowStream;
import io.vertx.cassandra.ResultSet;
import io.vertx.core.Future;
import io.vertx.core.impl.future.FutureImpl;

@SuppressWarnings("rawtypes")
@Weave(originalName = "io.vertx.cassandra.impl.CassandraClientImpl")
public abstract class CassandraClientImpl_Instrumentation {

	@Trace
	public Future<ResultSet> execute(Statement statement) {
		AgentBridge.getAgent().getLogger().log(Level.FINEST,
				"CassandraClientImpl_Instrumentation.execute called with statement: {0}", statement);
		Future<ResultSet> f = Weaver.callOriginal();
		if (f instanceof FutureImpl) {
			NRCompletableListener<ResultSet> listener = new NRCompletableListener<ResultSet>(
					NewRelic.getAgent().getTransaction().startSegment("Vertx-Cassandra"));
			((FutureImpl<ResultSet>) f).addListener(listener);
		}
		return f;
	}

	@Trace
	public <R> Future<R> execute(Statement statement, Collector<Row, ?, R> collector) {
		Future<R> f = Weaver.callOriginal();
		if (f instanceof FutureImpl) {
			NRCompletableListener<R> listener = new NRCompletableListener<R>(
					NewRelic.getAgent().getTransaction().startSegment("Vertx-Cassandra"));
			((FutureImpl<R>) f).addListener(listener);
		}
		return f;
	}

	@Trace
	public Future<CassandraRowStream> queryStream(Statement statement) {
		Future<CassandraRowStream> f = Weaver.callOriginal();
		if (f instanceof FutureImpl) {
			NRCompletableListener<CassandraRowStream> listener = new NRCompletableListener<CassandraRowStream>(
					NewRelic.getAgent().getTransaction().startSegment("Vertx-Cassandra"));
			((FutureImpl<CassandraRowStream>) f).addListener(listener);
		}

		return f;
	}
}
