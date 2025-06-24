package io.vertx.sqlclient.impl;

import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.sqlclient.SQLUtils;

import io.vertx.core.Completable;
import io.vertx.sqlclient.internal.command.CommandBase;

@Weave(type = MatchType.BaseClass, originalName = "io.vertx.sqlclient.impl.SocketConnectionBase")
public abstract class SocketConnectionBase_Instrumentation {

	@NewField
	public static final ThreadLocal<String> dbTypeContext = new ThreadLocal<>();

	public <R> void schedule(CommandBase<R> cmd, Completable<R> handler) {

		String dbType = SQLUtils.getDBType(getClass());

		if (dbType != null) {
			// sqlContext.set(sql);
			dbTypeContext.set(dbType);

		}
		Weaver.callOriginal();
	}
}