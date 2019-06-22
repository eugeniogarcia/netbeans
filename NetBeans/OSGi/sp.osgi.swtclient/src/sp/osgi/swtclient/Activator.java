package sp.osgi.swtclient;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import sp.osgi.contador.servicio.Contador;

public class Activator implements BundleActivator {

	private ServiceReference[] refs = null;

	SWTWindow window = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		String filter = "(tipo.subscripcion=gratuita)";
		refs = context.getServiceReferences(Contador.class.getName(), filter);
		if (refs == null) {
			System.err.println("No se encontró ningún servicio con las propiedades " + filter);
			return;
		}

		window = new SWTWindow((Contador) context.getService(refs[0]));
		window.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		if (window != null) {
			window.quit = true;
			window.close();
			window.interrupt();
		}
		if (refs != null && refs.length > 0) {
			context.ungetService(refs[0]);
			System.out.println("Liberada referencia a servicio " + Contador.class.getName());
		}
	}

}
