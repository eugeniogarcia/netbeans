package sesionbean;

import javax.ejb.EJBLocalObject;

public interface SimpleLocal extends EJBLocalObject {
    int calcular(int num1, int num2);

    String mostrar();
}
