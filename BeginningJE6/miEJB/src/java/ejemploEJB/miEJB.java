/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploEJB;

import java.awt.print.Book;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author e.garcia.san.martin
 */
@Stateless
@LocalBean
public class miEJB implements miEJBRemote, miEJBLocal {

    @PersistenceContext(unitName = "chapter04PU")
    private EntityManager em;
            
    @Override
    public String holaAmigo(String nombre) {
        return "Hola, como estas "+nombre+" ?";
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public long toD() {
        return new java.util.Date().getTime();
    }

    @Override
    public List<miLibro> recuperaLibros() {
        TypedQuery<miLibro> query = em.createNamedQuery("encuentraTodos", miLibro.class);
        return query.getResultList();
    }

    @Override
    public miLibro encuentraLibro(String isbn) {
        TypedQuery<miLibro> query = em.createNamedQuery("encuentraISBN", miLibro.class);
        query.setParameter("isbn", isbn);
        query.setMaxResults(1);
        List<miLibro> lista=query.getResultList();
        if(lista.isEmpty()) return null;
        else return lista.get(0);
    }

    @Override
    public miLibro creaLibro(miLibro book) {
        em.persist(book);
        return book;
    }

    @Override
    public boolean borraLibro(String isbn) {
        TypedQuery<miLibro> query  = em.createQuery("SELECT b FROM libro b where b.isbn= :isbn", miLibro.class);
        query.setParameter("isbn", isbn);
        List<miLibro> lista=query.getResultList();
        if(lista.isEmpty()) return false;
        for(int i=0;i<lista.size();i++)
        {
            em.remove(lista.get(i));
        }
        return true;
    }
    
}
