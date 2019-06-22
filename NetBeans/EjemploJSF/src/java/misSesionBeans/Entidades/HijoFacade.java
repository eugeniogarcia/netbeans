/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package misSesionBeans.Entidades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import misEntidades.Hijo;

/**
 *
 * @author Eugenio
 */
@Stateless
public class HijoFacade {
    @PersistenceContext(unitName = "miUnidadPersistencia")
    private EntityManager em;

    public void create(Hijo hijo) {
        em.persist(hijo);
    }

    public void edit(Hijo hijo) {
        em.merge(hijo);
    }

    public void remove(Hijo hijo) {
        em.remove(em.merge(hijo));
    }

    public Hijo find(Object id) {
        return em.find(Hijo.class, id);
    }

    public List<Hijo> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Hijo.class));
        return em.createQuery(cq).getResultList();
    }

    public List<Hijo> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Hijo.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Hijo> rt = cq.from(Hijo.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
