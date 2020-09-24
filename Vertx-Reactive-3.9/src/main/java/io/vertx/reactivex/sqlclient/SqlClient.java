package io.vertx.reactivex.sqlclient;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type=MatchType.BaseClass)
public abstract class SqlClient {

	
	@Trace
	public io.vertx.reactivex.sqlclient.Query<io.vertx.reactivex.sqlclient.RowSet<io.vertx.reactivex.sqlclient.Row>> query(String sql){ 
		return Weaver.callOriginal();
	}
	
}
