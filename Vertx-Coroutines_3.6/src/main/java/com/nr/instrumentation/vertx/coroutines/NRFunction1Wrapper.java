package com.nr.instrumentation.vertx.coroutines;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class NRFunction1Wrapper<T> implements Function1<T, Unit> {

	private Function1<T, Unit> delegate = null;
	private static boolean isTransformed = false;
	private Token token = null;

	public NRFunction1Wrapper(Function1<T, Unit> d, Token t) {
		delegate = d;
		token = t;
		if (!isTransformed) {
			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());
			isTransformed = true;
		}
	}

	@Override
	@Trace(async = true)
	public Unit invoke(T arg0) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom", "Function1", delegate.getClass().getName(), "run");
		if (token != null) {
			token.linkAndExpire();
			token = null;
		}
		return delegate.invoke(arg0);
	}

}
