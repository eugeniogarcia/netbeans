package seguro;

import javax.ejb.EJBLocalObject;

public interface InfoLocal extends EJBLocalObject {
    int suma(int num1, int num2);

    int resta(int num1, int num2);
}
