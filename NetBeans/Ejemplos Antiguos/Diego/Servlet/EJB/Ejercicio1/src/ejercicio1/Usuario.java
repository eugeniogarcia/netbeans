package ejercicio1;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface Usuario extends EJBObject {
    String getUsuario() throws RemoteException;

    String getClave() throws RemoteException;

    void setClave(String clave) throws RemoteException;
}
