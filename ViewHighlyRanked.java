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
import java.util.Scanner;

public class ViewHighlyRanked {
    public User loginUser = OnlineDatingSystem2.CurrUser;
    Search search = new Search();
    
    LogOut logout = new LogOut();
    
    public void ViewR()
    {
      Scanner input = new Scanner(System.in);
      String selection = "";
        
      while (!selection.equals("x") || !selection.equals("X")) {
            
            System.out.println("Choose what you need to View");
            System.out.println("1: View Top 3 Ranked Males ");
            System.out.println("2: View Top 3 Ranked Females");
            System.out.println("3: Return to previous menu");
            System.out.println("4: Logout");

            selection = input.nextLine();
            System.out.println();

            switch (selection) {
                case "1":
                    Top3("M");
                    break;
                case "2":
                    Top3("F");
                    break;
                case "3":
                    return;
                case "4":
                    logout.logout();                 
                    break;
            }
    
    }
    
    
}
    
    public void Top3(String gender)
    {
      Scanner input = new Scanner(System.in);
      String selection = ""; 
      System.out.println("");
        
   
     do{
                   final String DB_URL= "jdbc:mysql://mis-sql.uhcl.edu/munawwari1567";
                   Connection conn=null; 
                   Statement st=null; 
                   ResultSet rs=null;
       
            try
                {
                    conn= DriverManager.getConnection(DB_URL,"munawwari1567","1598177");
                    st= conn.createStatement();
                    String sql= "SELECT loginID,noOfViews FROM profileusers1 where loginID != '"+loginUser.getLoginId() + "' and gender='"+gender+"' order by noOfViews desc limit 3 ";                    
                    rs= st.executeQuery(sql);                    
                    System.out.println("Login ID" + "\t" + "No of Views");
                   
                    while(rs.next())
                    {   
                         String login_id=rs.getString(1);
                         String no = rs.getString(2);
                         System.out.println( login_id  + "\t\t\t" + no ) ;
                    }
                    
                    System.out.println("Do you want to view user profile with high rank : yes | no ");
                    String select = input.next();
                       switch (select) {
                           case "yes":
                               System.out.println("Enter the user id you need to view");
                               String user_id= input.next();
                               rs=st.executeQuery("Select UserName,loginID,gender,age,city,interest1,interest2,interest3 from profileusers1 where loginID='"+ user_id +"' and loginID!='" + loginUser.getLoginId() + "'");
                               while(rs.next())
                               {
                                   String name=rs.getString(1);
                                   String id = rs.getString(2);
                                   String gend= rs.getString(3);
                                   String age= rs.getString(4);
                                   String city= rs.getString(5);
                                   String int1=rs.getString(6);
                                   String int2=rs.getString(7);
                                   String int3=rs.getString(8);
                                   
                                   System.out.println("Users details are: " +name+ "," +id+ "," +gend+ "," +age+ "," +city+ "," +int1+ "," +int2+ "," +int3+  ".") ;
                                   search.SearchSpecificUsers(user_id);
                               }      break;
                           case "no":
                               return;
                       }

                }    
            
            catch(SQLException e)
                        {
                            e.printStackTrace();//print out the exception
                        }

            finally
                        {

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
                   
		   System.out.println("If you want to continue your search? Y/N");
                   selection = input.next();
                   if(selection.equals("N")) 
                   {
                   return;
                   }             
                   
        } while(selection.equals("Y"));
    
    
    }
    

    
}

