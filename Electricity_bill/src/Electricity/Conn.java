package Electricity;

import java.sql.*;  

public class Conn{
    Connection c;
    Statement s;
    public Conn(){  
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            c =DriverManager.getConnection("jdbc:mysql:///ebs","root","rpmMySQL@123");    
            s =c.createStatement();
            if(c.isClosed()){
                System.out.println("Connection Closed");
            }
            else{
                System.out.println("Connection Open..");
            }
            
           
        }catch(Exception e){ 
            System.out.println(e);
        }  
    }
    public static void main(String[] args) {
        new Conn();
    }
}  