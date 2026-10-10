package com.dbcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class DbConnection {
  private static final Dotenv dotenv=Dotenv.configure().
		   directory("C:\\Users\\hp\\eclipse-workspace\\Studlogin").load();
  private static final String url=dotenv.get("DB_URL");
  private static final String user=dotenv.get("DB_USER");
  private static final String password=dotenv.get("DB_PASSWORD");
  public static Connection getConnection() throws SQLException {
	  try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
	  }catch(ClassNotFoundException e) {
		  System.out.println(e);
	  }
	  return DriverManager.getConnection(url,user,password);
	 
  }
}
