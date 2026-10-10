//THIS SERVLET CODE FOR LOGIN FROM DB
package com.studlog;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class logpage extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	  String name=req.getParameter("email");
	  String pas=req.getParameter("pass");
	  
	  PrintWriter out=resp.getWriter();
	  System.out.println("EMAIL = [" + name + "]");
	  System.out.println("PASS = [" + pas + "]");
	  System.out.println("Executing login query...");

	   try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
	   }catch(ClassNotFoundException e) {
		   out.print(e);
	   }try {
		   String url="jdbc:mysql://localhost:3306/COLLEGE";
		   String user="root";
		   String pin="system";
		   Connection con=DriverManager.getConnection(url,user,pin);
			String  qry="SELECT EMAIl,PASS FROM STUD WHERE EMAIL=? AND PASS=?";
			  PreparedStatement stm=con.prepareStatement(qry);
			  stm.setString(1, name);
			  stm.setString(2,pas);
			  ResultSet set=stm.executeQuery();
			 	  System.out.println("perfectly executed");
			  
			  boolean isfound=set.next();
			  System.out.println("HAS ROW = " + isfound);
			  
			  if(isfound) {
				  resp.sendRedirect("regis.html");
			  }else {
				  System.out.print("login fail");
				 out.print("user and password incorrect");
			  }
  
			  con.close();
			  stm.close();
			  set.close();
		  }catch(Exception e) {
			  out.print(e);
	  }
		
  }  
	  

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	  doGet(req,resp);
  }
	
}
