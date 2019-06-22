/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miclientews;

/**
 *
 * @author Eugenio
 */
public class MiClienteWS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String respuesta=new miswebservices.MiWS_Service().getMiPuerto().hola("pepe");
        System.out.println(respuesta);
    }
}
