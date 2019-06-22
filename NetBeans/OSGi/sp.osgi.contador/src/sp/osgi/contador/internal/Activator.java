package sp.osgi.contador.internal;

import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sp.osgi.contador.servicio.Contador;
import sp.osgi.contador.servicio.IContador;


public class Activator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		Properties props = new Properties();
		props.put("tipo.subscripcion", "gratuita");
		context.registerService(Contador.class.getName(), new IContador(), props);
		System.out.println("Registrado servicio "+Contador.class.getName());
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		// el servicio se des-registra automáticamente
	}

}
