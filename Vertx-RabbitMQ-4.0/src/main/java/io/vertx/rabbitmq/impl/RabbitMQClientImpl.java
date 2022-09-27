package io.vertx.rabbitmq.impl;

import java.util.HashMap;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.fit.instrumentation.vertx.rabbitmq.NRResultHandlerWrapper;
import com.nr.fit.instrumentation.vertx.rabbitmq.VertxRabbitMQUtils;
import com.rabbitmq.client.BasicProperties;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;

@Weave
public abstract class RabbitMQClientImpl {

	@Trace
	public void basicAck(long deliveryTag, boolean multiple, Handler<AsyncResult<JsonObject>> resultHandler) {
		Token token = NewRelic.getAgent().getTransaction().getToken();
		Segment segment = NewRelic.getAgent().getTransaction().startSegment("RabbitMQClientImpl-basicAck");
		NRResultHandlerWrapper<JsonObject> wrapper = new NRResultHandlerWrapper<JsonObject>(resultHandler, token, segment);
		resultHandler = wrapper;
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","Vertx","RabbitMQ","RabbitMQClient","basicAck"});
		Weaver.callOriginal();
	}
	
	@Trace
	public void basicNack(long deliveryTag, boolean multiple, boolean requeue, Handler<AsyncResult<JsonObject>> resultHandler) {
		Token token = NewRelic.getAgent().getTransaction().getToken();
		Segment segment = NewRelic.getAgent().getTransaction().startSegment("RabbitMQClientImpl-basicNack");
		NRResultHandlerWrapper<JsonObject> wrapper = new NRResultHandlerWrapper<JsonObject>(resultHandler, token, segment);
		resultHandler = wrapper;
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","Vertx","RabbitMQ","RabbitMQClient","basicNack"});
		Weaver.callOriginal();
	}
	
	@Trace
	public void basicGet(String queue, boolean autoAck, Handler<AsyncResult<JsonObject>> resultHandler) {
		Token token = NewRelic.getAgent().getTransaction().getToken();
		Segment segment = NewRelic.getAgent().getTransaction().startSegment("RabbitMQClientImpl-basicGet");
		NRResultHandlerWrapper<JsonObject> wrapper = new NRResultHandlerWrapper<JsonObject>(resultHandler, token, segment);
		resultHandler = wrapper;
		if(queue != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttribute("Queue", queue);
		}
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","Vertx","RabbitMQ","RabbitMQClient","basicGet",queue});
		Weaver.callOriginal();
	}

	@Trace
	public void basicPublish(String exchange, String routingKey, BasicProperties properties, Buffer body, Handler<AsyncResult<Void>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxRabbitMQUtils.addAttribute(attributes, "Exchange", exchange);
		VertxRabbitMQUtils.addAttribute(attributes, "RoutingKey", routingKey);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Token token = NewRelic.getAgent().getTransaction().getToken();
		Segment segment = NewRelic.getAgent().getTransaction().startSegment("RabbitMQClientImpl-basicGet");
		NRResultHandlerWrapper<Void> wrapper = new NRResultHandlerWrapper<Void>(resultHandler, token, segment);
		resultHandler = wrapper;
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","Vertx","RabbitMQ","RabbitMQClient","basicPublish",exchange});
		Weaver.callOriginal();
	}
	
	  public void basicPublishWithDeliveryTag(String exchange, String routingKey, BasicProperties properties, Buffer body, Handler<Long> deliveryTagHandler, Handler<AsyncResult<Void>> resultHandler) {
			HashMap<String, Object> attributes = new HashMap<String, Object>();
			VertxRabbitMQUtils.addAttribute(attributes, "Exchange", exchange);
			VertxRabbitMQUtils.addAttribute(attributes, "RoutingKey", routingKey);
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
			Token token = NewRelic.getAgent().getTransaction().getToken();
			Segment segment = NewRelic.getAgent().getTransaction().startSegment("RabbitMQClientImpl-basicGet");
			NRResultHandlerWrapper<Void> wrapper = new NRResultHandlerWrapper<Void>(resultHandler, token, segment);
			resultHandler = wrapper;
			NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","Vertx","RabbitMQ","RabbitMQClient","basicPublish",exchange});
			Weaver.callOriginal();
	  }

}
