package com.nr.instrumentation.vertx.reactive;

import com.newrelic.agent.bridge.datastore.DatastoreVendor;
import com.newrelic.agent.bridge.datastore.JdbcDatabaseVendor;

public class OracleDatabaseVendor extends JdbcDatabaseVendor {
  public static OracleDatabaseVendor INSTANCE = new OracleDatabaseVendor();
  
  public OracleDatabaseVendor() {
    super("Oracle", "oracle", true);
  }
  
  public DatastoreVendor getDatastoreVendor() {
    return DatastoreVendor.Oracle;
  }
}
