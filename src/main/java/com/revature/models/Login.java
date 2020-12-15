package com.revature.models;

public class Login {

	private String username;
	
	private String password;
	
	private boolean isCustomer;
	
	private int loginId;

	public Login(String username, String password, boolean isCustomer, int loginId) {
		super();
		this.username = username;
		this.password = password;
		this.isCustomer = isCustomer;
		this.loginId = loginId;
	}
	

	public Login() {
		super();
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isCustomer() {
		return isCustomer;
	}

	public void setCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
}
