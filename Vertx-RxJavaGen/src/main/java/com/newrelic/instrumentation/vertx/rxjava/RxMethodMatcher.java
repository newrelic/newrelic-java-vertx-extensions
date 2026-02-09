package com.newrelic.instrumentation.vertx.rxjava;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.newrelic.agent.deps.org.objectweb.asm.commons.Method;
import com.newrelic.agent.instrumentation.methodmatchers.MethodMatcher;

public class RxMethodMatcher implements MethodMatcher {
	

	@Override
	public boolean matches(int access, String name, String desc, Set<String> annotations) {
		return name.startsWith("rx");
	}

	@Override
	public Method[] getExactMethods() {
		return null;
	}

}
