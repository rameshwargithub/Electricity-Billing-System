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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class qickbillpay extends JDialog implements ActionListener
{
	JLabel lbid,lbcardnumber,lbexpiry,lbcvv,lbtotal;
	JTextField txid,txcardnumber,txexpiry,txcvv,txtotal;
	JButton btnup;
	JPanel pc,pn,pc1,pc2;
	public qickbillpay()
	{
		setVisible(true);
		setSize(300, 200);
		pc=new JPanel();
		pn=new JPanel();
		pc1=new JPanel();
		pc2=new JPanel();
		pc1.setLayout(new GridLayout(3, 2));
		pc2.setLayout(new GridLayout(2, 4));
		pn.setLayout(new GridLayout(1, 2));
		pc.setLayout(new GridLayout(2,  1));
		lbid=new JLabel("Id");
		String idd=QuickView.knoid;
		txid=new JTextField(20);
		txid.setText(idd);
		lbcardnumber=new JLabel("Enter Your Card Number");
		txcardnumber=new JTextField(20);
		lbtotal=new JLabel("Total Ammount");
		lbexpiry=new JLabel("Expiry-mm/yy");
		txexpiry=new JTextField(20);
		txtotal=new JTextField(20);
		lbcvv=new JLabel("CVV");
		txcvv=new JTextField(20);
			
		
		
		btnup=new JButton("Make A Payment");
		pc1.add(lbid);
		pc1.add(txid);
		pc1.add(lbtotal);
		pc1.add(txtotal);
		pc1.add(lbcardnumber);
		pc1.add(txcardnumber);
		
		pc2.add(lbexpiry);
		pc2.add(txexpiry);
		pc2.add(lbcvv);
		pc2.add(txcvv);
		pc2.add(btnup);
		pc2.add(new JLabel(""));
		pc2.add(new JLabel(""));
		pc.add(pc1);
		pc.add(pc2);
		
		SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy");
		Date d=new Date();
		String crntdate=formatter.format(d);

		String kno=txid.getText();
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
				if(crntdate.compareToIgnoreCase(todaydate)>=0)
					total+=amtb;
				else
					total+=amta;
	
				}
				
				amtb=0;
				amta=0;

			}
			//lbbill.setText(total+"");
			//JOptionPane.showMessageDialog(null, total);
			txtotal.setText(total+"");
			con.close();
			
			
		} 
		catch (ClassNotFoundException e) 
		{
		
			e.printStackTrace();
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}

	
		setLayout(new BorderLayout());
		add(pc, BorderLayout.CENTER);
	
		btnup.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String cardno=null,expiry=null,cv=null;
		 cardno=txcardnumber.getText();
		 expiry=txexpiry.getText();
		 cv=txcvv.getText();
		//System.out.println(cardno+""+expiry);
		if(cardno.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Enter Valid Information of Bank","",JOptionPane.ERROR_MESSAGE);

		}
	
		else if(expiry.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Enter Valid Information of Expiry date","",JOptionPane.ERROR_MESSAGE);

		}
		else if(cv.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Enter Valid Information of CVV Number","",JOptionPane.ERROR_MESSAGE);

		}

		else
		{

			String kno=txid.getText();
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ElectrcityDb","root","");
				
				PreparedStatement pstmt=con.prepareStatement("update "+kno+"_db set PaidStatus=? where Kno=?");
				pstmt.setString(1, "1");
				pstmt.setString(2, kno);
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "Payment Sucessfully Paid");
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

}