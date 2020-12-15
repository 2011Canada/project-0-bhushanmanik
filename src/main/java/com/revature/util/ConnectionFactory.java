package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//factory design pattern?
//factory is an object that builds other objects -> based on provided configuration
public class ConnectionFactory {
	
	//turn the factory into a singleton so everyone can only get a connection from our factory
	//make a private static reference to our self -> the one and only copy
	private static ConnectionFactory cf = new ConnectionFactory(1);
	
	//provide a single point of access to the connection factory
	public static ConnectionFactory getConnectionFactory() {
		return cf;
	}
	
	
	//this holds all of our connections
	//we could potential implement this as a connection pool
	private Connection [] conn;
	
	//this is a very basic factory
	//only a single param for configuration
	//if we are making a singleton
	//all of our constructores must be private
	//otherwise others could make new instances
	private ConnectionFactory(int numberOfConnections) {
		
		String url = System.getenv("DB_URL");
		String user = System.getenv("DB_USER");
		String password = System.getenv("DB_PASSWORD");
		try {
			this.conn = new Connection[numberOfConnections];
			for(int i = 0; i< this.conn.length; i++) {
				Connection conn = DriverManager.getConnection(url, user, password);
				this.conn[i] = conn;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	//if we were gonna implement for real
	//we would want to synchronize for multiple threads
	//add locks and .wait and .notify
	//I'm going to pretend only thread exists
	//and we will only ever need a single connection
	public Connection getConnection() {
		//TODO
		return this.conn[0];
	}
	
	public void releaseConnection(Connection conn) {
		//TODO
	}
	

}
