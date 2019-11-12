/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinedatingsystem2;

/**
 *
 * @author sayed
 */
public class User {
 
    private  String LoginId;
    private  String UserName;

    public User( String UserName, String LoginId) {
        this.LoginId = LoginId;
        this.UserName = UserName;
    }


    public String getLoginId() {
        return LoginId;
    }

    public void setLoginId(String LoginId) {
        this.LoginId = LoginId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    
    
    

   

    @Override
    public String toString() {
        
        //To change body of generated methods, choose Tools | Templates.
        String user = getUserName() + " " + getLoginId();
        return user;
    }
    
    
    
}
