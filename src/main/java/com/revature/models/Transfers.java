package com.revature.models;

public class Transfers {

	private int transferId;
	
	private int firstAccount;
	
	private int secondAccount;
	
	private double amount;

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	public int getFirstAccount() {
		return firstAccount;
	}

	public void setFirstAccount(int firstAccount) {
		this.firstAccount = firstAccount;
	}

	public int getSecondAccount() {
		return secondAccount;
	}

	public void setSecondAccount(int secondAccount) {
		this.secondAccount = secondAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Transfers(int transferId, int firstAccount, int secondAccount, double amount) {
		super();
		this.transferId = transferId;
		this.firstAccount = firstAccount;
		this.secondAccount = secondAccount;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transfers [transferId=" + transferId + ", firstAccount=" + firstAccount + ", secondAccount="
				+ secondAccount + ", amount=" + amount + "]";
	}

	public Transfers() {
		super();
	}
	
	
}
