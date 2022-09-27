package com.nr.fit.instrumentation.vertx.rabbitmq;

import java.util.Map;

public class VertxRabbitMQUtils {

	public static void addAttribute(Map<String, Object> attributes, String key, Object value) {
		if(attributes != null && key != null && !key.isEmpty() && value != null) {
			attributes.put(key, value);
		}
	}
}
