package com.smart_4.customer.details;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.smart_1.login_details.ProductDetails;
import com.smart_1.login_details.UserLogin;

public class Cart {
	
	private static Connection connection = null;
	private static Statement statement = null;
	private static PreparedStatement ps = null;
	private static ResultSet set = null;
	
	int p_id = 0;
	double p_price = 0;
	String p_name = "";
	double totalPrice=0;
	public static int product_id;
	
	ArrayList<Product> al = new ArrayList<>();
	List list = new ArrayList();
	
	public void buyProductId(int productId) {  
		UserBuy ub = new UserBuy();
		try {
			//loading Driver & Establishing Connection with Database
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/smart";
			connection = DriverManager.getConnection(url, "root", "root");
			statement = connection.createStatement(); 
			
			String checkquery = "SELECT productId FROM products WHERE productId='"+productId+"'";
			ResultSet set1 = statement.executeQuery(checkquery);
			int i = 0;
			while(set1.next()) {
				i = set1.getInt(1);
			}

			if(i==productId) {
				String query = "SELECT * FROM products WHERE productId='"+productId+"'"; 
			    set = statement.executeQuery(query);
				while(set.next()) {	
					p_id = set.getInt("productId");
					p_price = set.getDouble("productPrice");
					p_name = set.getString("productName");
				}
				
				al.add(new Product(productId, p_name, p_price));
				list.add(productId);
				totalPrice = totalPrice + p_price;	
			}
			else {
				System.out.println("\n\t Given number "+productId+" Product is not available in Database");
				System.out.println("\t Input Valid User_id ");
				ub.buy();
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
	
	public void showUserCart() {
		System.out.println("\n---------------------------------------------------------------------");
		System.out.println("\t\t - You are in CART PAGE -\n");
		System.out.println("Total Products You ADDED-TO-CART :");
	
		al.forEach((product) -> System.out.println(product));

	}
	
	public void productDeductionAndAddcart() {
		UserLogin ul = new UserLogin();
		ProductDetails pd = new ProductDetails();
		
		for(int i=0; i<list.size(); i++) {
			product_id = (int) list.get(i);
			pd.decreaseProductQuantity(product_id);
			ul.addCart( p_id, p_name, p_price);
		}
		
	}
}
