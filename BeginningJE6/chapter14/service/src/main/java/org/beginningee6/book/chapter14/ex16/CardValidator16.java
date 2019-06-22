package org.beginningee6.book.chapter14.ex16;


import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 6 with Glassfish
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
//Personalizamos el nombre del servicio y del puerto
@WebService(name = "ValidadorTarjetaCredito", portName = "PuertoValidacion")
public class CardValidator16 implements CCValidator16 {

    // ======================================
    // =            Constructors            =
    // ======================================
    @Resource
    private WebServiceContext context;

    // ======================================
    // =           Public Methods           =
    // ======================================

    //Personalizamos el nombre del webmethod
    @WebMethod(operationName = "ValidaTarjetaCredito")
    //Y del resultado
    @WebResult(name = "EsValida")
    //Y de los argumentos de entrada al webmethod
    public boolean validate(@WebParam(name = "TarjetaCredito") CreditCard16 creditCard) {

        Character lastDigit = creditCard.getNumber().charAt(creditCard.getNumber().length() - 1);

        if (Integer.parseInt(lastDigit.toString()) % 2 != 0) {
            return true;
        } else {
            return false;
        }
    }

    //Indica que este método se exclauido del web service
    @WebMethod(exclude = true)
    public void validate(String ccNumber) {

    }
}