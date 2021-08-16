package io.vertx.reactivex.sqlclient;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.reactive.Utils;

@Weave(type=MatchType.BaseClass)
public abstract class SqlClient {


	@Trace(dispatcher=true)
	public Query<RowSet<Row>> query(String sql){ 
		Query<RowSet<Row>> query = Weaver.callOriginal();
//		query.query = sql;
//		query.vendor = Utils.getDBType(this);
		return query;
	}

	@Trace(dispatcher=true)
	public PreparedQuery<RowSet<Row>> preparedQuery(String sql) { 
		PreparedQuery<RowSet<Row>> query = Weaver.callOriginal();
//		query.query = sql;
//		query.vendor = Utils.getDBType(this);
		return query;
	}
}
