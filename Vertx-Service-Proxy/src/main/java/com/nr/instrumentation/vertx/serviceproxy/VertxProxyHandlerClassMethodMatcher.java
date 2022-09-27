package com.nr.instrumentation.vertx.serviceproxy;

import com.newrelic.agent.instrumentation.classmatchers.ClassAndMethodMatcher;
import com.newrelic.agent.instrumentation.classmatchers.ClassMatcher;
import com.newrelic.agent.instrumentation.methodmatchers.AllMethodsMatcher;
import com.newrelic.agent.instrumentation.methodmatchers.MethodMatcher;

public class VertxProxyHandlerClassMethodMatcher implements ClassAndMethodMatcher {
	
	private ClassMatcher classMatcher;
	private MethodMatcher methodMatcher;
	
	public VertxProxyHandlerClassMethodMatcher() {
		classMatcher = new ProxyHandlerClassMatcher();
		methodMatcher = new AllMethodsMatcher();
	}

	@Override
	public ClassMatcher getClassMatcher() {
		return classMatcher;
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		return methodMatcher;
	}

}
