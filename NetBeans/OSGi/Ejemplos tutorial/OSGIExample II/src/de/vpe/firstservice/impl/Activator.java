/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.vpe.firstservice.impl;

//Interfaz para definir la clase Activación
import org.osgi.framework.BundleActivator;

//PAra manejar el contexto del Bundle
import org.osgi.framework.BundleContext;
//Constantes
import org.osgi.framework.Constants;
//Registro de un servicio
import org.osgi.framework.ServiceRegistration;

//Interfaz con la definición del primer servicio
import de.vpe.firstservice.FirstService;

//Para pasar los argumentos al registrar el servicio
import java.util.Hashtable;


/**
 *
 * @author Leihta
 */
//Clase de Activación del Bundle. Es invocada por el framework OSGI al arrancar y parar el bundle
public class Activator implements BundleActivator{
    public static BundleContext bc = null;

    public void start(BundleContext arg0) throws Exception {
        //Almacenamos el contexto de este bundle. Esta parámetro es pasado por el framework OSGI
        Activator.bc = arg0;
        
        //Mostramos el nombre
        System.out.println(arg0.getBundle().getHeaders().get(Constants.BUNDLE_NAME) + " arrancando...");
        System.out.println(arg0.getBundle().getHeaders().get(Constants.BUNDLE_DESCRIPTION));
        System.out.println(arg0.getBundle().getHeaders().get(Constants.BUNDLE_VENDOR));

        //Instancia el servicio que vamos a registrar
        FirstService service = new FirstServiceImpl();

        //registra especificando el nombre del servicio, la instancia, y pasandole una tabla con los argumentos (clave/valor) que utilizará el servicio
        //En este caso no pasamos ningún argumento
        ServiceRegistration registration = bc.registerService(FirstService.class.getName(), service, new Hashtable());

        System.out.println("Servicio Registrado: Mi Primer Servicio");

    }

    public void stop(BundleContext arg0) throws Exception {
        System.out.println(bc.getBundle().getHeaders().get(Constants.BUNDLE_NAME) + " parando...");

        Activator.bc = null;
    }





}
