package com.luv2code.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class StudentDbUtil {
	private DataSource dataSource;

	public StudentDbUtil(DataSource theDataSource) {
		this.dataSource = theDataSource;
	}

	public List<Student> getStudents() throws Exception {
		List<Student> students = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {

			myConn = dataSource.getConnection();
			String sql = " SELECT * FROM STUDENT ORDER BY first_name";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");

				Student tempStudent = new Student(id, firstName, lastName, email);
				students.add(tempStudent);

			}
			return students;
		} finally {
			close(myConn, myStmt, myRs);

		}

	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
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

	public void addStudent(Student theStudent) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			// getting our Connection
			myConn = dataSource.getConnection();
			// create sql for insert
			String sql = "INSERT INTO STUDENT " + " (first_name, last_name, email) " + "values (?, ?, ?)";
			// set the param values for the student
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			// excute the sql insert
			myStmt.execute();

		} finally {
			close(myConn, myStmt, null);
		}
	}

	public Student getStudent(String theStudentId) throws Exception {
		Student theStudent = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		try {
			// convert student id to int
			studentId = Integer.parseInt(theStudentId);
			// get connection
			myConn = dataSource.getConnection();
			// create sql query to get selected student
			String sql = "SELECT * FROM STUDENT WHERE ID=?";
			// create prepared student
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1, studentId);
			// execute statement
			myRs = myStmt.executeQuery();
			// retrieve data from resultset row
			if (myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");

				theStudent = new Student(studentId, firstName, lastName, email);
			} else {
				throw new Exception("Invalid ID :" + studentId);
			}
			return theStudent;
		} finally {
			close(myConn, myStmt, myRs);

		}
	}

	public void editStudents(Student theStudent) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		// get Db Connection
		try {
			myConn = dataSource.getConnection();
			// create Sql Update Statement
			String sql = "UPDATE STUDENT " + "set first_name=?, last_name=?, email=? " + "where id=?";
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
 			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());
			// execute SQl statement
			myStmt.execute();
		} finally {
			close(myConn, myStmt, null);
		}

	}

	public void deleteStudents(String theStudentId)throws Exception {
		Connection myConn=null;
		PreparedStatement myStmt=null;
		try {
			//convert studentId from String into Int
			int studentId=Integer.parseInt(theStudentId);
			//Getting the connetion to database
			myConn= dataSource.getConnection();
			//create sql to delete the student
			String sql= "Delete from student where id=?"; 
			//prepare Statement
			myStmt= myConn.prepareStatement(sql);
			//set params
			myStmt.setInt(1, studentId);
			//execute Sql Statement
			myStmt.execute();
			
			
		}finally {
			close(myConn,myStmt,null);
			
		}
		
		
	}
}
