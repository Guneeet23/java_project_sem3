    package registration;

    import java.sql.Connection;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import EmployeeManager.UserData;
import rooms.roomManager;


    public class Checkout {
        public void emptyroom(Connection con, Scanner sc)
        {

            UserData user = new UserData();
            roomManager rm = new roomManager();
            LocalDate cDate = LocalDate.now();
            try {
                Statement stmt = con.createStatement();

                System.out.println("Enter the room number you want to check out: ");
                int room = sc.nextInt();
                sc.nextLine();

                // System.out.println("Enter checkout date (YYYY-MM-DD): ");
                // String cDate = sc.nextLine();

                String sql="update bookings set check_out_date= '" + cDate + "' where room_id="+room;
                stmt.executeUpdate(sql);
                sql="update roominfo set availability = 'vacant' where room_id=" + room;
                stmt.executeUpdate(sql);
                // sql="select Fname from customer where room_no=" + room;
                // int rs = stmt.executeUpdate(sql);
                // ResultSet rs=stmt.executeQuery(sql);

                // rm.removefromRooms(room);
                //user.DeleteCustomer(rs.getString("Fname"));

            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }


        public void generatebill(Connection con, Scanner sc)
        {

            try {
                Statement stmt = con.createStatement();

                System.out.println("Enter the room number you want to generate bill for: ");
                int room = sc.nextInt();
                sc.nextLine();

                String sql="select * from customer where room_no = " + room;
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();

                String name=rs.getString("Fname") + " " + rs.getString("Lname");
                int days =rs.getInt("no_of_days");
                int deposit = rs.getInt("Deposit");

                sql="select * from roominfo where room_id = " + room;
                rs = stmt.executeQuery(sql);
                rs.next();

                float amount = rs.getFloat("price");
                float famount = (days*amount)-deposit;

                System.out.println("The total remaining amount for " + name + " is: " + famount);

            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
