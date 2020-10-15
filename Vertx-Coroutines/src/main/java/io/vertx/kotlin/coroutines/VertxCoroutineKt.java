package io.vertx.kotlin.coroutines;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.coroutines.NRFunction1Wrapper;

import io.vertx.core.Handler;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

@Weave
public abstract class VertxCoroutineKt {

	@Trace
	public static <T> Object awaitEvent(Function1<? super Handler<T>, Unit> f, Continuation<? super T> c) {
		NRFunction1Wrapper<? super Handler<T>> wrapper = new NRFunction1Wrapper(f, NewRelic.getAgent().getTransaction().getToken());
		f = wrapper;
		Object value = Weaver.callOriginal();

		return value;
	}
}
