/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploEJB;

import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author e.garcia.san.martin
 */
public class miEJBTest {
    
    public miEJBTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testHolaAmigo() throws Exception {
        System.out.println("holaAmigo");
        String nombre = "Eugenio";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        miEJBRemote instance = (miEJBRemote)container.getContext().lookup("java:global/classes/miEJB!ejemploEJB.miEJB");
        String expResult = "Hola, como estas Eugenio ?";
        String result = instance.holaAmigo(nombre);
        assertEquals(expResult, result);
        container.close();
        fail("The test case is a prototype.");
    }


    @Test
    public void testMetodos() throws Exception {
        
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        miEJBRemote instance = (miEJBRemote)container.getContext().lookup("java:global/classes/miEJB!ejemploEJB.miEJB");

        miLibro libro=new miLibro();
        libro.setDescripcion("descripción1");
        libro.setIsbn("descripción1");
        libro.setPrecio(100F);
        libro.setTitulo("titulo1");
        instance.creaLibro(libro);
        miLibro libro1=new miLibro();
        libro1.setDescripcion("descripción2");
        libro1.setIsbn("descripción2");
        libro1.setPrecio(200F);
        libro1.setTitulo("titulo2");
        instance.creaLibro(libro1);
        miLibro libro2=new miLibro();
        libro2.setDescripcion("descripción22");
        libro2.setIsbn("descripción2");
        libro2.setPrecio(220F);
        libro2.setTitulo("titulo22");
        instance.creaLibro(libro2);
        
        
        miLibro libro3= instance.encuentraLibro("descripción2");
        
        List<miLibro> lista= instance.recuperaLibros();
        
        instance.borraLibro("descripción2");
        
        lista= instance.recuperaLibros();

        container.close();
    }
}
