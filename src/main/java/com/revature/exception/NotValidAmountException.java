package com.revature.exception;

public class NotValidAmountException extends Exception {


	public NotValidAmountException() {
		super("That amount was not Valid, Please Try Again");
		
	}
}
