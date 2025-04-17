package io.vertx.redis.client.impl;

import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;

@Weave(originalName = "io.vertx.redis.client.impl.CommandImpl", type = MatchType.ExactClass)
public abstract class CommandImpl_Instrumentation {

	@NewField
	public String redisCommandType9 = null;

	public CommandImpl_Instrumentation(String command, int arity, int firstKey, int lastKey, int interval,
			boolean write, boolean readOnly, boolean movable, boolean pubsub) {
		redisCommandType9 = command;
	}
}
