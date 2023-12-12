package rooms;

import java.util.Scanner;

import DisplayManager.Display;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageRoom implements Display{

    public void addRoom(Connection connection, Scanner sc)
    {
        try{
            Statement stmt = connection.createStatement();

            System.out.println("Enter room number to add: ");
            int roomNum = sc.nextInt();
            sc.nextLine();
            
            if(!roomExists(connection, roomNum))
            {

            System.out.println("Enter bed type (Single or Double): ");
            String bedType = sc.nextLine();

            System.out.println("Enter price (float value): ");
            double price = sc.nextDouble();
            sc.nextLine();

            String sql="insert into roominfo(room_id,bed_type,price) values(" + roomNum + " , '" + bedType + "' , " + price + ")";
            stmt.executeUpdate(sql);

            try  {
                int affectedRows = stmt.executeUpdate(sql);

                if (affectedRows > 0) {
                    System.out.println("Room added succesfully");
                } else {
                    System.out.println("Room addition failed.");
                }
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
            }
            else{
                System.out.println("Room already exists.");
            }

        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }

    public void updatePrice(Scanner sc, Connection connection) 
    {
        String sql="";;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs;
            System.out.println("Enter room number to update: ");
            int roomNumber = sc.nextInt();
            sc.nextLine();

            if(roomExists(connection, roomNumber))
            {

            sql = "select price from roominfo where room_id = " + roomNumber;
            rs=stmt.executeQuery(sql);
            System.out.println("Current price is: " + rs.getInt("price"));
        
            System.out.println("\nEnter new price: ");
            double price = sc.nextDouble();
            sc.nextLine();

            sql="update roominfo set price = " + price + " where room_id = " + roomNumber;
            stmt.executeUpdate(sql);
            }
            else{
                System.out.println("Room doesn't exists.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void RemoveRoom(Connection con, Scanner sc)
    {
        String sql="";
        try {
            Statement stmt = con.createStatement();
            System.out.println("Enter room number to update: ");
            int roomNumber = sc.nextInt();
            sc.nextLine();

            if(roomExists(con, roomNumber))
            {
                sql="delete from roominfo where room_id = " + roomNumber;
                stmt.executeUpdate(sql);
                System.out.println("Room removed successfully.");
            }
            else{
                System.out.println("The room you are trying to remove doesn't exist.");
            }
        }catch(SQLException e){
            System.out.println(e);
        }

    }

    public void updateStatus(Connection con, Scanner sc)
    {
        
        try {
            Statement stmt = con.createStatement();
            ResultSet rs;
            System.out.println("Enter room number to update cleaniness: ");
            int roomNumber = sc.nextInt();
            sc.nextLine();

            if(roomExists(con, roomNumber))
            {
                String sql="select isClean from roominfo where room_id = " + roomNumber;
                rs = stmt.executeQuery(sql);
                rs.next();
                if(rs.getString("isClean").equals("Unclean"))
                {
                    sql="update roominfo set isClean = 'Clean' where room_id = " + roomNumber;
                    System.out.println("Room status changes to clean.");
                }
                else{
                    sql="update roominfo set isClean = 'Unclean' where room_id = " + roomNumber;
                    System.out.println("Room status changes to Unclean.");
                }

            }
            else{
                System.out.println("Room doesn't exist.");
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public void DisplayDetails(Connection con){
        String query = "SELECT distinct * FROM roominfo";
        try{
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            System.out.println("Room Number\tBed Type\tCleanines\tPrice\tStatus");
            while (rs.next()) {
                System.out.printf("%d\t\t%s\t\t%s\t\t%.2f\t%s%n",
                        rs.getInt("room_id"),
                        rs.getString("bed_type"),
                        rs.getString("isClean"),
                        rs.getDouble("price"),
                        rs.getString("Availability"));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    private boolean roomExists(Connection con, int roomNumber) throws SQLException {
        String query = "SELECT distinct * FROM roominfo WHERE room_id=?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, roomNumber);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void adminPowers(Connection con, Scanner sc)
    {
        String runner="y";
        while(runner.equals("y"))
        {
            System.out.println("\n1.Add room \n2.Delist room \n3.Update price \n4.View all room status \n5.Back");
            System.out.println("\nChoose option for managing rooms: ");
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch(choice)
            {
                case 1:
                    addRoom(con, sc);
                    break;
                case 2:
                    RemoveRoom(con, sc);
                    break;
                case 3:
                    updatePrice(sc, con);
                    break;
                case 4:
                    DisplayDetails(con);
                    break;
                case 5:
                    runner="n";
                    break;
            }
        }
    }

}


 



// make a array room which stores only roomnumber and make a func which gives it true of false as available or not and use that while checking in (probably in checkin)
//make arrays room and guests and one which booked rooms in guest and limit max room allotment
//make classes user and rooms and admin and put functions accordingly in them
//this would help in making arrays and segregating them


