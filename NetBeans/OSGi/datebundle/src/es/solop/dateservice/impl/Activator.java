package es.solop.dateservice.impl;

import java.util.Hashtable;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import es.solop.dateservice.DateService;

public class Activator implements BundleActivator {
	public static BundleContext bc = null;

	public void start(BundleContext bc) throws Exception {
		System.out.println("Empezando " + bc.getBundle().getHeaders().get(Constants.BUNDLE_NAME) + " ...");
		Activator.bc = bc;
		DateService service = new DateServiceImpl();
		ServiceRegistration registration = bc.registerService(DateService.class.getName(), service, new Hashtable());
		System.out.println("Servicio registrado: dateservice");
	}

	public void stop(BundleContext bc) throws Exception {
		System.out.println("Parando " + bc.getBundle().getHeaders().get(Constants.BUNDLE_NAME) + " ...");
		Activator.bc = null;
	}
}