package com.nr.instrumentation.vertx.serviceproxy;

import com.newrelic.agent.Transaction;
import com.newrelic.agent.tracers.ClassMethodSignature;
import com.newrelic.agent.tracers.DefaultTracer;
import com.newrelic.agent.tracers.Tracer;
import com.newrelic.agent.tracers.TracerFactory;

public class VertxServiceProxyTracerFactory implements TracerFactory {

	@Override
	public Tracer getTracer(Transaction transaction, ClassMethodSignature sig, Object object, Object[] args) {
		Tracer tracer = new DefaultTracer(transaction, sig, object);
		String classname = sig.getClassName();
		String methodName = sig.getMethodName();
		tracer.setMetricName("Custom","Vert.x","ServiceProxy",classname,methodName);
		return tracer;
	}

}
