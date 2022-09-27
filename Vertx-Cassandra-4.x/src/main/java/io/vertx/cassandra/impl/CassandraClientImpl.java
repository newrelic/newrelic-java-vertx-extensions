package io.vertx.cassandra.impl;


import java.util.stream.Collector;

import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.Statement;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.cassandra4.NRFutureListener;

import io.vertx.cassandra.CassandraRowStream;
import io.vertx.cassandra.ResultSet;
import io.vertx.core.Future;
import io.vertx.core.impl.future.FutureInternal;

@SuppressWarnings("rawtypes")
@Weave
public abstract class CassandraClientImpl {

	@Trace
	public Future<ResultSet> execute(Statement statement) {
		Future<ResultSet> f = Weaver.callOriginal();
		if(f instanceof FutureInternal) {
			NRFutureListener<ResultSet> listener = new NRFutureListener<ResultSet>(NewRelic.getAgent().getTransaction().startSegment("Vertx-Cassandra"));
			((FutureInternal<ResultSet>)f).addListener(listener);
		}
		return f;
	}
	
	@Trace
	public <R> Future<R> execute(Statement statement, Collector<Row, ?, R> collector) {
		Future<R> f = Weaver.callOriginal();
		if(f instanceof FutureInternal) {
			NRFutureListener<R> listener = new NRFutureListener<R>(NewRelic.getAgent().getTransaction().startSegment("Vertx-Cassandra"));
			((FutureInternal<R>)f).addListener(listener);
		}
		return f;
	}
	
	@Trace
	public Future<CassandraRowStream> queryStream(Statement statement) {
		Future<CassandraRowStream> f = Weaver.callOriginal();
		if(f instanceof FutureInternal) {
			NRFutureListener<CassandraRowStream> listener = new NRFutureListener<CassandraRowStream>(NewRelic.getAgent().getTransaction().startSegment("Vertx-Cassandra"));
			((FutureInternal<CassandraRowStream>)f).addListener(listener);
		}
	
		return f;
	}
}
