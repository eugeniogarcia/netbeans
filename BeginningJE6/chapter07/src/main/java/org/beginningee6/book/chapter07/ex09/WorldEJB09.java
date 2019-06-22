package org.beginningee6.book.chapter07.ex09;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author: Antonio
 */
@Stateless
public class WorldEJB09 {

    //Inyecta una bean, sin precisar del jndi, del contexto, del contenedor...
    @EJB
    private ItemEJB09 itemEJB;

    public String sayWorld() {
        itemEJB.findBooks();
        return "World !!!!!!!!!!!!!!!";
    }
}
