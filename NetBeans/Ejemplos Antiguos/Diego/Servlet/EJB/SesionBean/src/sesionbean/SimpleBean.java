package sesionbean;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class SimpleBean implements SessionBean {
    private SessionContext _context;

    public void ejbCreate() {
    }

    public void setSessionContext(SessionContext context) throws EJBException {
        _context = context;
    }

    public void ejbRemove() throws EJBException {
    }

    public void ejbActivate() throws EJBException {
    }

    public void ejbPassivate() throws EJBException {
    }

    //METODOS CREADOS DE NEGOCIO.
    public int calcular(int num1, int num2) {
        return num1+num2;
    }

    public String mostrar() {
        return "Hola desde un bean de sesion";
    }
}
