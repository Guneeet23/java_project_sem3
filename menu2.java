import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import registration.Checkin;
import registration.Checkout;
import rooms.*;
import EmployeeManager.*;
import DisplayManager.*;


public class menu2 {
    static Scanner sc = new Scanner(System.in);
    static String currentuser;
    public static void main(String[] args) {

        String runner="y";
        String runner2 = "y";
        String runner22 = "y";
        String runner3 = "y";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try 
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management", "root","dps2008");
            
            System.out.println("Welocme to our Hotel");

            while(runner=="y")
            {
                System.out.println("\n\n");
                System.out.println("1.Admin \n2.Staff \n3.Exit");
                System.out.println("\nEnter your authority level: ");

                int authchoice = sc.nextInt();
                sc.nextLine();

                switch (authchoice) {
                    case 1:                        
                        if(authenticator("Admin",con))
                        {
                            System.out.println("\n");
                            while(runner3=="y")
                            {
                                System.out.println("\n------------------------------------------------\n");
                                System.out.println("\n1.Employee Info \n2.Reservations Info \n3.Manage Room \n5.Back");  
                                System.out.println("Enter your choice: ");    

                                int Achoice=sc.nextInt();
                                sc.nextLine();
                                

                                switch(Achoice)
                                {
                                    case 1:
                                        Employee emp = new Employee();
                                        emp.adminPower(con, sc);
                                        break;
                                    case 2:
                                        DisplayUser.UserStalker(con);
                                        break;
                                    case 3:
                                        ManageRoom mng = new ManageRoom();
                                        mng.adminPowers(con, sc);
                                        break;
                                
                                    case 4:
                                        runner3="n";
                                        break;
                                }
                            }
                        }
                        else
                        {
                            System.out.println("Wrong username or password. Please try again.");
                        }
                        break;
                    case 2:
                        if(authenticator("Staff",con))
                        {
                            while (runner2.equals("y")) {
                                System.out.println("\n------------------------------------------------\n");
                                System.out.println("1.Customer CheckIn \n2.Customer CheckOut \n3.Bill generation \n4.Manage rooms \n5.Exit");
                                System.out.println("Enter your choice: "); 
        
                                int Schoice = sc.nextInt();
                                sc.nextLine();
                                Checkout co= new Checkout();
        
                                switch (Schoice) {
                                    case 1:
                                        Checkin ci = new Checkin();
                                        ci.reserveRoom(con, sc);
                                        break;
        
                                    case 2:
                                        co.emptyroom(con, sc);
                                        break;
                                    
                                    case 3:
                                        co.generatebill(con, sc);
                                        break;
                                    
                                    case 4:
                                        while(runner22 == "y") 
                                        {
                                            System.out.println("\n1.Add room \n 2.Update Room Status \n 3.Exit");
                                            int Managechoice=sc.nextInt();
                                            sc.nextLine();
                                            ManageRoom mng= new ManageRoom();
        
                                            switch(Managechoice)
                                            {
                                                case 1:
                                                    mng.addRoom(con, sc);
                                                    break;
                                        
                                                case 2:
                                                    mng.updateStatus(con, sc);
                                                    break;

                                                case 3:
                                                    runner22="n";
                                                    break;
                                            }
        
                                        }
                                        break;

                                           
                                    case 5:
                                        runner2="n";
                                        break;
        
                                }
                                }
                        }
                        else
                        {
                            System.out.println("Wrong username or password. Please try again.");
                        }

                        break;
                    case 3:
                        runner="n";
                        UserData user= new UserData();
                        user.removeController(currentuser);
                        break;
                    default:
                        System.out.println("Invalid choice. Choose again.");
                        break;
                }
            }

        } 
        catch (SQLException e) 
        {
            System.out.println(e);
        }

    }
    

    private static boolean authenticator(String role, Connection con) 
    {
        boolean result =  true;
        UserData staff = new UserData();
        try{
        Statement stmt = con.createStatement();
        ResultSet rs;

        System.out.println("Enter your username: ");
        String username =sc.nextLine();
        currentuser = username;
        System.out.println("Enter your password: ");
        String password = sc.nextLine();

        System.out.println("\n");
        switch(role)
        {
            case "Admin":
                rs=stmt.executeQuery("Select distinct name,pass from login where authority = 'admin'");
                while(rs.next())
                {
                    String user=rs.getString("Name");
                    String pass = rs.getString("Pass");

                    if(user.equals(username) && pass.equals(password) && staff.userCount<=10)
                    {
                        System.out.println("\nAuthentication successful\n");
                        staff.addController(username);
                        return result;
                    }
                    else if(user.equals(username) && pass.equals(password) && staff.userCount>10)
                    {
                        System.out.println("MAximum concurrent users limit reached. PLease try later.");
                    }
                    else{
                        result = false;
                    }
                }
                break;
            
            case "Staff":
                rs=stmt.executeQuery("Select distinct name,pass from login where authority = 'Staff'");
                while(rs.next())
                {
                    String user=rs.getString("Name");
                    String pass = rs.getString("Pass");

                    if(user.equals(username) && pass.equals(password))
                    {
                        System.out.println("\nAuthentication successful\n");
                        staff.addController(username);
                        return result;
                    }
                    else{
                        result = false;
                    }
                }
                break;
        }
        
        }
        catch(SQLException e)
        {
            System.out.println(e);
            //result = false;
        }
        return result;
    }

}


//get room number using name and email id