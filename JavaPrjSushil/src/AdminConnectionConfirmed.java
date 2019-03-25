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
import javax.swing.JTextField;

public class AdminConnectionConfirmed extends JApplet implements ItemListener,ActionListener
{
	JLabel lbkno,lbload;
	JComboBox cbid;
	JTextField txload;
	JButton btnup;
	JPanel pc;
	public void init()
	{
		pc=new JPanel();
		pc.setLayout(new GridLayout(3, 2));
		lbkno=new JLabel("K-Number");
		lbload=new JLabel("Applied Load");
		
		cbid=new JComboBox();
		cbid.addItem("select id");
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ElectrcityDb","root","");
			
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from connectiontb");
			while(rs.next())
			{
				String r=rs.getString("Kno");
				String load=rs.getString("AppliedLoad");
				int k=load.indexOf("-");
				String load1=load.substring(k+1,load.length());
				//System.out.println(load1);
				if(load1.equals("kwh"))
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

		
		
		txload=new JTextField(20);
		btnup=new JButton("update");
		pc.add(lbkno);
		pc.add(cbid);
		pc.add(lbload);
		pc.add(txload);
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
			txload.setText("");
					return;
		}

		
		String kno=cbid.getSelectedItem().toString();
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ElectrcityDb","root","");
			
			Statement stmt=con.createStatement();
			PreparedStatement psmt=con.prepareStatement("select * from connectiontb where Kno=?");
			psmt.setString(1, kno);
			ResultSet rs=psmt.executeQuery();
			while(rs.next())
			{
		
				
				txload.setText(rs.getString("AppliedLoad"));
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
			PreparedStatement pstmt=con.prepareStatement("update connectiontb set AppliedLoad=? where Kno=?");
			pstmt.setString(1, txload.getText());
			pstmt.setString(2, cbid.getSelectedItem().toString());
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
