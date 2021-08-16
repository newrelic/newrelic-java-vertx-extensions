package com.nr.instrumentation.vertx.reactive;

import com.newrelic.agent.bridge.datastore.DatastoreVendor;
import com.newrelic.agent.bridge.datastore.JdbcDatabaseVendor;

public class MySQLDatabaseVendor extends JdbcDatabaseVendor {
	
	public static MySQLDatabaseVendor INSTANCE = new MySQLDatabaseVendor();

	public MySQLDatabaseVendor() {
		super("MySQL", "mysql", true);
	}

	@Override
	public DatastoreVendor getDatastoreVendor() {
		return DatastoreVendor.MySQL;
	}

}
