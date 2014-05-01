package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;

@SuppressWarnings("serial")
public class ItemCont extends DefaultTableModel
{
	private static ResultSetMetaData metaData;
	private static int columnCount;
	private static Vector<String> columnNames;
	private static Vector<Vector<Object>> data;
	private static Vector<Object> vector;
	
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    metaData = (ResultSetMetaData) rs.getMetaData();

	    // names of columns
	    columnNames = new Vector<String>();
	    columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
}
