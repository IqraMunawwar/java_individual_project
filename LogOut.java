/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinedatingsystem2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LogOut {
   
public User loginUser;
     
public void logout()
{
        loginUser= OnlineDatingSystem2.CurrUser;
        System.out.println("User" + loginUser.getLoginId() + "is logged out now");
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/munawwari1567";
        Connection conn = null;
        Statement st = null;
       

        try {
            conn = DriverManager.getConnection(DB_URL, "munawwari1567", "1598177");
            st = conn.createStatement();
            int  rs = st.executeUpdate("Update profileusers1 set timestamp='" +DateAndTime.DateTime()+ "' where loginID='"+loginUser.getLoginId()+"'" );
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
                            
                            }
            
                        catch(SQLException e)
                           {
                            e.printStackTrace();
            
                           }
                        }
        
        OnlineDatingSystem2 os = new OnlineDatingSystem2();
        os.MainMenu(); 
        
    }
    
}
