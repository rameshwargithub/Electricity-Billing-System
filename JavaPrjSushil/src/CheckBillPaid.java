import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CheckBillPaid extends JApplet implements ActionListener
{
	JTextField txbill;
	JButton btn;
	JLabel lbbill;
	public void init()
	{
		txbill=new JTextField(20);
		btn=new JButton("click");
		lbbill=new JLabel("");
		
		setLayout(new FlowLayout());
		add(new JLabel("enter your Kno"));
		add(txbill);
		add(lbbill);
		
		add(btn);
		
		btn.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy");
		Date d=new Date();
		String crntdate=formatter.format(d);

		String kno=txbill.getText();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ElectrcityDb","root","");
			
			Statement stmt=con.createStatement();
			PreparedStatement pstmt1=con.prepareStatement("select * from "+kno+"_db where Kno=?");
			pstmt1.setString(1,kno);
			ResultSet rs1=pstmt1.executeQuery();
			float total=0;
			while(rs1.next())
			{
				String paystatu=rs1.getString("PaidStatus");
				String todaydate=rs1.getString("DueDate");

				String amtbefor=rs1.getString("Ammount_Beforeduedate");
				String amtafter=rs1.getString("Ammount_AfterdueDate");
				
				float amtb=Float.parseFloat(amtbefor);
				float amta=Float.parseFloat(amtafter);
				if(paystatu.equals("0"))
				{
				if(todaydate.compareToIgnoreCase(crntdate)>=0)
					total+=amtb;
				else
					total+=amta;
	
				}
				
				amtb=0;
				amta=0;

			}
			lbbill.setText(total+"");
			
			con.close();
			
			
		} 
		catch (ClassNotFoundException e) 
		{
		
			e.printStackTrace();
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}


	}
}
