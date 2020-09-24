package io.vertx.redis.impl;

import java.util.List;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.redis_client.NRResultHandler;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Weave(type=MatchType.BaseClass)
public abstract class AbstractRedisClient {

	@Trace(dispatcher=true)
	<T> void send(RedisCommand command, List<?> redisArgs, Class<T> returnType,boolean binary,Handler<AsyncResult<T>> resultHandler) {
		String commandName = command.name();
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","Vert.x-Redis","RedisClient",commandName});
		resultHandler = new NRResultHandler<T>(resultHandler, NewRelic.getAgent().getTransaction().getToken());
		Weaver.callOriginal();
	}
}
	
