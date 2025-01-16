package io.vertx.lang.rx;

import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.WeaveAllConstructors;
import com.newrelic.api.agent.weaver.WeaveWithAnnotation;
import com.nr.instrumentation.vertx.rx.RxGenWeaverGeneration;
import com.nr.instrumentation.vertx.rx.VertxRx_InstrumentationUtils;

@WeaveWithAnnotation(annotationClasses = { "io.vertx.lang.rx.RxGen" },type=MatchType.Interface)
public abstract class RxGen_instrumentation {
	
	

	@WeaveAllConstructors
	public RxGen_instrumentation() {
		if(!VertxRx_InstrumentationUtils.isInstrumented(getClass())) {
			VertxRx_InstrumentationUtils.instrumentRXClass(getClass());
		}
	}
	
}
