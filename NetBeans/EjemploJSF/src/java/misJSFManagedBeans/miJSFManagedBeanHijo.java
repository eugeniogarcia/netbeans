/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package misJSFManagedBeans;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import misEntidades.Hijo;
import misSesionBeans.Entidades.HijoFacade;

/**
 *
 * @author Eugenio
 */
@ManagedBean(name="miJSFManagedBeanHijo")
@RequestScoped
public class miJSFManagedBeanHijo {
    @EJB
    private HijoFacade hijoFacade;

    /** Creates a new instance of miJSFManagedBeanHijo */
    public miJSFManagedBeanHijo() {
        IDseleccionado=-1;
        hijoSeleccionado=null;
        this.hijoFacade=new HijoFacade();
    }

    private Hijo hijoSeleccionado;

    public Hijo getHijoSeleccionado() {
        return hijoSeleccionado;
    }

    private Integer IDseleccionado;
    public void setHijoSeleccionado(Integer ID) {
        Hijo dummy= this.hijoFacade.find(ID);
        if(dummy!=null)
        {
            this.hijoSeleccionado =this.hijoFacade.find(ID);
            this.IDseleccionado=ID;
        }
    }


    public DataModel getListaHijos()
    {
        List<Hijo> lista=this.hijoFacade.findAll();
        if(lista!=null)return new ListDataModel<Hijo>(lista);
        else return null;
    }

}
