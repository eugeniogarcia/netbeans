package es.solop.holasolopbundle.impl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
/**
* @author Diego López de Ipiña
*/
public class Activator implements BundleActivator {
	public static BundleContext bc = null;
	private HolaSolopThread thread = null;

	public void start(BundleContext bc) throws Exception {
		System.out.println("Arrancando HolaSolop bundle ...");
		Activator.bc = bc;
		this.thread = new HolaSolopThread();
		this.thread.start();
	}
	public void stop(BundleContext bc) throws Exception {
		System.out.println("Parando HolaSolop bundle ...");
		this.thread.stopThread();
		this.thread.join();
		Activator.bc = null;
	}
}