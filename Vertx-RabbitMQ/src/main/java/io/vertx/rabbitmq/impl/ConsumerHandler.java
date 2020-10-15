package io.vertx.rabbitmq.impl;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;

@Weave
abstract class ConsumerHandler {

	@Trace(dispatcher=true)
	public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
		Weaver.callOriginal();
	}
}
