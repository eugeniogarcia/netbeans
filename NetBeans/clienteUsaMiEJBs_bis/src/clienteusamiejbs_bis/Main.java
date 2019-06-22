/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clienteusamiejbs_bis;
import EjemploEJB.NewSession3Remote;
import java.math.BigDecimal;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 *
 * @author Eugenio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Properties props = new Properties();
        props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        props.setProperty("org.omg.CORBA.ORBInitialPort","47478");
        props.setProperty("org.omg.CORBA.ORBInitialHost","localhost");

        try {
            InitialContext ic = new InitialContext(props);
            NewSession3Remote  converter = (NewSession3Remote ) ic.lookup(NewSession3Remote.class.getName());
            System.out.println("2000 yens son :"+converter.yenToEuro(new BigDecimal(2000)));
            }
        catch (NamingException ex) {
                System.out.println(ex.toString());
            }
    }

}
