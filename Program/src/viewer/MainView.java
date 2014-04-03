package viewer;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainView
{
	public static void main(String[] args)
	{
		new MainView();
	}
	
	private JPanel panel = new JPanel();
	private JTabbedPane jtp = new JTabbedPane();
	private JFrame frame = new JFrame("Test");
	private JLabel label1 = new JLabel("Liste over betalte produkter");
	private String[] data = {"GPS", "TV", "Køleskab", "Bukser"};
	private JList list = new JList(data);
	
	public MainView()
	{
		frame.setSize(400,600);
		frame.setLayout(new FlowLayout());
		panel.add(label1);
		
		jtp.addTab("Historik", label1);
		jtp.addTab("Kort", list);
		frame.add(jtp);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	}
}
