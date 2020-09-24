package com.nr.instrumentation.vertx.serviceproxy;

import com.newrelic.agent.Transaction;
import com.newrelic.agent.instrumentation.PointCutConfiguration;
import com.newrelic.agent.instrumentation.TracerFactoryPointCut;
import com.newrelic.agent.instrumentation.classmatchers.ClassMatcher;
import com.newrelic.agent.instrumentation.methodmatchers.MethodMatcher;
import com.newrelic.agent.tracers.ClassMethodSignature;
import com.newrelic.agent.tracers.Tracer;

public class ServicePointcut extends TracerFactoryPointCut {

	public ServicePointcut(PointCutConfiguration config, ClassMatcher classMatcher, MethodMatcher methodMatcher) {
		super(config, classMatcher, methodMatcher);
	}

	@Override
	protected Tracer doGetTracer(Transaction transaction, ClassMethodSignature sig, Object instance, Object[] args) {
		
		return null;
	}

}
