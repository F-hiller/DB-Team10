package p3;

import java.sql.*;

public class DatabaseManager {
	private String url;
	private String user;
	private String password;

	public DatabaseManager(ConfigLoader configLoader) {
		try {
			// Load a JDBC driver for Oracle DBMS
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Get a Connection object
			System.out.println("[System]: OracleDriver success.");
		} catch (ClassNotFoundException e) {
			System.err.println("[System]: error = " + e.getMessage());
			System.exit(1);
		}
		this.url = configLoader.getDatabaseUrl();
		this.user = configLoader.getUsername();
		this.password = configLoader.getPassword();
	}

	public Connection getConnection() throws SQLException {
		// Make a connection
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("[System]: Database Connected.");
			return conn;
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println("[System]: Cannot get a connection: " + ex.getLocalizedMessage());
			System.err.println("[System]: Cannot get a connection: " + ex.getMessage());
			System.exit(1);
		}
		return null;
	}

	public void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
