package com.softra.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import com.softra.model.Student;

public class StudentDAOImpl implements StudentDAO {

	private ServletContext servletContext;
	
	public StudentDAOImpl(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	private Connection openConnection() {
		
		String dbURL = servletContext.getInitParameter("dbURL");
        String dbUser = servletContext.getInitParameter("dbUser");
        String dbPassword = servletContext.getInitParameter("dbPassword");
		
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); // type-4 driver is a registered with DriverManager
			System.out.println("MySQL Driver registered with DriverManager");
			con = DriverManager.getConnection(dbURL, dbUser, dbPassword);
			System.out.println(con);
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL suitable driver not found");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return con;
	}
	
	private void closeConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// JDBC API code in order to connect to underlying storage
	@Override
	public void create(Student student) {
		
		String name = student.getName();
		int age = student.getAge();
		String mobile = student.getMobile();
		String address = student.getAddress();
		
		Connection con = openConnection();
		
		try {
			
			String sql = "INSERT INTO student (name, age, mobile, address) VALUES (?, ?, ?, ?)";
			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setString(1, name);
			pstat.setInt(2, age);
			pstat.setString(3, mobile);
			pstat.setString(4, address);
			int n = pstat.executeUpdate();
			
			
			System.out.println(sql);
			System.out.println("Number of rows inserted: " + n);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		closeConnection(con);
	}

	@Override
	public List<Student> retrieveAll() {
		
		List<Student> students = new ArrayList<>();
		Connection con = openConnection();
		
		String sql = "SELECT * FROM student;";
		try {
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String mobile = rs.getString("mobile");
				String address = rs.getString("address");
				
				Student student = new Student(name, age, mobile, address);
				
				students.add(student);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		closeConnection(con);
		
		return students;
	}

	@Override
	public Student retrieve(int id) {
		
		Connection con = openConnection();
		Student student = null;
		String sql = "SELECT * FROM student WHERE id=?";
		try {
			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setInt(1, id);
			ResultSet rs = pstat.executeQuery();
			
			while(rs.next()) {
				int stuId = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String mobile = rs.getString("mobile");
				String address = rs.getString("address");
				
				student = new Student(name, age, mobile, address);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		closeConnection(con);
		
		return student;
	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
