package com.nr.instrumentation.sqlclient;

import com.newrelic.agent.bridge.datastore.DatastoreVendor;
import com.newrelic.agent.bridge.datastore.JdbcDatabaseVendor;

public class MySQLDatabaseVendor extends JdbcDatabaseVendor {
  public static MySQLDatabaseVendor INSTANCE = new MySQLDatabaseVendor();
  
  public MySQLDatabaseVendor() {
    super("MySQL", "mysql", true);
  }
  
  public DatastoreVendor getDatastoreVendor() {
    return DatastoreVendor.MySQL;
  }
}
