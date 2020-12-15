package com.revature.models;

public class Customer {

	private int accountNumber;
	
	private String name;
	
	private double balance;
	
	private int loginId;
	
	private boolean approved;
	

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	

	public Customer(int accountNumber, String name, double balance, int loginId) {
		super();
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
		this.loginId = loginId;
	}

	public Customer(String name, double balance, int loginId) {
		super();
		this.name = name;
		this.balance = balance;
		this.loginId = loginId;
	}
	
	public Customer() {
		super();
	}

	@Override
	public String toString() {
		return "Customer [accountNumber=" + accountNumber + ", name=" + name + ", balance=" + balance + ", loginId="
				+ loginId + "]";
	}
	
	public String toStringOne() {
		return "Customer [accountNumber=" + accountNumber + ", name=" + name + ", balance=" + balance + "]";
	}
	
	

}
