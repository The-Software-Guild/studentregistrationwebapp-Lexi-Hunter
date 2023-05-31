package com.softra.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softra.dao.StudentDAO;
import com.softra.dao.StudentDAOImpl;
import com.softra.model.Student;

/**
 * Servlet implementation class StudentRegistrationServlet
 */
public class StudentRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StudentDAOImpl dao;
	ServletContext servletContext;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentRegistrationServlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
        super.init();
        servletContext = getServletContext();
        dao = new StudentDAOImpl(servletContext);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Inside doGet of StudentRegistrationServlet");
		
		RequestDispatcher rd = request.getRequestDispatcher("/Index.html");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("Inside doPost of StudentRegistrationServlet");
		
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		
		Student student = new Student(name, age, mobile, address);
		
		dao.create(student);
		
		request.setAttribute("student", student);
		
		RequestDispatcher rd = request.getRequestDispatcher("/success.jsp");
		rd.forward(request, response);
	}

}
