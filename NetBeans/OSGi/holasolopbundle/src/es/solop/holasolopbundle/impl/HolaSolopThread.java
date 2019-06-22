package es.solop.holasolopbundle.impl;
/**
* @author Diego López de Ipiña
*/
public class HolaSolopThread extends Thread {
	private boolean running = true;
	
	public HolaSolopThread() {
	}
	
	public void run() {
		while (running) {
			System.out.println("¡Hola Solop!");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				System.out.println("HolaSolpThread ERROR: " + e);
			}
		}
	}
	
	public void stopThread() {
		this.running = false;
	}
}