package es.solop.dateserviceuser.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.util.tracker.ServiceTracker;
import es.solop.dateservice.DateService;

public class Activator implements BundleActivator {
	public static BundleContext bc = null;
	private ServiceTracker tracker = null;
	
	public void start(BundleContext bc) throws Exception {
		System.out.println("Arrancando " + bc.getBundle().getHeaders().get(Constants.BUNDLE_NAME) + " ...");
		Activator.bc = bc;
		MyServiceTrackerCustomizer customizer = new MyServiceTrackerCustomizer(bc);
		tracker = new ServiceTracker(bc, DateService.class.getName(), customizer);
		tracker.open();
	}
	
	public void stop(BundleContext bc) throws Exception {
		System.out.println("Parando " + bc.getBundle().getHeaders().get(Constants.BUNDLE_NAME) + " ...");
		tracker.close();
		Activator.bc = null;
	}
}