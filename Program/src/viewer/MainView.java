package viewer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;

public class MainView
{
	public static void main(String[] args)
	{
		new MainView();
	}
	
	private final JPanel panel = new JPanel();
	private final JTabbedPane jtp = new JTabbedPane();
	private final JFrame frame = new JFrame("Test");
	private final JTable table;
	private final JTable table_1;
	
	
	public MainView()
	{
		frame.setSize(400,600);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(10, 11, 364, 539);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Historik", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		panel_1.add(table, BorderLayout.CENTER);
		
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
		
		table_1 = new JTable();
		table_1.setFillsViewportHeight(true);
		table_1.setColumnSelectionAllowed(true);
		table_1.setCellSelectionEnabled(true);
		panel_2.add(table_1, BorderLayout.CENTER);
		
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
