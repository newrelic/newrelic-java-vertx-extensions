package com.nr.instrumentation.vertx.mysqlclient;


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
import io.vertx.sqlclient.SqlConnection;

public class Utils {
  private static SqlObfuscator obfuscator;
  
  private static DatabaseStatementParser parser;
  
  public static boolean initialized = false;
  
  static {
    initialize();
  }
  
  private static void initialize() {
    if (!initialized) {
      DatabaseService databaseService = ServiceFactory.getDatabaseService();
      if (databaseService != null) {
        obfuscator = databaseService.getDefaultSqlObfuscator();
        parser = databaseService.getDatabaseStatementParser();
        initialized = (obfuscator != null);
      } 
    } 
  }
  
  public static String getObfuscated(String sql) {
    if (obfuscator != null)
      return obfuscator.obfuscateSql(sql); 
    return null;
  }
  
  public static ParsedDatabaseStatement getParsedStatement(String sql) {
    if (parser != null)
      return parser.getParsedDatabaseStatement(null, sql, null); 
    return null;
  }
  
  public static <T> NRResultWrapper<T> getWrapper(Handler<AsyncResult<T>> h, String sql) {
    ParsedDatabaseStatement parsedStatement = parser.getParsedDatabaseStatement(MySQLDatabaseVendor.INSTANCE, sql, null);
    DatastoreParameters params = DatastoreParameters.product(parsedStatement.getDbVendor().getName()).collection(parsedStatement.getModel()).operation(parsedStatement.getOperation()).build();
    Token token = NewRelic.getAgent().getTransaction().getToken();
    Segment segment = NewRelic.getAgent().getTransaction().startSegment("Vertx-SqlClient");
    return new NRResultWrapper<>(h, token, segment, params);
  }
  
  public static <T> NRResultWrapper<T> getWrapper(Handler<AsyncResult<T>> h, String sql, SqlConnection connection) {
    ParsedDatabaseStatement parsedStatement = parser.getParsedDatabaseStatement(MySQLDatabaseVendor.INSTANCE, sql, null);
    DatastoreParameters params = DatastoreParameters.product(parsedStatement.getDbVendor().getName()).collection(parsedStatement.getModel()).operation(parsedStatement.getOperation()).build();
    Token token = NewRelic.getAgent().getTransaction().getToken();
    Segment segment = NewRelic.getAgent().getTransaction().startSegment("Vertx-SqlConnection");
    return new NRResultWrapper<>(h, token, segment, params);
  }
  
}
