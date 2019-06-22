package es.solop.dateserviceuser.impl;

import java.util.Date;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import es.solop.dateservice.DateService;

public class ActivatorWithServiceListener implements BundleActivator, ServiceListener {
	public static BundleContext bc = null;
	private ServiceUserThread thread = null;
	private DateService service = null;
	
	public void start(BundleContext bc) throws Exception {
		System.out.println("Empezando " + getClass().getName());
		ActivatorWithServiceListener.bc = bc;
		String filter = "(objectclass=" + DateService.class.getName() + ")";
		bc.addServiceListener(this, filter);
		ServiceReference references[] = bc.getServiceReferences(null, filter);
		for (int i = 0; references != null && i < references.length; i++) {
			this.serviceChanged(new ServiceEvent(ServiceEvent.REGISTERED, references[i]));
		}
	}

	public void stop(BundleContext bc) throws Exception {
		System.out.println("Parando " + bc.getBundle().getHeaders().get(Constants.BUNDLE_NAME) + " ...");
		ActivatorWithServiceListener.bc = null;
	}
	
	
	public void serviceChanged(ServiceEvent event) {
		switch (event.getType()) {
			case ServiceEvent.REGISTERED:
				log("Recibido ServiceEvent.REGISTERED");
				this.service = (DateService) ActivatorWithServiceListener.bc.getService(event.getServiceReference());
				this.startUsingService();
				break;
			case ServiceEvent.MODIFIED:
				log("Recibido ServiceEvent.MODIFIED");
				this.stopUsingService();
				this.service = (DateService) ActivatorWithServiceListener.bc.getService(event.getServiceReference());
				this.startUsingService();
				break;
			case ServiceEvent.UNREGISTERING:
				log("Recibido ServiceEvent.UNREGISTERING");
				this.stopUsingService();
				break;
		}
	}
	
	private void stopUsingService() {
		this.thread.stopThread();
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.service = null;
	}
	
	private void startUsingService() {
		this.thread = new ServiceUserThread(this.service);
		this.thread.start();
	}
	
	private void log(String message) {
		System.out.println(ActivatorWithServiceListener.bc.getBundle().getHeaders().get(Constants.BUNDLE_NAME)	+ ": " + message);
	}
}