package io.vertx.redis.client;

import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.redis_client9.NRQueryResultHandler;
import com.nr.instrumentation.vertx.redis_client9.NRResultHandler;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.redis.client.impl.CommandImpl_Instrumentation;

@Weave(originalName = "io.vertx.redis.client.RedisConnection", type = MatchType.Interface)
public abstract class RedisConnection_Instrumentation {

	@Trace
	public RedisConnection send(Request command, Handler<AsyncResult<Response>> onSend) {
		Command cmd = command.command();
		String cmdName = "Unknown";
		if (cmd instanceof CommandImpl_Instrumentation) {
			CommandImpl_Instrumentation cmdImpl = (CommandImpl_Instrumentation) cmd;
			if (cmdImpl.redisCommandType9 != null) {
				cmdName = cmdImpl.redisCommandType9;
			}
		}
		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "Vertx-Redis", getClass().getSimpleName(), "send", cmdName });
		Token token = NewRelic.getAgent().getTransaction().getToken();
		NRResultHandler<Response> wrapper = null;
		if (!cmdName.equalsIgnoreCase("unknown")) {
			Segment segment = NewRelic.getAgent().getTransaction().startSegment(cmdName);
			DatastoreParameters params = DatastoreParameters.product("Redis").collection("?").operation(cmdName)
					.build();
			wrapper = new NRQueryResultHandler<Response>(onSend, segment, params, token);
		} else {
			wrapper = new NRResultHandler<Response>(onSend, token);
		}
		onSend = wrapper;
		return Weaver.callOriginal();
	}
}
