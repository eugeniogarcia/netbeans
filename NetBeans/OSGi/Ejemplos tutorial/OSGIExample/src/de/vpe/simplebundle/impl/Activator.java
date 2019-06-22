/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.vpe.simplebundle.impl;

//Interfaz para definir la clase Activación
import org.osgi.framework.BundleActivator;
//PAra manejar el contexto del Bundle
import org.osgi.framework.BundleContext;


/**
 *
 * @author Leihta
 */
//Clase de Activación del Bundle. Es invocada por el framework OSGI al arrancar y parar el bundle
public class Activator implements BundleActivator{

    public static BundleContext bc = null;
    private HelloWorldThread thread = null;

    public void start(BundleContext arg0) throws Exception {
        System.out.println("Mi Primer Bundle arrancando...");
        Activator.bc = arg0;

        this.thread = new HelloWorldThread();
        this.thread.start();

    }

    public void stop(BundleContext arg0) throws Exception {
        System.out.println("Mi Primer Bundle parando...");
        this.thread.stopThread();
        //Waits for this thread to die
        this.thread.join();
        
        Activator.bc=null;

    }

}
