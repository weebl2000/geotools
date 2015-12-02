package org.geotools.data.teiid;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.geotools.jdbc.BasicSQLDialect;
import org.geotools.jdbc.JDBCDataStore;
import org.opengis.feature.type.GeometryDescriptor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by weebl on 2-12-15.
 *
 * @author Wessel Nieboer
 */
public class TeiidDialect extends BasicSQLDialect {
	protected TeiidDialect( JDBCDataStore dataStore ) {
		super( dataStore );
	}

	/**
	 * Encodes a geometry value in an sql statement.
	 * <p>
	 * An implementations should serialize <tt>value</tt> into some exchange
	 * format which will then be transported to the underlying database. For
	 * example, consider an implementation which converts a geometry into its
	 * well known text representation:
	 * <pre>
	 *   <code>
	 *   sql.append( "GeomFromText('" );
	 *   sql.append( new WKTWriter().write( value ) );
	 *   sql.append( ")" );
	 *   </code>
	 *  </pre>
	 * </p>
	 * <p>
	 * The <tt>srid</tt> parameter is the spatial reference system identifier
	 * of the geometry, or 0 if not known.
	 * </p>
	 * <p>
	 * Attention should be paid to emtpy geometries (<code>g.isEmtpy() == true</code>) as
	 * they cannot be encoded in WKB and several databases fail to handle them property.
	 * Common treatment is to equate them to NULL</p>
	 *
	 * @param value
	 * @param dimension
	 * @param srid
	 * @param sql
	 */
	@Override public void encodeGeometryValue( Geometry value, int dimension, int srid, StringBuffer sql ) throws IOException {

	}

	/**
	 * Encodes the spatial extent function of a geometry column in a SELECT statement.
	 * <p>
	 * This method must also be sure to properly encode the name of the column
	 * with the {@link #encodeColumnName(String, StringBuffer)} function.
	 * </p>
	 *
	 * @param tableName
	 * @param geometryColumn
	 * @param sql
	 */
	@Override public void encodeGeometryEnvelope( String tableName, String geometryColumn, StringBuffer sql ) {

	}

	/**
	 * Decodes the result of a spatial extent function in a SELECT statement.
	 * <p>
	 * This method is given direct access to a result set. The <tt>column</tt>
	 * parameter is the index into the result set which contains the spatial
	 * extent value. The query for this value is build with the {@link #encodeGeometryEnvelope(String, String, StringBuffer)}
	 * method.
	 * </p>
	 * <p>
	 * This method must not read any other objects from the result set other then
	 * the one referenced by <tt>column</tt>.
	 * </p>
	 *
	 * @param rs     A result set
	 * @param column Index into the result set which points at the spatial extent
	 *               value.
	 * @param cx
	 */
	@Override public Envelope decodeGeometryEnvelope( ResultSet rs, int column, Connection cx ) throws SQLException, IOException {
		return null;
	}

	/**
	 * Decodes a geometry value from the result of a query.
	 * <p>
	 * This method is given direct access to a result set. The <tt>column</tt>
	 * parameter is the index into the result set which contains the geometric
	 * value.
	 * </p>
	 * <p>
	 * An implementation should deserialize the value provided by the result
	 * set into {@link Geometry} object. For example, consider an implementation
	 * which deserializes from well known text:
	 * <code>
	 * <pre>
	 *   String wkt = rs.getString( column );
	 *   if ( wkt == null ) {
	 *     return null;
	 *   }
	 *   return new WKTReader(factory).read( wkt );
	 *   </pre>
	 * </code>
	 * Note that implementations must handle <code>null</code> values.
	 * </p>
	 * <p>
	 * The <tt>factory</tt> parameter should be used to instantiate any geometry
	 * objects.
	 * </p>
	 *
	 * @param descriptor
	 * @param rs
	 * @param column
	 * @param factory
	 * @param cx
	 */
	@Override public Geometry decodeGeometryValue( GeometryDescriptor descriptor, ResultSet rs, String column, GeometryFactory factory, Connection cx ) throws IOException, SQLException {
		return null;
	}
}
