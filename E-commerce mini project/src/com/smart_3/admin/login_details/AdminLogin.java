package com.smart_3.admin.login_details;

import java.sql.*;
import java.util.Scanner;

import com.smart_1.login_details.CustomerShoping;
import com.smart_1.login_details.UserLogin;

public class AdminLogin {
	
	Connection connection = null;
	Statement statement = null;
	
	String em = "";
	String pw = "";
	String f_name = "";
	String l_name = "";
	
	public void adminSignIn() {
		
		CustomerShoping cs = new CustomerShoping();
		Scanner sc = new Scanner(System.in); 
		
		System.out.print("\tEnter your emailID :\n\t>");
		String emailId = sc.next();
		System.out.print("\tEnter your Password :\n\t>");
		String password = sc.next();
		
		
		try {
			//loading Driver & Establishing Connection with Database
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/smart";
			connection = DriverManager.getConnection(url, "root", "root");
			
			String query = "SELECT * FROM adminRegistration WHERE email='"+emailId+"'";
			
			statement = connection.createStatement();
			
            ResultSet set = statement.executeQuery(query);
            
            
            
			while(set.next()) {
				f_name = set.getString("firstName");
				l_name = set.getString("lastName");
				em = set.getString("email");
				pw = set.getString("password");
			}
		
			if(em.equals(emailId) && pw.equals(password)) {
				this.welcomeAdmin();
			}
			else {
				System.err.println("\tInvalid Email or Password..!!");
				System.out.println("Try Again.");
				this.adminSignIn();
			}
			
			
		}
		catch(ClassNotFoundException | SQLException e) {
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
	
	public void welcomeAdmin() {
		
		System.out.println("----------------------------------------------------------------------------------------");
		System.err.println("\n                                     WEL-COME ADMIN                                     \n");
		System.out.println("Hello "+f_name+" "+l_name+"...");
		System.out.println("----------------------------------------------------------------------------------------");

		this.adminChoice();
		
	}
	
	public void adminChoice() {
		AdminProduct ap = new AdminProduct();
		UserLogin ul = new UserLogin();
		Scanner sc = new Scanner(System.in);
		System.out.println("  \t  1.Products \t  2.Customers \t  3.Log-out \t\n");
		System.out.println("Enter The Number :");
		int num = sc.nextInt();
		switch(num) {
			case 1:
				ap.Product();;
				break;
			case 2:
				ap.customer();
				break;
			case 3:
				ul.welcome();
				break;
			default :
				System.err.println("Invalid Input ..!!\n");
				this.adminChoice();			
	   }
		
	}

}
