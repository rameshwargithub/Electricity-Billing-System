import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegistrationForm extends JPanel implements ActionListener
{
	JLabel lbuser,lbpass,lbques,lbans,lbhead;
	JTextField txuser,txpass,txques,txans;
	JComboBox cbques;
	JButton btsubmit,btlogin;
	
	JPanel pn,ps,pe,pw,pc;
	
	public RegistrationForm()
	{
		setVisible(true);
		setSize(300, 300);
		//setTitle("Registration Electrcity...");

		
		Font f=new Font("arial",Font.BOLD,28);
		this.setFont(f);

		
		pn=new JPanel();
		pc=new JPanel();
		pe=new JPanel();
		pw=new JPanel();
		ps=new JPanel();
		
		pn.setFont(f);
		pn.add(new JLabel("ELECTRCITY MANAGEMENT SYSTEM"));
		
		
	
		pn.setBackground(Color.cyan);
		pc.setLayout(new GridLayout(5, 2));
		
		lbuser=new JLabel("User");
		lbpass=new JLabel("Password");
		lbques=new JLabel("Ques");
		lbans=new JLabel("Ans");
		
		txuser=new JTextField(20);
	
		txpass=new JTextField(20);
		txans=new JTextField(20);
		
		btsubmit=new JButton("Submit");
		btlogin=new JButton("LogIn");
		cbques=new JComboBox();
		
		cbques.addItem("select");
		cbques.addItem("Your nik Name");
		cbques.addItem("Your DOB");
		cbques.addItem("Your friend name");
		
		setLayout(new BorderLayout());
		
		pc.add(lbuser);
		pc.add(txuser);
		
		pc.add(lbpass);
		pc.add(txpass);
		
		pc.add(lbques);
		pc.add(cbques);
		
		pc.add(lbans);
		pc.add(txans);
		
		pc.add(btsubmit);
		pc.add(btlogin);
		
		add(pc, BorderLayout.CENTER);
		add(pn,BorderLayout.NORTH);
		add(pe, BorderLayout.EAST);
		add(ps,BorderLayout.SOUTH);
		add(pw, BorderLayout.WEST);
		
		btsubmit.addActionListener(this);
		btlogin.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent Ae) {
		// TODO Auto-generated method stub
		
		Object src=Ae.getSource();
		
		String user=txuser.getText();
		String pass=txpass.getText();
		String ques=cbques.getSelectedItem().toString();
		String ans=txans.getText();
		
		if(src==btlogin)
			new Login();
		
		if(src==btsubmit)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				
				Statement stmt=con.createStatement();
				
				stmt.executeUpdate("create Database if not exists ElectrcityDb");
				stmt.execute("use ElectrcityDb");
				stmt.executeUpdate("create table if not exists usertb(user varchar(100),password varchar(100),ques varchar(100),ans varchar(100),primary key(user))");
				
				PreparedStatement pstmt=con.prepareStatement("insert into usertb(user,password,ques,ans) values(?,?,?,?)");
				
				pstmt.setString(1, user);
				pstmt.setString(2, pass);
				pstmt.setString(3, ques);
				pstmt.setString(4, ans);
				
				pstmt.executeUpdate();
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
