/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploEJB;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author e.garcia.san.martin
 */
@Remote
public interface miEJBRemote {
    public String holaAmigo(String nombre); 
    
    public List<miLibro> recuperaLibros();
    public miLibro encuentraLibro(String isbn);
    public miLibro creaLibro(miLibro book);
    public boolean borraLibro(String isbn);
}
