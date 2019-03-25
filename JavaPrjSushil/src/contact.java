import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class contact extends JPanel
{
	JLabel lb,lbnumber,lbname;
	JScrollPane jsp;
	JPanel pn,ps,pw,pe,pc,pn1,pn2;
	public contact()
	{
		setVisible(true);
		setSize(300,300);
		pn=new JPanel();
		pc=new JPanel();
		pe=new JPanel();
		pw=new JPanel();
		ps=new JPanel();
		pn1=new JPanel();
		pn2=new JPanel();
	
		Font f=new Font("comics scene",Font.BOLD,20);
		setFont(f);

		
		lbname=new JLabel("Make a call to electrcity Bord");
		lbnumber=new JLabel("7976076925");
		
		lbname.setFont(f);
		lbnumber.setFont(f);
		
		setLayout(new BorderLayout());
		
		lb=new JLabel(new ImageIcon("contact.jpg"));
		jsp=new JScrollPane(lb,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
		pn.setLayout(new GridLayout(2, 1));
		pn1.add(lbname);
		pn2.add(lbnumber);
		pn.add(pn1);
		pn.add(pn2);
		pc.add(jsp);
		
		add(pn,BorderLayout.NORTH);
		add(ps,BorderLayout.SOUTH);
		add(pw,BorderLayout.WEST);
		add(pe,BorderLayout.EAST);
		add(pc,BorderLayout.CENTER);
		
		
	}
	
}
