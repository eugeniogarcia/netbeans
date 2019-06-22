/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misWebServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Eugenio
 */
@Path("mipuerto")
public class miPuerto {
    private misWebServices_client.MiWS port;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of miPuerto
     */
    public miPuerto() {
        port = getPort();
    }

    /**
     * Invokes the SOAP method hola
     * @param nombre resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("hola/")
    public String getHola(@QueryParam("nombre") String nombre) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.hola(nombre);
                return result;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     *
     */
    private misWebServices_client.MiWS getPort() {
        try {
            // Call Web Service Operation
            misWebServices_client.MiWS_Service service = new misWebServices_client.MiWS_Service();
            misWebServices_client.MiWS p = service.getMiPuerto();
            return p;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
}
