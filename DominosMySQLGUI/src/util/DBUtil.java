package util;

import java.sql.*;
import com.sun.rowset.CachedRowSetImpl;

public class DBUtil {

	//private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/dominos";
	//private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String CONNECTION_STRING = "jdbc:sqlite:testdb.db";
	//private static final String SQL_USER = "root";
	//private static final String SQL_PASS = "root";

	private static Connection con = null;

	/************************************************************************************
	 * Static method used for establishing connection to the MySQL server.
	 * Usage: will be used in our data access objects before executing query and
	 * updates
	 * 
	 * @throws SQLException,
	 *             ClassNotFoundException
	 ************************************************************************************/
	/*
	public static void dbConnect() throws ClassNotFoundException, SQLException {
		// set the JDBC driver to be used
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("Error on loading JDBC driver: " + e);
			e.printStackTrace();
			throw e;
		}

		// establish the connection to the MySQL database
		try {
			con = DriverManager.getConnection(CONNECTION_STRING, SQL_USER, SQL_PASS);
		} catch (SQLException e) {
			System.out.println("Error on establishing MySQL connection " + CONNECTION_STRING + ": " + e);
			e.printStackTrace();
			throw e;
		}
	}
	*/
	public static void dbConnect() throws ClassNotFoundException, SQLException {
		try{
			con = DriverManager.getConnection(CONNECTION_STRING);
		}catch(SQLException e){
			System.out.println("Error on establishing MySQL connection " + CONNECTION_STRING + ": " + e);
			e.printStackTrace();
			throw e;
		}
	}

	/*********************************************************************
	 * Static method, used for closing the connection to the MySQL server.
	 * Usage: used in DAO after we have executed query or update.
	 * 
	 * @throws SQLException
	 *********************************************************************/
	public static void dbDisconnect() throws SQLException {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("Error on terminating connection: " + e);
			e.printStackTrace();
			throw e;
		}
	}

	/**************************************************************
	 * Static method, returns RowSet. Accepts SQL query as String. Usage: Parse
	 * from DAO SQL statement to execute
	 **************************************************************/
	public static ResultSet executeQuery(String query) throws SQLException, ClassNotFoundException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CachedRowSetImpl crs = null;

		try {
			dbConnect();
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			// populate our cached row set with row set so we are able to close
			// the result set
			crs = new CachedRowSetImpl();
			crs.populate(rs);
		} catch (SQLException e) {
			System.out.println("Exception on executing query on the database: " + e);
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			dbDisconnect();
		}
		return crs;
	}

	/**************************************************************************
	 * Static method. Accepts SQL update as String. Usage: Parse SQL from the
	 * DAO to here and we handle database operations here.
	 * @throws ClassNotFoundException 
	 **************************************************************************/
	public static void executeUpdate(String update) throws SQLException, ClassNotFoundException{
		PreparedStatement stmt = null;
		try{
			dbConnect();
			stmt = con.prepareStatement(update);
			stmt.executeUpdate();
		}catch(SQLException e ){
			System.out.println("Error on executing update on the database: " + e);
			e.printStackTrace();
			throw e;
		}finally{
			if (stmt != null){
				stmt.close();
			}
			dbDisconnect();
		}
	}
	
}
