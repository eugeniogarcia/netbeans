
package org.beginningee6.book.chapter14.ex01;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.beginningee6.book.chapter14.ex01 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Validate_QNAME = new QName("http://ex01.chapter14.book.beginningee6.org/", "validate");
    private final static QName _CreditCard01_QNAME = new QName("http://ex01.chapter14.book.beginningee6.org/", "creditCard01");
    private final static QName _ValidateResponse_QNAME = new QName("http://ex01.chapter14.book.beginningee6.org/", "validateResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.beginningee6.book.chapter14.ex01
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreditCard01 }
     * 
     */
    public CreditCard01 createCreditCard01() {
        return new CreditCard01();
    }

    /**
     * Create an instance of {@link ValidateResponse }
     * 
     */
    public ValidateResponse createValidateResponse() {
        return new ValidateResponse();
    }

    /**
     * Create an instance of {@link Validate }
     * 
     */
    public Validate createValidate() {
        return new Validate();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Validate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ex01.chapter14.book.beginningee6.org/", name = "validate")
    public JAXBElement<Validate> createValidate(Validate value) {
        return new JAXBElement<Validate>(_Validate_QNAME, Validate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditCard01 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ex01.chapter14.book.beginningee6.org/", name = "creditCard01")
    public JAXBElement<CreditCard01> createCreditCard01(CreditCard01 value) {
        return new JAXBElement<CreditCard01>(_CreditCard01_QNAME, CreditCard01 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ex01.chapter14.book.beginningee6.org/", name = "validateResponse")
    public JAXBElement<ValidateResponse> createValidateResponse(ValidateResponse value) {
        return new JAXBElement<ValidateResponse>(_ValidateResponse_QNAME, ValidateResponse.class, null, value);
    }

}
