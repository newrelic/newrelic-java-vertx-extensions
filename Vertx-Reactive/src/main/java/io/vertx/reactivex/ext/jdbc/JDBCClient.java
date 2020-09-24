package io.vertx.reactivex.ext.jdbc;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.reactive.NRHandlerWrapper;

import io.reactivex.Maybe;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;

@Weave
public abstract class JDBCClient {

	@Trace
	public io.vertx.reactivex.ext.sql.SQLOperations querySingle(String sql, Handler<AsyncResult<JsonArray>> handler) { 
		NRHandlerWrapper<JsonArray> wrapper = new NRHandlerWrapper<JsonArray>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}
	
	@Trace
	public io.vertx.reactivex.ext.sql.SQLOperations querySingleWithParams(String sql, JsonArray arguments, Handler<AsyncResult<JsonArray>> handler) { 
		NRHandlerWrapper<JsonArray> wrapper = new NRHandlerWrapper<JsonArray>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}
	
	@Trace
	public Maybe<JsonArray> rxQuerySingle(String sql) { 
		return Weaver.callOriginal();
	}
	
	@Trace
	public Maybe<JsonArray> rxQuerySingleWithParams(String sql, JsonArray arguments) { 
		return Weaver.callOriginal();
	}
}
