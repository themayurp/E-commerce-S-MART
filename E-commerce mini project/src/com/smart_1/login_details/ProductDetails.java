package com.smart_1.login_details;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.smart_3.admin.login_details.AdminProduct;

public class ProductDetails {

	private static Connection connection = null;
	private static Statement statement = null;
	private static PreparedStatement preparedstatement = null;
	private static ResultSet set = null;
	
	

	
	public void insertProduct() {
		AdminProduct ap = new AdminProduct();
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		
		boolean flag = true;
		
		while(flag) {
		
			try {
				//loading Driver & Establishing Connection with Database
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/smart";
				connection = DriverManager.getConnection(url, "root", "root");
				
				String query = "INSERT INTO products (productName, productDescription, productPrice, productQuantity)"
						     + " values (?, ?, ?, ?)";
				
				preparedstatement = connection.prepareStatement(query);
				 
				System.out.print("\tEnter ProductName > ");
				String productName = sc1.nextLine();
				System.out.print("\tEnter productDescription > ");
				String productDescription = sc2.nextLine();
				System.out.print("\tEnter productPrice > ");
				long productPrice = sc.nextInt();
				System.out.print("\tEnter productQuantity > ");
				int productQuantity = sc.nextInt();
				
				
				preparedstatement.setString(1, productName);
				preparedstatement.setString(2, productDescription);
				preparedstatement.setLong(3, productPrice);
				preparedstatement.setInt(4, productQuantity);
				
				
				int no = preparedstatement.executeUpdate();
				System.out.println("\n\t\t== DATA INSERTED ==\n");
			}
			catch(ClassNotFoundException | SQLException e) {
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
			System.out.print("\tDo you want to insert one more Product into Database Table : y/n \n\t\t\t");
			String c = sc.next();
			if(c.equalsIgnoreCase("Y")) flag=true;
			else {
				flag=false;
				ap.Product();
			}
		}
	}
	
	public void showProduct() {
		AdminProduct ap = new AdminProduct();
		Scanner sc = new Scanner(System.in);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smart", "root", "root");
			statement = connection.createStatement();
			
			String query = "SELECT * FROM products ORDER BY productName ";
			
			set = statement.executeQuery(query);
			System.out.println("\t\t\t == Product Details ==");
			System.out.println("________________________________________________________________________________________________");
			System.err.println("\t Id \t Name \t\t\t Description \t Price \t Quantity");
			while(set.next()) {
				int productId = set.getInt(1);
				String productName = set.getString(2);
				String productDescription = set.getString(3);
				int productPrice = set.getInt(4);
				int productQuantity = set.getInt(5);
				
				System.out.println("\t "+productId +" \t "+productName+" \t\t "+productDescription+" \t "+productPrice +" \t "+productQuantity);

//				System.out.println("=>\n"
//						     + "\t id          : "+productId
//					       + "\n\t Name        : "+productName
//					       + "\n\t Description : "+productDescription
//					       + "\n\t Price       : "+productPrice
//					       + "\n\t Quantity    : "+productQuantity
//					       + "");	
			
			}	
			System.out.println("__________________________________________________________________________________________________");

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
	
	public void increaseProductQuantity() {
		AdminProduct ap = new AdminProduct();
		boolean flag = true;
		while(flag) {
			this.showAdminProduct();
			
			Scanner sc = new Scanner(System.in);
			System.out.print("\t Enter the productId number where quantity need to be change : ");
			int productId = sc.nextInt();
			System.out.print("\t Enter the productId Quantity number to be INCREASED : ");
			int increasedQuantity = sc.nextInt();
			int oldProductQuantity = 0;
			int newProductQuantity = 0;
			String productName = "";
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smart", "root", "root");
				
				String query1 = "SELECT * from products where productId="+productId+"";
			    statement = connection.createStatement();
			    set = statement.executeQuery(query1);
			    
			    while(set.next()) {
			    	oldProductQuantity = set.getInt("productQuantity");
			    	productName = set.getString("productName");
			    }
			    
			    newProductQuantity = oldProductQuantity + increasedQuantity;
			    String query2 = "UPDATE products SET productQuantity="+newProductQuantity+" WHERE productId="+productId+" ";
			    statement.execute(query2);
			    System.out.println("             SUCCESSFULLY UPDATED              \n");
			    
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("\tDo you want to Upadate one more Product Quantity into Database Table : y/n \n\t\t\t ");
			String c = sc.next();
			if(c.equals("Y")) flag=true;
			else {
				flag=false;
				ap.Product();
			}
			
		}			
	}
	
	
	public void decreaseProductQuantity(int productId) {
				
			int oldProductQuantity = 0;
			int newProductQuantity = 0;
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smart", "root", "root");
				
				String query1 = "SELECT * from products where productId="+productId+"";
			    statement = connection.createStatement();
			    set = statement.executeQuery(query1);
			    
			    while(set.next()) {
			    	oldProductQuantity = set.getInt("productQuantity");
			    }
			    
			    newProductQuantity = oldProductQuantity - 1 ;
			    String query2 = "UPDATE products SET productQuantity="+newProductQuantity+" WHERE productId="+productId+" ";
			    statement.execute(query2);
			    
			} catch (ClassNotFoundException | SQLException e) {
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

	public void showAdminProduct() {
		AdminProduct ap = new AdminProduct();
		Scanner sc = new Scanner(System.in);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smart", "root", "root");
			statement = connection.createStatement();
			
			String query = "SELECT * FROM products ";
			
			set = statement.executeQuery(query);
			
			System.out.println("\n\t\t\t == PRODUCT DETAILS == \n");
			System.out.println("____________________________________________________________________________________________________\n");
			while(set.next()) {
				int productId = set.getInt(1);
				String productName = set.getString(2);
				String productDescription = set.getString(3);
				int productPrice = set.getInt(4);
				int productQuantity = set.getInt(5);
		
				System.out.println(" id          : "+productId
						       + "\n Name        : "+productName
						       + "\n Description : "+productDescription
						       + "\n Price       : "+productPrice
						       + "\n Quantity    : "+productQuantity
						       + "");
		        System.out.println("-------------------------------------------------------------");      
			}
			System.out.print("\n\t\tEnter 'Y' to go Back : ");
			String c = sc.next();
			if(c.equalsIgnoreCase("y")) {
				ap.Product();
			}
			else {
				System.out.println("Invalid Input..!!");
				this.showAdminProduct();
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
