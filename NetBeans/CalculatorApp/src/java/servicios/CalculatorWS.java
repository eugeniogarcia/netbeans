/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servicios;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Eugenio
 */
@WebService()
public class CalculatorWS {
    @WebMethod(operationName = "add")
    public int add(@WebParam(name = "i") int i, @WebParam(name = "j") int j) {
        return i + j;
    }

}
