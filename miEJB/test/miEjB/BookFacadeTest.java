/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miEjB;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import misEntidades.Book;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eugenio
 */
public class BookFacadeTest {
    
    public BookFacadeTest() {
    }
    
    private static EJBContainer ec = null;
    private static Context ctx;

    
    @BeforeClass
    public static void setUpClass() throws Exception{
        Map<String, Object> properties = new HashMap<String, Object>();
        //Aqui tenemos que especificar donde encontrar los modulos. Lo que estamos indicando
        //es que sobre el raiz del proyecto, en el directorio build/classes encontrará los jar, .class,
        //etc. (si usaramos maven sería 'target/classes'). Además aquí si hay un directorio META-INF, buscará los archivos de configuracion, incluyendo
        //el de persistencia
        //El nombre de la conexion a base de datos que espera es jdbc/__default. Si lo cambiamos en el archivo
        //de persistencia no lo va a encontrar. Esto es una limitacion que tiene el embeded container a dia de hoy
        //29/07/2013
        properties.put(EJBContainer.MODULES, new File("build/classes"));
        //Define un contenedor donde poder alojar la EJB
        ec = EJBContainer.createEJBContainer(properties);
        //Obtiene el contexto asociado al contenedor
        ctx = ec.getContext();

    }
    
    @AfterClass
    public static void tearDownClass() throws Exception{
        if (ec != null)
            ec.close();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void prueba() throws Exception {
        // Creates an instance of book
        Book book = new Book();
        book.setTitle("The Hitchhiker's Guide to the Galaxy");
        book.setPrice(12.5F);
        book.setDescription("Science fiction comedy series created by Douglas Adams.");
        book.setIsbn("1-84023-742-2");
        book.setNbOfPage(354);
        book.setIllustrations(false);
       
        BookFacade bookEJB = (BookFacade) ctx.lookup("java:global/classes/BookFacade!miEjB.BookFacade");
        book = bookEJB.createBook(book);
        book = bookEJB.find(book.getId());

        assertTrue(book.getPrice()== 12.5F);

        System.out.println("### Book created : " + book);
        System.out.println("### "+ bookEJB.findAll().size() + " books in the db" );
        bookEJB.remove(book);
        System.out.println("### Book deleted");
        book = bookEJB.find(book.getId());
        assertTrue(book== null);

    }
    
}