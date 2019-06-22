/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misqljdbc;
import java.sql.*; 
/**
 *
 * @author Eugenio
 */
public class MiSQLJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
            try
        { 
            // Load the SQLServerDriver class, build the 
            // connection string, and get a connection
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            String connectionUrl = "jdbc:sqlserver://EUGENIO\\Eugenio;" + 
                "database=DBName;" + 
                "user=UserName;" + 
                "password=Password"; 
            Connection con = DriverManager.getConnection(connectionUrl); 
            System.out.println("Connected."); 
            // Create and execute an SQL statement that returns some data.  
            String SQL = "SELECT CustomerID, ContactName FROM Customers"; 
            Statement stmt = con.createStatement(); 
            ResultSet rs = stmt.executeQuery(SQL); 
            // Iterate through the data in the result set and display it.  
            while (rs.next()) 
            {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            } 
        }
        catch(Exception e)
        { 
            System.out.println(e.getMessage()); 
            System.exit(0); 
        } 
    }
}
