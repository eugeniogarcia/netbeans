package sesionbean;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import javax.rmi.PortableRemoteObject;

public class SimpleClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            final SimpleHome simpleHome = 
                (SimpleHome) PortableRemoteObject.narrow( context.lookup( "Simple" ), SimpleHome.class );
            Simple simple;
            simple = simpleHome.create();            
            
            // Call any of the Remote methods below to access the EJB
            System.out.println(simple.calcular(1,30));
            System.out.println(simple.mostrar());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Context getInitialContext() throws NamingException {
        // Get InitialContext for Embedded OC4J
        // The embedded server must be running for lookups to succeed.
        return new InitialContext();
    }
}
