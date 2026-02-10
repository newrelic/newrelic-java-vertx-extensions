package com.nr.instrumentation.vertx.rxjava2;

import java.util.Map;

public class VertxReactiveUtils {

	public static void addAttribute(Map<String, Object> attributes, String key, Object value) {
		if(attributes != null && key != null && !key.isEmpty() && value != null) {
			attributes.put(key, value);
		}
	}
}
