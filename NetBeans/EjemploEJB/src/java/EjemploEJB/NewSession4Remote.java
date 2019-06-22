/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EjemploEJB;

import java.math.BigDecimal;
import javax.ejb.Remote;

/**
 *
 * @author Eugenio
 */
@Remote
public interface NewSession4Remote {
    public BigDecimal dollarToYen(BigDecimal dollars);

    public BigDecimal yenToEuro(BigDecimal yen);

}