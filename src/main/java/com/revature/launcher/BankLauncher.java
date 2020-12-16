package com.revature.launcher;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Customer;
import com.revature.models.Login;
import com.revature.models.Transfers;
import com.revature.repositories.BankDAO;
import com.revature.repositories.BankPostgresDAO;

public class BankLauncher {
	
	public static Logger bankLogger = LogManager.getLogger("com.revature.bank");

	//Main menu method
	public static void menu(String userName,String passWord) {
		
		BankDAO emd = new BankPostgresDAO();
		Scanner userIn = new Scanner(System.in);
		
		int option;
		Login c = emd.login(userName, passWord);
		if(c == null) {
			System.out.println("User does not exit. Wrong username or password!");

		}
		else {
			try {
			
			//if its an employee then it will go in the if statement
			boolean isCustomer = c.isCustomer();
			Customer cw = isCustomer?emd.getCustomerBasedOnLoginId(c.getLoginId()):null;
			do {
				if(isCustomer){
				System.out.println();
				System.out.println("1:Apply for a new account under this username.");
				System.out.println("2:View Account Info");
				System.out.println("3:Deposit into account");
				System.out.println("4:Withdraw from account");
				System.out.println("5:Transfer money into other account");
				System.out.println("6:Accept money into account");
				System.out.println("7.Sign out and sign in as a different user.");
				System.out.println("8.Exit");
				}
				else{
				System.out.println();
				System.out.println("1:View all customer's accounts");
				System.out.println("2:View log for all transactions");
				System.out.println("3:Approve an account");
				System.out.println("4:Reject an account");
				System.out.println("5.Sign out and sign in as a different user.");
				System.out.println("6.Exit");
				}
				System.out.println("Select your option:");
			    option = userIn.nextInt();
			  
			    
			    switch(option) {
			    case 1:
			    	if(isCustomer) {
			    		emd.applyForNewAccount(cw);
			    		
			    	}
			    	else {

			    		java.util.List<Customer> m  = emd.ViewCustomerInfo();
			    		for(int i = 0;i < m.size();i++) {
			    			System.out.println("Customer [accountNumber=" + m.get(i).getAccountNumber() + ", name=" + m.get(i).getName() + ", balance=" + m.get(i).getBalance() + "]");
			    		}
			    	}
			    	break;
			    case 2:
			    	if(isCustomer) {
			    		
			    		
			    		java.util.List<Customer> c1  = emd.viewBalance(c.getLoginId());
			    		for(int i = 0;i < c1.size();i++) {
			    			System.out.println("Customer [accountNumber=" + c1.get(i).getAccountNumber() + ", name=" + c1.get(i).getName() + ", balance=" + c1.get(i).getBalance() + "]");
			    		}
			    	}
			    	else {
			    		BufferedReader reader;
			    		try {
			    			reader = new BufferedReader(new FileReader(
			    					"/Users/User/Documents/GitHub/project-0-bhushanmanik/logs/trace.log"));
			    			String line = reader.readLine();
			    			while (line != null) {
			    				System.out.println(line);
			    				// read next line
			    				line = reader.readLine();
			    			}
			    			reader.close();
			    		} catch (IOException e) {
			    			e.printStackTrace();
			    		
						}
			    	}
			    	break;
			    case 3:
			    	if(isCustomer) {
			    		System.out.println("Enter the account number in which you want to deposit : ");
			    		int accountDeposit = userIn.nextInt();
			    		
			    		
			    		System.out.println("Enter the amount you want to deposit  ");
			    		double depositAmount = userIn.nextDouble();
			    		
			    		
			    		if(depositAmount > 0 )
						{
						emd.depositMoney(accountDeposit, depositAmount);
						}
						else {
							System.out.println("Please enter valid amount!");
						}
			    	}
			    	else {
			    		
						
						java.util.List<Customer> c1  = emd.approveAccount();
			    		for(int i = 0;i < c1.size();i++) {
			    			System.out.println("Customer [accountNumber=" + c1.get(i).getAccountNumber() + ", name=" + c1.get(i).getName() + ", balance=" + c1.get(i).getBalance() + "]");
			    		}
			    		System.out.println("Enter the account number which you want to approve:");
						int approvedAccount= userIn.nextInt();
						
			    		emd.ApprovedByEmployee(approvedAccount);
			    	}
			    	break;
			    case 4:
			    	if(isCustomer) {
			    		System.out.println("Enter the account number from which you want to withdraw : ");
			    		int accountwithdraw = userIn.nextInt();
			    		
			    		
			    		System.out.println("Enter the amount you want to withdraw  ");
			    		double withdrawAmount = userIn.nextDouble();
			    		
			    		
			    		if(withdrawAmount > 0 )
						{
						emd.withdrawMoney(accountwithdraw, withdrawAmount);
						}
						else {
							System.out.println("Please enter valid amount!");
						}
			    	}
			    	else {

						java.util.List<Customer> c1  = emd.approveAccount();
			    		for(int i = 0;i < c1.size();i++) {
			    			System.out.println("Customer [accountNumber=" + c1.get(i).getAccountNumber() + ", name=" + c1.get(i).getName() + ", balance=" + c1.get(i).getBalance() + "]");
			    		}
			    		System.out.println("Enter the account number which you want to reject:");
						int rejectedAccount= userIn.nextInt();
						
			    		emd.DeleteCustomerIfRejected(rejectedAccount);
			    	}
			    	break;
			    case 5:
			    	if(isCustomer) {

			    		System.out.println("Enter the balance you want to transfer :  ");
			    		double transferAmount = userIn.nextDouble();
			    		
			    		System.out.println("Enter the Account number in which you want to tranfer :  ");
			    		int transferAccount = userIn.nextInt();
			    		
		    		emd.transferMoney(cw.getAccountNumber(), transferAmount, transferAccount);
			    	}

			    	else {
			    		System.out.println("Enter your Username : ");
			    		String usernm = userIn.next();
			    		
			    		System.out.println("Enter your Password : ");
			    		String passwd = userIn.next();
			    		menu(usernm,passwd);
			    	}
		    	break;
		    	
			    case 6:
			    	if(isCustomer) {
			    		System.out.println("Do you want to accept all the transfers?(Y/N)");
			    	    String acceptTransfer = userIn.next();
			    		
			    		
			    		java.util.List<Transfers> c2  = emd.acceptMoneyTransfer(cw.getAccountNumber());
			    		for(int i = 0;i < c2.size();i++) {
			    			
			    			System.out.println("Transfers [transferId=" + c2.get(i).getTransferId() + ", firstAccount=" + c2.get(i).getFirstAccount() + ", secondAccount="
				+ c2.get(i).getSecondAccount() + ", amount=" + c2.get(i).getAmount() + "]");
			    			

				    		
				    		if(acceptTransfer.equals("Yes") || acceptTransfer.equals("yes") || acceptTransfer.equals("Y") || acceptTransfer.equals("y")) {
				    			
				    			emd.moneyTransferRequests(true,c2.get(i).getSecondAccount(),c2.get(i).getAmount(),c2.get(i).getFirstAccount(),c2.get(i).getTransferId());
				    		}
				    		else {
				    			
				    			emd.deleteTransfers(c2.get(i).getTransferId());
				    		}
			    		}
	    		
		    	}
	    	break;
			    case 7:
			    	if(isCustomer) {
			    		System.out.println("Enter your Username : ");
			    		String usernm = userIn.next();
			    		
			    		System.out.println("Enter your Password : ");
			    		String passwd = userIn.next();
			    		menu(usernm,passwd);
			    	}
			    	
		} 
		}
			    while(!((option == 8 && isCustomer) || (!isCustomer && option == 6)));
			}catch(InputMismatchException m) {
				System.out.println("Please enter a integer ");
			}
	   }
    }
	
	
	public static void main(String[] args) throws InterruptedException {
		BankDAO emd = new BankPostgresDAO();
		//System.out.println(emd.viewBalance(5));
		
		Scanner userIn = new Scanner(System.in);
		
		System.out.println("|------------------------------------Welcome to Manik's Bank! ------------------------------------|\n");
		System.out.println("Existing users, please enter yes to go to the login page !  otherwise enter no, to register a bank account.");
		String mainOption = userIn.nextLine();
		
		if(mainOption.equals("Yes") || mainOption.equals("yes") || mainOption.equals("Y") || mainOption.equals("y")) {
		
			System.out.println();
			
		System.out.println("Existing user\n");
		
		System.out.println("Enter your Username : ");
		String userName = userIn.nextLine();
		
		System.out.println();
		
		System.out.println("Enter your Password : ");
		String passWord = userIn.nextLine();
		menu(userName,passWord);
		

	}
		else {
			
			
			System.out.println("Enter the username you want:  ");
    		String newUsername = userIn.next();
    		
    		System.out.println("Enter the password you want :  ");
    		String Newpassword = userIn.next();
    		
    		System.out.println("Enter your Name :  ");
    		String newName = userIn.next();
    		
    		System.out.println("Enter your starting balance :  ");
    		Double newBalance = userIn.nextDouble();
    		
    		emd.createNewLogin(newUsername, Newpassword,newName, newBalance, false,true);
    		Thread.sleep(500);
    		menu(newUsername,Newpassword);
    		
		}
	}
}
