package io.vertx.kafka.client.producer.impl;

import java.util.HashMap;

import org.apache.kafka.clients.producer.ProducerRecord;

import com.newrelic.api.agent.DestinationType;
import com.newrelic.api.agent.MessageProduceParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumenation.vertx.kafka.KafkaUtils;

import io.vertx.core.Future;
import io.vertx.kafka.client.producer.RecordMetadata;

@Weave
public class KafkaWriteStreamImpl<K, V> {

	@Trace
	public Future<RecordMetadata> send(ProducerRecord<K, V> record) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		KafkaUtils.addAttribute(attributes, "Producer-Topic", record.topic());
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		MessageProduceParameters params = MessageProduceParameters.library("Vertx-Kafka").destinationType(DestinationType.NAMED_TOPIC).destinationName(record.topic()).outboundHeaders(null).build();
		traced.reportAsExternal(params);
		return Weaver.callOriginal();
	}
}
