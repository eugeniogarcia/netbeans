package es.solop.dateserviceuser.impl;

import java.util.Date;
import es.solop.dateservice.DateService;

public class ServiceUserThread extends Thread {
	private DateService service = null;
	private boolean running = true;
	
	public ServiceUserThread (DateService service) {
		this.service = service;
	}
	
	public void run() {
		Date date = null;
		String formattedDate = null;
		while (running) {
			date = new Date();
			try {
				formattedDate = this.service.getFormattedDate(date);
			} catch (RuntimeException e) {
				System.out.println("RuntimeException ocurrido durante la ejecución del servicio: " + e);
			}
			System.out.println("ServiceUserThread: fecha convertida tiene valor: " + formattedDate);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("ServiceUserThread ERROR: " + e);
			}
		}
	}
	
	public void stopThread() {
		this.running = false;
	}
}