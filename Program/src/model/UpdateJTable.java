package model;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.UserHistory;

public class UpdateJTable
{
	  public static void updateJTable(JTable table)
	  { 
		UserHistory uh = new UserHistory();
		uh = Persistance.load();
	    //JTable setup
	    DefaultTableModel model = new DefaultTableModel(); 
	    model.setColumnIdentifiers(new String[] { "Produktets navn", "Produktets beskrivelse", "Pris"});
	    model.setRowCount(uh.getIds().size());
	    int count = 0;
	    for (String item : uh.getIds()) 
	    {
	      model.setValueAt(QueryMethods.getItemName(item), count, 0);
	      model.setValueAt(QueryMethods.getItemDesc(item),  count, 1);
	      model.setValueAt(QueryMethods.getItemPrice(item), count, 2);
	      count++;
	    }
	    table.setModel(model);
	  }

}
