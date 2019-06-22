/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miclienteejb;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import miEjB.BookFacadeRemote;
import misEntidades.Book;

/**
 *
 * @author Eugenio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
        Properties props = new Properties();
        props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        //Direccion del servicio de directorio
        props.setProperty("org.omg.CORBA.ORBInitialPort","3700");
        props.setProperty("org.omg.CORBA.ORBInitialHost","localhost");

        try {
            InitialContext ic = new InitialContext(props);
            BookFacadeRemote  bookEJB = (BookFacadeRemote ) ic.lookup(BookFacadeRemote.class.getName());

            // Creates an instance of book
            Book book_2 = new Book();
            book_2.setTitle("The Hitchhiker's Guide to the Galaxy II");
            book_2.setPrice(12.5F);
            book_2.setDescription("Science fiction comedy series created by Douglas Adams. Tail II");
            book_2.setIsbn("1-84023-742-22");
            book_2.setNbOfPage(354);
            book_2.setIllustrations(false);

            book_2 =bookEJB.createBook(book_2);
            System.out.println("### Book created : " + book_2);

            book_2.setTitle("H2G2 II");
            bookEJB.edit(book_2);
            
            //bookEJB.remove(book_2);
            System.out.println("### Book deleted");
        }
        catch (NamingException ex) {
            System.out.println(ex.toString());
        }

    }
}
