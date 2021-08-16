package io.vertx.reactivex.sqlclient;

import java.util.List;

import com.newrelic.agent.bridge.datastore.DatabaseVendor;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.reactive.NRHandlerWrapper;
import com.nr.instrumentation.vertx.reactive.Utils;

import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Weave
public abstract class PreparedQuery<T>  {

	@NewField
	public String query;
	
	@NewField
	public DatabaseVendor vendor;
	
	@Trace(dispatcher=true)
	public void execute(io.vertx.reactivex.sqlclient.Tuple tuple, Handler<AsyncResult<T>> handler) { 
//		if(!(handler instanceof NRHandlerWrapper)) {
//			NRHandlerWrapper<T> wrapper = Utils.getWrapper(handler, query, vendor);
//			handler = wrapper;
//		}
		Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public void executeBatch(List<io.vertx.reactivex.sqlclient.Tuple> batch, Handler<AsyncResult<T>> handler) { 
//		if(!(handler instanceof NRHandlerWrapper)) {
//			NRHandlerWrapper<T> wrapper = Utils.getWrapper(handler, query, vendor);
//			handler = wrapper;
//		}
		Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public Single<T> rxExecute(io.vertx.reactivex.sqlclient.Tuple tuple) { 
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public Single<T> rxExecuteBatch(List<io.vertx.reactivex.sqlclient.Tuple> batch) { 
		return Weaver.callOriginal();
	}
}
