package com.revature.menus;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BankingMenu {
	public static Logger bankLogger = LogManager.getLogger("com.revature.bank");
	
	private String accountNo;
    private String customerName;
    private double balance;
    
	public void applyForAccount(){
		
	}
	public void viewCustomerAccounts(){}
	
	public void showAccount() {
		System.out.println(accountNo + "   -  " + customerName + "  -  " + balance );
	}

	public static void main(String[] args) {
		
		bankLogger.info("Server has Started");
		
		Scanner userIn = new Scanner(System.in);
		
		System.out.println("Welcome to your Bank! ");
		
		System.out.println("Register with us to start Banking ! ");
		System.out.println("Enter your Username : ");
		String userName = userIn.nextLine();
		
		System.out.println("Enter your Password : ");
		String passWord = userIn.nextLine();
		
		
		System.out.println("Are you an Employee or a Customer? (E/C)");
		String input = userIn.nextLine();
		System.out.println(input);
		
		System.out.println("Thanks for registering ! Now you can login with your"
				+ " username and password");
		
		System.out.println("Enter your Username : ");
		String name = userIn.nextLine();
		
		System.out.println("Enter your Password : ");
		String pass = userIn.nextLine();
		
		System.out.println("Customer Login : ");
		boolean isCustomer = true;
		if(isCustomer){
			System.out.println("1:Apply for account");
			System.out.println("2:View Account Info");
			System.out.println("3:Deposit into account");
			System.out.println("4:Deposit into account");
			System.out.println("5:Transfer money into other account");
			System.out.println("6:Accept money into account");
			}
			else{
			System.out.println(":View customer account");
			System.out.println("2:View log for all transactions");
			System.out.println("3:Approve an account");
			System.out.println("4:Reject an account");
			}
		    int option = userIn.nextInt();

			
			       }
			
			
	
	}
	
	

