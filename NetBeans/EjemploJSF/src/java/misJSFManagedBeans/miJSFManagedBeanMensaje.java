/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package misJSFManagedBeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import misEntidades.miMensaje;
import misSesionBeans.Entidades.miMensajeFacade;

/**
 *
 * @author Eugenio
 */
@ManagedBean(name="miJSFManagedBeanMensaje")
@SessionScoped
public class miJSFManagedBeanMensaje {
    @EJB
    private miMensajeFacade miMensajeFacade;

    /** Creates a new instance of miJSFManagedBeanMen */
    public miJSFManagedBeanMensaje() {
        miMensajeFacade=new miMensajeFacade();
        this.mensaje=new miMensaje();

    }

    //Codigo añadido
    protected miMensaje mensaje;

    public miMensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(miMensaje mensaje) {
        this.mensaje = mensaje;
    }

    public int getNumeroDeMensajes()
    {
        return miMensajeFacade.findAll().size();
    }

    public String postMensaje()
    {
        try
        {
        if(this.mensaje==null ||this.mensaje.getMensaje()==null ||this.mensaje.getMensaje().equals(""))
        {
            this.error="No especifico ningún mensaje";
            return "noHayMensaje";
        }

        this.miMensajeFacade.create(this.mensaje);
        //De cara a poder utilizarlo para la navegación entre páginas
        this.error="";
        return "mensajeInsertado";
        }
        catch(Exception ex)
        {
            this.error="Error: "+ex.getMessage();
            ex.printStackTrace();
            return "error";
        }
    }
    protected String error;

    /**
     * Get the value of error
     *
     * @return the value of error
     */
    public String getError() {
        return error;
    }

    /**
     * Set the value of error
     *
     * @param error new value of error
     */
    public void setError(String error) {
        this.error = error;
    }

}
