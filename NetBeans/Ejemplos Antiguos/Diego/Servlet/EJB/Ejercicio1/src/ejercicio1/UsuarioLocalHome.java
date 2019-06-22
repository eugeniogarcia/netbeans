package ejercicio1;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface UsuarioLocalHome extends EJBLocalHome {
    UsuarioLocal create() throws CreateException;

    UsuarioLocal findByPrimaryKey(String primaryKey) throws FinderException;

    Collection findAll() throws FinderException;

    UsuarioLocal create(String usuario, String clave) throws CreateException;
}
