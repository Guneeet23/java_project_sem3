package EmployeeManager;

public class UserData 
{
    String[] activestaff = new String[10];
    String customerarr[] = new String[10];
    public int userCount = 0;
    public int customerCount = 0;


    public void addController(String username)
    {
        activestaff[userCount] = username;
        userCount++;
    }


    public  void removeController(String username)
    {
        int index = finder(activestaff, username);
        if(index!=-1)
        {
            activestaff[index] = null;
            userCount--;
            for(int x=index; x<userCount;x++)
            {
                activestaff[x] = activestaff[x+1];
            }
        }
        else{
            System.out.println("Error logging out");
        }
    }


    public int finder(String[] array, String word) {
        for (int i = 0; i < array.length; i++) {
            if (array[i]==word) {
                return i; 
            }
        }
        return -1; 
    }

    public void displayStaff()
    {
        System.out.println("Current active staff is: " + userCount);
        for(int i = 0; i < userCount; i++)
        {
            System.out.println((i+1) + ". " + activestaff[i]);
        }
    }

    public void addCustomer(String name)
    {
        customerarr[customerCount] = name;
        customerCount++;
    }

    public  void DeleteCustomer(String username)
    {
        int index = finder(customerarr, username);
        if(index!=-1)
        {
            customerarr[index] = null;
            customerCount--;
            for(int x=index; x<customerCount;x++)
            {
                customerarr[x] = customerarr[x+1];
            }
        }
        else{
            System.out.println("Customer not found.");
        }
    }

    public void displayCustomer()
    {
        System.out.println("Today's Customers are: " + customerCount);
        for(int i = 0; i < customerCount; i++)
        {
            System.out.println((i+1) + ". " + customerarr[i]);
        }
    }
 
}