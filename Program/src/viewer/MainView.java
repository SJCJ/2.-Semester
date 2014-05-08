package viewer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import model.Persistance;
import model.QueryMethods;
import model.UpdateJTable;
import controller.UserHistory;

public class MainView extends DefaultTableModel
{
	private final String[] cities = {"København", "Århus"};
	private static UserHistory uh;
	
	public static void main(String[] args)
	{
		startUp();
	}
	private final JFrame frame = new JFrame("Brugernes del");
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public MainView(final JTable table, final JTable table2, final UserHistory uh)
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
		UpdateJTable.updateJTable(table, uh);
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
					int selectedColumnIndex = 0;
					String selectedObject = (String) table.getModel().getValueAt(selectedRowIndex, selectedColumnIndex);
					
					int i = QueryMethods.getOfferInt(selectedObject);
					QueryMethods.getOffers(selectedObject, table2, i);
					}
				}
			}
		});
			
		JPanel south = new JPanel(new FlowLayout());
		
		final JTextField idField = new JTextField(5);
		
		JButton btnNewButton = new JButton("Tilføj produkt");
		btnNewButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String id = idField.getText();
				uh.addItem(id);
				Persistance.save(uh);
				UpdateJTable.updateJTable(table, uh);
			}
		});
		
		JButton btnNewButton_1 = new JButton("Slet fra historik");
		btnNewButton_1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int selectedRowIndex = table.getSelectedRow();
				int selectedColumnIndex = 0;
				Object selectedObject = table.getModel().getValueAt(selectedRowIndex, selectedColumnIndex);
				((DefaultTableModel) table.getModel()).removeRow(selectedRowIndex);
			}
		});
		south.add(idField);
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
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Destination", null, panel_4, null);
		panel_4.setLayout(new BorderLayout());
		panel_3.setLayout(new GridLayout(3, 4, 0, 0));
		
		JLabel lblButikkensNavn = new JLabel("Butikkens navn: ");
		panel_3.add(lblButikkensNavn);
		
		final JLabel lblNewLabel = new JLabel("navn fra database");
		panel_3.add(lblNewLabel);
		
		JLabel lblbningstider = new JLabel("\u00C5bningstider: ");
		panel_3.add(lblbningstider);
		
		final JLabel lblbningstiderFraDatabase = new JLabel("\u00E5bningstider fra database");
		panel_3.add(lblbningstiderFraDatabase);
		
		JLabel lblAdresse = new JLabel("Adresse: ");
		panel_3.add(lblAdresse);
		
		final JLabel lblAdresseFraDatabase = new JLabel("Adresse fra database");
		panel_3.add(lblAdresseFraDatabase);
		
		btnKb.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int selectedRowIndex = table2.getSelectedRow();
				int selectedColumnIndex = 0;
				String selectedObject = (String) table2.getModel().getValueAt(selectedRowIndex, selectedColumnIndex);
				int i = QueryMethods.getItemId(selectedObject);
				QueryMethods.getStores(selectedObject, i, lblNewLabel, lblbningstiderFraDatabase, lblAdresseFraDatabase);
				tabbedPane.setSelectedIndex(2);
			}
		});
		JComboBox comboBox = new JComboBox(cities);
		panel_4.add(comboBox, BorderLayout.NORTH);
		panel_4.add(panel_3);
		frame.setVisible(true);
	}
	public static void startUp()
	{
		uh = Persistance.load();
		JTable table = new JTable();
		JTable table2 = new JTable();
		new MainView(table, table2, uh);
	}
}
