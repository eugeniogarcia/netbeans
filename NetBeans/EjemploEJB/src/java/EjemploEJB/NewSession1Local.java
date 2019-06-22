/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EjemploEJB;

import javax.ejb.Local;
import java.math.BigDecimal;

/**
 *
 * @author Eugenio
 */
@Local
public interface NewSession1Local {
    public BigDecimal dollarToYen(BigDecimal dollars);

    public BigDecimal yenToEuro(BigDecimal yen);
    
}
