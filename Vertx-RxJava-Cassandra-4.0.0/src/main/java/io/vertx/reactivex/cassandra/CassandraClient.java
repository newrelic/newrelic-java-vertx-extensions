package io.vertx.reactivex.cassandra;

import java.util.List;

import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.Statement;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRCompletionConsumer;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRErrorConsumer;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRHolder;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRSubscribeConsumer;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRTerminateAction;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;

@SuppressWarnings("rawtypes")
@Weave
public abstract class CassandraClient {

	@Trace
	public Single<io.vertx.reactivex.cassandra.ResultSet> rxExecute(Statement statement) { 
		Single<io.vertx.reactivex.cassandra.ResultSet> resultSingle = Weaver.callOriginal();
		NRHolder holder = new NRHolder("Cassandra", "rxExecute", NewRelic.getAgent().getTransaction());
		Consumer<io.vertx.reactivex.cassandra.ResultSet> completeConsumer = new NRCompletionConsumer<>(holder);
		return resultSingle.doOnSuccess(completeConsumer).doOnError(new NRErrorConsumer(holder)).doOnSubscribe(new NRSubscribeConsumer(holder)).doFinally(new NRTerminateAction(holder));
	}

	@Trace
	public Single<io.vertx.reactivex.cassandra.ResultSet> rxExecute(String query) { 
		Single<io.vertx.reactivex.cassandra.ResultSet> resultSingle = Weaver.callOriginal();
		NRHolder holder = new NRHolder("Cassandra", "rxExecute", NewRelic.getAgent().getTransaction());
		Consumer<io.vertx.reactivex.cassandra.ResultSet> completeConsumer = new NRCompletionConsumer<>(holder);
		return resultSingle.doOnSuccess(completeConsumer).doOnError(new NRErrorConsumer(holder)).doOnSubscribe(new NRSubscribeConsumer(holder)).doFinally(new NRTerminateAction(holder));
	}

	@Trace
	public Single<List<Row>> rxExecuteWithFullFetch(Statement statement) { 
		Single<List<Row>> resultSingle = Weaver.callOriginal();
		NRHolder holder = new NRHolder("Cassandra", "rxExecuteWithFullFetch", NewRelic.getAgent().getTransaction());
		Consumer<List<Row>> completeConsumer = new NRCompletionConsumer<>(holder);
		return resultSingle.doOnSuccess(completeConsumer).doOnError(new NRErrorConsumer(holder)).doOnSubscribe(new NRSubscribeConsumer(holder)).doFinally(new NRTerminateAction(holder));
	}

	@Trace
	public Single<List<Row>> rxExecuteWithFullFetch(String query) { 
		Single<List<Row>> resultSingle = Weaver.callOriginal();
		NRHolder holder = new NRHolder("Cassandra", "rxExecuteWithFullFetch", NewRelic.getAgent().getTransaction());
		Consumer<List<Row>> completeConsumer = new NRCompletionConsumer<>(holder);
		return resultSingle.doOnSuccess(completeConsumer).doOnError(new NRErrorConsumer(holder)).doOnSubscribe(new NRSubscribeConsumer(holder)).doFinally(new NRTerminateAction(holder));
	}

	@Trace
	public Single<io.vertx.reactivex.cassandra.CassandraRowStream> rxQueryStream(Statement statement) { 
		Single<io.vertx.reactivex.cassandra.CassandraRowStream> resultSingle = Weaver.callOriginal();
		NRHolder holder = new NRHolder("Cassandra", "rxQueryStream", NewRelic.getAgent().getTransaction());
		Consumer<io.vertx.reactivex.cassandra.CassandraRowStream> completeConsumer = new NRCompletionConsumer<>(holder);
		return resultSingle.doOnSuccess(completeConsumer).doOnError(new NRErrorConsumer(holder)).doOnSubscribe(new NRSubscribeConsumer(holder)).doFinally(new NRTerminateAction(holder));
	}
	
	@Trace
	public Single<io.vertx.reactivex.cassandra.CassandraRowStream> rxQueryStream(String query) { 
		Single<io.vertx.reactivex.cassandra.CassandraRowStream> resultSingle = Weaver.callOriginal();
		NRHolder holder = new NRHolder("Cassandra", "rxQueryStream", NewRelic.getAgent().getTransaction());
		Consumer<io.vertx.reactivex.cassandra.CassandraRowStream> completeConsumer = new NRCompletionConsumer<>(holder);
		return resultSingle.doOnSuccess(completeConsumer).doOnError(new NRErrorConsumer(holder)).doOnSubscribe(new NRSubscribeConsumer(holder)).doFinally(new NRTerminateAction(holder));
	}
	
}
