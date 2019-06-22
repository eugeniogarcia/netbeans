
package org.beginningee6.book.chapter14.ex16;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.beginningee6.book.chapter14.ex16 package. 
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

    private final static QName _ValidateCreditCardResponse_QNAME = new QName("http://ex16.chapter14.book.beginningee6.org/", "ValidateCreditCardResponse");
    private final static QName _ValidateCreditCard_QNAME = new QName("http://ex16.chapter14.book.beginningee6.org/", "ValidateCreditCard");
    private final static QName _CreditCard16_QNAME = new QName("http://ex16.chapter14.book.beginningee6.org/", "creditCard16");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.beginningee6.book.chapter14.ex16
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ValidateCreditCardResponse }
     * 
     */
    public ValidateCreditCardResponse createValidateCreditCardResponse() {
        return new ValidateCreditCardResponse();
    }

    /**
     * Create an instance of {@link ValidateCreditCard }
     * 
     */
    public ValidateCreditCard createValidateCreditCard() {
        return new ValidateCreditCard();
    }

    /**
     * Create an instance of {@link CreditCard16 }
     * 
     */
    public CreditCard16 createCreditCard16() {
        return new CreditCard16();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateCreditCardResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ex16.chapter14.book.beginningee6.org/", name = "ValidateCreditCardResponse")
    public JAXBElement<ValidateCreditCardResponse> createValidateCreditCardResponse(ValidateCreditCardResponse value) {
        return new JAXBElement<ValidateCreditCardResponse>(_ValidateCreditCardResponse_QNAME, ValidateCreditCardResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateCreditCard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ex16.chapter14.book.beginningee6.org/", name = "ValidateCreditCard")
    public JAXBElement<ValidateCreditCard> createValidateCreditCard(ValidateCreditCard value) {
        return new JAXBElement<ValidateCreditCard>(_ValidateCreditCard_QNAME, ValidateCreditCard.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditCard16 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ex16.chapter14.book.beginningee6.org/", name = "creditCard16")
    public JAXBElement<CreditCard16> createCreditCard16(CreditCard16 value) {
        return new JAXBElement<CreditCard16>(_CreditCard16_QNAME, CreditCard16 .class, null, value);
    }

}
