/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package misJSFManagedBeans;

import javax.faces.model.DataModel;
import misEntidades.Hijo;
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
public class miJSFManagedBeanHijoTest {

    public miJSFManagedBeanHijoTest() {
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

    /**
     * Test of getHijoSeleccionado method, of class miJSFManagedBeanHijo.
     */
    @Test
    public void testGetHijoSeleccionado() {
        System.out.println("getHijoSeleccionado");
        miJSFManagedBeanHijo instance = new miJSFManagedBeanHijo();
        Hijo expResult = null;
        Hijo result = instance.getHijoSeleccionado();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHijoSeleccionado method, of class miJSFManagedBeanHijo.
     */
    @Test
    public void testSetHijoSeleccionado() {
        System.out.println("setHijoSeleccionado");
        Integer ID = null;
        miJSFManagedBeanHijo instance = new miJSFManagedBeanHijo();
        instance.setHijoSeleccionado(ID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListaHijos method, of class miJSFManagedBeanHijo.
     */
    @Test
    public void testGetListaHijos() {
        System.out.println("getListaHijos");
        miJSFManagedBeanHijo instance = new miJSFManagedBeanHijo();
        DataModel expResult = null;
        DataModel result = instance.getListaHijos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}