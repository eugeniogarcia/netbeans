/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EjemploEJB;

import java.math.BigDecimal;
import javax.ejb.Stateless;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 *
 * @author Eugenio
 */
@Stateless
public class NewSession3Bean implements NewSession3Remote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
    private BigDecimal euroRate = new BigDecimal("0.0070");
    private BigDecimal yenRate = new BigDecimal("112.58");

    public BigDecimal dollarToYen(BigDecimal dollars) {
        referencia++;
        System.out.println("El valor de la referencia es "+referencia);
        BigDecimal result = dollars.multiply(yenRate);

        return result.setScale(2, BigDecimal.ROUND_UP);
    }

    public BigDecimal yenToEuro(BigDecimal yen) {
        referencia++;
        System.out.println("El valor de la referencia es "+referencia);
        BigDecimal result = yen.multiply(euroRate);

        return result.setScale(2, BigDecimal.ROUND_UP);
    }

     int referencia=0;
    @PostConstruct
    public void despuesCreacion()
    {
        referencia=1;
        System.out.println("Acaba de crearse");
        System.out.println("El valor de la referencia es "+referencia);
    }

    @PreDestroy
    public void antesDestruccion()
    {
        System.out.println("Se va a destruir");
        System.out.println("El valor de la referencia es "+referencia);
    }

}