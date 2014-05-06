package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;
import viewer.AdminGui;
import controller.ItemCont;


public class QueryMethods 
{
	private static PreparedStatement pstmt;
	private static Statement stmt;
    private static String sql;
    private static String url = "jdbc:mysql://10.111.180.2:3306/mydb";
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
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static int getItemId(String string)
    {
    	int id = -1;
    	sql = "select item_id from item where item ='" + string + "'";
    	try
    	{
    		con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				id = rs.getInt("item_id");
			}
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
    	
    	return id;
    }
    public static void getStores(String string, int i, JLabel label1, JLabel label2, JLabel label3)
    {
    	String storeName = "";
    	String openingHours1 = "";
    	String openingHours2 = "";
    	String address = "";
    	String openingHours = "";
    	
    	sql = "select store, opening_hours_1, opening_hours_2, address from store, address where item_id = " + i + " and address.address_id = store.Address_address_id;";
    	try
    	{
    		con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
    		while(rs.next())
    		{
    		storeName = rs.getString("store");
    		openingHours1 = rs.getString("opening_hours_1");
    		openingHours2 = rs.getString("opening_hours_2");
    		address = rs.getString("address");
    		
    		openingHours = openingHours1 + "-" + openingHours2;
    		}
    		label1.setText(storeName);
    		label2.setText(openingHours);
    		label3.setText(address);
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
    }
    public static int getOfferInt(String string)
    {
    	String sql1 = "select offer from item where item = '" + string + "'";
    	int offer = -1;
    	
    	try
    	{	
    		con = DriverManager.getConnection(url, user, password);
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
	public static void startUpAdmin()
	{
		sql = "select * from item";
		try
		{
			con = DriverManager.getConnection(url, user, password);
		    stmt = con.createStatement();
		    rs = stmt.executeQuery(sql);
		    table = new JTable(ItemCont.buildTableModel(rs));
		    new AdminGui(table);
		}
		catch(Exception e)
		{
			System.out.println("Yup"+e);
		}
	}
	public static void updateTableAdmin()
    {
    	String sql = "select * from item";
    	try
		{
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public static int getCategoryInt(String string)
    {
    	sql = "select category_id from category where category = '" + string + "'";
    	int id = -1;
    	
    	try
    	{	
    		con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				id = rs.getInt("category_id");
			}
    	}
    	catch(Exception e)
    	{
    		System.out.println("Uups" + e);
    	}
    	return id;
    }
	public static void adminAddItem(JTextField jtxt1, JTextField jtxt2,JTextField jtxt3,JTextField jtxt4,String string,JTextField jtxt5)
	{
		sql = "insert into item (item, description, release_date, price, category_id, offer)" + "values(?,?,?,?,?,?)";
		String item = jtxt1.getText();
		String descrip = jtxt2.getText();
		String date = jtxt3.getText();
		String price = jtxt4.getText();
		String offer = jtxt5.getText();
		try
		{
			con = DriverManager.getConnection(url, user, password);
		    stmt = con.createStatement();
		    PreparedStatement pstmt = con.prepareStatement(sql);
		    pstmt.setString(1, item);
		    pstmt.setString(2, descrip);
		    pstmt.setString(3, date);
		    pstmt.setString(4, price);
		    pstmt.setString(5, string);
		    pstmt.setString(6, offer);
		    
		    pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("uups" + e);
		}
	}
	public static void adminDelItem(JTextField jtxt1, JTextField jtxt2, String string)
	{
		
		String item = jtxt1.getText();
    	String price = jtxt2.getText();
    	sql = "delete from item where `item` = '" + item + "' and `price` = " + price +" and `category_id` = "+ string;
    	try
    	{
    		con = DriverManager.getConnection(url, user, password);
    		pstmt = con.prepareStatement(sql);
    		pstmt.executeUpdate();
    	}
    	catch(Exception e)
    	{
    		System.out.println("theres an error here" +e);
    	}
	}
	public static String getItemName(String id)
	{
		String itemName ="";
		sql ="select item from item where item_id =" + id;
		try
		{
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
    		while(rs.next())
    		{
    			itemName = rs.getString("item");
    		}
    		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return itemName;
		
	}
	public static String getItemDesc(String id)
	{
		String itemDesc ="";
		sql ="select item from item where item_id =" + id;
		try
		{
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
    		while(rs.next())
    		{
    			itemDesc = rs.getString("description");
    		}
    		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return itemDesc;
	}
	public static String getItemPrice(String id)
	{
		String itemPrice ="";
		sql ="select item from item where item_id =" + id;
		try
		{
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
    		while(rs.next())
    		{
    			itemPrice = rs.getString("price");
    		}
    		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return itemPrice;
	}
}
