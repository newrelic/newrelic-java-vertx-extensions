package com.nr.instrumentation.vertx.reactive;

import com.newrelic.agent.bridge.datastore.DatastoreVendor;
import com.newrelic.agent.bridge.datastore.JdbcDatabaseVendor;

public class DB2DatabaseVendor extends JdbcDatabaseVendor {
  public static DB2DatabaseVendor INSTANCE = new DB2DatabaseVendor();
  
  public DB2DatabaseVendor() {
    super("IBMDB2", "db2", true);
  }
  
  public DatastoreVendor getDatastoreVendor() {
    return DatastoreVendor.IBMDB2;
  }
}
