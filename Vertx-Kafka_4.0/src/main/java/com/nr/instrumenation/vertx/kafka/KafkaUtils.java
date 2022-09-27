package com.nr.instrumenation.vertx.kafka;

import java.util.Map;

public class KafkaUtils {

	public static void addAttribute(Map<String, Object> attributes, String key, Object value) {
		if(attributes != null && key != null && !key.isEmpty() && value != null) {
			attributes.put(key, value);
		}
	}
}
