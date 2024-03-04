package com.nr.instrumentation.sqlclient;

import com.newrelic.agent.database.DatabaseService;
import com.newrelic.agent.database.DatabaseStatementParser;
import com.newrelic.agent.database.ParsedDatabaseStatement;
import com.newrelic.agent.service.ServiceFactory;

import io.vertx.sqlclient.impl.command.CommandBase;
import io.vertx.sqlclient.impl.command.PrepareStatementCommand;
import io.vertx.sqlclient.impl.command.QueryCommandBase;

public class SQLUtils {

	private static DatabaseStatementParser parser;
	
	public static ParsedDatabaseStatement getParsed(String sql) {
		if(parser == null) {
			getParser();
			if(parser == null) {
				return null;
			}
		}
		
		return parser.getParsedDatabaseStatement(null, sql, null);
	}

	public  static <R> String getSQL(CommandBase<R> cmd) {
		if(cmd instanceof QueryCommandBase) {
			QueryCommandBase<?> qCmd = (QueryCommandBase<?>)cmd;
			return qCmd.sql();
		}
		if(cmd instanceof PrepareStatementCommand) {
			PrepareStatementCommand pCmd = (PrepareStatementCommand)cmd;
			return pCmd.sql();
		}
		return null;
	}
	
	public static String getDBType(Class<?> sqlClass) {
		String classname = sqlClass.getSimpleName().toLowerCase();
		
		if(classname.startsWith("mysql")) {
			return "MySQL";
		}
		if(classname.startsWith("pg")) {
			return "Postgres";
		}
		if (classname.startsWith("oracle")) {
			return "Oracle"; 
		}
		if (classname.startsWith("mssql")) {
			return "MSSQL"; 
		}
		if (classname.startsWith("db2")) {
			return "IBMDB2"; 
		}
		
		return "Unknown";
	}
	
	private static void getParser() {
		DatabaseService databaseService = ServiceFactory.getDatabaseService();
		if(databaseService != null) {
			parser = databaseService.getDatabaseStatementParser();
		}
	}
}
