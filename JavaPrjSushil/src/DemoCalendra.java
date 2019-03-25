import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JApplet;

public class DemoCalendra extends JApplet
{

	public void init()
	{
		System.out.println("Enter Year");
		Scanner sc=new Scanner(System.in);
		
		int year=sc.nextInt();
		System.out.println("Enter 1st day of"+year);
		
		String day=sc.next();
		System.out.println("Enter month");
		
		String month=sc.next();
		
		String [] thirtyone= {"January","March","May","July","August","October","December"};
		
		String [] thirtydays= {"April","June","September","November"};
				
		String [] feb= {"February"};

		
		for(int i=0;i<7;i++)
		{	
			if(thirtyone[i].equals(month))
			{
					if(day.equals("monday"))
					{
						System.out.println("M\t"+"T\t"+"W\t"+"Th\t"+"Fri\t"+"Sat\t"+"Sun\t");
						
						int p=1;
						for(int j=1;j<=5;j++)
						{
							for(int k=1;k<=7;k++)
							{
								System.out.print(p+"\t");
								if(p!=32)
								{	p++;
									if(p==32)
									break;
								}
							}
							System.out.println();
						}
					
					}
				}
		}
		
		
		
		
						
	}
}
