package com.nr.instrumentation.vertx.reactive;

import com.newrelic.agent.bridge.datastore.DatastoreVendor;
import com.newrelic.agent.bridge.datastore.JdbcDatabaseVendor;

public class MSSQLDatabaseVendor extends JdbcDatabaseVendor {
  public static MSSQLDatabaseVendor INSTANCE = new MSSQLDatabaseVendor();
  
  public MSSQLDatabaseVendor() {
    super("MSSQL", "mssql", true);
  }
  
  public DatastoreVendor getDatastoreVendor() {
    return DatastoreVendor.MSSQL;
  }
}
