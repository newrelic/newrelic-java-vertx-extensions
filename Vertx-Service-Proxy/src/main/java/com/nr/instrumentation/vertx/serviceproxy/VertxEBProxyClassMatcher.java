package com.nr.instrumentation.vertx.serviceproxy;

import java.util.Collection;

import com.newrelic.agent.deps.org.objectweb.asm.ClassReader;
import com.newrelic.agent.instrumentation.classmatchers.ClassMatcher;

public class VertxEBProxyClassMatcher extends ClassMatcher {

	@Override
	public boolean isMatch(ClassLoader loader, ClassReader cr) {
		return cr.getClassName().endsWith("VertxEBProxy");
	}

	@Override
	public boolean isMatch(Class<?> clazz) {
		
		return clazz.getName().endsWith("VertxEBProxy");
	}

	@Override
	public Collection<String> getClassNames() {
		return null;
	}

}
