import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AdminComplainaction extends JApplet implements ItemListener,ActionListener
{
	JLabel lbid,lbaction;
	JComboBox cbid;
	JTextField txaction;
	JTextArea txreasion;
	JButton btnup;
	JPanel pc;
	public void init()
	{
		pc=new JPanel();
		pc.setLayout(new GridLayout(4, 2));
		lbid=new JLabel("Id");
		lbaction=new JLabel("Action");
		
		cbid=new JComboBox();
		cbid.addItem("select id");
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ElectrcityDb","root","");
			
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from complain_tb");
			while(rs.next())
			{
				String r=rs.getString("id");
				String reasion=rs.getString("Action");
				if(reasion.equals("yes"))
				{
					cbid.addItem("");
					
				}
				else
					cbid.addItem(r+"");
				
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

		
		
		txaction=new JTextField(20);
		txreasion=new JTextArea();
		btnup=new JButton("update");
		pc.add(lbid);
		pc.add(cbid);
		pc.add(lbaction);
		pc.add(txaction);
		pc.add(new JLabel("Reasion"));
		pc.add(txreasion);
		pc.add(btnup);
		
		setLayout(new BorderLayout());
		add(pc, BorderLayout.CENTER);
		btnup.addActionListener(this);
		cbid.addItemListener(this);
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
int index=cbid.getSelectedIndex();
		
		if(index==0)
		{
			txaction.setText("");
					return;
		}

		
		String id=cbid.getSelectedItem().toString();
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ElectrcityDb","root","");
			
			Statement stmt=con.createStatement();
			PreparedStatement psmt=con.prepareStatement("select * from complain_tb where id=?");
			psmt.setString(1, id);
			ResultSet rs=psmt.executeQuery();
			while(rs.next())
			{
		
				
				txaction.setText(rs.getString("Action"));
				txreasion.setText(rs.getString("reasion"));
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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ElectrcityDb","root","");
			
			Statement stmt=con.createStatement();
			PreparedStatement pstmt=con.prepareStatement("update complain_tb set Action=?,reasion=? where id=?");
			pstmt.setString(1, txaction.getText());
			pstmt.setString(2, txreasion.getText());
			pstmt.setString(3, cbid.getSelectedItem().toString());
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "sucessfully updated");
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
