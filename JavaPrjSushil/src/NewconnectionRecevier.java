import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JApplet;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewconnectionRecevier extends JPanel implements KeyListener,ItemListener
{
	JLabel lbname;
	JTextField txname;
	List cbname;
	JPanel pn,pc,pn1,pn2;
	public NewconnectionRecevier()
	{
		setVisible(true);
		setSize(600,300);
		Font f=new Font("comics scene",Font.BOLD,28);

		cbname=new List();
		cbname.add("select");
		lbname=new JLabel("Enter Your Name");
		lbname.setFont(f);
		txname=new JTextField(20); 
		pn=new JPanel();
		pn.setLayout(new GridLayout(2, 1));
		pc=new JPanel();
		pn1=new JPanel();
		pn2=new JPanel();
		pn1.setLayout(new GridLayout(1, 2));
		pn2.setLayout(new GridLayout(1, 2));
		pn1.add(lbname);
		pn1.add(txname);
		pn2.add(new JLabel(""));
		pn2.add(cbname);
		pn2.setVisible(false);
		pn.add(pn1);
		pn.add(pn2);
		setLayout(new BorderLayout());
		add(pn, BorderLayout.NORTH);
		add(pc, BorderLayout.CENTER);
		txname.addKeyListener(this);
		cbname.addItemListener(this);
	}
	@Override
	public void keyPressed(KeyEvent Ke) {
		// TODO Auto-generated method stub
		pn2.setVisible(true);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ElectrcityDb","root","");
			
			Statement stmt=con.createStatement();
			PreparedStatement pstmt=con.prepareStatement("select name from connectiontb where name LIKE ?");
			pstmt.setString(1, "%"+txname.getText()+Ke.getKeyChar()+"%");
			ResultSet rs=pstmt.executeQuery();

			cbname.removeAll();
			cbname.add("select");
			while(rs.next())
			{
				String s=rs.getString("name");
				cbname.add(s);
			}
			con.close();
			
			
		} 
			catch (ClassNotFoundException e) 
			{
			
				e.printStackTrace();
			} 
			catch (SQLException e)
			{
				
				e.printStackTrace();
			}



		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		int index=cbname.getSelectedIndex();	
		if(index!=0)
		{
				txname.setText(cbname.getItem(index));
				pn2.setVisible(false);
		}
		String mobile=JOptionPane.showInputDialog("Enter Your Mobile Number");

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ElectrcityDb","root","");
			
			Statement stmt=con.createStatement();
			PreparedStatement psmt=con.prepareStatement("select * from connectiontb where mobile=?");
			psmt.setString(1,mobile);
			ResultSet rs=psmt.executeQuery();
			while(rs.next())
			{
				//String r=rs.getString("Kno");
				String load=rs.getString("AppliedLoad");
				int k=load.indexOf("-");
				String load1=load.substring(k+1,load.length());
				System.out.println(load1);
				if(load1.equals("kwh"))
					JOptionPane.showMessageDialog(null, "Congratulations you have got the new connection few time ago");
				else
					JOptionPane.showMessageDialog(null,"Sorry still Your connection in processing","about connection",JOptionPane.ERROR_MESSAGE);
				
			}
			con.close();
			
			
		} 
			catch (ClassNotFoundException e) 
			{
			
				e.printStackTrace();
			} 
			catch (SQLException e)
			{
				
				e.printStackTrace();
			}

		

	}
}
