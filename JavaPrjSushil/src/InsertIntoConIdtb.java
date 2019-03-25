import java.awt.BorderLayout;
import java.awt.GridLayout;
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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.xml.internal.txw2.output.TXWResult;

import jdk.nashorn.internal.runtime.linker.JavaAdapterFactory;

public class InsertIntoConIdtb extends JApplet implements ActionListener{
	JLabel lbid,lbunit,lbrent,lbbillmonth,lbduedate,lbamtB,lbamtA,lbpaid;
	JComboBox cbid;
	JTextField txunit,txpaid,txbillmonth,txduedate;
	JPanel pc,p1;
	
	JButton btnsubmit,btnupdate,btndlt;
	public void init()
	{
		btnsubmit=new JButton("Submit");
		btnupdate=new JButton("Update");
		btndlt=new JButton("Delete");
		p1=new JPanel();
		p1.setLayout(new GridLayout(1, 3));
		p1.add(btnsubmit);
		p1.add(btnupdate);
		p1.add(btndlt);
		lbid=new JLabel("Kno");
		lbunit=new JLabel("Unit");
		lbbillmonth=new JLabel("Bill Month-year");
		lbduedate=new JLabel("Due date");
		lbpaid=new JLabel("Paid Status");
		pc=new JPanel();
		pc.setLayout(new GridLayout(5, 2));
		txunit=new JTextField(20);
		txbillmonth=new JTextField(20);
		txduedate=new JTextField(20);
		txpaid=new JTextField(20);
	
		cbid=new JComboBox();
		cbid.addItem("Select Id");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ElectrcityDb","root","");
			
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select Kno from connectiontb");
			while(rs.next())
			{
				String r=rs.getString("Kno");
				cbid.addItem(r+"");
				
			}
			pc.add(lbid);
			pc.add(cbid);
			pc.add(lbunit);
			pc.add(txunit);
			pc.add(lbbillmonth);
			pc.add(txbillmonth);
			pc.add(lbduedate);
			pc.add(txduedate);
			pc.add(lbpaid);
			pc.add(txpaid);
			
			setLayout(new BorderLayout());

			add(pc, BorderLayout.CENTER);
			add(p1, BorderLayout.SOUTH);
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

		btnsubmit.addActionListener(this);
		btnupdate.addActionListener(this);
		btndlt.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent Ae) {
		// TODO Auto-generated method stub
		
		
		
		String Rent = null,Ammount = null,Ammountafter = null;
		Object src=Ae.getSource();
		 String Kno=cbid.getSelectedItem().toString();
		
		String unit=txunit.getText();
		
		if(Integer.parseInt(unit)<0)
			JOptionPane.showMessageDialog(null, "unit must Be GraterThan Zero");
		else if(Integer.parseInt(unit)>0 & Integer.parseInt(unit)<=100)
		{
			 Rent=Integer.toString(100);
			float Amt=(float) (Float.parseFloat(unit)*5.25);
			 Ammountafter=Double.toString((Amt+100.0)); 
			Ammount=Float.toString(Amt);
			
		}
		
		else if(Integer.parseInt(unit)>100 & Integer.parseInt(unit)<=500)
		{
			Rent=Integer.toString(200);
			float Amt=(float) (Float.parseFloat(unit)*6.25);
			 Ammount=Float.toString(Amt);
			 Ammountafter=Double.toString((Amt+200.0));
		}
		else if(Integer.parseInt(unit)>500 )
		{
			 Rent=Integer.toString(300);
			float Amt=(float) (Float.parseFloat(unit)*7.25);
			 Ammount=Float.toString(Amt);
			 Ammountafter=Double.toString((Amt+300.0));
		}

		
		String paid=txpaid.getText();
		String due=txduedate.getText();
		String billmonth=txbillmonth.getText();
		
		
		
		
		if(src==btnsubmit)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				
				Statement stmt=con.createStatement();
				stmt.executeUpdate("create Database if not exists ElectrcityDb");
				stmt.execute("use ElectrcityDb");
				//stmt.executeUpdate("create table if not exists "+Kno+"_Db(Kno varchar(50),Unit varchar(50),Rent varchar(50),BillMonth varchar(50),DueDate varchar(50),Ammount_Beforeduedate varchar(50),Ammount_AfterdueDate varchar(50),PaidStatus varchar(50))");
				//System.out.println(Kno);
				PreparedStatement pstmt=con.prepareStatement("insert into "+Kno+"_db(Kno,Unit,Rent,BillMonth,DueDate,Ammount_Beforeduedate,Ammount_AfterdueDate,PaidStatus) values(?,?,?,?,?,?,?,?)");
				
				pstmt.setString(1, Kno);
				pstmt.setString(2, unit);
				pstmt.setString(3, Rent);
				pstmt.setString(4, billmonth);
				pstmt.setString(5, due);
				pstmt.setString(6, Ammount);
				pstmt.setString(7, Ammountafter);
				pstmt.setString(8, paid);
				
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
