package com.nr.instrumentation.vertx.serviceproxy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

import com.newrelic.agent.deps.org.objectweb.asm.ClassReader;
import com.newrelic.agent.instrumentation.classmatchers.ClassMatcher;
import com.newrelic.api.agent.NewRelic;

public class ServiceProxyClassMatcher extends ClassMatcher {
	
	List<String> classesToMatch = new ArrayList<String>();

	
	@Override
	public boolean isMatch(ClassLoader loader, ClassReader cr) {
		String classname = cr.getClassName();
		NewRelic.getAgent().getLogger().log(Level.FINE, "ServiceProxyClassMatcher.isMatch checking {0}",classname);
		return classname.endsWith("VertxProxyHandler") || classname.endsWith("VertxEBProxy");
	}

	@Override
	public boolean isMatch(Class<?> clazz) {
		String classname = clazz.getName();
		NewRelic.getAgent().getLogger().log(Level.FINE, "ServiceProxyClassMatcher.isMatch checking {0}",classname);
		return classname.endsWith("VertxProxyHandler") || classname.endsWith("VertxEBProxy");
	}

	@Override
	public Collection<String> getClassNames() {
		// TODO Auto-generated method stub
		return classesToMatch;
	}

}
