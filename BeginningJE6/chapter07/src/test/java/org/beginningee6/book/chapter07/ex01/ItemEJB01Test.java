/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.beginningee6.book.chapter07.ex01;

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
 * @author Eugenio
 */
public class ItemEJB01Test {
    
    public ItemEJB01Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findBooks method, of class ItemEJB01.
     */
    @Test
    public void testFindBooks() throws Exception {
        System.out.println("findBooks");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ItemEJB01 instance = (ItemEJB01)container.getContext().lookup("java:global/classes/ItemEJB01");
        List expResult = null;
        List result = instance.findBooks();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findCDs method, of class ItemEJB01.
     */
    @Test
    public void testFindCDs() throws Exception {
        System.out.println("findCDs");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ItemEJB01 instance = (ItemEJB01)container.getContext().lookup("java:global/classes/ItemEJB01");
        List expResult = null;
        List result = instance.findCDs();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findBookById method, of class ItemEJB01.
     */
    @Test
    public void testFindBookById() throws Exception {
        System.out.println("findBookById");
        Long id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ItemEJB01 instance = (ItemEJB01)container.getContext().lookup("java:global/classes/ItemEJB01");
        Book01 expResult = null;
        Book01 result = instance.findBookById(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findCDById method, of class ItemEJB01.
     */
    @Test
    public void testFindCDById() throws Exception {
        System.out.println("findCDById");
        Long id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ItemEJB01 instance = (ItemEJB01)container.getContext().lookup("java:global/classes/ItemEJB01");
        CD01 expResult = null;
        CD01 result = instance.findCDById(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createBook method, of class ItemEJB01.
     */
    @Test
    public void testCreateBook() throws Exception {
        System.out.println("createBook");
        Book01 book = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ItemEJB01 instance = (ItemEJB01)container.getContext().lookup("java:global/classes/ItemEJB01");
        Book01 expResult = null;
        Book01 result = instance.createBook(book);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCD method, of class ItemEJB01.
     */
    @Test
    public void testCreateCD() throws Exception {
        System.out.println("createCD");
        CD01 cd = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ItemEJB01 instance = (ItemEJB01)container.getContext().lookup("java:global/classes/ItemEJB01");
        CD01 expResult = null;
        CD01 result = instance.createCD(cd);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteBook method, of class ItemEJB01.
     */
    @Test
    public void testDeleteBook() throws Exception {
        System.out.println("deleteBook");
        Book01 book = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ItemEJB01 instance = (ItemEJB01)container.getContext().lookup("java:global/classes/ItemEJB01");
        instance.deleteBook(book);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCD method, of class ItemEJB01.
     */
    @Test
    public void testDeleteCD() throws Exception {
        System.out.println("deleteCD");
        CD01 cd = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ItemEJB01 instance = (ItemEJB01)container.getContext().lookup("java:global/classes/ItemEJB01");
        instance.deleteCD(cd);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateBook method, of class ItemEJB01.
     */
    @Test
    public void testUpdateBook() throws Exception {
        System.out.println("updateBook");
        Book01 book = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ItemEJB01 instance = (ItemEJB01)container.getContext().lookup("java:global/classes/ItemEJB01");
        Book01 expResult = null;
        Book01 result = instance.updateBook(book);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateCD method, of class ItemEJB01.
     */
    @Test
    public void testUpdateCD() throws Exception {
        System.out.println("updateCD");
        CD01 cd = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ItemEJB01 instance = (ItemEJB01)container.getContext().lookup("java:global/classes/ItemEJB01");
        CD01 expResult = null;
        CD01 result = instance.updateCD(cd);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}