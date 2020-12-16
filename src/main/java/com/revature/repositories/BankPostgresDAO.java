package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.launcher.BankLauncher;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Login;
import com.revature.models.Transfers;
import com.revature.util.ConnectionFactory;

public class BankPostgresDAO implements BankDAO{


	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();

	//login method
	@Override
	public Login login(String username, String password) {
		Connection conn = this.cf.getConnection();
		Login c = new Login();
		try {
			String sql = "select * from \"Bank\".\"Login\" where \"Login\".\"Username\" = '" + username 
					+ "' and \"Login\".\"Password\" = '" + password + "';";
			// we only use statements for very basic sql queries
			Statement s = conn.createStatement();

			ResultSet res = s.executeQuery(sql);
			
			
			if (res.next()) { 
				c.setCustomer(res.getBoolean("isCustomer"));
				c.setLoginId(res.getInt("LoginId"));
			}
			else {
				c = null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// if we actually had a pool of connections, we would do this
			cf.releaseConnection(conn);
		}
		BankLauncher.bankLogger.debug(username + " logged in ");
		return c;
	}

	//existing customer applying for a new account
	@Override
	public Customer applyForNewAccount(Customer customer) {
		Connection conn = cf.getConnection();
		Scanner userIn = new Scanner(System.in);
		System.out.println("Enter the starting balance for the new Account :  ");
		double startingAmount = userIn.nextDouble();
		try {
			conn.setAutoCommit(false);
				String newAccount = "Insert into \"Bank\".\"Customer\" (\"Name\",\"Balance\",\"loginid\") values( ?, ?, ?) returning \"loginid\";";
				PreparedStatement insertAccount = conn.prepareStatement(newAccount);
				insertAccount.setString(1,customer.getName() );
				insertAccount.setDouble(2, startingAmount);
				insertAccount.setInt(3, customer.getLoginId());

				insertAccount.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cf.releaseConnection(conn);
		}
		//BankLauncher.bankLogger.info(customer);
		// return the original object but with any database generated fields filled out
		return customer;
	}

	//gets the customer based on its login id
	public Customer getCustomerBasedOnLoginId(int loginId) {
		Connection conn = this.cf.getConnection();
		Customer c = new Customer();
		try {
			String sql = "select * from \"Bank\".\"Customer\" where \"Customer\".\"loginid\"=" + loginId + ";";
			
			Statement s = conn.createStatement();

			ResultSet res = s.executeQuery(sql);
			
			while (res.next()) {
				c.setName(res.getString("Name"));
				c.setBalance(res.getDouble("Balance"));
				c.setAccountNumber(res.getInt("AccountNumber"));
				c.setLoginId(res.getInt("loginId"));
				
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			cf.releaseConnection(conn);
		}
		//BankLauncher.bankLogger.info(c);
		return c;
		
	}
	
	//adding a customer
	@Override
	public Customer addCustomer(String name,double balance,int loginid,boolean approved) {
		Connection conn = cf.getConnection();
		Scanner userIn = new Scanner(System.in);
		Customer c = new Customer();
		try {
			conn.setAutoCommit(false);
				String newCustomer = "Insert into \"Bank\".\"Customer\" (\"Name\",\"Balance\",\"loginid\",\"approved\" ) values( ?, ?, ?, ?) returning \"AccountNumber\";";
				PreparedStatement second = conn.prepareStatement(newCustomer);
				second.setString(1, name);
				second.setDouble(2, balance);
				second.setInt(3, loginid);
				second.setBoolean(4, approved);

				second.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cf.releaseConnection(conn);
		}
		//BankLauncher.bankLogger.info(customer);
		// return the original object but with any database generated fields filled out
		return c;
	}

	
	//viewing the balances of all customers
	@Override
	public List<Customer> viewBalance(int customerId) {
		Connection conn = this.cf.getConnection();
		List<Customer> all = new ArrayList<Customer>();
		try {
			String sql = "select * from \"Bank\".\"Customer\" where \"Customer\".\"loginid\"=" + customerId + ";";
			
			Statement s = conn.createStatement();

			ResultSet res = s.executeQuery(sql);
			
			
			while (res.next()) {
				
				Customer c = new Customer();
				c.setName(res.getString("Name"));
				c.setBalance(res.getDouble("Balance"));
				c.setAccountNumber(res.getInt("AccountNumber"));
				all.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// if we actually had a pool of connections, we would do this
			cf.releaseConnection(conn);
		}
		
		return all;
	}

	//withdraw money
	@Override
	public Customer withdrawMoney(int accountNumber, double balance) {
		Connection conn = cf.getConnection();
		Scanner userIn = new Scanner(System.in);
		Customer c = new Customer();
		try {
			conn.setAutoCommit(false);
			  String withdraw = "Update \"Bank\".\"Customer\" set \"Balance\"= \"Balance\" -" + balance +
					" where \"Balance\" -" + balance +" > 0 and \"AccountNumber\" = " + accountNumber +"returning \"Balance\";";// we only use statements for very basic sql queries
				Statement s = conn.createStatement();
				ResultSet res = s.executeQuery(withdraw);
				if(res.next()) {
					System.out.println("Your money has been withdrawed");
				}
				else {
					System.out.println("Please Enter valid amount:");
					c = null;
				}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cf.releaseConnection(conn);
		}
		BankLauncher.bankLogger.debug("Withdrawed the amount of $" + balance + " from the account number :" + accountNumber);
		// return the original object but with any database generated fields filled out
		return c;
	}

	//Depositing money
	@Override
	public Customer depositMoney(int accountNumber, double balance) {
		Connection conn = cf.getConnection();
		Scanner userIn = new Scanner(System.in);
		
		Customer c = new Customer();
		try {
			
			conn.setAutoCommit(false);

			String deposit = "Update \"Bank\".\"Customer\" set \"Balance\"= \"Balance\" +" + balance +
					" where \"AccountNumber\" = " + accountNumber +"returning \"Balance\";";
				
				// we only use statements for very basic sql queries
				Statement s = conn.createStatement();

				ResultSet res = s.executeQuery(deposit);
				
				
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cf.releaseConnection(conn);
		}
		BankLauncher.bankLogger.debug("Deposited the amount of $" + balance + " to the account number" + accountNumber);
		// return the original object but with any database generated fields filled out
		return c;
	}

	//transfering money to other account
	@Override
	public Customer transferMoney(int accountNumber, double balance, int secondAccount) {
		Connection conn = cf.getConnection();
		Scanner userIn = new Scanner(System.in);
		Customer customer = new Customer();
		try {
			conn.setAutoCommit(false);
				String transfer = "Insert into \"Bank\".\"transfers\" (\"firstaccount\",\"secondaccount\",\"amount\") values( ?, ?, ?) returning \"transferid\";";
				PreparedStatement transferAmount = conn.prepareStatement(transfer);
				transferAmount.setInt(1, accountNumber );
				transferAmount.setInt(2, secondAccount);
				transferAmount.setDouble(3, balance);

				transferAmount.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cf.releaseConnection(conn);
		}
		BankLauncher.bankLogger.debug("Transfered money from account number : " + accountNumber + " to account number :" + secondAccount) ;
		// return the original object but with any database generated fields filled out
		return customer;
	}

	//accept money transfers
	@Override
	public List<Transfers> acceptMoneyTransfer(int accountNumber) {
		Connection conn = this.cf.getConnection();
		List<Transfers> all = new ArrayList<Transfers>();
		try {
			String sql = "select * from \"Bank\".\"transfers\" where \"transfers\".\"secondaccount\"=" + accountNumber + ";";
 
			Statement s = conn.createStatement();

			ResultSet res = s.executeQuery(sql);
			
			
			while (res.next()) {
				// make a new movie
				Transfers t = new Transfers();
				t.setTransferId(res.getInt("transferid"));
				t.setFirstAccount(res.getInt("firstaccount"));
				t.setSecondAccount(res.getInt("secondaccount"));
				t.setAmount(res.getInt("amount"));
				all.add(t);
			}
			// TODO
			// repeat for finding books as well

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// if we actually had a pool of connections, we would do this
			cf.releaseConnection(conn);
		}
		//BankLauncher.bankLogger.info(all);
		return all;
	}

	//approving accounts
	@Override
	public List<Customer> approveAccount() {
		Connection conn = this.cf.getConnection();
		List<Customer> all = new ArrayList<Customer>();
		try {
			String sql = "select * from \"Bank\".\"Customer\" where \"Customer\".\"approved\" = false;";
 
			Statement s = conn.createStatement();

			ResultSet res = s.executeQuery(sql);
			
			
			while (res.next()) {
				
				Customer c = new Customer();
				c.setName(res.getString("Name"));
				c.setBalance(res.getDouble("Balance"));
				c.setAccountNumber(res.getInt("AccountNumber"));
				
				all.add(c);
			}
			// TODO
			// repeat for finding books as well

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// if we actually had a pool of connections, we would do this
			cf.releaseConnection(conn);
		}
		//BankLauncher.bankLogger.info(all);
		return all;
	}

	//rejecting account
	@Override
	public Employee rejectAccount(int accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	//list of customer info
	@Override
	public List<Customer> ViewCustomerInfo() {
		Connection conn = this.cf.getConnection();
		List<Customer> all = new ArrayList<Customer>();
		
		try {
			String sql = "select * from \"Bank\".\"Customer\" where \"Customer\".\"approved\" = true;";
			
			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery(sql);
			
			while (res.next()) {
				Customer c = new Customer();
				c.setName(res.getString("Name"));
				c.setBalance(res.getDouble("Balance"));
				c.setAccountNumber(res.getInt("AccountNumber"));
				all.add(c);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// if we actually had a pool of connections, we would do this
			cf.releaseConnection(conn);
		}
		//BankLauncher.bankLogger.info(c);
		return all;
	}


	//money transfer requests
	@Override
	public Customer moneyTransferRequests(boolean hasTransfer, int depositAccountNumber, double amount,int withdrawAccountNumber,int transferid) {
		if(hasTransfer) {
			Customer c = withdrawMoney(withdrawAccountNumber,amount);
			if(c != null) {
			depositMoney(depositAccountNumber,amount);
			deleteTransfers(transferid);
			}
		}
		else {
			deleteTransfers(transferid);
		}
		return null;
	}

	//delete transfers
	@Override
	public Customer deleteTransfers(int transferId) {
		Connection conn = cf.getConnection();
		Scanner userIn = new Scanner(System.in);
		
		Customer c = new Customer();
		try {
			
			conn.setAutoCommit(false);
			String deleteTransfer = "delete from \"Bank\".\"transfers\"  where \"transferid\" = " + transferId +"returning \"transferid\";";

				Statement s = conn.createStatement();

				ResultSet res = s.executeQuery(deleteTransfer);
				System.out.println("Your money has been deposit.");
				
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			cf.releaseConnection(conn);
		}
		return c;
	}

	
	//appproved by employee
	@Override
	public Customer ApprovedByEmployee(int accountNumber) {
		Connection conn = cf.getConnection();
		Scanner userIn = new Scanner(System.in);
		
		Customer c = new Customer();
		try {
			
			conn.setAutoCommit(false);
//UPDATE weather SET (temp_lo, temp_hi, prcp) = (temp_lo+1, temp_lo+15, DEFAULT) WHERE city = 'San Francisco' AND date = '2003-07-03';
			String deposit = "Update \"Bank\".\"Customer\" set \"approved\" = true where \"AccountNumber\" =" + accountNumber +"returning \"AccountNumber\";";
				
				Statement s = conn.createStatement();

				ResultSet res = s.executeQuery(deposit);
				
				
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cf.releaseConnection(conn);
		}
		//BankLauncher.bankLogger.debug("A customer's account got approved by an employee");
		// return the original object but with any database generated fields filled out
		return c;
	}

	//deleting customer
	@Override
	public Customer DeleteCustomerIfRejected(int accountNumber) {
		Connection conn = cf.getConnection();
		Scanner userIn = new Scanner(System.in);
		
		Customer c = new Customer();
		try {
			
			conn.setAutoCommit(false);

			String deleteTransfer = "delete from \"Bank\".\"Customer\"  where \"AccountNumber\" = " + accountNumber +"returning \"AccountNumber\";";
				Statement s = conn.createStatement();

				ResultSet res = s.executeQuery(deleteTransfer);
				
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cf.releaseConnection(conn);
		}
		//BankLauncher.bankLogger.debug("A rejected customer was deleted");
		// return the original object but with any database generated fields filled out
		return c;
	}

	//create new login and customer
	@Override
	public Login createNewLogin(String username, String password,String name,double balance,boolean approved,boolean isCustomer) {
		Connection conn = cf.getConnection();
		Scanner userIn = new Scanner(System.in);
		Login login = new Login();
		try {
			conn.setAutoCommit(false);
				String newLogin = "Insert into \"Bank\".\"Login\" (\"Username\",\"Password\",\"isCustomer\" ) values( ?, ?, ?) returning \"LoginId\";";
				PreparedStatement first = conn.prepareStatement(newLogin);
				first.setString(1, username );
				first.setString(2, password);
				first.setBoolean(3, isCustomer);
				ResultSet res = first.executeQuery();
				if (res.next()) {
					String newCustomer = "Insert into \"Bank\".\"Customer\" (\"Name\",\"Balance\",\"loginid\",\"approved\" ) values( ?, ?, ?, ?) returning \"AccountNumber\";";
					PreparedStatement second = conn.prepareStatement(newCustomer);
					second.setString(1, name);
					second.setDouble(2, balance);
					second.setInt(3, res.getInt("loginid"));
					second.setBoolean(4, approved);

					second.executeQuery();
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cf.releaseConnection(conn);
		}
		//BankLauncher.bankLogger.debug("A new login was created");
		
		return login;
		
	}
	@Override
	public Customer viewBalanceByAccount(int customerId) {
		Connection conn = this.cf.getConnection();
		Customer c = new Customer();
		try {
			String sql = "select * from \"Bank\".\"Customer\" where \"Customer\".\"AccountNumber\" =" + customerId + ";";
			
			Statement s = conn.createStatement();

			ResultSet res = s.executeQuery(sql);
			
			
			while (res.next()) {
				
				
				c.setName(res.getString("Name"));
				c.setBalance(res.getDouble("Balance"));
				c.setAccountNumber(res.getInt("AccountNumber"));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// if we actually had a pool of connections, we would do this
			cf.releaseConnection(conn);
		}
		
		return c;
	}
}
