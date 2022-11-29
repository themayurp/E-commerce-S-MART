package com.smart_4.customer.details;

import java.util.Scanner;

import com.smart_1.login_details.CustomerShoping;
import com.smart_1.login_details.ProductDetails;
import com.smart_1.login_details.UserLogin;

public class UserBuy {
	
	public void buy() {
		UserLogin ul = new UserLogin();
		CustomerShoping cs = new CustomerShoping();
		ProductDetails pd = new ProductDetails();
		Cart c = new Cart();
		Scanner sc = new Scanner(System.in);
		
		boolean flag = true;
		while(flag) {
			System.out.print("\t Enter the PRODUCT ID number you want to add to cart : ");
			int num = sc.nextInt();
			c.buyProductId(num);
			System.out.print("\t Want to buy more Product enter : Y/N \n\t\t ");
			String s = sc.next();
			if(s.equalsIgnoreCase("N")) {
				flag = false;
				c.showUserCart();
				System.out.println("___________________________________________________________\n");
				System.out.print("\t Total Amount : ");
				System.err.println(c.totalPrice);
				
				System.out.print("\n\t Are you sure to BUY Enter : Y/N \n\t\t");
				String a = sc.next();
				if(a.equalsIgnoreCase("y")) {
					System.out.print("Transaction in process please wait ");
					c .productDeductionAndAddcart();
					     
					try {
						System.err.print(".");
						Thread.sleep(800);
						System.err.print(".");
						Thread.sleep(800);
						System.err.print(".");
						Thread.sleep(800);
						System.err.print(".");
						Thread.sleep(800);
						System.err.print(".");						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("\n\n\t\t Transaction Successfull \n\t\t      Thank you..!! ");
					System.out.print("\n\t\t1.Back \n\t\t 2.Log-out \n\tEnter : ");
					int n = sc.nextInt();
					switch(n) {
					case 1:
						cs.shopingChoice();
						break;
					case 2:
						ul.welcome();
						break;
					}		
				}
				else {
					System.out.println("\n\t\t      Thank you..!! ");

					System.out.print("\n\t 1.Home page \n\t2.Log-out \n\tEnter : ");
					int n = sc.nextInt();
					switch(n) {
					case 1:
						cs.shoping(ul.getF_name(), ul.getL_name());
						break;
					case 2:
						ul.welcome();
						break;
					}		
				}
				
			}
			else if (s.equalsIgnoreCase("y")) flag=true;
			else {
				System.err.println("Invalid Input...!!");
				this.buy();
			}
		}
		
	}
}
