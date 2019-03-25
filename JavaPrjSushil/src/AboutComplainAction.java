import java.awt.FlowLayout;
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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AboutComplainAction extends JPanel implements ActionListener
{
	JLabel lbid;
	JTextField txid;
	JButton btnid;
	
	public AboutComplainAction()
	{
		setVisible(true);
		setSize(500,400);
		
		lbid=new JLabel("Enter Your complain Id");
		txid=new JTextField(20);
		btnid=new JButton("search");
		
		add(lbid);
		add(txid);
		add(btnid);
		
		setLayout(new FlowLayout());
		btnid.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stmt=con.createStatement();
			stmt.executeUpdate("create Database if not exists ElectrcityDb");
			stmt.execute("use ElectrcityDb");
			
			PreparedStatement psmt=con.prepareStatement("select * from complain_tb where id=?");
			psmt.setString(1, txid.getText());
			ResultSet rs=psmt.executeQuery();
			String action=null,reason=null;
			int c=0;
			while(rs.next())
			{
				 action=rs.getString("Action");
				 reason=rs.getString("reasion");
				c=rs.getInt(1);
			}
			if(c!=0)
			{
			if(action.equals("no"))
				JOptionPane.showMessageDialog(null, "Sorry,Still Reasion="+reason,"complain",JOptionPane.PLAIN_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Thanks for complain,Action Has Been Taken");
			}
			else
				JOptionPane.showMessageDialog(null, "Invalid Id","",JOptionPane.ERROR_MESSAGE);
			
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
