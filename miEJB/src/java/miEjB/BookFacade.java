/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miEjB;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import misEntidades.Book;

/**
 *
 * @author Eugenio
 */
@Stateless
@LocalBean
public class BookFacade extends AbstractFacade<Book> implements BookFacadeLocal, BookFacadeRemote {
    @PersistenceContext(unitName = "miEJBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookFacade() {
        super(Book.class);
    }
    
    public Book createBook(Book book)
    {
        em.persist(book);
        return book;
    }
    
    @Override
    public List<Book> findBooks() {
        TypedQuery<Book> query = em.createNamedQuery("findAllBooks", Book.class);
        return query.getResultList();
    }
}
