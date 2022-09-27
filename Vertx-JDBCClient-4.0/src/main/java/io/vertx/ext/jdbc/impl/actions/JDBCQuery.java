package io.vertx.ext.jdbc.impl.actions;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.logging.Level;

import com.newrelic.agent.database.DefaultDatabaseStatementParser;
import com.newrelic.agent.database.ParsedDatabaseStatement;
import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.jdbcclient.Utils;

@Weave
public abstract class JDBCQuery  {

	
	private final String sql = Weaver.callOriginal();
	
	@Trace(leaf=true)
	public io.vertx.ext.sql.ResultSet execute(Connection conn) {
		String product = "Vertx-JDBC";
		
		try {
			DatabaseMetaData metadata = conn.getMetaData();
			if(metadata != null) {
				product = metadata.getDatabaseProductName();
			}
		} catch (SQLException e) {
			NewRelic.getAgent().getLogger().log(Level.FINEST, e, "Failed to get database product");
		}
		
		DefaultDatabaseStatementParser parser = new DefaultDatabaseStatementParser();
		ParsedDatabaseStatement stmt = parser.getParsedDatabaseStatement(Utils.getVendor(product), sql, null);
		
		DatastoreParameters params;
		
		if(stmt != null) {
			params = DatastoreParameters.product(product).collection(stmt.getModel()).operation(stmt.getOperation()).build();
		} else {
			params = DatastoreParameters.product(product).collection(Utils.parseTable(sql)).operation(Utils.parseOperation(sql)).build();
		}
		NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		
		return Weaver.callOriginal();
	}
	
}
