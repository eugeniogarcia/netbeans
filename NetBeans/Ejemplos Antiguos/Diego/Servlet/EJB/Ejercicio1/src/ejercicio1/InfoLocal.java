package ejercicio1;

import javax.ejb.EJBLocalObject;

public interface InfoLocal extends EJBLocalObject {
    String recuperarInfo(int idEmpleado);
}
