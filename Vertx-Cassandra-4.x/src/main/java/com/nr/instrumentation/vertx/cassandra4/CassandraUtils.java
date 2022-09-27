package com.nr.instrumentation.vertx.cassandra4;

import java.util.Map;

import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.Statement;

public class CassandraUtils {
	
	public static void addAttribute(Map<String, Object> attributes, String key, Object value) {
		if(attributes != null && key != null && !key.isEmpty() && value != null) {
			attributes.put(key, value);
		}
	}
	
	public static void addStatement(Map<String, Object> attributes,Statement<?> statement) {
		if (statement != null) {
			addAttribute(attributes, "KeySpace", statement.getKeyspace());
			addAttribute(attributes, "StatementType", statement.getClass().getSimpleName());
			if(statement instanceof BoundStatement) {
				addBoundStatement(attributes, (BoundStatement)statement);
			}
		}
	}

	public static void addBoundStatement(Map<String, Object> attributes, BoundStatement statement) {
		if(statement != null) {
			PreparedStatement prepared = statement.getPreparedStatement();
			if (prepared != null) {
				addAttribute(attributes, "BoundStatement-Prepared-QueryString", prepared.getQuery());
			}
		}
	}
	
}
