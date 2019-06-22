package sesionbean;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface Simple extends EJBObject {
    int calcular(int num1, int num2) throws RemoteException;

    String mostrar() throws RemoteException;
}
