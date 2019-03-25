import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
 
public class democal extends JDialog implements ActionListener,ListSelectionListener{
 
	public static String crntdate,Currentdate;
	public int numberOfDays;
  DefaultTableModel model;
  Calendar cal = new GregorianCalendar();
  JLabel label;
  JButton []bt;
  JButton btnprv,btnnext,btnprvyear,btnnextyear,btndate,btnny,btnpy;
  JPanel ps;
  JTable table;
  JComboBox cbyear,cbdate;
  public democal()
  {
	  setVisible(true);
	  setSize(450,300);
	  bt=new JButton[35];
	  
	  
	ps=new JPanel();
	ps.setLayout(new GridLayout(1,4));
    label = new JLabel();
    label.setHorizontalAlignment(SwingConstants.CENTER);
 
    btnnextyear=new JButton("next Year");
    btnprvyear=new JButton("prv year");
    btnprv = new JButton("Prv");
    btnny=new JButton(">>>");
    btnpy=new JButton("<<<");
    ps.add(btnpy);
    ps.add(btnprvyear);
    ps.add(btnnextyear);
    ps.add(btnny);
    
    btnnext = new JButton("Next");
   
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(btnprv,BorderLayout.WEST);
    panel.add(label,BorderLayout.CENTER);
    panel.add(btnnext,BorderLayout.EAST);
 
 
    String [] columns = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
    model = new DefaultTableModel(null,columns);
     table = new JTable(model);
    JScrollPane pane = new JScrollPane(table);

    
    add(panel,BorderLayout.NORTH);
    add(pane,BorderLayout.CENTER);
    add(ps, BorderLayout.SOUTH);
    this.updateMonth();
    
    
    
    btnprv.addActionListener(this);
    btnnext.addActionListener(this);
    btnnextyear.addActionListener(this);
    btnprvyear.addActionListener(this);
    btnny.addActionListener(this);
    btnpy.addActionListener(this);
    //btndate.addActionListener(this);
   table.getSelectionModel().addListSelectionListener(this);
  }
  public void updateMonth() 
  {
    cal.set(Calendar.DAY_OF_MONTH, 1);
 
    int year = cal.get(Calendar.YEAR);
    
     String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
   
    label.setText(month + " " + year);
    
    int startDay = cal.get(Calendar.DAY_OF_WEEK);
    
       numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
  //System.out.println(weeks+"");
    model.setRowCount(0);
    model.setRowCount(weeks);
 
    int i = startDay-1;
    for(int day=1;day<=numberOfDays;day++)
    {
      model.setValueAt(day, i/7 , i%7);    
      i = i+1;
    
    }
  }
  
@Override
public void actionPerformed(ActionEvent Ae) {
	Object src=Ae.getSource();
	if(src==btnprv)
	{
       cal.add(Calendar.MONTH, -1);
       updateMonth();

	}
	if(src==btnnext)
	{
		cal.add(Calendar.MONTH, +1);
	       updateMonth();

	}
	
	if(src==btnnextyear)
	{
		cal.add(Calendar.YEAR, +1);
		updateMonth();
	}
	if(src==btnprvyear)
	{
		cal.add(Calendar.YEAR, -1);
		updateMonth();

	}
	if(src==btnny)
	{
		cal.add(Calendar.YEAR, +5);
		updateMonth();

	}

	if(src==btnpy)
	{
		cal.add(Calendar.YEAR, -5);
		updateMonth();

	}

}

@Override
public void valueChanged(ListSelectionEvent arg0) {
	// TODO Auto-generated method stub
	
String printdate= table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString();
	 Currentdate=label.getText();
	 NewConnectionDetail.txdob.setText(printdate+" "+Currentdate);
 
	 
}
}
