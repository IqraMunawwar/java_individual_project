/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinedatingsystem2;
import java.util.Scanner;

public class WelcomeScreen {
    public    Search search = new Search();
    public    ViewNotifications viewN =new ViewNotifications();
    public    LogOut logout= new LogOut();
    public    sendRequests send = new sendRequests();

    User loginUser = OnlineDatingSystem2.CurrUser;
 
    public void welcome() {
        
        
        System.out.println( "Welcome to your Account " + loginUser.getUserName() + " & user_id: " + loginUser.getLoginId());
     
        Scanner input = new Scanner(System.in);
        String selection = "";
        
        while(!selection.equals("x") || !selection.equals("X"))
        {
            System.out.println("Select what you want to do? ");
            System.out.println("1: Search for users");
            System.out.println("2: View Messages,Requests,Friend Lists or Users");   
            System.out.println("3: Send Messages or Requests to Friends");
            System.out.println("4: Return to previous menu");
            System.out.println("5: Logout");
         
            selection = input.nextLine();
            System.out.println();
         
            switch (selection) {
               case "1":
                   search.SerachUsers();
                    break;
              case "2":
                   viewN.ViewNotification();             
                    break;
             case "3":
                   send.SendRequests();
                   break;
             case "4":
                   return;
             case "5":
                   logout.logout();
                   break;
            }
        }
    }
}

    

