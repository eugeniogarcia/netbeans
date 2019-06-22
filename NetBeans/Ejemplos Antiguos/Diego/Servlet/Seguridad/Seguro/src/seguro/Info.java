package seguro;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface Info extends EJBObject {
    int suma(int num1, int num2) throws RemoteException;

    int resta(int num1, int num2) throws RemoteException;
}
