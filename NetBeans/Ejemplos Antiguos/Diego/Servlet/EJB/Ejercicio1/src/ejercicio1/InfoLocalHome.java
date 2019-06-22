package ejercicio1;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface InfoLocalHome extends EJBLocalHome {
    InfoLocal create() throws CreateException;
}
