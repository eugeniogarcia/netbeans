
package org.beginningee6.book.chapter14.ex01;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-b02-
 * Generated source version: 2.1
 * 
 */
@WebService(name = "CardValidator01", targetNamespace = "http://ex01.chapter14.book.beginningee6.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CardValidator01 {


    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "validate", targetNamespace = "http://ex01.chapter14.book.beginningee6.org/", className = "org.beginningee6.book.chapter14.ex01.Validate")
    @ResponseWrapper(localName = "validateResponse", targetNamespace = "http://ex01.chapter14.book.beginningee6.org/", className = "org.beginningee6.book.chapter14.ex01.ValidateResponse")
    public boolean validate(
        @WebParam(name = "arg0", targetNamespace = "")
        CreditCard01 arg0);

}