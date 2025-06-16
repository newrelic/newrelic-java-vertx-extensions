package io.vertx.sqlclient.impl;

import java.util.logging.Level;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.sqlclient.SQLUtils;

import io.vertx.core.Completable;
import io.vertx.sqlclient.internal.command.CommandBase;

@Weave(type = MatchType.BaseClass)
public abstract class SocketConnectionBase {

	@NewField
	public static final ThreadLocal<String> dbTypeContext = new ThreadLocal<>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Trace
	public <R> void schedule(CommandBase<R> cmd, Completable<R> handler) {

		AgentBridge.getAgent().getLogger().log(Level.FINEST, "SocketConnectionBase.schedule called with cmd: {0}", cmd);

		String dbType = SQLUtils.getDBType(getClass());

		if (dbType != null) {
			// sqlContext.set(sql);
			dbTypeContext.set(dbType);

		}
		Weaver.callOriginal();
	}
}