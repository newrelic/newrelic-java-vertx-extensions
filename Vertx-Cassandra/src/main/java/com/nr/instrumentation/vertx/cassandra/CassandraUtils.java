package com.nr.instrumentation.vertx.cassandra;

import java.util.Map;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.RegularStatement;
import com.datastax.driver.core.Statement;

public class CassandraUtils {
	
	public static void addAttribute(Map<String, Object> attributes, String key, Object value) {
		if(attributes != null && key != null && !key.isEmpty() && value != null) {
			attributes.put(key, value);
		}
	}
	
	public static void addStatement(Map<String, Object> attributes,Statement statement) {
		if (statement != null) {
			addAttribute(attributes, "KeySpace", statement.getKeyspace());
			addAttribute(attributes, "StatementType", statement.getClass().getSimpleName());
			if (statement instanceof RegularStatement) {
				addRegularStatement(attributes, (RegularStatement) statement);
			} else if(statement instanceof BoundStatement) {
				addBoundStatement(attributes, (BoundStatement)statement);
			}
		}
	}

	public static void addRegularStatement(Map<String, Object> attributes,RegularStatement statement) {
		if(statement != null) {
			addAttribute(attributes, "QueryString", statement.getQueryString());	
		}
	}
	
	public static void addBoundStatement(Map<String, Object> attributes, BoundStatement statement) {
		if(statement != null) {
			PreparedStatement prepared = statement.preparedStatement();
			if (prepared != null) {
				addAttribute(attributes, "BoundStatement-Prepared-QueryString", prepared.getQueryString());
				addAttribute(attributes, "BoundStatement-Prepared-QueryKeyspace", prepared.getQueryKeyspace());
			}
		}
	}
	
}
