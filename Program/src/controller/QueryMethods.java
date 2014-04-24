package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class QueryMethods 
{
	
	private static Statement stmt;
    private static String sqlString;
    private static String url = "jdbc:mysql://172.16.23.252:3306/mydb";
    private static String user = "testUser";
    private static String password = "kea13";
    private static Connection con = null;
    //DriverManager.getConnection(url, user, password);
    private static ResultSet rs = null;
    //stmt.executeQuery(sqlString);
    
    	
    
	public static void selectionItems()
	{
		
		String query = "select * from item";
		 try 
			{    
		    //Register the JDBC driver for MySQL.
		    Class.forName("com.mysql.jdbc.Driver");
		    con = DriverManager.getConnection(url, user, password);
		    
		    stmt = con.createStatement();
		    
		    rs = stmt.executeQuery(query);
		    
		    //Display the URL and connection information
		    System.out.println("URL: " + url);
		    System.out.println("Connection: " + con);
			}
		  	catch(Exception e)
		  	{
		  		System.out.println("Couldnt connect to Database");
		  	}
		
	}
}
