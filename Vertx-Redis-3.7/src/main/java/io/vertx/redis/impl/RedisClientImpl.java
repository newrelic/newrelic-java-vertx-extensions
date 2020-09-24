package io.vertx.redis.impl;

import java.util.List;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.redis_client.NRResultHandler;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.redis.client.Response;
import io.vertx.redis.client.impl.CommandImpl;
import io.vertx.redis.client.Command;

@SuppressWarnings("rawtypes")
@Weave
public abstract class RedisClientImpl {

	@Trace(dispatcher=true)
	private void send(Command command, List arguments, Handler<AsyncResult<Response>> handler) {
		String theRedisCmd = "Unknown";
		if(CommandImpl.class.isInstance(command)) {
			CommandImpl commandImpl = (CommandImpl)command;
			theRedisCmd = commandImpl.redisCommandType;
		}
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","Vertx-Redis","RedisClient",theRedisCmd});
		NRResultHandler<Response> wrapper = new NRResultHandler<Response>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		Weaver.callOriginal();
	}
}
