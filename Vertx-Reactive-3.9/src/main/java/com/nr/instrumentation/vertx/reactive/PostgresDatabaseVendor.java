package com.nr.instrumentation.vertx.reactive;

import com.newrelic.agent.bridge.datastore.DatastoreVendor;
import com.newrelic.agent.bridge.datastore.JdbcDatabaseVendor;

public class PostgresDatabaseVendor extends JdbcDatabaseVendor {
	
	public static PostgresDatabaseVendor INSTANCE = new PostgresDatabaseVendor();

	public PostgresDatabaseVendor() {
		super("Postgres", "postgres", true);
	}

	@Override
	public DatastoreVendor getDatastoreVendor() {
		return DatastoreVendor.Postgres;
	}

}
