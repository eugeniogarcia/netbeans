package org.beginningee6.book.chapter08.ex03;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 6 with Glassfish
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
@Stateless
public class CustomerEJB03 {

    // ======================================
    // =             Attributes             =
    // ======================================

    @PersistenceContext(unitName = "chapter08PU")
    private EntityManager em;

    // ======================================
    // =           Public Methods           =
    // ======================================

    //Interceptors
    //define que este método sea interceptado por el interceptor definido en la clase indicada
    //Se ejecutara el método dentro del contexto del interceptor
    @Interceptors(LoggingInterceptor03.class)
    public Customer03 createCustomer(Customer03 customer) {
        em.persist(customer);
        return customer;
    }

    public Customer03 findCustomerById(Long id) {
        return em.find(Customer03.class, id);
    }
}