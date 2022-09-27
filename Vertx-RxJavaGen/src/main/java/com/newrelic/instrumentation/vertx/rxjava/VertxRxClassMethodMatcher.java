package com.newrelic.instrumentation.vertx.rxjava;

import com.newrelic.agent.instrumentation.classmatchers.ClassAndMethodMatcher;
import com.newrelic.agent.instrumentation.classmatchers.ClassMatcher;
import com.newrelic.agent.instrumentation.methodmatchers.MethodMatcher;

public class VertxRxClassMethodMatcher implements ClassAndMethodMatcher {
	
	private ClassMatcher classMatcher = new RxGenAnnotationMatcher();
	private MethodMatcher methodMatcher = new RxMethodMatcher();

	@Override
	public ClassMatcher getClassMatcher() {
		return classMatcher;
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		return methodMatcher;
	}

}
