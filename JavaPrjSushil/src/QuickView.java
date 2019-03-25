import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class QuickView extends JDialog implements ActionListener,KeyListener,ItemListener,ListSelectionListener
{
	JLabel lbkno,lbkno1,lbname,lbmont,lbdue,lbrent,lbamt,lbload;
	List cbid;
	public static String knoid;
	JComboBox cbmonth,cbyear;
	JTextField txkno;
	JButton btnshow,btnpay;
	JPanel pn1,pn,pn2,pc,pc2;
	JTable table;
	
	JScrollPane jsp;
	public QuickView()
	{
		setVisible(true);
		setSize(800,400);
		
		Font f=new Font("comics scene",Font.BOLD,28);

		setVisible(true);
		setSize(800,500);
		
		cbmonth=new JComboBox();
		cbmonth.addItem("select month");
		cbyear=new JComboBox();
		cbyear.addItem("select Year");

		pn=new JPanel();
		pn.setLayout(new GridLayout(2, 1));
		pn2=new JPanel();
		pn2.setLayout(new GridLayout(1, 5));
		pn1=new JPanel();
		pn1.setLayout(new GridLayout(1, 5));
		lbkno=new JLabel("Enter KNumber");
		lbkno.setFont(f);
		
		pc=new JPanel();
		pc2=new JPanel();
		
		pc.setBackground(Color.CYAN);
		lbkno1=new JLabel("");
		lbname=new JLabel("");
		lbamt=new JLabel("");
		lbdue=new JLabel("");
		lbload=new JLabel("");
		lbmont=new JLabel("");
		lbrent=new JLabel("");
		
		btnpay=new JButton("Pay Now");
		
		pc2.setLayout(new GridLayout(8, 2));
		pc2.add(new JLabel("Name"));
		pc2.add(lbname);
		pc2.add(new JLabel("Kno"));
		pc2.add(lbkno1);
		pc2.add(new JLabel("Bill month"));
		pc2.add(lbmont);
		pc2.add(new JLabel("Due Date"));
		pc2.add(lbdue);
		pc2.add(new JLabel("Rent"));
		pc2.add(lbrent);
		pc2.add(new JLabel("Ammount"));
		pc2.add(lbamt);
		pc2.add(new JLabel("Applied Load"));
		pc2.add(lbload);
		pc2.add(btnpay);
		pc2.setVisible(false);
		//pc.add(pc2);


		txkno=new JTextField(15);
		txkno.setText("Electricity_");
		//txkno.setSelectionEnd(12);
		txkno.setCaretPosition(12);
		
		btnshow=new JButton("Get Bill");
		cbid=new List();
		cbid.add("select");
		cbid.setVisible(false);		
		
		pn1.add(lbkno);
		pn1.add(txkno);
		pn1.add(cbmonth);
		pn1.add(cbyear);
		pn1.add(btnshow);
		
		pn2.add(new JLabel());
		pn2.add(cbid);
		pn2.add(new JLabel());
		pn2.add(new JLabel());
		pn2.add(new JLabel());
		
		pn.add(pn1);
		pn.add(pn2);
		setLayout(new BorderLayout());
		add(pn, BorderLayout.NORTH);
		add(pc, BorderLayout.CENTER);
		btnshow.addActionListener(this);
		txkno.addKeyListener(this);
		cbid.addItemListener(this);
		cbyear.addItemListener(this);
		cbmonth.addItemListener(this);
		btnpay.addActionListener(this);
	}
	
	
	
	
	public void billshow(String Billname)
	{
		SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy");
		Date d=new Date();
		String crntdate=formatter.format(d);
	//	System.out.println(crntdate);
		String kno=txkno.getText();
		knoid=kno;
		//String lastname=txkno.getText();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ElectrcityDb","root","");
			
			Statement stmt=con.createStatement();
			PreparedStatement pstmt=con.prepareStatement("select * from connectiontb where Kno=?");
			pstmt.setString(1,txkno.getText());
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				lbname.setText(rs.getString("name"));
				lbkno1.setText(rs.getString("Kno"));
				lbload.setText(rs.getString("AppliedLoad"));
			}
			
			
			PreparedStatement pstmt1=con.prepareStatement("select * from "+kno+"_db where BillMonth=?");
			pstmt1.setString(1,Billname);
			ResultSet rs1=pstmt1.executeQuery();
			
			while(rs1.next())
			{
				lbmont.setText(rs1.getString("BillMonth"));
				String todaydate=rs1.getString("DueDate");
				lbdue.setText(todaydate);
				lbrent.setText(rs1.getString("Rent"));
				String amtbefor=rs1.getString("Ammount_Beforeduedate");
				String amtafter=rs1.getString("Ammount_AfterdueDate");
				
	//			System.out.println();
				if(crntdate.compareToIgnoreCase(todaydate)>=0)
							lbamt.setText(amtbefor);
				else
					lbamt.setText(amtafter+"(with panelty)");
				
			}
			

		/*	PreparedStatement pstmt2=con.prepareStatement("select * from "+kno+"_db where Kno=?");
			pstmt2.setString(1, kno);
			ResultSet rs2=pstmt1.executeQuery();
			float total=0;
			
			while(rs2.next())
			{
				String paystatu=rs2.getString("PaidStatus");
				String todaydate=rs2.getString("DueDate");

				String amtbefor=rs2.getString("Ammount_Beforeduedate");
				String amtafter=rs2.getString("Ammount_AfterdueDate");
				
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
			
			*/
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
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// TODO Auto-generated method stub
		Object src=ae.getSource();
		String Billname=getstring();
		if(Billname!=null)
		{	pc.removeAll();
			pc2.setVisible(true);
			pc.add(pc2);
			cbid.setVisible(false);
	
			billshow(Billname);
		}
		else
			JOptionPane.showMessageDialog(null, "Please select your billmonth from table","",JOptionPane.ERROR_MESSAGE);
		if(src==btnpay)
				new qickbillpay();
		
			validate();
	}
	
	
public void search(char key)
{
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ElectrcityDb","root","");
		
		Statement stmt=con.createStatement();
		PreparedStatement pstmt=con.prepareStatement("select * from connectiontb where Kno LIKE ?");
		pstmt.setString(1, txkno.getText()+key+ "%");
		//JOptionPane.showMessageDialog(null,txkno.getText()+ke.getKeyChar()+"*");
		ResultSet rs=pstmt.executeQuery();
		while(rs.next())
		{
			String load=rs.getString("AppliedLoad");
			int k=load.indexOf("-");
			String load1=load.substring(k+1,load.length());
			//System.out.println(load1);
			String s=rs.getString("Kno");

			if(load1.equals("kwh"))
						cbid.add(s);
			else 
				cbid.add("");

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
	public void keyPressed(KeyEvent ke) {
		
		
		char D=KeyEvent.VK_D;
		char C=KeyEvent.VK_C;
		char I=KeyEvent.VK_I;
		
		
		if(ke.getKeyChar()==D||ke.getKeyChar()=='d')
		{
					cbid.setVisible(true);
					cbid.removeAll();
					cbid.add("select");
					search(ke.getKeyChar());
		}
		else if(ke.getKeyChar()==C||ke.getKeyChar()=='c')
		{
			cbid.setVisible(true);
					cbid.removeAll();
					cbid.add("select");
					search(ke.getKeyChar());
		}
		else if(ke.getKeyChar()==I||ke.getKeyChar()=='i')
		{
			cbid.setVisible(true);
					cbid.removeAll();
					cbid.add("select");
					search(ke.getKeyChar());
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
	
	//choose month function
	public void selection()
	{
			
		String kno=txkno.getText();
		
		try
		{
			
			String mm1=cbmonth.getSelectedItem().toString();
			cbid.setVisible(false);
		//	System.out.println(mm1);
			//int i=mm1.indexOf('-');
			 //String mm=mm1.substring(0, i+1);
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ElectrcityDb","root","");
			
			Statement stmt=con.createStatement();
	
			PreparedStatement pstmt1=con.prepareStatement("select count(*) from "+kno+"_db where BillMonth LIKE ?");
			pstmt1.setString(1, mm1 +"%");
			
			ResultSet rs1=pstmt1.executeQuery();
			int c=0;
			while(rs1.next())
			{
				c=rs1.getInt(1);

			}
			//System.out.println(c);
			Object [] colheader= {"Kno","BillMonth","DueDate"};
					
			Object [][] rowdata= new Object[c][3];

			
			PreparedStatement pstmt=con.prepareStatement("select * from "+kno+"_db where BillMonth LIKE ?");
			pstmt.setString(1, mm1+ "%");
			ResultSet rs=pstmt.executeQuery();
			
			
			//String kid=null,BillMonth=null,Duedate=null;
			int i=0;
			while(rs.next())
			{
				rowdata[i][0]=rs.getString(1);
				rowdata [i][1]=rs.getString(4);
				rowdata [i][2]=rs.getString(5);
				i++;
			}
			
			if(jsp!=null)
			{
				jsp.remove(table);
				pc.remove(jsp);
				validate();
			}
			table=new JTable(rowdata,colheader);
			table.getSelectionModel().addListSelectionListener(this);
			jsp=new JScrollPane(table);
			pc.add(jsp);
			
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

	
	public void bothselection()
	{

		
		String kno=txkno.getText();

		try
		{
			
			String yy1=cbyear.getSelectedItem().toString();
			String mm1=cbmonth.getSelectedItem().toString();
			cbid.setVisible(false);
			//System.out.println(mm1);
			//int i=mm1.indexOf('-');
			 //String mm=mm1.substring(0, i+1);
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ElectrcityDb","root","");
			
			Statement stmt=con.createStatement();
	
			PreparedStatement pstmt1=con.prepareStatement("select count(*) from "+kno+"_db where BillMonth LIKE ?");
			pstmt1.setString(1, mm1+"%"+yy1 );
			
			ResultSet rs1=pstmt1.executeQuery();
			int c=0;
			while(rs1.next())
			{
				c=rs1.getInt(1);

			}
			//System.out.println(c);
			Object [] colheader= {"Kno","BillMonth","DueDate"};
					
			Object [][] rowdata= new Object[c][3];

			
			PreparedStatement pstmt=con.prepareStatement("select * from "+kno+"_db where BillMonth LIKE ?");
			pstmt.setString(1,  mm1+"%"+yy1);
			ResultSet rs=pstmt.executeQuery();
			
			
			//String kid=null,BillMonth=null,Duedate=null;
			int i=0;
			while(rs.next())
			{
				rowdata[i][0]=rs.getString(1);
				rowdata [i][1]=rs.getString(4);
				rowdata [i][2]=rs.getString(5);
				i++;
			}
			
			if(jsp!=null)
			{
				jsp.remove(table);
				pc.remove(jsp);
				validate();
			}
			table=new JTable(rowdata,colheader);
			jsp=new JScrollPane(table);
			table.getSelectionModel().addListSelectionListener(this);
			pc.add(jsp);
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
	
	public void selectionyear()
	{
		String kno=txkno.getText();

		try
		{
			
			String yy1=cbyear.getSelectedItem().toString();
			//System.out.println(mm1);
			cbid.setVisible(false);
			//int i=mm1.indexOf('-');
			 //String mm=mm1.substring(0, i+1);
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ElectrcityDb","root","");
			
			Statement stmt=con.createStatement();
	
			PreparedStatement pstmt1=con.prepareStatement("select count(*) from "+kno+"_db where BillMonth LIKE ?");
			pstmt1.setString(1, "%"+yy1 );
			
			ResultSet rs1=pstmt1.executeQuery();
			int c=0;
			while(rs1.next())
			{
				c=rs1.getInt(1);

			}
			//System.out.println(c);
			Object [] colheader= {"Kno","BillMonth","DueDate"};
					
			Object [][] rowdata= new Object[c][3];

			
			PreparedStatement pstmt=con.prepareStatement("select * from "+kno+"_db where BillMonth LIKE ?");
			pstmt.setString(1,  "%"+yy1);
			ResultSet rs=pstmt.executeQuery();
			
			
			//String kid=null,BillMonth=null,Duedate=null;
			int i=0;
			while(rs.next())
			{
				rowdata[i][0]=rs.getString(1);
				rowdata [i][1]=rs.getString(4);
				rowdata [i][2]=rs.getString(5);
				i++;
			}
			
			if(jsp!=null)
			{
				jsp.remove(table);
				pc.remove(jsp);
				validate();
			}
			table=new JTable(rowdata,colheader);
			jsp=new JScrollPane(table);
			table.getSelectionModel().addListSelectionListener(this);
			pc.add(jsp);
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
	public void itemStateChanged(ItemEvent Ie) 
	{
	Object src=Ie.getSource();
	
	if(src==cbid && Ie.getStateChange()==ItemEvent.SELECTED)
	{
		int index=cbid.getSelectedIndex();	
		if(index!=0)
				txkno.setText(cbid.getItem(index));
		String kno=txkno.getText();
		
		//System.out.println(kno);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ElectrcityDb","root","");
			
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select BillMonth from "+kno+"_db");
			String r,mm = null;
			
			while(rs.next())
			{
				 r=rs.getString("BillMonth");
				int i=r.indexOf('-');
				 mm=r.substring(0, i);
				
				String yy=r.substring(i+1,r.length());
				
				
				int l=cbyear.getItemCount();
				int l1=cbmonth.getItemCount();
				boolean found=false;
				for(int j=1;j<l1;j++)
				{
					String month=cbmonth.getItemAt(j).toString();
					if(month.equalsIgnoreCase(mm))
					{
						found=true;
						break;
					}
				}
				if(!found)
					cbmonth.addItem(mm+"");
				for(int j=1;j<l;j++)
				{
					String year=cbyear.getItemAt(j).toString();
					if(year.equals(yy))
					{
						found=true;
						break;
					}
				}
				if(!found)
					cbyear.addItem(yy);
					
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
	else if(src==cbmonth && Ie.getStateChange()==ItemEvent.SELECTED)
	{
		Boolean f=true;
		int choose1=cbyear.getSelectedIndex();
		int choose=cbmonth.getSelectedIndex();
		//System.out.println(choose);
		if(choose!=0)
		{
			selection();
			if(choose1!=0)
				bothselection();
		}
	
		validate();
	}//else if
	
	else if(src==cbyear &&  Ie.getStateChange()==ItemEvent.SELECTED)
	{
		int choose1=cbyear.getSelectedIndex();
		int choose=cbmonth.getSelectedIndex();
		if(choose1!=0)
		{
			selectionyear();
			if(choose!=0)
				bothselection();
		}
		validate();

		
	}
	
}
	
	String monthsel;
	public String getstring()
	{
		return monthsel;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent VE) 
	{
		
		 monthsel= table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString();
		
		
	}

}
