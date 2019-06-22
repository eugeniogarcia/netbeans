package seguro;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface InfoHome extends EJBHome {
    Info create() throws RemoteException, CreateException;
}
