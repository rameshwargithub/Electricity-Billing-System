import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.FlowLayout;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EBComplains extends JDialog implements ActionListener{
	
	JLabel lbcomp,lbnoc,lbdet,lblocgui;
	Choice cbcom;
	JTextField txloc;
	JTextArea txadd;
	JButton btsubmit;
	JPanel pn,ps,pe,pw,pc,pc1,pc2,pc3,pc4;
	
	public EBComplains() 
	{
		
		setVisible(true);
		setSize(300,300);
		lbcomp = new JLabel("Complains");
		lbnoc = new JLabel("Nature of Complain");
		lblocgui= new JLabel("Location guidance");
		lbdet = new JLabel("Address Detail");

		
		
		cbcom = new Choice();
		cbcom.addItem("Select type of complain");
		cbcom.addItem("Meter Tempered");
		cbcom.addItem("Service CutOut");
		cbcom.addItem("Low Consumption");
		cbcom.addItem("Pillan box");
		cbcom.addItem("Service cabel");
		cbcom.addItem("Domestic use for comercial/industrial");
		cbcom.addItem("Others");
		
		txloc = new JTextField(20);
		txadd = new JTextArea();
		
		btsubmit = new JButton("Submit");
		
		ps = new JPanel();
		pe = new JPanel();
		pw = new JPanel();
		pc  = new JPanel();
		pc.setLayout(new GridLayout(4, 1));
		pc1 = new JPanel();
		pc2 = new JPanel();
		pc3 = new JPanel();
		pc4 = new JPanel();
		
		pc1.setLayout(new GridLayout(1, 2));
		pc1.add(lbnoc);
		pc1.add(cbcom);
		
		pc2.setLayout(new GridLayout(1, 2));
		pc2.add(lblocgui);
		pc2.add(txloc);
		
		pc3.setLayout(new GridLayout(1, 2));
		pc3.add(lbdet);
		pc3.add(txadd);
		
		pc4.add(btsubmit);
		
		pn = new JPanel();
		pn.add(lbcomp);

		pc.add(pc1);
		pc.add(pc2);
		pc.add(pc3);
		pc.add(pc4);
		pn.setLayout(new FlowLayout());
		add(pn,BorderLayout.NORTH);
		add(pc,BorderLayout.CENTER);
		add(ps,BorderLayout.SOUTH);
		add(pe,BorderLayout.EAST);
		add(pw,BorderLayout.WEST);
		
		add(pn);
		add(pc);

		btsubmit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent Ae) {
		// TODO Auto-generated method stub
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stmt=con.createStatement();
			stmt.executeUpdate("create Database if not exists ElectrcityDb");
			stmt.execute("use ElectrcityDb");
			stmt.executeUpdate("create table if not exists complain_tb(id int auto_increment, NatureOfcompalin varchar(50),Location varchar(255),AddressDetail varchar(255),Action varchar(50),reasion varchar(255), primary key(id))");
			PreparedStatement pstmt=con.prepareStatement("insert into complain_tb(NatureOfcompalin,Location,AddressDetail,Action,reasion) values(?,?,?,?,?)");
			pstmt.setString(1, cbcom.getSelectedItem());
			pstmt.setString(2, txloc.getText());
			pstmt.setString(3, txadd.getText());
			pstmt.setString(4, "no");
			pstmt.setString(5, "nothing");
			pstmt.executeUpdate();

			ResultSet rs=stmt.executeQuery("select count(*) from complain_tb");
			
			int c=0;
			while(rs.next())
			{
				c=rs.getInt(1);
			}
			JOptionPane.showMessageDialog(null, "Your Complain Id="+c+" Please note down it");
			con.close();
		}
		
		
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
