package DisplayManager;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DisplayUser {
    public static void UserStalker(Connection con)
    {
        String viewBookingsSQL = "SELECT distinct c.*,b.check_out_date FROM Bookings as b left join customer as c on b.cust_id = c.cust_id";

        try  {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(viewBookingsSQL);
            System.out.println("Current Bookings: ");

            while (rs.next()) {
                int custId = rs.getInt("cust_id");
                String Fname = rs.getString("Fname");
                String Lname = rs.getString("Lname");
                String gender = rs.getString("Gender");
                String email = rs.getString("Email");
                String phone = rs.getString("PhoneNo");
                int roomId = rs.getInt("room_no");
                String checkInDate = rs.getString("check_in_date");
                String checkOutDate = rs.getString("check_out_date");
                int no_of_days= rs.getInt("no_of_days");

                System.out.println("Customer ID: " + custId + "\nFirst Name: " + Fname + "\nLast Name: " + Lname + "\nGender: " + gender +
                        "\nEmail id: " + email + "\nPhone no.: " + phone + "\nRoom ID: " + roomId +
                        "\nCheck-in Date: " + checkInDate +
                        "\nCheck-out Date: " + checkOutDate + "\nNo. of Days of Stay: " + no_of_days);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
}
