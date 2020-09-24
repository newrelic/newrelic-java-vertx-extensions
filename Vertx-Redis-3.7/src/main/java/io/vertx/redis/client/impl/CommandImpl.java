package io.vertx.redis.client.impl;

import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;

@Weave
public abstract class CommandImpl {
	
	@NewField
	public String redisCommandType = null;
	

	public CommandImpl(String command, int arity, int firstKey, int lastKey, int interval, boolean readOnly, boolean movable) {
		redisCommandType = command;
	}
}
