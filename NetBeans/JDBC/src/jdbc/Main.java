/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdbc;

import java.io.IOException;
import java.sql.SQLException;
import javax.transaction.xa.XAException;

/**
 *
 * @author Leihta
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException, XAException {
        // TODO code application logic here
        new DB_Demo_Quries().query1(2);
       
        new DB_Demo_Quries().queryDistribuida();


    }

}
