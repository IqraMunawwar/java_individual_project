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

public class ViewNotifications {

    public User loginUser = OnlineDatingSystem2.CurrUser;
    LogOut logout = new LogOut();
    ViewHighlyRanked rank = new ViewHighlyRanked();

    public void ViewNotification()
    {

        Scanner input = new Scanner(System.in);
        String selection = "";
        

        while (!selection.equals("x") || !selection.equals("X")) {
        
            System.out.println("Choose what you need to View");
            System.out.println("1: View Friend Requests");
            System.out.println("2: View Messages");
            System.out.println("3: View Profile of users");
            System.out.println("4: View Highly ranked users");
            System.out.println("5: View Friend List");
            System.out.println("6: Return to previous menu");
            System.out.println("7: Logout");

            selection = input.nextLine();
            System.out.println();

            switch (selection) {
                case "1":
                    ViewFriendRequests();
                    break;
                case "2":
                    ViewMessages();
                    break;
                case "3":
                    ViewProfile();
                    break;
                case "4":
                    rank.ViewR();
                    break;
                case "5":
                    ViewFriendList();
                    break;
                case "6":
                    return;
                case "7":
                    logout.logout();                 
                    break;
            }

        }
    }
    
    public void ViewFriendList()
    {
    Scanner input = new Scanner(System.in);
    String selection = "";      
        
   
     do{
                   final String DB_URL= "jdbc:mysql://mis-sql.uhcl.edu/munawwari1567";
                   Connection conn=null; 
                   Statement st=null; 
                   ResultSet rs=null;
       
            try
                {
                    conn= DriverManager.getConnection(DB_URL,"munawwari1567","1598177");
                    st= conn.createStatement();
                    String sql= "select UserName,loginID,gender,age,city from profileusers1 "
                            + "where loginID in (select friend_id from friendlist where loginID!='" + loginUser.getLoginId() + "')";
                    rs= st.executeQuery(sql);                    
                  
                    while(rs.next())
                    {    
                         String name=rs.getString(1);
                         String id = rs.getString(2);
                         String gend= rs.getString(3);
                         String age= rs.getString(4);
                         String city= rs.getString(5);
                         

                         System.out.println("Friends are: " +name+ "," +id+ "," +gend+ "," +age+ "," +city+ ".") ;
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
    
    public void ViewProfile()
    {
    Scanner input = new Scanner(System.in);
    String selection = "";      
        
   
     do{
                   final String DB_URL= "jdbc:mysql://mis-sql.uhcl.edu/munawwari1567";
                   Connection conn=null; 
                   Statement st=null; 
                   ResultSet rs=null;
       
            try
                {
                    conn= DriverManager.getConnection(DB_URL,"munawwari1567","1598177");
                    st= conn.createStatement();

                    rs=st.executeQuery("Select UserName,loginID,gender,age,city,interest1,interest2,interest3 from profileusers1 where loginID!='" + loginUser.getLoginId() + "'");



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
                         
                         

                         System.out.println("Other Profile users are: " +name+ "," +id+ "," +gend+ "," +age+ "," +city+ "," +int1+ "," +int2+ "," +int3+  ".") ;
                         
                         
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
 
    public void ViewMessages()
    {
      Scanner input = new Scanner(System.in);
      String selection = "";      
        
   
     do{
                   final String DB_URL= "jdbc:mysql://mis-sql.uhcl.edu/munawwari1567";
                   Connection conn=null; 
                   Statement st=null; 
                   ResultSet rs=null;
       
            try
                {
                    conn= DriverManager.getConnection(DB_URL,"munawwari1567","1598177");
                    st= conn.createStatement();
                    String sql= "select * from messages where (receiver_id='"+loginUser.getLoginId()+"' or sender_id='"+loginUser.getLoginId()+"')";
                    rs= st.executeQuery(sql); 
                    
                    
                    if (rs.next() == false)
                    { 
                        System.out.println("No messages to display");
                    }
                    else 
                    { 
                        System.out.println("Messages received are as follows");
                        System.out.println("Friend" + "\t" + "Message");
                        
                        do 
                        {   
                            String msg= rs.getString(3);
                            String msg_sender= rs.getString(2);
                            System.out.println(msg_sender + "\t" + msg);
                        } while (rs.next()); 
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
                   
		   System.out.println("If you want to continue looking at messages? Y/N");
                   selection = input.next();
                   if(selection.equals("N")) 
                   {
                   return;
                   }             
                   
        } while(selection.equals("Y"));
        
        
    
    
    
    }

    public void ViewFriendRequests()
    {
        Scanner input = new Scanner(System.in);
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/munawwari1567";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try 
        {
            conn = DriverManager.getConnection(DB_URL, "munawwari1567", "1598177");
            st = conn.createStatement();
            rs = st.executeQuery("Select receiver_id,sender_id,status from friends where receiver_id='" + loginUser.getLoginId() + "'and status = 'pending'");
          
                    if (rs.next() == false)
                    { 
                        System.out.println("No requests to approve | deny  | view ");
                    }
                    else 
                    { 
                        
                        do 
                        {  
                            System.out.println("All available requests which are pending");
                            String send_id = rs.getString(2);
                            System.out.println(send_id);
                            System.out.println("Enter a login id to accept or decline request:");
                            String received_Id = input.next();
                            AcceptOrDeny(received_Id, loginUser.getLoginId());
                            
                        } while (rs.next()); 
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
                           
    public void AcceptOrDeny(String received_id, String id)
    {
        Scanner input = new Scanner(System.in);
        String selection = "";
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/munawwari1567";
        Connection conn = null;
        Statement st = null;

        try {
            conn = DriverManager.getConnection(DB_URL, "munawwari1567", "1598177");
            st = conn.createStatement();

            while (!selection.equals("x") || !selection.equals("X")) {

                System.out.println("Please make your selection");
                System.out.println("1: Accept Friend Request");
                System.out.println("2: Deny Friend Request");
                System.out.println("3: Return to previous menu");
                selection = input.next();

                if (selection.equals("1")) {
                    String sql = "UPDATE `friends` SET `status`= 'approved' WHERE `receiver_id` = '" + id + "' and `sender_id` = '" + received_id + "'";
                    st.executeUpdate(sql);
                    System.out.println("Friendship established");
                    sql= "INSERT INTO `friendlist`(`user_id`, `friend_id`) VALUES ( "+ id +" , "+ received_id +" )";
                    st.executeUpdate(sql);                    
                    break;
                }
                if (selection.equals("2")) {
                    String sql = "UPDATE `friends` SET `status`= 'denied' WHERE `receiver_id` = '" + id + "' and `sender_id` = '" + received_id + "'";
                    st.executeUpdate(sql);
                    System.out.println("Friendship request denied");
                    break;
                }
                if (selection.equals("3")) {
                    return;
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();//print out the exception
        } finally {

            try {
                conn.close();
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }

    }

}
