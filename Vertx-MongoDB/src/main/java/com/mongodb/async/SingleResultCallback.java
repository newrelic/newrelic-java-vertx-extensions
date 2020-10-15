package com.mongodb.async;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type=MatchType.Interface)
public abstract class SingleResultCallback<T> {
	
	@NewField
	public Token token = null;

	@Trace(async=true)
	public void onResult(T result, Throwable t) {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		if(t != null) {
			NewRelic.noticeError(t);
		}
		Weaver.callOriginal();
	}
}
