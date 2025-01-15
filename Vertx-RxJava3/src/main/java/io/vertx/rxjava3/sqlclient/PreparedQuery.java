package io.vertx.rxjava3.sqlclient;

import java.util.List;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRCompletionConsumer;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRErrorConsumer;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRHolder;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRSubscribeConsumer;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRTerminateAction;

import io.reactivex.rxjava3.core.Single;

@Weave
public abstract class PreparedQuery<T> {

	@Trace
	public Single<T> rxExecute() {
		Single<T> singleResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("SQLClient/PreparedQuery", "rxExecute", NewRelic.getAgent().getTransaction());
		return singleResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doOnTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Single<T> rxExecute(Tuple tuple) {
		Single<T> singleResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("SQLClient/PreparedQuery", "rxExecute", NewRelic.getAgent().getTransaction());
		return singleResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doOnTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Single<T> rxExecuteBatch(List<Tuple> batch) {
		Single<T> singleResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("SQLClient/PreparedQuery", "rxExecuteBatch", NewRelic.getAgent().getTransaction());
		return singleResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doOnTerminate(new NRTerminateAction(holder));
	}
}
