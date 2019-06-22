package es.solop.dateserviceuser.impl;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import es.solop.dateservice.DateService;

public class MyServiceTrackerCustomizer implements ServiceTrackerCustomizer {
	private ServiceUserThread thread = null;
	private BundleContext bc;
	
	public MyServiceTrackerCustomizer(BundleContext bc) {
		this.bc = bc;
	}
	
	public Object addingService(ServiceReference reference) {
		DateService service = (DateService) bc.getService(reference);
		if (this.thread == null) {
			this.thread = new ServiceUserThread(service);
			this.thread.start();
			return service;
		} else 
			return service;
	}
	
	public void modifiedService(ServiceReference reference, Object	serviceObject) {
		this.thread.stopThread();
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		DateService service = (DateService) bc.getService(reference);
		this.thread = new ServiceUserThread(service);
		this.thread.start();
	}
	
	public void removedService(ServiceReference reference, Object serviceObject) {
		this.thread.stopThread();
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.thread = null;
	}
}