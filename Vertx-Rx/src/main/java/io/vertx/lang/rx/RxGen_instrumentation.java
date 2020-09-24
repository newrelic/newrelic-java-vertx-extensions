package io.vertx.lang.rx;

import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.WeaveAllConstructors;
import com.newrelic.api.agent.weaver.WeaveWithAnnotation;
import com.nr.instrumentation.vertx.rx.RxGenWeaverGeneration;

@WeaveWithAnnotation(annotationClasses = { "io.vertx.lang.rx.RxGen" },type=MatchType.Interface)
public abstract class RxGen_instrumentation {
	
	

	@WeaveAllConstructors
	public RxGen_instrumentation() {
		Class<?> thisClass = getClass();
		if(!RxGenWeaverGeneration.isInstrumented(thisClass)) {
			RxGenWeaverGeneration.generateWeaver(thisClass);
		}
	}
	
}
