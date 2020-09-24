package io.vertx.redis.client;

import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.redis_client.NRQueryResultHandler;
import com.nr.instrumentation.vertx.redis_client.NRResultHandler;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.redis.client.impl.CommandImpl;

@Weave(type=MatchType.Interface)
public abstract class Redis {
	
	@Trace
	public Redis send(Request command, Handler<AsyncResult<Response>> onSend) {
		Command cmd = command.command();
		String cmdName = "Unknown";
		if(cmd instanceof CommandImpl) {
			CommandImpl cmdImpl = (CommandImpl)cmd;
			if(cmdImpl.redisCommandType != null) {
				cmdName = cmdImpl.redisCommandType;
			}
		}
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","Vertx-Redis",getClass().getSimpleName(),"send",cmdName});
		Token token = NewRelic.getAgent().getTransaction().getToken();
		NRResultHandler<Response> wrapper = null;
		if(!cmdName.equalsIgnoreCase("unknown")) {
			Segment segment = NewRelic.getAgent().getTransaction().startSegment(cmdName);
			DatastoreParameters params = DatastoreParameters.product("Redis").collection("?").operation(cmdName).build();
			wrapper = new NRQueryResultHandler<Response>(onSend, segment, params, token);
		} else {
			wrapper = new NRResultHandler<Response>(onSend, token);
		}
		onSend = wrapper;
		return Weaver.callOriginal();
	}

}
