package io.vertx.reactivex.sqlclient;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.reactive.NRHandlerWrapper;
import com.nr.instrumentation.vertx.reactive.Utils;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Weave(type=MatchType.BaseClass)
public class SqlConnection {

	@Trace(dispatcher=true)
	public SqlConnection prepare(String sql, Handler<AsyncResult<PreparedStatement>> handler) { 
//		if(!(handler instanceof NRHandlerWrapper)) {
//			NRHandlerWrapper<PreparedStatement> wrapper = Utils.getWrapper(handler, sql, this);
//			handler = wrapper;
//		}
		return Weaver.callOriginal();
	}
}
