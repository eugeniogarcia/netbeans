package org.beginningee6.book.chapter10;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 6 with Glassfish
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
//Una bean asociada a los JSF, con scope request
//Especifica el nombre de la bean
@ManagedBean(name = "bookController")
@RequestScoped
public class BookController {

    // ======================================
    // =             Attributes             =
    // ======================================

    //Inyecta la EJB. La aplicación web debe compartir contenedor con la EJB
    //La EJB se declara como @LocalBean para que podamos invocarla directamente, no por medio 
    //del interfaz remote o local
    @EJB
    private BookEJB bookEJB;

    private Book book = new Book();
    private List<Book> bookList = new ArrayList<Book>();

    // ======================================
    // =           Public Methods           =
    // ======================================

    public String doCreateBook() {
        book = bookEJB.createBook(book);
        bookList = bookEJB.findBooks();
        return "listBooks.xhtml";
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}