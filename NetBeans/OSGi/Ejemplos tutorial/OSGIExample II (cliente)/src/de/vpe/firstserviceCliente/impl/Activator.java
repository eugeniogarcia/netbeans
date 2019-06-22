/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.vpe.firstserviceCliente.impl;

//Para el manejo de fechas
import java.util.Date;

//Interfaz que implementa la clase de activación
import org.osgi.framework.BundleActivator;

//Contexto del Bundle
import org.osgi.framework.BundleContext;

//Constantes empleadas en el framework
import org.osgi.framework.Constants;

//Para gestionar el uso de servicios (consumo)
import org.osgi.framework.ServiceReference;


import de.vpe.firstservice.FirstService;


/**
 *
 * @author Leihta
 */
//Clase que realiza la activación del Bundle. Invocada por el framework OSGI al arrancar y parar el bundle
public class Activator implements BundleActivator{

    public static BundleContext bc = null;

    public void start(BundleContext arg0) throws Exception {
        //Guarda la referencia al contexto del bundle
        Activator.bc=arg0;

        //Mostramos el nombre
        System.out.println(arg0.getBundle().getHeaders().get(Constants.BUNDLE_NAME) + " arrancando...");
        System.out.println(arg0.getBundle().getHeaders().get(Constants.BUNDLE_DESCRIPTION));
        System.out.println(arg0.getBundle().getHeaders().get(Constants.BUNDLE_VENDOR));

        //Obtiene una referencia al servicio que queremos utilizar
        ServiceReference reference = bc.getServiceReference(FirstService.class.getName());

        //Si obtuvimos una referencia al servicio
        if (reference != null)
        {
            //Instancia el servicio
            FirstService service = (FirstService)bc.getService(reference);

            System.out.println("Formateamos la fecha utilizando el servicio: " +service.getFormattedDate(new Date()));

            //Devuelve el servicio
            bc.ungetService(reference);
        }else
        {
            System.out.println("El servicio no está disponible!");
        }


    }

    public void stop(BundleContext arg0) throws Exception {

         System.out.println(bc.getBundle().getHeaders().get(Constants.BUNDLE_NAME) + " parando...");

         
        //Elimina la referencia al contexto del bundle - para que sea garbage collected
        Activator.bc=null;
    }

}
