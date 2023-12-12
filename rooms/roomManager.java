package rooms;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class roomManager {
    Integer rooms[] = new Integer[10];
    int roomcounter = 0;

    public void addtoRooms(int i)
    {
        rooms[roomcounter]=i;
        roomcounter++;
    }

    public void removefromRooms(int i)
    {
        int index = finder(rooms, i);
        if(index!=-1)
        {
            rooms[index] = null;
            roomcounter--;
            for(int x=index; x<roomcounter;x++)
            {
                rooms[x] = rooms[x+1];
            }
        }
        else{
            System.out.println("Room not found");
        }
    }

    public int finder(Integer[] array, int temp) {
        for (int i = 0; i < array.length; i++) {
            if (array[i]==temp) {
                return i; 
            }
        }
        return -1; 
    }

    public void dispOccupied()
    {
        System.out.println("Current occupied rooms: ");
        for(int x=0;x<roomcounter;x++)
        {
            System.out.println(rooms[x] + "\t");
        }
    }


    public void dispFree(Connection con)
    {
        System.out.println("Room free/available currently: ");
        try{
            Statement st = con.createStatement();
            String sql="select distinct room_id from roominfo where availability='vacant'";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next())
            {
                System.out.println(rs.getInt("room_id") + "\t");
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
    }



    // public void dispFree(Connection con)
    // {
    //     System.out.println("Room free/available currently: ");
    //     try{
    //         Statement st = con.createStatement();
    //         String sql="select room_id from roominfo where availability='vacant'";
    //         ResultSet rs = st.executeQuery(sql);
    //         int checker=1;

    //         while(rs.next())
    //         {
    //             for(int i = 0; i <roomcounter;i++)
    //             {
    //                 if(!arrayContains(rooms, rs.getInt("room_id")))
    //                 {
    //                     checker=0;
    //                     break;
    //                 }
    //                 else
    //                 {
    //                     checker=1;
    //                 }
    //             }
    //             if(checker==0)
    //             {
    //                 System.out.println(rs.getInt("room_id") + "\t");
    //             }
    //             else{
    //                 continue;
    //             }
    //         }
            
    //     }catch(SQLException e){
    //         System.out.println(e);
    //     }
    // }

    // public boolean arrayContains(Integer[] array, int value) {
    //     for (Integer element : array) {
    //         if (element==value) {
    //             return true; // Element found in the array
    //         }
    //     }
    //     return false; // Element not found in the array
    // }
    
}

