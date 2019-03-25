import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class homepc extends JPanel 
{
	JLabel lb;
	JScrollPane jsp;
	
	public homepc()
	{
		setVisible(true);
		setSize(300,300);
		
		lb=new JLabel(new ImageIcon("hme.jpg"));
		jsp=new JScrollPane(lb,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(jsp);
		
	}
	
}
