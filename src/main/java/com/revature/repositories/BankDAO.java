package com.revature.repositories;

import java.util.List;

import com.revature.exception.NotValidAmountException;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Login;
import com.revature.models.Transfers;


public interface BankDAO {
	
	public Customer applyForNewAccount(Customer customer);
	
	public Customer addCustomer(String name,double balance,int loginid,boolean approved);
	
	public List<Customer> viewBalance(int customerId);
	
	
	public Customer withdrawMoney(int accountNumber, double balance);
	

	public Customer depositMoney(int accountNumber, double balance);
	

	public Customer transferMoney(int accountNumber, double balance, int secondAccount);
	

	public List<Transfers> acceptMoneyTransfer(int accountNumber);
	
	public List<Customer> approveAccount();
	
	public Employee rejectAccount(int accountNumber);
	
	public Customer ViewCustomerInfo(int accountNumber);

	public Login login(String username, String password);

	public Customer getCustomerBasedOnLoginId(int loginId);
	
	public Customer moneyTransferRequests(boolean hasTransfer,int depositAccountNumber, double amount,int withdrawAccountNumber,int tranferid);
	
	public Customer deleteTransfers(int transferId);
	
	public Customer ApprovedByEmployee(int accountNumber);
	
	public Customer DeleteCustomerIfRejected(int accountNumber);
	
	public Login createNewLogin(String username, String password,String name,double balance,boolean approved,boolean isCustomer);

}
