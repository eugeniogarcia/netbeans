package org.beginningee6.book.chapter08.ex03;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

//Esta clase contiene interceptors. Los métodos que hagan de interceptors se decorarán con 
//apropiadamente
public class LoggingInterceptor03 {

    private Logger logger = Logger.getLogger("com.apress.javaee6");

    //Interceptor
    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        //Se arranca este método cuando se invoque a alguno de los métodos que se hayan decorado
        //El contexto puede determinar dede que método se ha lanzado el interceptor
        //puede controlar cuando debe ejecutarse, si debe, el cuerpo del método
        logger.entering(ic.getTarget().toString(), ic.getMethod().getName());
        try {
            //Indicamos que se ejecute la lógica del método y retorne el valor del método
            return ic.proceed();
        } finally {
            //A continuación, registrará en el log el target y el método que se han ejecutado
            logger.exiting(ic.getTarget().toString(), ic.getMethod().getName());
        }
    }
}