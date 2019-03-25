import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class NewConnectionDetail extends JDialog implements ActionListener {
	
	JScrollPane jsp;
	JLabel lbhead,lbname,lbcat,lbgen,lbmob,lbmail,lbdob,lbarea,lbhn,lblocal,lbdist,lbbnk,lbacc,lbpan,lbadh,lbapp,lbdate;
	JRadioButton rdmale,rdfemale;
	ButtonGroup gr;
	JTextField txname,txmob,txmail,txarea,txhn,txdist,txbank,txacc,txpan,txadh,txapp,txdate;
	public static JTextField txdob;
	JComboBox chloc,bank,cat;
	JButton btsubmit,btndob;
	JPanel pn,ps,pe,pw,pc,pc1,pc2,pc3,pc4,pc5,pc6,pc7,pc8,pc9,pc10,pc11,pc12,pc13;
	
	
	public NewConnectionDetail()
	{
		setVisible(true);
		setSize(1100, 750);
		
		jsp= new JScrollPane();
		lbhead= new JLabel("New Connection Details");
		lbname= new JLabel("Enter Name");
		lbgen = new JLabel("Gender");
		lbmob = new JLabel("Mobile");
		lbmail= new JLabel("EMail");
		lbdob = new JLabel("Date Of Birth");
		lbarea= new JLabel("Area Name");
		lbhn  = new JLabel("House number");
		lblocal= new JLabel("Locality");
		lbdist= new JLabel("District");
		lbcat=new JLabel("Category");
		
		
		
		lbbnk = new JLabel("Bank Name");
		lbacc = new JLabel("Account Number");
		lbpan = new JLabel("PAN Card Number");
		lbadh = new JLabel("Adhar Card Number");
		lbapp = new JLabel("Applied Load(Kwh)");
		lbdate= new JLabel("Today Date");
		
		
		txbank= new JTextField(20);
		txacc = new JTextField(20);
		txpan = new JTextField(20);
		txadh = new JTextField(20);
		txapp = new JTextField(20);
		txdate= new JTextField(20);
		
		bank =new JComboBox();
		
		bank.addItem("select Bank");
		bank.addItem("SBBJ");
		bank.addItem("ICICI");
		bank.addItem("Bank of Baroda");
		bank.addItem("Punjab national Bank");
		bank.addItem("State Bank of India");
		
		cat=new JComboBox();
		cat.addItem("Select Category");
		cat.addItem("Industrial");
		cat.addItem("domestic");
		cat.addItem("commercial");
		
		
		btsubmit = new JButton("Submit");
		btndob=new JButton("Choose DOB");
		
		
		txname=new JTextField(20);
		txmob =new JTextField(20);
		txmail=new JTextField(20);
		txdob =new JTextField(2);
		txdob.disable();
		txarea=new JTextField(20);
		txhn  =new JTextField(20);
		txdist=new JTextField(20);
		
		gr=new ButtonGroup();
		rdmale=new JRadioButton("male");
		rdfemale=new JRadioButton("Female");
		
		gr.add(rdmale);
		gr.add(rdfemale);
		
		chloc= new JComboBox();
		
		chloc.addItem("select Locality");
		chloc.addItem("Urban");
		chloc.addItem("Village");
		chloc.addItem("Tribal");
		
		pc= new JPanel();
		pc1=new JPanel();
		pc2=new JPanel();
		pc4=new JPanel();
		pc3=new JPanel();
		pc5=new JPanel();
		pc6=new JPanel();
		pc7=new JPanel();
		pc8=new JPanel();
		pc9=new JPanel();
		pc10=new JPanel();
		pc11=new JPanel();
		pc12=new JPanel();
		pc13=new JPanel();
		
		pc1.setLayout(new GridLayout(2,2));
		pc1.add(lbname);
		pc1.add(txname);
		pc1.add(lbmob);
		pc1.add(txmob);

		
		
		pc2.setLayout(new GridLayout(2,2));
		pc2.add(lbmail);
		pc2.add(txmail);
		pc2.add(lbcat);
		pc2.add(cat);
		pc3.setLayout(new GridLayout(1,4));
		pc3.add(lbdob);
		pc3.add(new JLabel(""));
		pc3.add(btndob);
		pc3.add(txdob);
		
		pc4.setLayout(new GridLayout(2,2));
		pc4.add(lbarea);
		pc4.add(txarea);
		pc4.add(lbhn);
		pc4.add(txhn);
		
		pc5.setLayout(new GridLayout(2,2));
		pc5.add(lblocal);
		pc5.add(chloc);
		pc5.add(lbdist);
		pc5.add(txdist);
		
		
		pc7.setLayout(new GridLayout(1,2));
		pc7.add(lbbnk);
		pc7.add(bank);
		
		pc8.setLayout(new GridLayout(1, 2));
		pc8.add(lbacc);
		pc8.add(txacc);
		
		pc9.setLayout(new GridLayout(1,2));
		pc9.add(lbpan);
		pc9.add(txpan);
		
		pc10.setLayout(new GridLayout(1,2));
		pc10.add(lbadh);
		pc10.add(txadh);
		
		pc11.setLayout(new GridLayout(1,2));
		pc11.add(lbapp);
		pc11.add(txapp);
		
		pc12.setLayout(new GridLayout(1,2));
		pc12.add(lbdate);
		pc12.add(txdate);
		
		pc13.add(btsubmit);
		
		
		pn=new JPanel();
		pn.add(lbhead);
		pn.setBackground(Color.ORANGE);
		
		
		
		pc.setLayout(new GridLayout(14, 1));
		
		
		pc.add(pc1);
		pc.add(pc6);
		pc.add(pc2);
		pc.add(pc3);
		pc.add(pc4);
		pc.add(pc5);
		pc.add(pc7);
		pc.add(pc8);
		pc.add(pc9);
		pc.add(pc10);
		pc.add(pc11);
		pc.add(pc12);
		pc.add(pc13);
		
		ps = new JPanel();
		pe = new JPanel();
		pw = new JPanel();
		
		add(jsp);

		add(pn,BorderLayout.NORTH);
		add(pc,BorderLayout.CENTER);
		add(ps,BorderLayout.SOUTH);
		add(pe,BorderLayout.EAST);
		add(pw,BorderLayout.WEST);
		btsubmit.addActionListener(this);
		btndob.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent Ae) {
		// TODO Auto-generated method stub
		
		String Category=cat.getSelectedItem().toString();
		Object src=Ae.getSource();
		String name=txname.getText();
		String mob=txmob.getText();
		String email=txmail.getText();
		String Dob=txdob.getText();
		String AreaName=txarea.getText();
		String Houseno=txhn.getText();
		String locality=chloc.getSelectedItem().toString();
		String district=txdist.getText();
		String Bank=bank.getSelectedItem().toString();
		String account=txacc.getText();
		String pan=txpan.getText();
		String addar=txadh.getText();
		String appliedload=txapp.getText();
		String today=txdate.getText();
		
		
		if(src==btsubmit)
		{
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				
				Statement stmt=con.createStatement();
				
				stmt.executeUpdate("create Database if not exists ElectrcityDb");
				stmt.execute("use ElectrcityDb");
				stmt.executeUpdate("create table if not exists connectiontb(Kno varchar(100),name varchar(100),mobile varchar(100),Email varchar(100),category varchar(100),Dob varchar(100),AreaName varchar(100),HouseNumber varchar(100),Locality varchar(100),District varchar(100),BankName varchar(100),AccountNumber varchar(100),PanCard varchar(100),AddarNumber varchar(100),AppliedLoad varchar(100),Date varchar(100),primary key(Kno))");
				ResultSet rs=stmt.executeQuery("select count(*) from connectiontb");
				int c=0;
				
				while(rs.next())
				{
					c=rs.getInt(1);
				}
				++c;
				String con_id="Electricity_"+Category+"_2018_"+c;
			
				PreparedStatement pstmt=con.prepareStatement("insert into connectiontb(Kno,name,mobile,Email,category,Dob,AreaName,HouseNumber,Locality,District,BankName,AccountNumber,PanCard,AddarNumber,AppliedLoad,Date) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
				pstmt.setString(1, con_id);
				pstmt.setString(2, name);
				pstmt.setString(3, mob);
				pstmt.setString(4,email);
				pstmt.setString(5, Category);
				pstmt.setString(6, Dob);
				pstmt.setString(7, AreaName);
				pstmt.setString(8, Houseno);
				pstmt.setString(9, locality);
				pstmt.setString(10, district);
				pstmt.setString(11, Bank);
				pstmt.setString(12,account);
				pstmt.setString(13,pan);
				pstmt.setString(14, addar);
				pstmt.setString(15, appliedload);
				pstmt.setString(16, today);
				pstmt.executeUpdate();
				stmt.executeUpdate("create table if not exists "+con_id+"_Db(Kno varchar(50),Unit varchar(50),Rent varchar(50),BillMonth varchar(50),DueDate varchar(50),Ammount_Beforeduedate varchar(50),Ammount_AfterdueDate varchar(50),PaidStatus varchar(50))"); 
				JOptionPane.showMessageDialog(null, "Data Has Been Taken");
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
		
		if(src==btndob)
		{
			new democal();
			
		}
	}
	
	

}
