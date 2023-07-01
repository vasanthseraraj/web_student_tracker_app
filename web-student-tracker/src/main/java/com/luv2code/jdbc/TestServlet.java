package com.luv2code.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//Step 1: Get The PrintWriter
		PrintWriter out= response.getWriter();
		response.setContentType("text/plain");
		//Step 2: Get Connection to the Database
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs= null;
		try {
			myConn= dataSource.getConnection();
			//Step 3: Create a SQL Statements
			String sql = "select * from student";
			myStmt= myConn.createStatement();
			//step 4: Execute SQL Query
			myRs= myStmt.executeQuery(sql);
			//step 5: Process the result
			while(myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				out.println(firstName+"        "+lastName+"        "+email);
			}
			
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		
		
		
	}

}
