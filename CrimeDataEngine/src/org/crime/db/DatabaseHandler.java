package org.crime.db;

import java.sql.Connection;
import java.sql.DriverManager;

import org.crime.utils.EngineNotWorkingException;

public class DatabaseHandler {

	private static Connection connection;

	public static Connection handleDbConnection() throws EngineNotWorkingException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cde", "root", "");

		} catch (Exception e) {
			throw new EngineNotWorkingException("Database connection not established...");
		}
		return connection;
	}

}
