package com.luv2code.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDbUtil studentDbUtil;
	@Resource(name = "jdbc/web_student_tracker")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		} catch (Exception exc) {
			throw new ServletException();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			// if the command is missing the default to listing the students...
			if (theCommand == null) {
				theCommand = "LIST";
			}
			// route the apppropriate method
			switch (theCommand) {
			case "LIST":
				listStudents(request, response);
				break;
			case "ADD":
				addStudents(request, response);
				break;
			case "LOAD":
				loadStudents(request, response);
				break;
			case "EDIT":
				editStudents(request, response);
				break;
			case "DELETE":
				deleteStudents(request,response);
				break;
			default:
				listStudents(request, response);
			}

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void deleteStudents(HttpServletRequest request, HttpServletResponse response)throws Exception {
		//read studentid from form data
		String theStudentId=request.getParameter("studentId");
		/*
		 * String firstname=request.getParameter("firstName"); String
		 * lastname=request.getParameter("lastName"); String
		 * email=request.getParameter("email");
		 */
		
		//delete student from database
		studentDbUtil.deleteStudents(theStudentId);
		//send back to "list-students.jsp"
		listStudents(request,response);
	}

	private void editStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student info from form data
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		// create a new student object
		Student theStudent = new Student(id, firstName, lastName, email);
		// perform update on database
		studentDbUtil.editStudents(theStudent);
		// send them back to "list-students.jsp"
		listStudents(request, response);

	}

	private void loadStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read Student id from form data
		String theStudentId = request.getParameter("studentId");
		// get Student from database (db util)
		Student theStudent = studentDbUtil.getStudent(theStudentId);
		// place student in the request attribute
		request.setAttribute("THE_STUDENT", theStudent);
		// send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/edit-student-form.jsp");
		dispatcher.forward(request, response);

	}

	private void addStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read Student info from the form
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		// creating new student object
		Student theStudent = new Student(firstName, lastName, email);
		// add the student to the database
		studentDbUtil.addStudent(theStudent);
		// send back to main page (the student list)
		listStudents(request, response);
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Student> students = studentDbUtil.getStudents();
		request.setAttribute("STUDENT_LIST", students);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);

	}
}