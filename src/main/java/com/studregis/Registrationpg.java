//THIS IS FOR REGISTRATION PAGE
package com.studregis;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/Registration")
public class Registrationpg extends HttpServlet {
	/**
	 * 
	 */
	private static String url="jdbc:mysql://localhost:3306/COLLEGE";
	private static String user="root";
	private static String password="system";
	 protected final String sql="INSERT INTO FORM VALUES(?,?,?,?,?,?,?,?,?,?)";
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String name=req.getParameter("sname");
		String father=req.getParameter("fname");
		String mother=req.getParameter("mname");
		String email=req.getParameter("email");
		String pnum=req.getParameter("pnum");
		String dob=req.getParameter("dob");
		String course=req.getParameter("course");
		int sem=Integer.parseInt(req.getParameter("sem"));
		long roll=Long.parseLong(req.getParameter("rollno"));
		String addrs=req.getParameter("addr");
		java.sql.Date sqlDob = java.sql.Date.valueOf(dob);
	    PrintWriter out=res.getWriter();
	   
	    //Driver loading
	    try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	System.out.println("Driver Loaded");
	    }catch(ClassNotFoundException e) {
	    	e.printStackTrace();
	    }
		// Connection establish
	    try {
	
	    	Connection con=DriverManager.getConnection(url,user,password);
	    	System.out.println("Connection successful");
	    	PreparedStatement stm=con.prepareStatement(sql);
	    	stm.setString(1,name );
	    	stm.setString(2, father);
	    	stm.setString(3,mother);
	    	stm.setString(4,email);
	    	stm.setString(5, pnum);
	    	stm.setDate(6, sqlDob);
	    	stm.setString(7,course);
	    	stm.setInt(8,sem);
	    	stm.setLong(9,roll);
	    	stm.setString(10, addrs);
	    	int row=stm.executeUpdate();
//	    	System.out.println("value comes by login page: "+umail);
	    	System.out.println("no of rows created "+row);
	    	if(row>0) {
	    		res.sendRedirect("success.html");
	    		}

	    	else {
	    		out.println("something went to wrong");
	    		out.print("Try Again! After sometime or");
	    	
	    		out.println("Login back "+" <a href='index.html'>Login</a>");
	    	}
	    	
	    	con.close();
	    	stm.close();
	    	
	    }catch(Exception e) {
	    	System.out.println("Exception form connection block");
	    	out.print(e);
	    }
	
	
	
	}
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
	doGet(req,res);
}
}
