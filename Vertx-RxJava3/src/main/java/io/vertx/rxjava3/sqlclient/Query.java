package io.vertx.rxjava3.sqlclient;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRCompletionConsumer;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRErrorConsumer;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRHolder;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRSubscribeConsumer;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRTerminateAction;

import io.reactivex.rxjava3.core.Single;

@Weave(type = MatchType.BaseClass)
public abstract class Query<T> {

	@Trace
	public Single<T> rxExecute() {
		Single<T> singleResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("SQLClient/PreparedQuery", "rxExecute", NewRelic.getAgent().getTransaction());
		return singleResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doOnTerminate(new NRTerminateAction(holder));
	}
}
