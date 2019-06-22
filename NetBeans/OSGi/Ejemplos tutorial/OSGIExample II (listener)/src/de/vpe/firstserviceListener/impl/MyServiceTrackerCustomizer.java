package de.vpe.firstserviceListener.impl;

import de.vpe.firstservice.FirstService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;


//Helper class que implementa un service tracker para un determinado servicio
public class MyServiceTrackerCustomizer implements ServiceTrackerCustomizer {
    private ServiceUserThread thread = null;

    private BundleContext bc;

    public MyServiceTrackerCustomizer(BundleContext bc) {
        this.bc = bc;
    }

    //El evento fue, añadir un servicio
    public Object addingService(ServiceReference reference) {
        //Como se ha incluido en el registro, podemos tomar una referencia
        FirstService service = (FirstService) bc.getService(reference);

        //Lanza en un thread el "uso" del servicio
        if (this.thread == null) {
            this.thread = new ServiceUserThread(service);
            this.thread.start();
            return service;
        }
        else
            return service;
    }

    //El evento fue modificar un servicio
    public void modifiedService(ServiceReference reference, Object serviceObject) {
        //Paramos el thread...
        this.thread.stopThread();
        try {
            //...y esperamos a que se pare
            this.thread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Toma una referencia de nuevo (recordemos que el servicio cambio, este es el evento que hemos tomado)
        FirstService service = (FirstService) bc.getService(reference);

        this.thread = new ServiceUserThread(service);

        this.thread.start();
    }

    //El evento fue servicio desregistrado
    public void removedService(ServiceReference reference, Object serviceObject) {
        //Paramos el servicio, se desregistro
        this.thread.stopThread();

        try {
            this.thread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.thread = null;
    }
}

