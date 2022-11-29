package com.smart_4.customer.details;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.smart_1.login_details.UserLogin;

public class CustomerDetails {
	
	private static Connection connection = null;
	private static Statement statement = null;
	private static PreparedStatement preparedstatement = null;
	private static ResultSet set = null;
	public static String u_fName;
	public static String u_lName;
	public static double p_price;

	public void showCustomer() {
		Scanner sc = new Scanner(System.in);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smart", "root", "root");
			statement = connection.createStatement();
			
			String query = "SELECT * FROM userRegistration ";	
			set = statement.executeQuery(query);
			System.out.println("\n \t\t  == All User Details == ");
			System.out.println("_________________________________________________________________________________________\n");
			while(set.next()) {
				int c_Id = set.getInt(1);
				String c_fName = set.getString(2);
				String c_lName = set.getString(3);
				String c_email = set.getString(4);
				String c_password = set.getString(5);
				
				System.out.println( "=>"
					              + "\n\t id         : "+c_Id
						          + "\n\t firstName  : "+c_fName
						          + "\n\t lasstName  : "+c_lName
						          + "\n\t email      : "+c_email
						          + "\n\t password   : "+c_password
						          + "");
//				System.out.println("-------------------------------------------------------");
			}
			System.out.println("_________________________________________________________________________________________");

		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}	
	}
	
	public void customerPurchaseHistory() {
		Scanner sc = new Scanner(System.in);
//		this.showCustomer();
		System.out.print("\n\t Enter the User's Id number : ");
		int User_ID = sc.nextInt();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smart", "root", "root");
			statement = connection.createStatement();
			
			String checkId = "SELECT userId FROM cart WHERE userId='"+User_ID+"' LIMIT 1";	
			ResultSet set1 = statement.executeQuery(checkId);
			int i = 0;
			while(set1.next()) {
				i=set1.getInt(1);
			}
			if(i==User_ID) {
				double totalPrice = 0;
				
				String query = "SELECT * FROM cart WHERE userId='"+User_ID+"'";	
				set = statement.executeQuery(query);	
				System.out.println("\n\t\t\t == Customer Transaction History == ");
				System.out.println("____________________________________________________________________________________________\n");
				while(set.next()) {
					int p_Id = set.getInt("productId");
					String p_Name = set.getString("productName");
					p_price = set.getDouble("productPrice");
					String c_email = set.getString(4);
					String c_password = set.getString(5);
					int c_Id = set.getInt("userId");
					u_fName = set.getString("userFirstName");
					u_lName = set.getString("userLastName");
					
					totalPrice = totalPrice + p_price;
					
					System.out.println(   "\t=>"
							          + "\n\t id        : "+p_Id
							          + "\n\t Name      : "+p_Name
							          + "\n\t Price     : "+p_price
							          + "");	
				}
				System.out.println("\n\t Total Price : "+totalPrice );
				System.out.println("-----------------------------------------------------------------");
			}
			else {
				System.out.println("\n\tGiven number "+User_ID+" User is not available in Database");
				System.out.println("\t Input Valid User_id ");
				this.customerPurchaseHistory();
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}			
	}
	
	
	

}
