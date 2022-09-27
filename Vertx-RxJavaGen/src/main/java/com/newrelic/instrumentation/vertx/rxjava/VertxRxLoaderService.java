package com.newrelic.instrumentation.vertx.rxjava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import com.newrelic.agent.instrumentation.ClassTransformerService;
import com.newrelic.agent.instrumentation.context.InstrumentationContextManager;
import com.newrelic.agent.service.AbstractService;
import com.newrelic.agent.service.ServiceFactory;
import com.newrelic.api.agent.NewRelic;

public class VertxRxLoaderService extends AbstractService {

	private ExecutorService executor = null;

	public VertxRxLoaderService() {
		super("VertxRxLoaderService");
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	protected void doStart() throws Exception {
		ClassTransformerService classTransformerService = ServiceFactory.getClassTransformerService();
		if(classTransformerService != null) {
			InstrumentationContextManager contextMgr = classTransformerService.getContextManager();
			if(contextMgr != null) {
				VertxRxJavaClassTransformer classTransformer = new VertxRxJavaClassTransformer();
				NewRelic.getAgent().getLogger().log(Level.FINE, "Constructed VertxRxJavaClassTransformer: {0}, matcher: {1}", classTransformer, classTransformer.getMatcher());
				contextMgr.addContextClassTransformer(classTransformer.getMatcher(), classTransformer);
			} else {
				startExecutor();
			}
		} else {
			startExecutor();
		}
	}

	@Override
	protected void doStop() throws Exception {
		// TODO Auto-generated method stub

	}

	private void startExecutor() {
		executor = Executors.newSingleThreadExecutor();
		RunCheck runCheck = new RunCheck();
		executor.submit(runCheck);
		NewRelic.getAgent().getLogger().log(Level.FINE, "Submit Rx RunCheck to executor");		

	}

	private void shutdownExecutor() {
		executor.shutdown();
		NewRelic.getAgent().getLogger().log(Level.FINE, "VertxRxLoaderService executor has shut down");
	}



	private class RunCheck implements Runnable {

		@Override
		public void run() {
			boolean done = false;
			while(!done) {
				ClassTransformerService classTransformerService = ServiceFactory.getClassTransformerService();
				if(classTransformerService != null) {
					InstrumentationContextManager contextMgr = classTransformerService.getContextManager();
					if(contextMgr != null) {
						VertxRxJavaClassTransformer classTransformer = new VertxRxJavaClassTransformer();
						NewRelic.getAgent().getLogger().log(Level.FINE, "Constructed VertxRxJavaClassTransformer: {0}, matcher: {1}", classTransformer, classTransformer.getMatcher());
						contextMgr.addContextClassTransformer(classTransformer.getMatcher(), classTransformer);
						done = true;
					} else {
						try {
							Thread.sleep(5000L);
						} catch (InterruptedException e) {
						}
					}
				} else {
					try {
						Thread.sleep(5000L);
					} catch (InterruptedException e) {
					}
				}
			}
			shutdownExecutor();
		}


	}
}
