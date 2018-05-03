package org.crime.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHandler {

	private static Connection connection;

	public static Connection handleDbConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cde", "root", "");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

}
