package viewer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import model.QueryMethods;

import com.mysql.jdbc.ResultSetMetaData;

public class MainView
{
	public static void main(String[] args)
	{
		QueryMethods.startUp();
	}
	private final JFrame frame = new JFrame("Test");
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public MainView(JTable table, final JTable table2)
	{
		try
		{
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
		    {
		        if ("Nimbus".equals(info.getName()))
		        {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		}
		catch (Exception e)
		{
		   
		}
		
		frame.setSize(400,600);
		frame.getContentPane().setLayout(null);
		
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(10, 11, 364, 539);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Historik", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scp = new JScrollPane(table);
		
		panel_1.add(scp, BorderLayout.CENTER);
		
		table.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent me)
			{
				JTable table =(JTable) me.getSource();
				Point p = me.getPoint();
				int row = table.rowAtPoint(p);
				if (me.getClickCount() == 2)
				{
					if(row >= 0)
					{	
					tabbedPane.setSelectedIndex(1);
					int selectedRowIndex = table.getSelectedRow();
					int selectedColumnIndex = table.getSelectedColumn();
					String selectedObject = (String) table.getModel().getValueAt(selectedRowIndex, selectedColumnIndex);
					
					int i = QueryMethods.getOfferInt(selectedObject);
					QueryMethods.getOffers(selectedObject, table2, i);
						
					}
				}
			}
		});
			
		
		JPanel south = new JPanel(new FlowLayout());
		
		JButton btnNewButton = new JButton("Tilføj bon kode");
		JButton btnNewButton_1 = new JButton("Slet fra historik");
		south.add(btnNewButton);
		south.add(btnNewButton_1);
		panel_1.add(south, BorderLayout.SOUTH);
		
		JLabel lblWelcomeToThe = new JLabel("Velkommen til din Historik");
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblWelcomeToThe, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Tilbud", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JButton btnKb = new JButton("K\u00D8B!");
		panel_2.add(btnKb, BorderLayout.SOUTH);
		
		table2.setFillsViewportHeight(true);
		table2.setColumnSelectionAllowed(true);
		table2.setCellSelectionEnabled(true);
		JScrollPane scp2 = new JScrollPane(table2);
		panel_2.add(scp2, BorderLayout.CENTER);
		
		JLabel lblHerKanDu = new JLabel("Her kan du se dine Tilbud :)");
		lblHerKanDu.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblHerKanDu, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Destination", null, panel_3, null);
		panel_3.setLayout(new GridLayout(3, 4, 0, 0));
		
		JLabel lblButikkensNavn = new JLabel("Butikkens navn: ");
		panel_3.add(lblButikkensNavn);
		
		JLabel lblNewLabel = new JLabel("navn fra database");
		panel_3.add(lblNewLabel);
		
		JLabel lblbningstider = new JLabel("\u00C5bningstider: ");
		panel_3.add(lblbningstider);
		
		JLabel lblbningstiderFraDatabase = new JLabel("\u00E5bningstider fra database");
		panel_3.add(lblbningstiderFraDatabase);
		
		JLabel lblAdresse = new JLabel("Adresse: ");
		panel_3.add(lblAdresse);
		
		JLabel lblAdresseFraDatabase = new JLabel("Adresse fra database");
		panel_3.add(lblAdresseFraDatabase);
		frame.setVisible(true);
	}
	
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
}
