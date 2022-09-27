package com.newrelic.instrumentation.vertx.rxjava;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.newrelic.agent.deps.org.objectweb.asm.commons.Method;
import com.newrelic.agent.instrumentation.methodmatchers.MethodMatcher;

public class RxMethodMatcher implements MethodMatcher {
	
	private static final List<String> ignores = new ArrayList<>();
	
	static {
		ignores.add("equals");
		ignores.add("toString");
		ignores.add("getDelegate");
		ignores.add("hashCode");
		ignores.add("newInstance");
		ignores.add("create");
		ignores.add("createProxy");
		ignores.add("<init>");
	}

	@Override
	public boolean matches(int access, String name, String desc, Set<String> annotations) {
		return !ignores.contains(name);
	}

	@Override
	public Method[] getExactMethods() {
		return null;
	}

}
