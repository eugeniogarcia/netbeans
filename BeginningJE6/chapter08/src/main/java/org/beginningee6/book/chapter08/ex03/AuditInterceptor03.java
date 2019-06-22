package org.beginningee6.book.chapter08.ex03;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

//Otra forma de definir un interceptor. En este caso el propio método tendrá la decoración
//El método debe tener una firma concreta
public class AuditInterceptor03 {

    private Logger logger = Logger.getLogger("com.apress.javaee6");

    //Se invoca antes y después de una invocación
    @AroundInvoke
    public Object profile(InvocationContext ic) throws Exception {
        //ic contiene el contexto de ejecución. En el contexto hay información valiosa
        //como el método desde el que se invoca, se permite proceder
        long initTime = System.currentTimeMillis();
        try {
            return ic.proceed();
        } finally {
            long diffTime = System.currentTimeMillis() - initTime;
            logger.fine(ic.getMethod() + " took " + diffTime + " milliseconds.");
        }
    }
}
