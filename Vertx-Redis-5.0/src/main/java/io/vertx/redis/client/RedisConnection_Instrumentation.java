package io.vertx.redis.client;

import java.util.logging.Level;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.redis_client9.NRCompletableListener;

import io.vertx.core.Future;
import io.vertx.redis.client.impl.CommandImpl_Instrumentation;

@Weave(originalName = "io.vertx.redis.client.RedisConnection", type = MatchType.Interface)
public abstract class RedisConnection_Instrumentation {

	// @Trace
	// public RedisConnection send(Request command, Handler<AsyncResult<Response>>
	// onSend) {
	@Trace
	public Future<Response> send(Request command) {
		Command cmd = command.command();
		String cmdName = "Unknown";
		if (cmd instanceof CommandImpl_Instrumentation) {
			CommandImpl_Instrumentation cmdImpl = (CommandImpl_Instrumentation) cmd;
			if (cmdImpl.redisCommandType9 != null) {
				cmdName = cmdImpl.redisCommandType9;
				AgentBridge.getAgent().getLogger().log(Level.FINEST,
						"Labs: Redis command type: " + cmdName + " for command: " + cmdImpl.toString());
			}
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "Vertx-Redis", getClass().getSimpleName(), "send", cmdName });

		Segment segment = null;

		if (!cmdName.equalsIgnoreCase("unknown")) {
			segment = NewRelic.getAgent().getTransaction().startSegment(cmdName);
			DatastoreParameters params = DatastoreParameters.product("Redis").collection("?").operation(cmdName)
					.build();
			if (params != null) {
				segment.reportAsExternal(params);
			} else {
				segment.addCustomAttribute("Model", "Redis");
				segment.addCustomAttribute("Operation", cmdName);
			}
		}

		Future<Response> onSend = Weaver.callOriginal();
		NRCompletableListener<Response> listener = new NRCompletableListener<Response>(segment);
		onSend.onComplete(listener);
		// if (onSend instanceof FutureImpl) {
		// NRCompletableListener<Response> listener = new
		// NRCompletableListener<Response>(segment);
		// ((FutureImpl<Response>) onSend).addListener(listener);
		// }
		return onSend;
	}

}
