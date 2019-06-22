package ejercicio1;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

public abstract class UsuarioBean implements EntityBean {
    private EntityContext _context;

    public String ejbCreate() {
        return null;
    }

    public void ejbPostCreate() {
    }

    public String ejbCreate(String usuario, String clave) {
        setUsuario(usuario);
        setClave(clave);
        return usuario;
    }

    public void ejbPostCreate(String usuario, String clave) {
    }

    public void setEntityContext(EntityContext context) throws EJBException {
        _context = context;
    }

    public void unsetEntityContext() throws EJBException {
        _context = null;
    }

    public void ejbRemove() throws EJBException, RemoveException {
    }

    public void ejbActivate() throws EJBException {
    }

    public void ejbPassivate() throws EJBException {
    }

    public void ejbLoad() throws EJBException {
    }

    public void ejbStore() throws EJBException {
    }

    public abstract String getUsuario();

    public abstract void setUsuario(String usuario);

    public abstract String getClave();

    public abstract void setClave(String clave);
}
