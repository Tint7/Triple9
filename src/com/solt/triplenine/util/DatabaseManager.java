package com.solt.triplenine.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	private static final String USER ="root";
	private static final String PASS = "root";
	private static final String URL="jdbc:mysql://localhost:3306/triple9";
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(URL,USER,PASS);
		
	}
}
