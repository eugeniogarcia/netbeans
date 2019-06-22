package EjemploEJB;

import java.math.BigDecimal;
import javax.ejb.Stateless;

/**
 *
 * @author Eugenio
 */
@Stateless
public class NewSession1Bean implements NewSession1Local {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
    private BigDecimal euroRate = new BigDecimal("0.0070");
    private BigDecimal yenRate = new BigDecimal("112.58");

    public BigDecimal dollarToYen(BigDecimal dollars) {
        BigDecimal result = dollars.multiply(yenRate);

        return result.setScale(2, BigDecimal.ROUND_UP);
    }

    public BigDecimal yenToEuro(BigDecimal yen) {
        BigDecimal result = yen.multiply(euroRate);

        return result.setScale(2, BigDecimal.ROUND_UP);
    }
}