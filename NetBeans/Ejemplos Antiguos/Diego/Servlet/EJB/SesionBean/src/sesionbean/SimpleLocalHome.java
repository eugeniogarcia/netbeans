package sesionbean;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface SimpleLocalHome extends EJBLocalHome {
    SimpleLocal create() throws CreateException;
}
