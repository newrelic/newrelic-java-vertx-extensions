package com.nr.instrumentation.vertx.reactive;

import com.newrelic.agent.bridge.datastore.DatastoreVendor;
import com.newrelic.agent.bridge.datastore.JdbcDatabaseVendor;

public class UnknownDatabaseVendor extends JdbcDatabaseVendor {
	
	public static UnknownDatabaseVendor INSTANCE = new UnknownDatabaseVendor();

	public UnknownDatabaseVendor() {
		super("JDBC", "jdbc", true);
	}

	@Override
	public DatastoreVendor getDatastoreVendor() {
		return DatastoreVendor.JDBC;
	}

}
