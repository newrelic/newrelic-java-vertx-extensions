package com.nr.instrumentation.vertx.reactive;

import java.util.logging.Level;

import com.newrelic.agent.bridge.datastore.DatabaseVendor;
import com.newrelic.agent.bridge.datastore.UnknownDatabaseVendor;
import com.newrelic.agent.database.DatabaseService;
import com.newrelic.agent.database.DatabaseStatementParser;
import com.newrelic.agent.database.ParsedDatabaseStatement;
import com.newrelic.agent.database.SqlObfuscator;
import com.newrelic.agent.service.ServiceFactory;
import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Token;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.reactivex.sqlclient.SqlClient;
import io.vertx.reactivex.sqlclient.SqlConnection;

public class Utils {
	
	private static SqlObfuscator obfuscator;
	private static DatabaseStatementParser parser;
	public static boolean initialized = false;
	
	static {
		initialize();
	}
	
	private static void initialize() {
		if(!initialized) {
			DatabaseService databaseService = ServiceFactory.getDatabaseService();
			if(databaseService != null) {
				obfuscator = databaseService.getDefaultSqlObfuscator();
				parser = databaseService.getDatabaseStatementParser();
				initialized = obfuscator != null;
			}
		}
	}
	
	public static String getObfuscated(String sql) {
		if(obfuscator != null) {
			return obfuscator.obfuscateSql(sql);
		}
		return null;
	}
	
	public static ParsedDatabaseStatement getParsedStatement(String sql) {
		if(parser != null) {
			return parser.getParsedDatabaseStatement(null, sql, null);
		}
		return null;
	}
	
	public static <T> NRHandlerWrapper<T> getWrapper(Handler<AsyncResult<T>> h,String sql, SqlClient client) {
		
		ParsedDatabaseStatement parsedStatement = parser.getParsedDatabaseStatement(getDBType(client), sql, null);
		DatastoreParameters params = DatastoreParameters.product(parsedStatement.getDbVendor().getName()).collection(parsedStatement.getModel()).operation(parsedStatement.getOperation()).build();
		Token token = NewRelic.getAgent().getTransaction().getToken();
		Segment segment = NewRelic.getAgent().getTransaction().startSegment("Vertx-SqlClient");
		NewRelic.getAgent().getLogger().log(Level.FINE, "started segment for Vertx-Reactive: {0}",segment);
		return new NRHandlerWrapper<T>(h, token, segment, params);
	}

	public static <T> NRHandlerWrapper<T> getWrapper(Handler<AsyncResult<T>> h,String sql, DatabaseVendor vendor) {
		
		ParsedDatabaseStatement parsedStatement = parser.getParsedDatabaseStatement(vendor, sql, null);
		DatastoreParameters params = DatastoreParameters.product(parsedStatement.getDbVendor().getName()).collection(parsedStatement.getModel()).operation(parsedStatement.getOperation()).build();
		Token token = NewRelic.getAgent().getTransaction().getToken();
		Segment segment = NewRelic.getAgent().getTransaction().startSegment("Vertx-SqlClient");
		NewRelic.getAgent().getLogger().log(Level.FINE, "started segment for Vertx-Reactive: {0}",segment);
		return new NRHandlerWrapper<T>(h, token, segment, params);
	}

	public static <T> NRHandlerWrapper<T> getWrapper(Handler<AsyncResult<T>> h,String sql, SqlConnection connection) {
		
		ParsedDatabaseStatement parsedStatement = parser.getParsedDatabaseStatement(getDBType(connection), sql, null);
		DatastoreParameters params = DatastoreParameters.product(parsedStatement.getDbVendor().getName()).collection(parsedStatement.getModel()).operation(parsedStatement.getOperation()).build();
		Token token = NewRelic.getAgent().getTransaction().getToken();
		Segment segment = NewRelic.getAgent().getTransaction().startSegment("Vertx-SqlConnection");
		NewRelic.getAgent().getLogger().log(Level.FINE, "started segment for Vertx-Reactive: {0}",segment);
		return new NRHandlerWrapper<T>(h, token, segment, params);
	}

	public static DatabaseVendor getDBType(SqlClient client) {
		NewRelic.getAgent().getLogger().log(Level.FINE, "Getting DBVendor using SqlClient class {0}", client.getClass().getName());
		String classname = client.getClass().getSimpleName().toLowerCase();
		if(classname.startsWith("mysql")) {
			return MySQLDatabaseVendor.INSTANCE;
		}
		if(classname.startsWith("pg")) {
			return PostgresDatabaseVendor.INSTANCE;
		}
		if(classname.startsWith("mssql")) {
			return MSSQLDatabaseVendor.INSTANCE;
		}
		if(classname.startsWith("oracle")) {
			return OracleDatabaseVendor.INSTANCE;
		}
		if(classname.startsWith("db2")) {
			return DB2DatabaseVendor.INSTANCE;
		}
		return UnknownDatabaseVendor.INSTANCE;
	}

	public static DatabaseVendor getDBType(SqlConnection connection) {
		NewRelic.getAgent().getLogger().log(Level.FINE, "Getting DBVendor using connection class {0}", connection.getClass().getName());
		String classname = connection.getClass().getSimpleName().toLowerCase();
		if(classname.startsWith("mysql")) {
			return MySQLDatabaseVendor.INSTANCE;
		}
		if(classname.startsWith("pg")) {
			return PostgresDatabaseVendor.INSTANCE;
		}
		if(classname.startsWith("mssql")) {
			return MSSQLDatabaseVendor.INSTANCE;
		}
		if(classname.startsWith("oracle")) {
			return OracleDatabaseVendor.INSTANCE;
		}
		if(classname.startsWith("db2")) {
			return DB2DatabaseVendor.INSTANCE;
		}
		return UnknownDatabaseVendor.INSTANCE;
	}
}
