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
import javax.persistence.criteria.Root;
import misEntidades.miMensaje;

/**
 *
 * @author Eugenio
 */
@Stateless
public class miMensajeFacade {
    @PersistenceContext(unitName = "miPersistenceUnitDC")
    private EntityManager em;

    public void create(miMensaje miMensaje) {
        em.persist(miMensaje);
    }

    public void edit(miMensaje miMensaje) {
        em.merge(miMensaje);
    }

    public void remove(miMensaje miMensaje) {
        em.remove(em.merge(miMensaje));
    }

    public miMensaje find(Object id) {
        return em.find(miMensaje.class, id);
    }

    public List<miMensaje> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(miMensaje.class));
        return em.createQuery(cq).getResultList();
    }

    public List<miMensaje> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(miMensaje.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<miMensaje> rt = cq.from(miMensaje.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
