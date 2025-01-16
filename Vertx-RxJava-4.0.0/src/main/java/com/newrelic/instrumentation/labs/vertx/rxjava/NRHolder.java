package com.newrelic.instrumentation.labs.vertx.rxjava;

import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Transaction;

public class NRHolder {

	private Segment segment = null;
	private String name = null;
	private String component = null;
	private Transaction transaction =  null;
	
	public NRHolder(String c, String n, Transaction txn) {
		component = c;
		name = n;
		transaction = txn;
	}
	
	public void startSegment() {
		if(transaction != null && name != null && !name.isEmpty()) {
			segment = transaction.startSegment("RxJava/"+component, name);
		}
	}
	
	public void endSegment() {
		if(segment != null) {
			segment.end();
			segment = null;
		}
	}
	
	public void ignoreSegment() {
		if(segment != null) {
			segment.ignore();
			segment = null;
		}
	}
}
