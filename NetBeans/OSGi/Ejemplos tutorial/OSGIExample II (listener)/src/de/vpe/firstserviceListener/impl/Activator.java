/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.vpe.firstserviceListener.impl;

//Para el manejo de fechas

//Interfaz que implementa la clase de activación
import de.vpe.firstservice.FirstService;
import org.osgi.framework.BundleActivator;

//Contexto del Bundle
import org.osgi.framework.BundleContext;

//Constantes empleadas en el framework
import org.osgi.framework.Constants;

//Para gestionar el uso de servicios (consumo)
import org.osgi.util.tracker.ServiceTracker;


/**
 *
 * @author Leihta
 */
public class Activator implements BundleActivator{

    public static BundleContext bc = null;
    private MyServiceTrackerCustomizer customizer;

    private ServiceTracker tracker;


    public void start(BundleContext arg0) throws Exception {
        //Mostramos el nombre
        System.out.println(arg0.getBundle().getHeaders().get(Constants.BUNDLE_NAME) + " arrancando...");
        System.out.println(arg0.getBundle().getHeaders().get(Constants.BUNDLE_DESCRIPTION));
        System.out.println(arg0.getBundle().getHeaders().get(Constants.BUNDLE_VENDOR));

        Activator.bc=arg0;

        //La helper class que manejara los cambios de estado del servicio
        customizer = new MyServiceTrackerCustomizer(bc);

        //Seguimos los cambios de estado del servicio, y cada cambio lo manejamos con customizer
        tracker = new ServiceTracker(bc, FirstService.class.getName(),customizer);
        tracker.open();

    }

    public void stop(BundleContext arg0) throws Exception {
        Activator.bc=null;
    }


}


