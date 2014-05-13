package viewer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import model.QueryMethods;

public class AdminGui
{
private static JFrame frame;
private static String[] categories = {"Elektronik", "Textil", "Køkken", "Dyr", "Hus & Have", "Børn & Baby", "Sport & Fritid", "Hvidevarer", "Spiritus", "Fødevarer"};
	
	public static void main(String[] args)
	{
		QueryMethods.startUpAdmin();
	}
    
	public AdminGui(JTable table)
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
		
		frame = new JFrame("Administration del");
		frame.setSize(800,600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JPanel west = new JPanel();
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setViewportView(table);
		panel.add(scrollPane, BorderLayout.CENTER);		 
		scrollPane.setVisible(true);
		
		west.add(panel);
		
		JPanel northEast = new JPanel(new BorderLayout());
		
		JPanel east = new JPanel(new GridLayout(0,2));
		
		JLabel productLabel = new JLabel("Tilføj Produkt: ");
		JLabel empyLabel = new JLabel("");
		JLabel itemLabel = new JLabel("Produktets navn: ");
		final JTextField itemField = new JTextField(10);
		JLabel descLabel = new JLabel("Produktets beskrivelse: ");
		final JTextField descField = new JTextField(10);
		JLabel relLabel = new JLabel("Produktets udgivelses dato: ");
		final JTextField relField = new JTextField("YYYY-MM-DD", 10);
		JLabel priceLabel = new JLabel("Produktets pris: ");
		final JTextField priceField = new JTextField(10);
		JLabel cateLabel = new JLabel("Produktets kategori: ");
		final JComboBox cateBox = new JComboBox(categories);
		JLabel offerLabel = new JLabel("Produktets tilbuds id: ");
		final JTextField offerField = new JTextField(10);
		JLabel emptylabel = new JLabel("");
		JButton addItem = new JButton("Tilføj Produktet");
		
		east.add(productLabel);
		east.add(empyLabel);
		east.add(itemLabel);
		east.add(itemField);
		east.add(descLabel);
		east.add(descField);
		east.add(relLabel);
		east.add(relField);
		east.add(priceLabel);
		east.add(priceField);
		east.add(cateLabel);
		east.add(cateBox);
		east.add(offerLabel);
		east.add(offerField);
		east.add(addItem);
		east.add(emptylabel);
		
		JLabel delLabel = new JLabel("Slet Produkt: ");
		JLabel emptyLabel = new JLabel("");
		JLabel itemLabel2 = new JLabel("Produktets navn: ");
		final JTextField itemField2 = new JTextField(10);
		JLabel priceLabel2 = new JLabel("Produktets pris: ");
		final JTextField priceField2 = new JTextField(10);
		JLabel cateLabel2 = new JLabel("Produktets kategori: ");
		final JComboBox cateBox2 = new JComboBox(categories);
		JButton delItem = new JButton("Slet Produktet");
		JLabel emptyLabel2 = new JLabel("");
		
		east.add(delLabel);
		east.add(emptyLabel);
		east.add(itemLabel2);
		east.add(itemField2);
		east.add(priceLabel2);
		east.add(priceField2);
		east.add(cateLabel2);
		east.add(cateBox2);
		east.add(delItem);
		east.add(emptyLabel2);
		
		northEast.add(east, BorderLayout.CENTER);
		
		mainPanel.add(west, BorderLayout.WEST);
		mainPanel.add(northEast, BorderLayout.EAST);
		frame.add(mainPanel);
		frame.pack();
		
		addItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//Checks status of JComboBox and asks the database for the category_id
				String s = cateBox.getSelectedItem().toString();
				int id = QueryMethods.getCategoryInt(s);
				String id2 = String.valueOf(id);
				//Adds item to database
				QueryMethods.adminAddItem(itemField, descField, relField, priceField, id2, offerField);
				QueryMethods.updateTableAdmin();
			}
		});
		delItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//Checks status of JComboBox and asks the database for the category_id
				String s = cateBox2.getSelectedItem().toString();
				int id = QueryMethods.getCategoryInt(s);
				String id2 = String.valueOf(id);
				//Deletes item in database
				QueryMethods.adminDelItem(itemField2, priceField2, id2);
				QueryMethods.updateTableAdmin();
			}
		});		
	}
}
