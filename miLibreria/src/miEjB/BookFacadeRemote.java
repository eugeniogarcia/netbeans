/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miEjB;

import java.util.List;
import javax.ejb.Remote;
import javax.persistence.TypedQuery;
import misEntidades.Book;

/**
 *
 * @author Eugenio
 */
@Remote
public interface BookFacadeRemote {

    void create(Book book);
    
    Book createBook(Book book);

    void edit(Book book);

    void remove(Book book);

    Book find(Object id);

    List<Book> findAll();

    List<Book> findRange(int[] range);

    int count();
    
    List<Book> findBooks();
    
}
