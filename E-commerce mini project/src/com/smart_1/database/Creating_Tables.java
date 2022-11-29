package com.smart_1.database;

import java.sql.*;

public class Creating_Tables {
	
	private static Connection connection = null;
	private static Statement statement = null;

	public static void main(String[] args) {
		
		try {
			//loading Driver & Establishing Connection with Database
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/smart";
			connection = DriverManager.getConnection(url, "root", "root");
			
			String query = "CREATE TABLE products ("
					+ "productId INT NOT NULL AUTO_INCREMENT, "
					+ "productName VARCHAR(150) NOT NULL, "
					+ "productDescription VARCHAR(150) NOT NULL, "
					+ "productPrice INT NOT NULL, "
					+ "productQuantity INT NOT NULL, "
					+ "PRIMARY KEY(productId), "
					+ "UNIQUE KEY(productName) )";
			
			statement = connection.createStatement();
			statement.execute(query);
			System.out.println("=========Successfully Table Created into SMART DB=======");
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
