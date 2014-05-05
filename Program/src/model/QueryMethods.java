package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;

import controller.ItemCont;
import net.proteanit.sql.DbUtils;
import viewer.MainView;


public class QueryMethods 
{
	private static PreparedStatement pstmt;
	private static Statement stmt;
    private static String sql;
    private static String url = "jdbc:mysql://10.111.180.4:3306/mydb";
    private static String user = "testUser";
    private static String password = "kea13";
    private static Connection con = null;
    //DriverManager.getConnection(url, user, password);
    private static ResultSet rs = null;
    //stmt.executeQuery(sqlString);
    private static JTable table;
    private static JTable table2;
    
    public static void getOffers(String string, JTable table, int offer)
    {
    	
    	sql = "select item, description, price from item where offer = " + offer +" and not item = '" + string + "'";
    	try
		{
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static int getOfferInt(String string)
    {
    	String sql1 = "select offer from item where item = '" + string + "'";
    	int offer = -1;
    	
    	try
    	{	
    		con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			pstmt = con.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				offer = rs.getInt("offer");
			}
    	}
    	catch(Exception e)
    	{
    		System.out.println("Uups" + e);
    	}
    	return offer;
    }
	
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
		    table2 = new JTable();
		    new MainView(table, table2);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
