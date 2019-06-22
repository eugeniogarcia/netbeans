package sesionbean;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface SimpleHome extends EJBHome {
    Simple create() throws RemoteException, CreateException;
}
