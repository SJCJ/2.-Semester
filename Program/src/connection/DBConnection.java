package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection
{
	public static void main(String args[])
	  {
  try 
	{
    
    //Register the JDBC driver for MySQL.
    Class.forName("com.mysql.jdbc.Driver");

    //Define URL of database server for database named test_hotel
	  // on the localhost with the default port number 3306.
    String url = "jdbc:mysql://172.16.23.252:3306/mydb";

    //Get a connection to the database for a user named root with password admin
    Connection con = DriverManager.getConnection(url,"testUser","kea13");

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