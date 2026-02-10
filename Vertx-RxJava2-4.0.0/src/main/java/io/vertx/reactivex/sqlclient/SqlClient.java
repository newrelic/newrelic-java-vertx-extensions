package io.vertx.reactivex.sqlclient;

import java.util.List;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import com.nr.instrumentation.vertx.rxjava2.NRHandlerWrapper;
import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Weave(type=MatchType.BaseClass)
public abstract class SqlClient {

	@Trace
	public io.vertx.reactivex.sqlclient.Query<io.vertx.reactivex.sqlclient.RowSet<io.vertx.reactivex.sqlclient.Row>> query(String sql)  {
		return Weaver.callOriginal();
	}
	
}
