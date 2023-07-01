package com.luv2code.jdbc.assignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.luv2code.jdbc.Student;

public class UserDatabase {
	private DataSource dataSource;


public UserDatabase(DataSource theDataSource) {
	this.dataSource = theDataSource;
}






private void close(Connection myConn, PreparedStatement myStmt, ResultSet myRs) {
	try {
		if (myRs != null) {
			myRs.close();
		}
		if (myStmt != null) {
			myStmt.close();
		}
		if (myConn != null) {
			myConn.close();
		}
	} catch (Exception e) {
		e.printStackTrace();

	}

}


public void addUser(User theUser) throws Exception{
	// TODO Auto-generated method stub
	Connection myConn = null;
	PreparedStatement myStmt = null;
	try {
		// getting our Connection
		myConn = dataSource.getConnection();
		// create sql for insert
		String sql = "INSERT INTO user " + " (userid, first_name, last_name, email, mobile, age, lang, country) " + "values (?, ?, ?, ?, ?, ?, ?, ?)";
		// set the param values for the student
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, theUser.getUserId());
		myStmt.setString(2, theUser.getFirstName());
		myStmt.setString(3, theUser.getLastName());
		myStmt.setString(4, theUser.getEmail());
		myStmt.setString(5, theUser.getMobile());
		myStmt.setString(6, theUser.getAge());
		myStmt.setString(7, theUser.getLang());
		myStmt.setString(8, theUser.getCountry());
		
		// excute the sql insert
		myStmt.execute();
		

	} finally {
		close(myConn, myStmt, null);
	}
}
	
}


	
	


