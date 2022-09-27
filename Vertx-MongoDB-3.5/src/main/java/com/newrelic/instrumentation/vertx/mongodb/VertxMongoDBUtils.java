package com.newrelic.instrumentation.vertx.mongodb;

import java.util.Map;

public class VertxMongoDBUtils {

	public static void addAttribute(Map<String, Object> attributes, String key, Object value) {
		if(attributes != null && key != null && !key.isEmpty() && value != null) {
			attributes.put(key, value);
		}
	}
}
