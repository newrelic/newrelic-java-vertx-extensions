package io.vertx.reactivex.ext.sql;

import java.util.List;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.reactive.NRHandlerWrapper;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.UpdateResult;

@Weave(type=MatchType.BaseClass)
public abstract class SQLConnection {

	@Trace
	public SQLOperations querySingle(String sql, Handler<AsyncResult<JsonArray>> handler) {
		NRHandlerWrapper<JsonArray> wrapper = new NRHandlerWrapper<JsonArray>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Maybe<JsonArray> rxQuerySingle(String sql) { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLOperations querySingleWithParams(String sql, JsonArray arguments, Handler<AsyncResult<JsonArray>> handler) { 
		NRHandlerWrapper<JsonArray> wrapper = new NRHandlerWrapper<JsonArray>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Maybe<JsonArray> rxQuerySingleWithParams(String sql, JsonArray arguments) { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLConnection execute(String sql, Handler<AsyncResult<Void>> resultHandler) { 
		NRHandlerWrapper<Void> wrapper = new NRHandlerWrapper<Void>(resultHandler, NewRelic.getAgent().getTransaction().getToken());
		resultHandler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Completable rxExecute(String sql) { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLConnection query(String sql, Handler<AsyncResult<ResultSet>> resultHandler) { 
		NRHandlerWrapper<ResultSet> wrapper = new NRHandlerWrapper<ResultSet>(resultHandler, NewRelic.getAgent().getTransaction().getToken());
		resultHandler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Single<ResultSet> rxQuery(String sql) { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLConnection queryStream(String sql, Handler<AsyncResult<SQLRowStream>> handler) { 
		NRHandlerWrapper<SQLRowStream> wrapper = new NRHandlerWrapper<SQLRowStream>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Single<SQLRowStream> rxQueryStream(String sql) { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLConnection queryWithParams(String sql, JsonArray params, Handler<AsyncResult<ResultSet>> resultHandler) { 
		NRHandlerWrapper<ResultSet> wrapper = new NRHandlerWrapper<ResultSet>(resultHandler, NewRelic.getAgent().getTransaction().getToken());
		resultHandler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Single<ResultSet> rxQueryWithParams(String sql, JsonArray params) { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLConnection queryStreamWithParams(String sql, JsonArray params, Handler<AsyncResult<SQLRowStream>> handler) { 
		NRHandlerWrapper<SQLRowStream> wrapper = new NRHandlerWrapper<SQLRowStream>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Single<SQLRowStream> rxQueryStreamWithParams(String sql, JsonArray params) { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLConnection update(String sql, Handler<AsyncResult<UpdateResult>> resultHandler) { 
		NRHandlerWrapper<UpdateResult> wrapper = new NRHandlerWrapper<UpdateResult>(resultHandler, NewRelic.getAgent().getTransaction().getToken());
		resultHandler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Single<UpdateResult> rxUpdate(String sql) { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLConnection updateWithParams(String sql, JsonArray params, Handler<AsyncResult<UpdateResult>> resultHandler) { 
		NRHandlerWrapper<UpdateResult> wrapper = new NRHandlerWrapper<UpdateResult>(resultHandler, NewRelic.getAgent().getTransaction().getToken());
		resultHandler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Single<UpdateResult> rxUpdateWithParams(String sql, JsonArray params) { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLConnection call(String sql, Handler<AsyncResult<ResultSet>> resultHandler) { 
		NRHandlerWrapper<ResultSet> wrapper = new NRHandlerWrapper<ResultSet>(resultHandler, NewRelic.getAgent().getTransaction().getToken());
		resultHandler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Single<ResultSet> rxCall(String sql) { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLConnection callWithParams(String sql, JsonArray params, JsonArray outputs, Handler<AsyncResult<ResultSet>> resultHandler) { 
		NRHandlerWrapper<ResultSet> wrapper = new NRHandlerWrapper<ResultSet>(resultHandler, NewRelic.getAgent().getTransaction().getToken());
		resultHandler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Single<ResultSet> rxCallWithParams(String sql, JsonArray params, JsonArray outputs) { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLConnection commit(Handler<AsyncResult<Void>> handler) { 
		NRHandlerWrapper<Void> wrapper = new NRHandlerWrapper<Void>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Completable rxCommit() { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLConnection rollback(Handler<AsyncResult<Void>> handler) { 
		NRHandlerWrapper<Void> wrapper = new NRHandlerWrapper<Void>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Completable rxRollback() { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLConnection batch(List<String> sqlStatements, Handler<AsyncResult<List<Integer>>> handler) { 
		NRHandlerWrapper<List<Integer>> wrapper = new NRHandlerWrapper<List<Integer>>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Single<List<Integer>> rxBatch(List<String> sqlStatements) { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLConnection batchWithParams(String sqlStatement, List<JsonArray> args, Handler<AsyncResult<List<Integer>>> handler) { 
		NRHandlerWrapper<List<Integer>> wrapper = new NRHandlerWrapper<List<Integer>>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Single<List<Integer>> rxBatchWithParams(String sqlStatement, List<JsonArray> args) { 
		return Weaver.callOriginal();
	}

	@Trace
	public SQLConnection batchCallableWithParams(String sqlStatement, List<JsonArray> inArgs, List<JsonArray> outArgs, Handler<AsyncResult<List<Integer>>> handler) { 
		NRHandlerWrapper<List<Integer>> wrapper = new NRHandlerWrapper<List<Integer>>(handler, NewRelic.getAgent().getTransaction().getToken());
		handler = wrapper;
		return Weaver.callOriginal();
	}

	@Trace
	public Single<List<Integer>> rxBatchCallableWithParams(String sqlStatement, List<JsonArray> inArgs, List<JsonArray> outArgs) { 
		return Weaver.callOriginal();
	}

}
