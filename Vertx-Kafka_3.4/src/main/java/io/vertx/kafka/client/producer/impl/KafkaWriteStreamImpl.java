package io.vertx.kafka.client.producer.impl;

import org.apache.kafka.clients.producer.ProducerRecord;

import com.newrelic.api.agent.DestinationType;
import com.newrelic.api.agent.MessageProduceParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.kafka.client.producer.RecordMetadata;

@Weave
public class KafkaWriteStreamImpl<K, V> {

	@Trace
	public KafkaWriteStreamImpl<K, V> write(ProducerRecord<K, V> record, Handler<AsyncResult<RecordMetadata>> handler) {
		MessageProduceParameters params = MessageProduceParameters.library("Vertx-Kafka").destinationType(DestinationType.NAMED_TOPIC).destinationName(record.topic()).outboundHeaders(null).build();
		NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		return Weaver.callOriginal();
	}
}
