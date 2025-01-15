package com.nr.instrumentation.vertx.rx;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class VertxRx_InstrumentationUtils {

	
	private static final Set<String> instrumented = new HashSet<>();
	
	public static boolean isInstrumented(Class<?> clazz) {
		return isInstrumented(clazz.getName());
	}
	
	public static boolean isInstrumented(String className) {
		return instrumented.contains(className);
	}
	
	public static void instrumentRXClass(Class<?> clazz) {
		Method[] methods = clazz.getDeclaredMethods();
		
		instrumented.add(clazz.getName());
	}
}
