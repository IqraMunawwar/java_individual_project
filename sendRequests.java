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

/**
 *
 * @author sayed
 */
public class sendRequests {
    LogOut logout = new LogOut();
    
    public User loginUser = OnlineDatingSystem2.CurrUser;
    
    public  void SendRequests()
    {
        
        Scanner input = new Scanner(System.in);
        String selection = "";
        
        while(!selection.equals("x") || !selection.equals("X"))
        {
            System.out.println("What do you want to Send?");
            System.out.println("1: Send Messages");
            System.out.println("2: Send Friend requests");
            System.out.println("3: Return to previous menu");
            System.out.println("4: Logout");
       
            selection = input.nextLine();
            System.out.println();
         
            switch (selection) {
               case "1":
                    SendMessage();
                    break;          
               case "2":
                    SendFriendRequest();             
                    break;
               case "3":
                   return;
               case "4":
                   logout.logout();
                   break;
            }
        }
     
    }
    
    public void SendMessage()
    {
        Scanner input = new Scanner(System.in);
        String friend_id = "";
        System.out.println("Enter the id of the friend you want to send request");
        friend_id = input.nextLine();
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/munawwari1567";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(DB_URL, "munawwari1567", "1598177");
            st = conn.createStatement();
            rs = st.executeQuery("Select * from friends where status='approved' and (receiver_id='"+loginUser.getLoginId()+"' and sender_id='"+friend_id+"') or (sender_id='"+loginUser.getLoginId()+"' and receiver_id='"+friend_id+"')");
            if (rs.next()) 
            {
                System.out.println("Enter the message you want to send");
                
                String input_message= input.nextLine();
                
                int msg= st.executeUpdate("insert into messages values('"+friend_id+"','"+ loginUser.getLoginId() +"','"+ input_message +"')");
                System.out.println("Message sent to the friend succesfully");
            } 
            else
            {
                System.out.println("You need to be a friend to send messages");            
            }
        }
        catch (SQLException e) {
            e.printStackTrace();//print out the exception
        } finally {

            try {
                conn.close();
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
        
        
        
    }
 
    public void SendFriendRequest() 
    {
        Scanner input = new Scanner(System.in);
        String friend_id = "";
        System.out.println("Enter the id of the friend you want to send request");
        friend_id = input.next();
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/munawwari1567";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(DB_URL, "munawwari1567", "1598177");
            st = conn.createStatement();
            rs = st.executeQuery("Select * from profileusers1 where loginID = '" + friend_id + "'");

            if (rs.next()) {
                String statusOfRequest = "";
                rs = st.executeQuery("Select status from friends where (receiver_Id = '" + friend_id + "' and sender_id = '" +  loginUser.getLoginId() + "') OR (receiver_Id = '" +  loginUser.getLoginId() + "' and Sender_id = '" + friend_id + "')");

                if (rs.next()) {
                    statusOfRequest = rs.getString(1);
                    if (statusOfRequest.equals("approved")) {
                        System.out.println("Friendship established");
                    } else if (statusOfRequest.equals("denied")) {
                        System.out.println("Friendship denied");
                    } else if (statusOfRequest.equals("pending")) {
                        System.out.println("Friendship pending");
                    } else {
                        int res = st.executeUpdate("Insert into friends Values "
                                + "('" + friend_id + "', '" + loginUser.getLoginId() + "', 'pending')");
                        System.out.println("Friend Request sent");
                    }

                } else {
                    int fes = st.executeUpdate("Insert into friends Values "
                            + "('" + friend_id + "', '" + loginUser.getLoginId() + "', 'pending')");
                    System.out.println("Friend Request sent");
                }
            } else {
                System.out.print("Friend not found");
            }

        } catch (SQLException e) {
            e.printStackTrace();//print out the exception
        } finally {

            try {
                conn.close();
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }

    }
    
    
}    
       
      
       





       
       
        

     
    
    
    

        
        
   
