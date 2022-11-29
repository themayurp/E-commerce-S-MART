package com.smart_1.login_details;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.smart_3.admin.login_details.AdminLogin;
import com.smart_4.customer.details.CustomerDetails;
import com.smart_4.customer.details.UserBuy;

public class CustomerShoping {
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet set = null;
	
	public void shoping(String a, String b) {
		ProductDetails pd = new ProductDetails();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		
		try {
			System.out.print("Hello ");
			Thread.sleep(100);
			System.out.print(a +" ");
			Thread.sleep(100);
			System.out.print(b +" ");
			Thread.sleep(100);
			System.out.print("...\n");
			System.out.print("\n                         ");
			Thread.sleep(100);		
			System.out.print("W");
			Thread.sleep(200);
			System.out.print("E");
			Thread.sleep(200);
			System.out.print("L");
			Thread.sleep(200);
			System.out.print("-");
			Thread.sleep(200);
			System.out.print("C");
			Thread.sleep(200);
			System.out.print("O");
			Thread.sleep(200);
			System.out.print("M");
			Thread.sleep(200);
			System.out.print("E");
			Thread.sleep(200);			
			System.out.print(" ");
			Thread.sleep(200);
			System.out.print("T");
			Thread.sleep(200);
			System.out.print("O");
			Thread.sleep(200);
			System.out.print(" ");
			Thread.sleep(200);
			System.out.print("T");
			Thread.sleep(200);
			System.out.print("H");
			Thread.sleep(200);
			System.out.print("E");
			Thread.sleep(200);
			System.out.print(" ");
			Thread.sleep(200);
			System.out.print("S");
			Thread.sleep(200);
			System.out.print("-");
			Thread.sleep(200);
			System.out.print("M");
			Thread.sleep(200);
			System.out.print("A");
			Thread.sleep(200);
			System.out.print("R");
			Thread.sleep(200);
			System.out.print("T");
			Thread.sleep(200);
			System.out.print(" ");
			Thread.sleep(200);
			System.out.print("S");
			Thread.sleep(200);
			System.out.print("H");
			Thread.sleep(200);
			System.out.print("O");
			Thread.sleep(200);
			System.out.print("P\n");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
		System.out.println("                              = User Home Page =                           ");
		System.out.println("\n________________________________________________________________________________________________\n");
		this.shopingChoice();
	}
	
	public void shopingChoice() {
		Scanner sc = new Scanner(System.in);
		UserLogin ul = new UserLogin();
		UserBuy ub = new UserBuy();
		ProductDetails pd = new ProductDetails();
		
		pd.showProduct();
		System.out.println("\n \t 1. Buy Product \n \t 2. Shoping History \n \t 3. Log-out \n");
		System.out.print("\t Enter number : ");
		int num = sc.nextInt();
		switch(num) {
			case 1:
				ub.buy();
				break;
			case 2:
				ul.userPurchaseHistory();
				System.out.println("\n\n\t 1. Back  \n\t 2. Log-out  \nEnter number :\n");
				int n = sc.nextInt();
				switch(n) {
					case 1:
						this.shopingChoice();
						break;
					case 2:
						ul.welcome();
						break;
					default :
						System.err.println("Invalid Input..!!\n");
						this.shopingChoice();
				}
				break;
			case 3:
				ul.welcome();
				break;
			default :
				System.err.println("Invalid Input..!!\n");
				this.shopingChoice();
		}				

	}
	
	

}
