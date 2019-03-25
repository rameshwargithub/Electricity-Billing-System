import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JPanel implements ActionListener{
	
	JLabel lbuser,lbpass;
	JTextField txuser,txpass;

	JButton btsubmit,btnchange,btnforgot;
	
	JPanel p1;
	JPanel pn,ps,pe,pw,pc;
	public Login()
	{
		setVisible(true);
		setSize(400, 300);

		//setTitle("Login Electrcity...");
		p1=new JPanel();
		
		pn=new JPanel();
		pc=new JPanel();
		pe=new JPanel();
		pw=new JPanel();
		ps=new JPanel();
		
		
		pn.add(new JLabel("ELECTRCITY MANAGEMENT SYSTEM"));
		
	
		pn.setBackground(Color.cyan);
		pc.setLayout(new GridLayout(3, 2));

		
		lbuser=new JLabel("User");
		lbpass=new JLabel("Password");
		
		txuser=new JTextField(20);
		txpass=new JTextField(20);
		
		btsubmit=new JButton("Submit");
		btnchange=new JButton("Change Password");
		btnforgot=new JButton("Forget");
		
		p1.setLayout(new GridLayout(1, 2));
	
		p1.add(btnchange);
		p1.add(btnforgot);
		
		setLayout(new BorderLayout());
		
		pc.add(lbuser);
		pc.add(txuser);
		
		pc.add(lbpass);
		pc.add(txpass);
		
		pc.add(btsubmit);
		pc.add(p1);
		
		
		add(pc, BorderLayout.CENTER);
		add(pn,BorderLayout.NORTH);
		add(pe, BorderLayout.EAST);
		add(ps,BorderLayout.SOUTH);
		add(pw, BorderLayout.WEST);
		
		btsubmit.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent Ae)
	{
		Object src=Ae.getSource();
		
		String user=txuser.getText();
		String pass=txpass.getText();
		
		if(src==btsubmit)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ElectrcityDb","root","");
				
				Statement stmt=con.createStatement();
				
				PreparedStatement pstmt=con.prepareStatement("select count(*) from usertb where user=? and password=?");
				
				pstmt.setString(1, user);
				pstmt.setString(2, pass);
				ResultSet rs=pstmt.executeQuery();
				
				int c=0;
				
				while(rs.next())
				{
					c=rs.getInt(1);
				}
				
				if(c==1)
				{
					JOptionPane.showMessageDialog(null, "Sucessfully login");
				}
				else
					JOptionPane.showMessageDialog(null, "Invalid Password","title", JOptionPane.ERROR_MESSAGE, null);
				
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

}

