//THIS CODE IS FOR CREATE NEW ACCOUNT
package com.signin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dbcon.DbConnection;

//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Studsigin extends HttpServlet {
	private static final long serialVersionUID = 1L;
 protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
	 String stud=req.getParameter("email");
	 String pas=req.getParameter("password");
	 PrintWriter out=res.getWriter();
	
//	   try {
//		   Class.forName("com.mysql.cj.jdbc.Driver");
//	   }catch(ClassNotFoundException e) {
//		   out.print(e);
//	   }try {
//		   String url="jdbc:mysql://localhost:3306/COLLEGE";
//		   String user="root";
//		   String pin="system";
//		   Connection con=DriverManager.getConnection(url,user,pin);
	 try {
	       Connection con=DbConnection.getConnection();
	       System.out.println("connetion established");
     String qry="INSERT INTO USER(EMAIL,PASS) VALUES(?,?)";
       System.out.println("Email received = " + stud);
       System.out.println("Password received = " + pas);
      
		   PreparedStatement stm=con.prepareStatement(qry);
		   stm.setString(1,stud);
		   stm.setString(2, pas);
		   int row=stm.executeUpdate();
		   System.out.println("row created "+row);		   
		
			if(row>0) {
			   System.out.println("if condition works data inserted");
			   res.sendRedirect("index.html");
		   }else {
			   out.print("data not inserted");
		   } 
   con.close();
stm.close();

		   
	   }catch(SQLException e) {
		   out.print("Your exception "+e);
	   }
 }
 protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
	 doGet(req,res);
 }
}
