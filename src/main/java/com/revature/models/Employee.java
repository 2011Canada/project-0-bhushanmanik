package com.revature.models;

public class Employee {

	private int employeeId;
	
	private String name;

	private int loginId;
	
	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee(int employeeId, String name,int loginId) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.loginId = loginId;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", loginId=" + loginId + "]";
	}

	
	

}
