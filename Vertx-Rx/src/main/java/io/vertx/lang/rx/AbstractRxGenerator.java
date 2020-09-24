package io.vertx.lang.rx;

import java.util.Map;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.vertx.codegen.ClassModel;

@Weave(type=MatchType.BaseClass)
public abstract class AbstractRxGenerator {

	@Trace
	public String render(ClassModel model, int index, int size, Map<String, Object> session) {
		return Weaver.callOriginal();
	}
}
