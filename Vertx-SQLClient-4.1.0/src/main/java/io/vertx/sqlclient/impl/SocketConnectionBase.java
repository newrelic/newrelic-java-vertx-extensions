package io.vertx.sqlclient.impl;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.sqlclient.SQLUtils;

import io.vertx.core.Future;
import io.vertx.core.impl.ContextInternal;
import io.vertx.sqlclient.impl.command.CommandBase;

@Weave(type = MatchType.BaseClass)
public abstract class SocketConnectionBase {

	@NewField
	static final ThreadLocal<String> dbTypeContext = new ThreadLocal<>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Trace
	public <R> Future<R> schedule(ContextInternal context, CommandBase<R> cmd) {

		String dbType = SQLUtils.getDBType(getClass());

		if (dbType != null) {
			// sqlContext.set(sql);
			dbTypeContext.set(dbType);

		}
		return Weaver.callOriginal();
	}
}