package com.nr.instrumentation.sqlclient;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.ExternalParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import java.util.logging.Level;

public class NRResultWrapper<T> implements Handler<AsyncResult<T>> {
  private static boolean isTransformed = false;
  
  private Handler<AsyncResult<T>> delegate = null;
  
  private Token token = null;
  
  private Segment segment = null;
  
  private DatastoreParameters params = null;
  
  public NRResultWrapper(Handler<AsyncResult<T>> h, Token t, Segment s, DatastoreParameters p) {
    this.delegate = h;
    this.token = t;
    this.segment = s;
    this.params = p;
    if (!isTransformed) {
      isTransformed = true;
      AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());
    } 
  }
  
  @Trace(async = true)
  public void handle(AsyncResult<T> event) {
    if (this.token != null) {
      this.token.linkAndExpire();
      this.token = null;
    } 
    if (this.segment != null) {
      if (this.params != null)
        this.segment.reportAsExternal((ExternalParameters)this.params); 
      this.segment.end();
      NewRelic.getAgent().getLogger().log(Level.FINE, "ended segment for Vertx-SQLClient: {0}", this.segment);
    } else if (this.params != null) {
      NewRelic.getAgent().getTracedMethod().reportAsExternal((ExternalParameters)this.params);
    } 
    if (this.delegate != null)
      this.delegate.handle(event); 
  }
}
