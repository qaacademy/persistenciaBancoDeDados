package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryTest {

	/**
	 * Metodo efetua conexão no banco de dados e retorna uma conexão.
	 * 
	 * @return Connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {

		try {
			// Create a connection to the database
			String serverName = "191.235.97.184";
			String portNumber = "1521";
			String sid = "XE";
			String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid;
			String username = "system";
			String password = "qaacademy";
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new SQLException(e);
		}

	}

}
