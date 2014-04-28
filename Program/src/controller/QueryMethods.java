package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;

import viewer.MainView;


public class QueryMethods 
{
	
	private static Statement stmt;
    private static String sql;
    private static String url = "jdbc:mysql://10.111.180.5:3306/mydb";
    private static String user = "testUser";
    private static String password = "kea13";
    private static Connection con = null;
    //DriverManager.getConnection(url, user, password);
    private static ResultSet rs = null;
    //stmt.executeQuery(sqlString);
    private static JTable table;
    
    	
    
	
	
	public static void addItem()
	{
		sql = "";
	}
	
	public static void startUp()
	{
		sql = "select item, description, price from item where item_id <= 5";
		
		try
		{
			con = DriverManager.getConnection(url, user, password);
		    stmt = con.createStatement();
		    rs = stmt.executeQuery(sql);
		    table = new JTable(ItemCont.buildTableModel(rs));
		    new MainView(table);
		}
		catch(Exception e)
		{
			System.out.println("Couldnt connect to Database");
		}
	}
}
