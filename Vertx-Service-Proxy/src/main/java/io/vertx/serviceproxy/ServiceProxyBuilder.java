package io.vertx.serviceproxy;

import java.util.logging.Level;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.serviceproxy.GenerateUtil;

@Weave
public abstract class ServiceProxyBuilder {

	@Trace
	public <T> T build(Class<T> clazz) {
		Exception e = new Exception("call to ServiceProxyBuilder");
		T instance = Weaver.callOriginal();
		NewRelic.getAgent().getLogger().log(Level.FINE, e, "call to ServiceProxyBuilder.build({0}) returns {1}",clazz,instance); 
		Class<?> proxyClass = instance.getClass();
		GenerateUtil.createWeaverClasses(clazz, null, proxyClass);
		return instance;
	}
}
