package io.vertx.sqlclient;

import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.sqlclient.NRResultWrapper;
import com.nr.instrumentation.sqlclient.Utils;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.sqlclient.impl.PreparedStatement;

@Weave(type = MatchType.Interface)
public abstract class SqlConnection {
  public SqlConnection prepare(String sql, Handler<AsyncResult<PreparedStatement>> handler) {
    if (!(handler instanceof NRResultWrapper)) {
      NRResultWrapper<PreparedStatement> wrapper = Utils.getWrapper(handler, sql, this);
      handler = wrapper;
    } 
    return (SqlConnection)Weaver.callOriginal();
  }
}
