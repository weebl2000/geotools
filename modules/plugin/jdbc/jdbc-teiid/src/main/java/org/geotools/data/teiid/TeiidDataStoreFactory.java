package org.geotools.data.teiid;

import org.geotools.jdbc.JDBCDataStore;
import org.geotools.jdbc.JDBCDataStoreFactory;
import org.geotools.jdbc.SQLDialect;

import java.util.Map;

/**
 * Created by weebl on 2-12-15.
 *
 * @author Wessel Nieboer
 */
public class TeiidDataStoreFactory extends JDBCDataStoreFactory {
	/** parameter for database type */
	public static final Param DBTYPE = new Param("dbtype", String.class, "Type", true,"teiid");
	/** Default port number for VDB */
	public static final Param PORT = new Param("port", Integer.class, "Port", true, 31000);

	@Override
	protected void setupParameters(Map parameters) {
		super.setupParameters(parameters);
		parameters.put(DBTYPE.key, DBTYPE);
		parameters.put(PORT.key, PORT);

		parameters.remove(SCHEMA.key);
	}
	/**
	 * Returns a string to identify the type of the database.
	 * <p>
	 * Example: 'teiid'.
	 * </p>
	 */
	@Override protected String getDatabaseID() {
		return "teiid";
	}

	/**
	 * Returns the fully qualified class name of the jdbc driver.
	 * <p>
	 * For example: org.teiid.jdbc.TeiidDriver
	 * </p>
	 */
	@Override protected String getDriverClassName() {
		return "org.teiid.jdbc.TeiidDriver";
	}

	/**
	 * Creates the dialect that the datastore uses for communication with the
	 * underlying database.
	 *
	 * @param dataStore The datastore.
	 */
	@Override protected SQLDialect createSQLDialect( JDBCDataStore dataStore ) {
		return new TeiidDialect(dataStore);
	}

	/**
	 * Override this to return a good validation query (a very quick one, such as one that
	 * asks the database what time is it) or return null if the factory does not support
	 * validation.
	 *
	 * @return
	 */
	@Override protected String getValidationQuery() {
		return "SELECT 1";
	}

	/**
	 * Describe the nature of the datasource constructed by this factory.
	 * <p>
	 * A non localized description of this data store type.
	 * </p>
	 *
	 * @return A human readable description that is suitable for inclusion in a
	 * list of available datasources.
	 */
	@Override public String getDescription() {
		return "Teiid JDBC Datasource";
	}
}
