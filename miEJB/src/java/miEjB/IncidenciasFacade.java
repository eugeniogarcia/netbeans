/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miEjB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import misEntidades.Incidencias;

/**
 *
 * @author Eugenio
 */
@Stateless
public class IncidenciasFacade extends AbstractFacade<Incidencias> implements IncidenciasFacadeLocal, miEjB.IncidenciasFacadeRemote {
    @PersistenceContext(unitName = "miEJBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IncidenciasFacade() {
        super(Incidencias.class);
    }
    
}
