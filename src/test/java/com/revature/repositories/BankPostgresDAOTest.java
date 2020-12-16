package com.revature.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.models.Customer;

public class BankPostgresDAOTest {
	
	private BankPostgresDAO bpd;
	
	@BeforeEach//run before each test
	public void setupbpd() {
		this.bpd = new BankPostgresDAO();//we setup a new calc before each test
	}
	
	
	@Test
	public void testAddWithPositives() {
	
		Customer c = bpd.viewBalanceByAccount(9);
		
		assertEquals(8433.00, c.getBalance());
		
	}

	private void assertEquals(double d, double balance) {
		// TODO Auto-generated method stub
		
	}

}
