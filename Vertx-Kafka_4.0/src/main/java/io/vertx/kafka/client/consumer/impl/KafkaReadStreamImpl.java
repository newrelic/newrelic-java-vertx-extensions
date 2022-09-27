package io.vertx.kafka.client.consumer.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumenation.vertx.kafka.NRConsumerRecordHandler;

import io.vertx.core.Handler;

@Weave
public abstract class KafkaReadStreamImpl<K,V> {

	public KafkaReadStreamImpl<K, V> handler(Handler<ConsumerRecord<K, V>> handler)  {
		NRConsumerRecordHandler<K, V> wrapper = new NRConsumerRecordHandler<K, V>(handler);
		handler = wrapper;
		
		return Weaver.callOriginal();
	}
}
