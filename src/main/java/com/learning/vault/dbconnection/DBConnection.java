package com.learning.vault.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static void main(String args[]) {
		//127.0.0.1:3306
		String jdbcUrl = "jdbc:mysql://localhost:3306/Learners_academy?useSSL=false";
		String user = "simplilearn_learner";
		String password ="abc@123";

		try {

			System.out.println("Connecting to Database : "+jdbcUrl);	

			Connection conn = DriverManager.getConnection(jdbcUrl,user,password);

			System.out.println("Connection successful !!! ");

			conn.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
