package com.luv2code.jdbc.assignment;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.luv2code.jdbc.Student;
import com.luv2code.jdbc.StudentDbUtil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDatabase userDatabase;
	@Resource(name = "jdbc/web_student_tracker")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			userDatabase = new UserDatabase(dataSource);
		} catch (Exception exc) {
			throw new ServletException();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// read the "command" parameter
			String insertDetails = request.getParameter("insert");
			// if the command is missing the default to listing the students...
			if (insertDetails == null) {
				insertDetails = "INSERT";
			}
			// route the apppropriate method
			switch (insertDetails) {

			case "INSERT":
				addUser(request, response);
				break;

			default:
				addUser(request, response);
				
				

			}

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response)throws Exception {
		String userId=request.getParameter("userId");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String age = request.getParameter("age");
		String lang = request.getParameter("lang");
		String country = request.getParameter("country");
		
	
		User theUser = new User(userId,firstName, lastName, email,mobile,age,lang,country);
		
		userDatabase.addUser(theUser);
	

	}

}
