/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misWebServices;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Eugenio
 */
@WebService(serviceName = "miWS", portName = "miPuerto")
public class miWS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hola")
    public String hello(@WebParam(name = "nombre") String txt) {
        return "Hola " + txt + " !";
    }
}
