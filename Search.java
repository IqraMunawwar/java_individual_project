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

public class Search {

public User loginUser = OnlineDatingSystem2.CurrUser;

public void SerachUsers() {

        Scanner input = new Scanner(System.in);
        String selection = "";
        int countMatches = 0;

        do {
            System.out.println("Enter the name of the user");
            String user_name = input.next();

            System.out.println("Enter the gender");
            String gender_s = input.next();

            System.out.println("Enter the MIN age");

            int min = input.nextInt();

            System.out.println("Enter the MAX age");

            int max = input.nextInt();

            System.out.println("Enter the city");
            String city_s = input.next();

            System.out.println("Enter the interest");
            String interest_s = input.next();

            final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/munawwari1567";
            Connection conn = null;
            Statement st = null;
            ResultSet rs = null;

            try {
                conn = DriverManager.getConnection(DB_URL, "munawwari1567", "1598177");
                st = conn.createStatement();
                rs = st.executeQuery("Select UserName,loginID,gender,age,city,timestamp from profileusers1 where loginID!='" + loginUser.getLoginId() + "' and UserName='" + user_name + "' and gender= '" + gender_s
                        + "'and age between '" + min + "'and '" + max + "' and city= '" + city_s + "'and (interest1= '" + interest_s + "' or interest2 = '" + interest_s + "' or interest3= '" + interest_s + "')");

                while (rs.next()) {
                    String name = rs.getString(1);
                    String id = rs.getString(2);
                    String gend = rs.getString(3);
                    String age = rs.getString(4);
                    String city = rs.getString(5);
                    String lastSeen= rs.getString(6);

                    System.out.println("The output is " + name + "" + id + "," + gend + "," + age + "," + city + "," + interest_s + ".");
                    System.out.println("The last seen of the user is " + lastSeen + ".");
                    countMatches++;
                    SearchSpecificUsers(id);

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
            if (countMatches == 0) {
                System.out.println("Match not found");
            }
            System.out.println("If you want to continue your search? Y/N");
            selection = input.next();
            if (selection.equals("N")) {
                return;
            }

        } while (selection.equals("Y"));

    }

public void SearchSpecificUsers(String id) {

        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/munawwari1567";
        Connection conn = null;
        Statement st = null;

        try {
            conn = DriverManager.getConnection(DB_URL, "munawwari1567", "1598177");
            st = conn.createStatement();

            conn.setAutoCommit(false);

            int r = st.executeUpdate("UPDATE profileusers1 SET noOfViews= noOfViews+1 WHERE loginID='" + id + "'");
            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
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
