package com.nr.instrumentation.vertx.serviceproxy;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import com.newrelic.agent.TracerService;
import com.newrelic.agent.instrumentation.ClassTransformerService;
import com.newrelic.agent.instrumentation.context.InstrumentationContextManager;
import com.newrelic.agent.service.AbstractService;
import com.newrelic.agent.service.ServiceFactory;
import com.newrelic.api.agent.NewRelic;

public class VertxServiceProxyService extends AbstractService {


	public VertxServiceProxyService() {
		super(VertxServiceProxyService.class.getSimpleName());
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	protected void doStart() throws Exception {
		boolean b = initialize();
		if(!b) {
			Executors.newSingleThreadScheduledExecutor().schedule(new Checker(), 5, TimeUnit.SECONDS);
		}

	}

	protected boolean initialize() {
		ClassTransformerService transformerService = ServiceFactory.getClassTransformerService();
		if(transformerService != null) {
			NewRelic.getAgent().getLogger().log(Level.FINE, "Found ClassTransformerService {0}",transformerService);
			InstrumentationContextManager ctxMgr = transformerService.getContextManager();
			if (ctxMgr != null) {
				NewRelic.getAgent().getLogger().log(Level.FINE, "Found InstrumentationContextManager {0}",ctxMgr);

				ServiceProxyClassTransformer classTransformer = new ServiceProxyClassTransformer();
				NewRelic.getAgent().getLogger().log(Level.FINE, "Submitting classTransformer {0} and matcher {1}",classTransformer,classTransformer.getMatcher());
				ctxMgr.addContextClassTransformer(classTransformer.getMatcher(), classTransformer);
				NewRelic.getAgent().getLogger().log(Level.FINE, "Intialized VertxServiceProxyService");
				return true;
			}
		}
		NewRelic.getAgent().getLogger().log(Level.FINE, "Cannot initialize VertxServiceProxyService yet");

		return false;
	}
	@Override
	protected void doStop() throws Exception {
		// TODO Auto-generated method stub

	}


	private class Checker implements Runnable {

		public void run() {
			boolean done = false;
			while(!done) {
				done = initialize();
				if(!done) {
					try {
						Thread.sleep(5000L);
					} catch (InterruptedException e) {
					}
				}
			}
		}
	}
}
