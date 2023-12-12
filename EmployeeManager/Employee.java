package EmployeeManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;
import java.time.LocalDate;

import DisplayManager.Display;

public class Employee implements Display{


    public void addEmployee(Connection conn, Scanner sc)
    {
        int tempid;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from employeeinfo order by employee_id desc");
            if(rs.next()) {
                tempid=rs.getInt("employee_id");
                tempid++;
                }
                else{
                    tempid=1;
                }

            System.out.println("Enter employee first name: ");
            String Fname = sc.nextLine();
            System.out.println("Enter employee last name: ");
            String Lname = sc.nextLine();
            System.out.println("Enter employee position: ");
            String position = sc.nextLine();
            System.out.println("Enter employee email: ");
            String email = sc.nextLine();
            System.out.println("Enter employee phone number: ");
            String phoneno = sc.nextLine();
            System.out.println("Enter employee date of birth: ");
            String dob = sc.nextLine();

            LocalDate date_of_hire = LocalDate.now();
            
            System.out.println("Enter employee salary: ");
            int salary = sc.nextInt();
            

            //employee_id,first_name,last_name,position,Email,Phoneno,date_of_birth,date_of_hire,salary
            String sql = "INSERT INTO EmployeeInfo () " + "VALUES ("+tempid + " , '" + Fname + "', '" + Lname + "' , '" + position + "' , '" + email +
             "' , " + phoneno + " , '" + dob + "' , '" + date_of_hire + "' , " + salary + "  )";

        

            try  {
                int affectedRows = stmt.executeUpdate(sql);

                if (affectedRows > 0) {
                    System.out.println("Hired successful!");
                } else {
                    System.out.println("Hiring failed.");
                }
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fireEmployee(Connection conn, Scanner sc)
    {
        try {
            Statement stmt = conn.createStatement();
            
            System.out.println("Enter employee id to fire: ");
            int emp_id= sc.nextInt();
            sc.nextLine();
            System.out.println("Enter employee first name: ");
            String name = sc.nextLine();
            

            String sql = "DELETE FROM EmployeeInfo WHERE employee_id = " + emp_id + " and  first_name = '" + name + "'";

            int rowsAffected = stmt.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println("Employee fired successfully.");
            } else {
                System.out.println("Employee deletion failed");
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }

    }

    public void DisplayDetails(Connection conn)
    {
        String sql = "SELECT distinct e.*, l.name as username, l.pass FROM EmployeeInfo e LEFT JOIN login l ON e.first_name = l.Name";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int employeeId = rs.getInt("employee_id");
                    String employeeFirstName = rs.getString("first_name");
                    String employeeLastName = rs.getString("last_name");
                    String position = rs.getString("position");
                    String email = rs.getString("Email");
                    String phoneNo = rs.getString("PhoneNo");
                    String dateOfBirth = rs.getString("date_of_birth");
                    String dateOfHire = rs.getString("date_of_hire");
                    float salary = rs.getFloat("salary");
                    String username =rs.getString("username");
                    String password = rs.getString("pass");

                    System.out.println("Employee ID: " + employeeId);
                    System.out.println("First Name: " + employeeFirstName);
                    System.out.println("Last Name: " + employeeLastName);
                    System.out.println("Position: " + position);
                    System.out.println("Email: " + email);
                    System.out.println("Phone Number: " + phoneNo);
                    System.out.println("Date of Birth: " + dateOfBirth);
                    System.out.println("Date of Hire: " + dateOfHire);
                    System.out.println("Salary: " + salary);
                    System.out.println("Username: " + username);
                    System.out.println("Password: " + password);
                    System.out.println("\n");
                }

        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    

    public void adminPower(Connection con, Scanner scanner)
    {
        String runner="y";
        while(runner.equals("y"))
        {
            System.out.println("\n1.Hire emplyee \n2.Fire employee \n3.View all employee details \n4.Back");
            System.out.println("Enter your choice: ");
            int choice =scanner.nextInt();
            scanner.nextLine();

            switch(choice)
            {
                case 1:
                    addEmployee(con, scanner);
                    break;
                
                case 2:
                    fireEmployee(con, scanner);
                    break;

                case 3:
                    DisplayDetails(con);
                    break;
                
                case 4:
                    runner="n";
                    break;
            }
        }
    }
    
}

