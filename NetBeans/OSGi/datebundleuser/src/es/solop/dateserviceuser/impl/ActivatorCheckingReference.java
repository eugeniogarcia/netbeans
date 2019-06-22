package es.solop.dateserviceuser.impl;

import java.util.Date;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import es.solop.dateservice.DateService;

public class ActivatorCheckingReference implements BundleActivator {
	public static BundleContext bc = null;
	public void start(BundleContext bc) throws Exception {
		System.out.println("Arrancando " + bc.getBundle().getHeaders().get(Constants.BUNDLE_NAME)	+ " ...");
		Activator.bc = bc;
		ServiceReference reference = bc.getServiceReference(DateService.class.getName());
		if (reference != null) {
			DateService service = (DateService)bc.getService(reference);
			System.out.println("Usando DateService: formateando fecha: " +service.getFormattedDate(new Date()));
			bc.ungetService(reference);
		} else {
			System.out.println("¡Servicio no disponible!");
		}
	}

	public void stop(BundleContext bc) throws Exception {
		System.out.println("Parando " + bc.getBundle().getHeaders().get(Constants.BUNDLE_NAME) + " ...");
		Activator.bc = null;
	}
}