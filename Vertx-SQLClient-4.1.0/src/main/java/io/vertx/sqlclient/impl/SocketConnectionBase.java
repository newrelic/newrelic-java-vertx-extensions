package io.vertx.sqlclient.impl;

import com.newrelic.agent.database.ParsedDatabaseStatement;
import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.sqlclient.NRHandlerWrapper;
import com.nr.instrumentation.sqlclient.SQLUtils;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.impl.ContextInternal;
import io.vertx.sqlclient.impl.command.CommandBase;

@Weave(type=MatchType.BaseClass)
public abstract class SocketConnectionBase {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Trace
	 public <R> Future<R> schedule(ContextInternal context, CommandBase<R> cmd) {
		Handler<?> handler = cmd.handler;
		
		if(!(handler instanceof NRHandlerWrapper)) {
			Segment segment = NewRelic.getAgent().getTransaction().startSegment("Query");
			String sql = SQLUtils.getSQL(cmd);
			DatastoreParameters params = null;
			if(sql != null) {
				String dbType = SQLUtils.getDBType(getClass());
				ParsedDatabaseStatement parsedStmt = SQLUtils.getParsed(sql);
				
				params = DatastoreParameters.product(dbType).collection(parsedStmt.getModel()).operation(parsedStmt.getOperation()).build();
			}
			NRHandlerWrapper wrapper = new NRHandlerWrapper(handler,segment,params);
			cmd.handler = wrapper;
		}
		return Weaver.callOriginal();
	}
}
