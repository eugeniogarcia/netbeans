package ejercicio1;

import java.util.Collection;

import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import javax.rmi.PortableRemoteObject;

public class UsuarioClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            final UsuarioHome usuarioHome = 
                (UsuarioHome) PortableRemoteObject.narrow( context.lookup( "Usuario" ), UsuarioHome.class );
            Usuario usuario;
            // Use one of the create() methods below to create a new instance
            // usuario = usuarioHome.create(  );
            // usuario = usuarioHome.create(  usuario,  clave );
            // Retrieve all instances using the findAll() method (CMP Entity beans only)
            final Collection coll = usuarioHome.findAll();
            final Iterator iter = coll.iterator();
            while ( iter.hasNext() ) {
                usuario = ( Usuario ) iter.next();
                System.out.println( "usuario = " + usuario.getUsuario() );
                System.out.println( "clave = " + usuario.getClave() );
                System.out.println();
            }
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
