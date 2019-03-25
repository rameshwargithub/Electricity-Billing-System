import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Homepage extends JApplet implements ActionListener
{

	JLabel lbs,lbn,lbw,lbe,lbc,lbele,lbcomp;
	JScrollPane jsp;
	JButton btnhome,btnourservices,btnreg,btnlogin,btncontect,btnconreciver,btncpmaction;
	
	JPanel pn,ps,pw,pe,pc,pn1,pn2,pn3;
	
	CardLayout clo;
	
	public void init()
	{
		
		Font f=new Font("comics scene",Font.BOLD,20);
		setFont(f);

		pc=new JPanel();
		clo=new CardLayout();
		
		
		Calendar cale=Calendar.getInstance();
		
		lbn=new JLabel("North Section");
		lbs=new JLabel("Make a call for any suggestion or Complain 18001201912(TollFree)");
		lbs.setFont(f);
		lbs.setForeground(Color.white);
		lbe=new JLabel(cale.getTime()+"");
		lbw=new JLabel("pay bill get one led");
		lbw.setFont(f);
		lbw.setForeground(Color.white);
		lbc=new JLabel("Ceter Section");
		lbele=new JLabel("ELECTRCITY MANAGEMENT SYSTEM");
		lbcomp=new JLabel("");
		lbele.setFont(f);
		
		
		btnhome=new JButton("Home");
		btnourservices=new JButton("Our Services");
		btnreg=new JButton("Register");
		btnlogin=new JButton("Login");
		btncontect=new JButton("Contact us");
		btnconreciver=new JButton("new Connection Receiver");
		btncpmaction=new JButton("Complain Action");
		
		
		pn1=new JPanel();
		pn2=new JPanel();
		pn3=new JPanel();
		
		pc.setLayout(clo);	
		pn3.add(btnhome);
		pn3.add(btnourservices);
		pn3.add(btnconreciver);
		pn3.add(btncpmaction);
		pn3.add(btnreg);
		pn3.add(btnlogin);
		pn3.add(btncontect);

		
		pc.add("Home",new homepc());
		pc.add("Login", new Login());
		pc.add("Our Services",new electrcityspage());
		pc.add("Register",new RegistrationForm());
		pc.add("Contact us",new contact());
		pc.add("new Connection Receiver", new NewconnectionRecevier());
		pc.add("Complain Action",new AboutComplainAction());
		
		pn1.add(lbele);
		pn2.add(lbcomp);

		pn3.setLayout(new GridLayout(1, 7));
		
		pn=new JPanel();
	//	pn.add(lbn);
		pn.setLayout(new GridLayout(3, 1));
		pn.setBackground(Color.CYAN);
		pn.add(pn1);
		pn.add(pn2);
		pn.add(pn3);
		
		
		ps=new JPanel();
		ps.add(lbs);
		ps.setBackground(Color.darkGray);
		
		pe=new JPanel();
		pe.add(lbe);
		
		pe.setBackground(Color.GREEN);
		
		pw=new JPanel();
		pw.add(lbw);
		pw.setBackground(Color.red);
		
		pc.add(lbc);
		pc.setBackground(Color.gray);
		
		setLayout(new BorderLayout());
		
		
		jsp=new JScrollPane(pc);
		
		add(pn,BorderLayout.NORTH);
		add(ps,BorderLayout.SOUTH);
		add(pw,BorderLayout.WEST);
		add(pe,BorderLayout.EAST);
		add(jsp,BorderLayout.CENTER);
		
		btnourservices.addActionListener(this);
		btnhome.addActionListener(this);
		btnreg.addActionListener(this);
		btnlogin.addActionListener(this);
		btncontect.addActionListener(this);
		btnconreciver.addActionListener(this);
		btncpmaction.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent Ae) {
		// TODO Auto-generated method stub
		JButton src=(JButton)Ae.getSource();
		
			clo.show(pc,src.getText());
	}
	
	
}
