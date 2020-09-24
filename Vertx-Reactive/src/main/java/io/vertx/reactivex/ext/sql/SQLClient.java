package io.vertx.reactivex.ext.sql;

import java.sql.ResultSet;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.reactive.NRHandlerWrapper;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.UpdateResult;

@Weave(type=MatchType.BaseClass)
public abstract class SQLClient {

	@Trace
	public SQLClient call(String sql, Handler<AsyncResult<ResultSet>> handler) { 
		NRHandlerWrapper<ResultSet> wrapper = new NRHandlerWrapper<ResultSet>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Single<ResultSet> rxCall(String sql) { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLClient query(String sql, Handler<AsyncResult<ResultSet>> handler) { 
		NRHandlerWrapper<ResultSet> wrapper = new NRHandlerWrapper<ResultSet>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public SQLOperations querySingle(String sql, Handler<AsyncResult<JsonArray>> handler) { 
		NRHandlerWrapper<JsonArray> wrapper = new NRHandlerWrapper<JsonArray>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public SQLOperations querySingleWithParams(String sql, JsonArray arguments, Handler<AsyncResult<JsonArray>> handler) { 
		NRHandlerWrapper<JsonArray> wrapper = new NRHandlerWrapper<JsonArray>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Single<ResultSet> rxQuery(String sql) { 
		return Weaver.callOriginal();
	}

	@Trace
	public Maybe<JsonArray> rxQuerySingle(String sql) { 
		return Weaver.callOriginal();
	}

	public SQLClient queryStream(String sql, Handler<AsyncResult<SQLRowStream>> handler) { 
		NRHandlerWrapper<SQLRowStream> wrapper = new NRHandlerWrapper<SQLRowStream>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Single<io.vertx.reactivex.ext.sql.SQLRowStream> rxQueryStream(String sql) { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLClient queryStreamWithParams(String sql, JsonArray params, Handler<AsyncResult<SQLRowStream>> handler) { 
		NRHandlerWrapper<SQLRowStream> wrapper = new NRHandlerWrapper<SQLRowStream>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Single<SQLRowStream> rxQueryStreamWithParams(String sql, JsonArray params) { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLClient queryWithParams(String sql, JsonArray arguments, Handler<AsyncResult<ResultSet>> handler) { 
		NRHandlerWrapper<ResultSet> wrapper = new NRHandlerWrapper<ResultSet>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Single<ResultSet> rxQueryWithParams(String sql, JsonArray arguments) { 
		return Weaver.callOriginal();
	}

	@Trace
	public Maybe<JsonArray> rxQuerySingleWithParams(String sql, JsonArray arguments) { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLClient update(String sql, Handler<AsyncResult<UpdateResult>> handler) { 
		NRHandlerWrapper<UpdateResult> wrapper = new NRHandlerWrapper<UpdateResult>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Single<UpdateResult> rxUpdate(String sql) { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLClient updateWithParams(String sql, JsonArray params, Handler<AsyncResult<UpdateResult>> handler) { 
		NRHandlerWrapper<UpdateResult> wrapper = new NRHandlerWrapper<UpdateResult>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Single<UpdateResult> rxUpdateWithParams(String sql, JsonArray params) { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLClient callWithParams(String sql, JsonArray params, JsonArray outputs, Handler<AsyncResult<ResultSet>> handler) { 
		NRHandlerWrapper<ResultSet> wrapper = new NRHandlerWrapper<ResultSet>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Single<ResultSet> rxCallWithParams(String sql, JsonArray params, JsonArray outputs) { 
		return Weaver.callOriginal();
	}

}
