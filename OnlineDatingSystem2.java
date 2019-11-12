/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinedatingsystem2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author sayed
 */
public class OnlineDatingSystem2 {
  
Scanner  scanner = new Scanner(System.in);
 
public static User CurrUser ;

public static void main(String[] args) 
{

    OnlineDatingSystem2 os = new OnlineDatingSystem2();
        os.MainMenu();       
        
    }
    
public void MainMenu()
{    
    String selection = "";
        
    OUTER:
    while (!selection.equals("x") || !selection.equals("X"))
    {
        System.out.println("Please make your selection");
        System.out.println("1: Signup for creating a new profile");
        System.out.println("2: Login into your account");
        System.out.println("x: Finish");
        selection = scanner.nextLine();
        System.out.println();
        switch (selection) {
            case "1":                 
                register();
                break;
            case "2":           
                login();
                break;
            case "X":
            case "x":
                break OUTER;
        }
    }     
}
    
public void register()
{
    Date date = new Date();
    String loginID="",name, password,gender,city,interest1,interest2,interest3;
    int age;

    Scanner input = new Scanner(System.in);

    System.out.println("Please enter the Name:");
    name = input.next();

    System.out.println("Please enter the Password:");
    password = input.next();

    System.out.println("Please enter the Gender: M | F");
    gender = input.next();

    System.out.println("Please enter the Age: Between 18 and 45");
    age = input.nextInt();

    System.out.println("Please enter the City:");
    city = input.next();

    System.out.println("Please enter the Interest 1:");
    interest1 = input.next();

    System.out.println("Please enter the Interest 2:");
    interest2 = input.next();

    System.out.println("Please enter the Interest 3:");
    interest3 = input.next();
       
    
    final String DB_URL= "jdbc:mysql://mis-sql.uhcl.edu/munawwari1567";
    Connection conn=null; 
    Statement st=null; 
    ResultSet rs=null;    
        
        try
        {
         conn= DriverManager.getConnection(DB_URL,"munawwari1567","1598177");
         st= conn.createStatement();
         rs=st.executeQuery("Select * from nextloginid");
         
         int nextNum=0;
         if(rs.next()) 
           {
            loginID= "" +rs.getInt(1);
            nextNum=rs.getInt(1) + 1;    
         
           }
         
         conn.setAutoCommit(false);
         
         int t=st.executeUpdate("Update nextloginid set nextLogin='" +nextNum+ "'"); 
         int r =st.executeUpdate("Insert into profileusers1( `UserName`, `loginID`, `password`,"
                 + " `gender`, `age`, `city`, `interest1`, `interest2`, `interest3`,"
                 + " `noOfViews`, `timestamp`) values('"+name+"', '"+loginID+"','"
                 +password+"','" +gender+"','" +age+"','" +city+ "','" +interest1+ "','" 
                 +interest2+ "','" +interest3+ "','" +0+ "','" +date+ "')");
        
         conn.commit();
         conn.setAutoCommit(true);
         
         System.out.println("***New User created with login id is " + loginID + "!***");
        
        }
        catch(SQLException e)
        {
        e.printStackTrace();//print out the exception
        }
        finally
        {
        //close the database
            try
            {
            conn.close();
            st.close();
            rs.close();
            }
            catch(SQLException e)
            {
            e.printStackTrace();
            
            }
            
            
            
        }
        
}
  
public void login()
{
        
        System.out.println("Please enter your ID");
        String id = scanner.next();
        
        System.out.println("Please enter your password");
        String password = scanner.next();
        
        
        final String DB_URL= "jdbc:mysql://mis-sql.uhcl.edu/munawwari1567";
        Connection conn=null; 
        Statement st=null; 
        ResultSet rs=null; 
        
        try
        {
         
            conn= DriverManager.getConnection(DB_URL,"munawwari1567","1598177");
            st= conn.createStatement();
         
            rs=st.executeQuery("Select * from profileusers1 where loginID= '" +id + "' and password = '" + password + "'");
         
            if(rs.next())
             
                {
                System.out.println("Login success");
                CurrUser = new User(rs.getString(2), rs.getString(3));
           
            
                WelcomeScreen ws = new WelcomeScreen();
                ws.welcome();
                }
         
            else 
                {
                System.out.println("Login failure");
                return;
                 }
        }
        catch(SQLException e)
        {
        e.printStackTrace();//print out the exception
        }
        finally
        {
        //close the database
            try
            {
            conn.close();
            st.close();
            rs.close();
            }
            catch(SQLException e)
            {
            e.printStackTrace();
            
            }     
        }
        
    }
      
}
