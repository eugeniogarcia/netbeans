package org.beginningee6.book.chapter06;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 6 with Glassfish
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
public class BookEJBTest {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static EJBContainer ec;
    private static Context ctx;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @BeforeClass
    public static void initContainer() throws Exception {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));
        //Define un contenedor donde poder alojar la EJB
        ec = EJBContainer.createEJBContainer(properties);
        //Obtiene el contexto asociado al contenedor
        ctx = ec.getContext();
    }

    @AfterClass
    public static void closeContainer() throws Exception {
        if (ec != null)
            ec.close();
    }

    // ======================================
    // =              Unit tests            =
    // ======================================

    @Test
    public void shouldCreateABook() throws Exception {

        // Creates an instance of book
        Book book = new Book();
        book.setTitle("The Hitchhiker's Guide to the Galaxy");
        book.setPrice(12.5F);
        book.setDescription("Science fiction comedy book");
        book.setIsbn("1-84023-742-2");
        book.setNbOfPage(354);
        book.setIllustrations(false);

        // Looks up the EJB
        //Utiliza los servicios jndi para localizar el recurso en el contenedor
        //El scope es global. No se indica la aplicación no el módulo en este caso, se especifica la clase
        //El EJB es BookEJB y está en el paquete indicado
        BookEJB bookEJB = (BookEJB) ctx.lookup("java:global/classes/BookEJB!org.beginningee6.book.chapter06.BookEJB");
        //Con esto ya estamos accediendo a la EJB
        
        
        // Persists the book to the database
        //Estamos accediendo desde el cliente al contenedor para usar la lógica de negocio de la EJB
        book = bookEJB.createBook(book);
        assertNotNull("ID should not be null", book.getId());

        // Retrieves all the books from the database
        List<Book> books = bookEJB.findBooks();
        assertNotNull(books);
    }
}