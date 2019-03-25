
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class electrcityspage extends JPanel implements ActionListener{
	
	JScrollPane leftjsp,rightjsp,downjspleft,downright;
	JSplitPane jsp,jsp2,jsp3;
	JLabel lbimg;
	JButton btnewcon,btpayment,btquick,btcomplain;
	JPanel p1,p2;
	
	public electrcityspage()
	{
		lbimg=new JLabel(new ImageIcon("hme.jpg"));
		lbimg.setLayout(new FlowLayout());
		
		p1=new JPanel();
		p2=new JPanel();
		btnewcon=new JButton("New Connection");
		btpayment=new JButton("Payment Status");
		btquick=new JButton("Quick view/Quick pay");
		btcomplain=new JButton("Complain And Request");
		leftjsp=new JScrollPane(btnewcon);
		rightjsp=new JScrollPane(btpayment);
		downjspleft=new JScrollPane(btquick);
		downright=new JScrollPane(btcomplain);
		
		jsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,leftjsp,rightjsp);
		jsp2=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,downjspleft,downright);
		
		jsp3=new JSplitPane(JSplitPane.VERTICAL_SPLIT,jsp,jsp2);
		
		
		jsp.setResizeWeight(0.5);
		jsp2.setResizeWeight(0.5);
		jsp3.setResizeWeight(0.5);
		setSize(300, 200);
		setBorder(BorderFactory.createLineBorder(Color.RED));
		setLayout(new FlowLayout());
		add(jsp3);
		
		btquick.addActionListener(this);
		btnewcon.addActionListener(this);
		btcomplain.addActionListener(this);
		btpayment.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent Ae) {
		// TODO Auto-generated method stub
		
		Object src=Ae.getSource();
		
		if(src==btquick)
			new QuickView();
		else if(src==btnewcon)
			new NewConnectionDetail();
		else if(src==btcomplain)
			new EBComplains();
	}

}
