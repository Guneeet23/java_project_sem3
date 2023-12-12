package registration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;
import EmployeeManager.UserData;
import rooms.roomManager;


public class Checkin {
    public void reserveRoom(Connection connection, Scanner scanner)
    {
        UserData user = new UserData();
        roomManager rm = new roomManager();
        int tempid;
        LocalDate cDate = LocalDate.now();

        // rm.dispOccupied();
        rm.dispFree(connection);
        System.out.println("\n\n");

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from customer order by cust_id desc");

            if(rs.next()) {
            tempid=rs.getInt("cust_id");
            tempid++;
            }
            else{
                tempid=1;
            }

            System.out.println("Enter guest first name: ");
            String fname = scanner.nextLine();
            System.out.println("Enter guest last name: ");
            String lname = scanner.nextLine();
            System.out.println("Enter guest gender (M/F): ");
            String gender = scanner.nextLine();
            System.out.print("Enter guest email: ");
            String email = scanner.nextLine();
            System.out.print("Enter contact number: ");
            String phoneno = scanner.nextLine();
            System.out.print("Enter room number: ");
            int room = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the no. of days of stay: ");
            int days = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the deposit amount: ");
            int deposit = scanner.nextInt();
            scanner.nextLine();

            String sql = "INSERT INTO customer (cust_id,fname,lname,gender,email,phoneno,room_no,check_in_date,no_of_days,deposit) " + 
            "VALUES ("+tempid + " , '" + fname + "', '" + lname + "' , '" + gender + "' , '" + email + "' , " + phoneno + " , " + room +
             " , '" + cDate + "' , " + days + " , " +deposit + ")";

            String name=fname + lname;
            user.addCustomer(name);
            rm.addtoRooms(room);

             try  {
                int affectedRows = stmt.executeUpdate(sql);

                if (affectedRows > 0) {
                    System.out.println("Reservation successful!");
                } else {
                    System.out.println("Reservation failed.");
                }

            //update bookings
            sql="insert into bookings(cust_id,room_id,check_in_date) values(" + tempid + "," + room + ", '" + cDate + "')";
            stmt.executeUpdate(sql);

            //update roominfo
            sql="update roominfo set availability='occupied' where room_id=" + room;
            stmt.executeUpdate(sql);

            }
            catch (SQLException ex){
                ex.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displaydetails()
    {}
}
    

//if no free rooms, display no available room before asking informaation about xustomer