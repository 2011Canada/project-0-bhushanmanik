package com.revature.repositories;


	
	import java.util.Collections;
	import java.util.List;
	import java.util.Set;
	import java.util.TreeSet;

import com.revature.models.Customer;

	
	public class BankDebuggerDAO {

		public static void main(String[] args) {
			BankDAO emd = new BankPostgresDAO();
			
			emd.viewBalance(5);
			
			
		}

}
