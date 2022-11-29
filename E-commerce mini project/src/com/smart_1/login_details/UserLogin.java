package com.smart_1.login_details;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.smart_3.admin.login_details.AdminLogin;

public class UserLogin {
	
	private static Connection connection = null;
	private static Statement statement = null;
	private static PreparedStatement preparedstatement = null;
	private static ResultSet set = null;
	
	public static String em;
	private static int u_Id;
	private String pw ;
	public static String f_name;
	public static String l_name ;

	public static int getU_Id() {
		return u_Id;
	}

	public static void setU_Id(int u_Id) {
		UserLogin.u_Id = u_Id;
	}

	public String getEm() {
		return em;
	}

	public void setEm(String em) {
		this.em = em;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public void welcome() {
		Scanner userInput = new Scanner(System.in);
		AdminLogin al = new AdminLogin();
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.print("                               ");
		try {
			System.out.print("W");
			Thread.sleep(100);
			System.out.print("e");
			Thread.sleep(100);
			System.out.print("l");
			Thread.sleep(100);
			System.out.print("-");
			Thread.sleep(100);
			System.out.print("C");
			Thread.sleep(100);
			System.out.print("o");
			Thread.sleep(100);
			System.out.print("m");
			Thread.sleep(100);
			System.out.print("e");
			Thread.sleep(100);			
			System.out.print(" ");
			Thread.sleep(100);
			System.out.print("T");
			Thread.sleep(100);
			System.out.print("o");
			Thread.sleep(100);
			System.out.print(" ");
			Thread.sleep(100);
			System.out.print("S");
			Thread.sleep(100);
			System.out.print("-");
			Thread.sleep(100);
			System.out.print("M");
			Thread.sleep(100);
			System.out.print("A");
			Thread.sleep(100);
			System.out.print("R");
			Thread.sleep(100);
			System.out.print("T\n");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println("\t 1. Sign In \t 2.Sign Up \t 3.Admin      \n\nEnter Number :");
		
		int input = userInput.nextInt();
		switch(input) {
			case 1:
				this.signIn();
				break;
			case 2:
				this.signUp();
				break;
			
			case 3:
				al.adminSignIn();;
				break;
			default :
				System.err.println("Invalid Input..!!\n\n");
				this.welcome();
				break;
		}
	
	}
	
	public void signIn() {
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
			
			String query = "SELECT * FROM userRegistration WHERE email='"+emailId+"'";	
			statement = connection.createStatement();		
            ResultSet set = statement.executeQuery(query);  
			
            while(set.next()) {
				String a = set.getString("firstName");
				String b = set.getString("lastName");
				String c = set.getString("email");
				String d = set.getString("password");
				this.setF_name(a);
				this.setL_name(b);
				this.setEm(c);
				this.setPw(d);
				break;
			}
			
			if(em.equals(emailId) && pw.equals(password)) {
				cs.shoping(this.getF_name(), this.getL_name());		
			}
			else {
				System.err.println("\tInvalid Email or Password..!!\n");
				System.out.println("\nTry Again.");
				this.signIn();
			}
				
		}
		catch(ClassNotFoundException | SQLException  e) {
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
	
	
	
	public void signUp() {
		Scanner sc = new Scanner(System.in);
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/smart";
			connection = DriverManager.getConnection(url, "root", "root");
			
			String query = "INSERT INTO userRegistration (firstName, lastName, email, password)"
					     + " values (?, ?, ?, ?)";
			
			preparedstatement = connection.prepareStatement(query);
			 
			System.out.print("\t Enter firstName \n\t >");
			String firstName = sc.next();
			System.out.print("\t Enter lastNameme \n\t >");
			String lastName = sc.next();
			System.out.print("\t Enter email \n\t >");
			String email = sc.next();
			System.out.print("\t Enter password \n\t >");
			String password = sc.next();
			
			
			preparedstatement.setString(1, firstName);
			preparedstatement.setString(2, lastName);
			preparedstatement.setString(3, email);
			preparedstatement.setString(4, password);
			
			
			int no = preparedstatement.executeUpdate();
			System.out.println("\n\t == Registration Successfully == \n");
			this.signIn();
		}
		catch(ClassNotFoundException | SQLException e) {
			System.err.print("\t Email-Id already exist..!! \n\t ");
			System.out.println("Try Again.");
			this.signUp();
		}
		finally {
			try {
				connection.close();
				preparedstatement.close();
			   
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
    public void addCart(int productId, String productName, double productPrice) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/smart";
			connection = DriverManager.getConnection(url, "root", "root");
          
			String query = "SELECT * FROM userRegistration WHERE email='"+UserLogin.em+"'";	
			statement = connection.createStatement();
		    ResultSet set = statement.executeQuery(query);
           	while(set.next()) {
				String a = set.getString("firstName");
				String b = set.getString("lastName");
				String c = set.getString("email");
				String d = set.getString("password");
				int e = set.getInt("userId");
				this.setF_name(a);
				this.setL_name(b);
				this.setEm(c);
				this.setPw(d);
				this.setU_Id(e);
			}
			
			String query1 = "INSERT INTO cart (userFirstName,userLastName,productName,productPrice,userEmail,userId,productId) values (?,?,?,?,?,?,?)";
			preparedstatement = connection.prepareStatement(query1);
			preparedstatement.setString(1, this.getF_name());
			preparedstatement.setString(2, this.getL_name());
			preparedstatement.setString(3, productName);
			preparedstatement.setDouble(4, productPrice);
			preparedstatement.setString(5, UserLogin.em);
			preparedstatement.setInt(6, UserLogin.u_Id);
			preparedstatement.setInt(7, productId);

			int no = preparedstatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedstatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
    public void userPurchaseHistory() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smart", "root", "root");
			statement = connection.createStatement();
		
			String query = "SELECT * FROM cart WHERE userEmail='"+UserLogin.em+"'";	
			set = statement.executeQuery(query);	
			System.out.println("\t\t\t == Your Transaction History == ");
			System.out.println("____________________________________________________________________________________________\n");
			while(set.next()) {

				int p_Id          = set.getInt("productId");
				String p_Name     = set.getString("productName");
				int p_price       = set.getInt("productPrice");
				String c_email    = set.getString(4);
				String c_password = set.getString(5);
				
				System.out.println(   "=>"
						          + "\n id        : "+p_Id
						          + "\n Name      : "+p_Name
						          + "\n Price     : "+p_price
						          + "");	

			}
			
			System.out.println("\n-------------------------------------------------------------------------------------------\n");
			
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
