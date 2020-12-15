package com.revature.repositories;

import java.util.ArrayList;

import java.util.List;

import com.revature.exception.NotValidAmountException;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Login;
import com.revature.models.Transfers;

public class BankMemoryDAO implements BankDAO{
	
	//static List<EntertainmentMedia> catalogue = new ArrayList<>();

	// static code block is a block of code that will execute when the program
	// before the main method
	/*static {
		EntertainmentMedia hotFuzz = new Movie(10.00, "HotFuzz", "Simon Pegg", "2007-01-01", 9, new int[] { 15, 55 });
		EntertainmentMedia hp3 = new Book(17.99, "Harry Potter and the Prisoner of Azkaban", "Penguin", "2003", 8,
				new int[] { 10, 25 }, 320, "J.K.Rowling");
		EntertainmentMedia jumpStreet = new Movie(10.00, "21 Jump Street", "Channing Tatum and Jonah Hill",
				"2010-01-01", 9, new int[] { 15, 55 });
		EntertainmentMedia hp4 = new Book(17.99, "Harry Potter and the Goblet of Fire", "Penguin", "2005", 10,
				new int[] { 10, 25 }, 450, "J.K.Rowling");
		catalogue.add(jumpStreet);
		catalogue.add(hotFuzz);
		catalogue.add(hp3);
		catalogue.add(hp4);
	}**/


	@Override
	public Login login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Customer getCustomerBasedOnLoginId(int loginId) {
		return null;
	}
	
	@Override
	public Customer applyForNewAccount(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Customer addCustomer(String name,double balance,int loginid,boolean approved) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> viewBalance(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer withdrawMoney(int accountNumber, double balance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer depositMoney(int accountNumber, double balance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer transferMoney(int accountNumber, double balance, int secondAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> approveAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee rejectAccount(int accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer ViewCustomerInfo(int accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transfers> acceptMoneyTransfer(int accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer moneyTransferRequests(boolean hasTransfer, int depositAccountNumber, double amount, int withdrawAccountNumber,int transferid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer deleteTransfers(int transferId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer ApprovedByEmployee(int accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer DeleteCustomerIfRejected(int accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Login createNewLogin(String username, String password,String name,double balance,boolean approved,boolean isCustomer) {
		// TODO Auto-generated method stub
		return null;
	}


}
