package com.nr.instrumentation.vertx.jdbcclient;

public class Utils {

	public static String parseTable(String sql) {
		String tableName = "?";
		if(sql != null && !sql.isEmpty()) {
			int index = sql.toLowerCase().indexOf(" from ");
			if(index > -1) {
				int index2 = sql.indexOf(" ", index+" from ".length());
				if(index2 > -1) {
					return sql.substring(index+" from ".length(),index2);
				}
			}
		}
		return tableName;
	}
	
	public static String parseOperation(String sql) {
		String tmp = sql.toLowerCase();
		if(tmp.contains("select ")) {
			return "SELECT";
		}
		if(tmp.contains("update ")) {
			return "UPDATE";
		}
		if(tmp.contains("insert ")) {
			return "INSERT";
		}
		if(tmp.contains("delete ")) {
			return "DELETE";
		}
		if(tmp.contains("create ")) {
			return "CREATE";
		}
		
		return "?";
	}
}
