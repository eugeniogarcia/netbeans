/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdbc;

import com.microsoft.sqlserver.jdbc.SQLServerXADataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

/**
 *
 * @author Leihta
 */
public class DB_Demo_Quries extends DB_Conn {

        private Connection connection = null;

        // use this to add top 100 to sql field for debugging
        // remove this during update
        private String topQuery = "";

        /**
         * work on some tables
         *
         * @throws SQLException
         */
        public DB_Demo_Quries() throws SQLException {


                // during debugging do this
                this.topQuery = " top 30"; // can be 'top 100'

        }

        /**
         * query example
         */
        public void query1(int EmpleadoID) throws SQLException {


                // open a connection to ms sql db
                connection = this.getConnection();

                //Vamos a controlar las transacciones de forma manual
                connection.setAutoCommit(false);


                String query = "SELECT " + topQuery
                                +  " [ID Empleado],[Vaciones Pendientes],[Ajuste Vacaciones],[Direccion],[Ciudad]  FROM [AdventureWorks].[dbo].[prueba] where [ID Empleado]="+String.valueOf(EmpleadoID);

                try {

                        Statement select = connection.createStatement();
                        ResultSet result = select.executeQuery(query);

                        int i = 0; // getResultSetSize(result);
                        while (result.next()) {
                                //Los indices son 1 BASED
                                int  IDEmpleado = result.getInt(1);
                                int  Vacaciones = result.getInt(2);
                                String Direccion = result.getString(4);
                                String Ciudad = result.getString(5);

                                // make the query and do it
                                updateQuery(i, IDEmpleado, Vacaciones+8);

                                i++;
                        }
                        result.close();

                } catch (Exception e) {

                        // debug out output this way
                        System.err.println("Query Statement Error: " + query);
                        e.printStackTrace();

                }
                finally
                {
                    //De forma aleatoria hacemos commit o no
                    if(new Random().nextBoolean())
                    {
                        connection.commit();
                    }
                    else
                    {
                        connection.rollback();
                    }


                    // close the connection when done
                    connection.close();
                }

        }

        public void query1() throws SQLException {

                // open a connection to ms sql db
                connection = this.getConnection();

                String query = "SELECT " + topQuery
                                +  " [ID Empleado],[Vaciones Pendientes],[Ajuste Vacaciones],[Direccion],[Ciudad]  FROM [AdventureWorks].[dbo].[prueba]";

                try {

                        Statement select = connection.createStatement();
                        ResultSet result = select.executeQuery(query);

                        int i = 0; // getResultSetSize(result);
                        while (result.next()) {
                                int  IDEmpleado = result.getInt(1);
                                int  Vacaciones = result.getInt(2);
                                String Ciudad = result.getString(3);

                                // make the query and do it
                                updateQuery(i, IDEmpleado, Vacaciones+8);
                                i++;
                        }
                        result.close();

                } catch (Exception e) {

                        // debug out output this way
                        System.err.println("Query Statement Error: " + query);
                        e.printStackTrace();

                }
                finally
                {
                    // close the connection when done
                    connection.close();
                }
        }

        /**
         * update query example
         *
         * @param i
         * @param recordID
         * @param center
         */
        private void updateQuery(int i, int  recordID, int valor) {

                String query = "UPDATE prueba SET [Vaciones Pendientes]=" + String.valueOf(valor)+
                        " WHERE [ID Empleado]=" + String.valueOf(recordID)+ ";";

                // debug
                System.out.println(i + ". " + query);

                try {

                        Statement update = connection.createStatement();
                        update.executeUpdate(query);

                } catch (Exception e) {
                        // debug out output this way
                        System.err.println("Mysql Statement Error: " + query);
                        e.printStackTrace();
                }
        }

        public void queryDistribuida() throws SQLException, IOException, XAException
        {
            // open a connection to ms sql db
            connection = this.getConnection();

            // Create a test table.
             Statement stmt = connection.createStatement();
             try {stmt.executeUpdate("DROP TABLE XAMin"); }catch (Exception e) {}
             stmt.executeUpdate("CREATE TABLE XAMin (f1 int, f2 varchar(max))");
             stmt.close();
             connection.close();

         // Create a logger.
         Logger logger = Logger.getLogger("com.microsoft.sqlserver.jdbc.internals.XA");
         FileHandler fh = new FileHandler("outputLog.txt");
         logger.addHandler(fh);
         logger.setLevel(Level.FINE);

         // Create the XA data source and XA ready connection.
         SQLServerXADataSource ds = new SQLServerXADataSource();
         ds.setUser("egsmartin");
         ds.setPassword("vera1511");
         ds.setServerName("LEITA-PC");
         ds.setPortNumber(1433);
         ds.setDatabaseName("AdventureWorks");
         XAConnection xaCon = ds.getXAConnection();
         connection = xaCon.getConnection();

         // Get a unique Xid object for testing.
         XAResource xaRes = null;
         Xid xid = null;
         xid = XidImpl.getUniqueXid(1);

         // Get the XAResource object and set the timeout value.
         xaRes = xaCon.getXAResource();
         xaRes.setTransactionTimeout(0);

         // Perform the XA transaction.
         System.out.println("Write -> xid = " + xid.toString());
         xaRes.start(xid,XAResource.TMNOFLAGS);
         //Este acceso se hace con un prepared statement. Esta forma de acceder nos permite definir parámetros (que sustituiran los ?; Los parametros
         //empiezan a contar por 1; Pueden ser de entrada o de salida - en este caso solo de entrada.
         PreparedStatement pstmt = connection.prepareStatement("INSERT INTO XAMin (f1,f2) VALUES (?, ?)");
         pstmt.setInt(1,1);
         pstmt.setString(2,xid.toString());
         pstmt.executeUpdate();

         // Commit the transaction.
         xaRes.end(xid,XAResource.TMSUCCESS);
         xaRes.commit(xid,true);

         // Cleanup.
         pstmt.close();
         connection.close();
         xaCon.close();

         // open a connection to ms sql db
         connection = this.getConnection();
         ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM XAMin");
         rs.next();
         System.out.println("Read -> xid = " + rs.getString(2));
         rs.close();
        }


}