package sp.osgi.bundle1.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sp.osgi.bundle1.paquete1.Clase1;
import sp.osgi.bundle2.paquete2.Clase2;
import sp.osgi.bundle3.paquete3.Clase3;

public class Activator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		(new Clase1()).sayHello();
		(new Clase2()).sayHello();
		(new Clase3()).sayHello();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		(new Clase3()).sayGoodbye();
		(new Clase2()).sayGoodbye();
		(new Clase1()).sayGoodbye();
	}

}
