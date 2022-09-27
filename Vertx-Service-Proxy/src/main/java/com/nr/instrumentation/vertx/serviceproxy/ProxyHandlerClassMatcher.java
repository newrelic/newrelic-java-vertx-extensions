package com.nr.instrumentation.vertx.serviceproxy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.newrelic.agent.deps.org.objectweb.asm.ClassReader;
import com.newrelic.agent.instrumentation.classmatchers.ClassMatcher;

public class ProxyHandlerClassMatcher extends ClassMatcher {
	
	List<String> classesToMatch = new ArrayList<String>();

	
	@Override
	public boolean isMatch(ClassLoader loader, ClassReader cr) {
		return cr.getClassName().endsWith("VertxProxyHandler");
	}

	@Override
	public boolean isMatch(Class<?> clazz) {
		return clazz.getName().endsWith("VertxProxyHandler");
	}

	@Override
	public Collection<String> getClassNames() {
		// TODO Auto-generated method stub
		return classesToMatch;
	}

}
