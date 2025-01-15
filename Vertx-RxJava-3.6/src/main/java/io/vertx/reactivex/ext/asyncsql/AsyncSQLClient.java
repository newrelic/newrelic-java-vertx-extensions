package io.vertx.reactivex.ext.asyncsql;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRCompletionAction;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRErrorConsumer;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRHolder;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRSubscribeConsumer;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRTerminateAction;

import io.reactivex.Maybe;
import io.vertx.core.json.JsonArray;

@Weave(type = MatchType.BaseClass)
public abstract class AsyncSQLClient {

	@Trace
	public Maybe<JsonArray> rxQuerySingle(String sql) { 
		Maybe<JsonArray> resultMaybe = Weaver.callOriginal();
		NRHolder holder = new NRHolder("AsyncSQL", "rxQuerySingle", NewRelic.getAgent().getTransaction());
		return resultMaybe.doOnComplete(new NRCompletionAction(holder)).doOnError(new NRErrorConsumer(holder)).doOnSubscribe(new NRSubscribeConsumer(holder)).doFinally(new NRTerminateAction(holder));
	}
	
	@Trace
	public Maybe<JsonArray> rxQuerySingleWithParams(String sql, JsonArray arguments) { 
		Maybe<JsonArray> resultMaybe = Weaver.callOriginal();
		NRHolder holder = new NRHolder("AsyncSQL", "rxQuerySingleWithParams", NewRelic.getAgent().getTransaction());
		return resultMaybe.doOnComplete(new NRCompletionAction(holder)).doOnError(new NRErrorConsumer(holder)).doOnSubscribe(new NRSubscribeConsumer(holder)).doFinally(new NRTerminateAction(holder));
	}
}
