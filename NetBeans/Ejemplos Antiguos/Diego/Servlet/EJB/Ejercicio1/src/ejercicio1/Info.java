package ejercicio1;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface Info extends EJBObject {
    String recuperarInfo(int idEmpleado) throws RemoteException;
}
