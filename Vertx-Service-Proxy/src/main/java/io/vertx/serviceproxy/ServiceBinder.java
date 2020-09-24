package io.vertx.serviceproxy;

import java.util.logging.Level;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.serviceproxy.GenerateUtil;

@Weave
public abstract class ServiceBinder {

	@SuppressWarnings("unused")
	private <T> ProxyHandler getProxyHandler(Class<T> clazz, T service) {
		Exception e = new Exception("call to ServiceBinder");
		ProxyHandler proxyHandler = Weaver.callOriginal();
		Package proxyPackage = proxyHandler.getClass().getPackage();
		
		Class<?> proxyClass = proxyHandler.getClass();
		NewRelic.getAgent().getLogger().log(Level.FINE, e, "call to ServiceBinder.getProxyHandler({0},{1}) returns {2}", clazz, service,proxyHandler);
		Class<?> serviceClass = service.getClass();
		GenerateUtil.createWeaverClasses(clazz,serviceClass,proxyClass);
		return proxyHandler;
	}
}
