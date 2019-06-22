package ejercicio1;

import javax.ejb.EJBLocalObject;

public interface UsuarioLocal extends EJBLocalObject {
    String getUsuario();

    String getClave();

    void setClave(String clave);
}
