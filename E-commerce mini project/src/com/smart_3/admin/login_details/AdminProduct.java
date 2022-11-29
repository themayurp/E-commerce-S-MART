package com.smart_3.admin.login_details;

import java.util.Scanner;
import com.smart_1.login_details.ProductDetails;
import com.smart_1.login_details.UserLogin;
import com.smart_4.customer.details.CustomerDetails;

public class AdminProduct {
	
	public void Product() {
		Scanner sc = new Scanner(System.in);
		ProductDetails pd = new ProductDetails();
		UserLogin ul = new UserLogin();
		AdminLogin al = new AdminLogin();
		
		System.out.print("\n\t 1.Add Product \t\n\t 2.Increase Product Quantity \t\n\t 3.Product Details \t\n\t 4.Back \t\n\t 5.Log-out"
				          + "  \n\nEnter Number : ");
	
		int num = sc.nextInt();
		switch(num) {
			case 1:
				pd.insertProduct();
				break;
			case 2:
				pd.increaseProductQuantity();
				break;
			case 3:
				pd.showAdminProduct();
				break;
			case 4:
				al.welcomeAdmin();
				break;
			case 5:
				ul.welcome();
				break;
			default :
				System.err.println("Invalid Input..!!\n");
				this.Product();
		}
		
	}
	
	public void customer() {
		Scanner sc = new Scanner(System.in);
		CustomerDetails cd = new CustomerDetails();
		UserLogin ul = new UserLogin();
		AdminLogin al = new AdminLogin();
		
		System.out.print("\t 1.All User Details  \n\t 2.User Shoping-History  \n\t 3.Log-out"
				          + "  \n\nEnter Number : ");
	
		int num = sc.nextInt();
		switch(num) {
			case 1:
				cd.showCustomer();
				System.out.print("\n\n\t 1. Back  \n\t 2. Log-out  \nEnter number : ");
				int n = sc.nextInt();
				switch(n) {
					case 1:
						al.welcomeAdmin();
						break;
					case 2:
						ul.welcome();
						break;
				   default:
				    	System.err.println("\t Invalid Input..");
				    	this.customer();
				    	break;	
				}
				break;
			case 2:
				cd.customerPurchaseHistory();
				System.out.print("\n\n\t 1. Back  \n\t 2. Log-out  \nEnter number :");
				int m = sc.nextInt();
				switch(m) {
					case 1:
						this.customer();
						break;
					case 2:
						ul.welcome();
						break;
				    default:
				    	System.err.println("\t Invalid Input..");
				    	this.customer();
				    	break;	    	
				}
				break;
			case 3:
				ul.welcome();
				break;
		   default :
			   System.err.println("Invalid Input..!!\n");
			   this.customer();
		}
		
	}


}
