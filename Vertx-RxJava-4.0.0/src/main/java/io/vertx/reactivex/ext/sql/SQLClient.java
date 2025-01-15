package io.vertx.reactivex.ext.sql;

import java.sql.ResultSet;

import com.mongodb.client.result.UpdateResult;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRCompletionAction;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRCompletionConsumer;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRErrorConsumer;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRHolder;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRSubscribeConsumer;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRTerminateAction;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.vertx.core.json.JsonArray;

@Weave
public class SQLClient {

	@Trace
	public Single<ResultSet> rxCall(String sql) { 
		Single<ResultSet> singleResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("SQLClient", "rxCall", NewRelic.getAgent().getTransaction());
		return singleResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<ResultSet>(holder)).doFinally(new NRTerminateAction(holder));
	}

	@Trace
	public Single<ResultSet> rxCallWithParams(String sql, JsonArray params, JsonArray outputs) { 
		Single<ResultSet> singleResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("SQLClient", "rxCallWithParams", NewRelic.getAgent().getTransaction());
		return singleResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<ResultSet>(holder)).doFinally(new NRTerminateAction(holder));
	}

	@Trace
	public Single<ResultSet> rxQuery(String sql) { 
		Single<ResultSet> singleResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("SQLClient", "rxQuery", NewRelic.getAgent().getTransaction());
		return singleResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<ResultSet>(holder)).doFinally(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<JsonArray> rxQuerySingle(String sql) { 
		Maybe<JsonArray> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("SQLClient", "rxQuerySingle", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnComplete(new NRCompletionAction(holder)).doOnError(new NRErrorConsumer(holder)).doOnSubscribe(new NRSubscribeConsumer(holder)).doFinally(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<JsonArray> rxQuerySingleWithParams(String sql, JsonArray arguments) { 
		Maybe<JsonArray> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("SQLClient", "rxQuerySingleWithParams", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnComplete(new NRCompletionAction(holder)).doOnError(new NRErrorConsumer(holder)).doOnSubscribe(new NRSubscribeConsumer(holder)).doFinally(new NRTerminateAction(holder));
	}

	@Trace
	public Single<SQLRowStream> rxQueryStream(String sql) { 
		Single<SQLRowStream> singleResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("SQLClient", "rxQueryStream", NewRelic.getAgent().getTransaction());
		return singleResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<SQLRowStream>(holder)).doFinally(new NRTerminateAction(holder));
	}
	
	@Trace
	public Single<SQLRowStream> rxQueryStreamWithParams(String sql, JsonArray params) { 
		Single<SQLRowStream> singleResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("SQLClient", "rxQueryStreamWithParams", NewRelic.getAgent().getTransaction());
		return singleResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<SQLRowStream>(holder)).doFinally(new NRTerminateAction(holder));
	}
	
	@Trace
	public Single<ResultSet> rxQueryWithParams(String sql, JsonArray arguments) { 
		Single<ResultSet> singleResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("SQLClient", "rxQueryWithParams", NewRelic.getAgent().getTransaction());
		return singleResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<ResultSet>(holder)).doFinally(new NRTerminateAction(holder));
	}
	
	@Trace
	public Single<UpdateResult> rxUpdate(String sql) { 
		Single<UpdateResult> singleResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("SQLClient", "rxUpdate", NewRelic.getAgent().getTransaction());
		return singleResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<UpdateResult>(holder)).doFinally(new NRTerminateAction(holder));
	}
	
	@Trace
	public Single<UpdateResult> rxUpdateWithParams(String sql, JsonArray params) { 
		Single<UpdateResult> singleResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("SQLClient", "rxUpdateWithParams", NewRelic.getAgent().getTransaction());
		return singleResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<UpdateResult>(holder)).doFinally(new NRTerminateAction(holder));
	}
}
