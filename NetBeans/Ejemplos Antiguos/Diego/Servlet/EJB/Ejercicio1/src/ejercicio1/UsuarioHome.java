package ejercicio1;

import java.rmi.RemoteException;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;

public interface UsuarioHome extends EJBHome {
    Usuario create() throws RemoteException, CreateException;

    Usuario findByPrimaryKey(String primaryKey) throws RemoteException, 
                                                       FinderException;

    Collection findAll() throws RemoteException, FinderException;

    Usuario create(String usuario, String clave) throws RemoteException, 
                                                        CreateException;
}
