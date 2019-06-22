package sp.osgi.webinterface;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;

import sp.osgi.contador.servicio.Contador;

public class Activator implements BundleActivator, ServiceListener {
	private BundleContext context = null;

	private ServiceReference httpRef = null;

	private ServiceReference contRef = null;

	private Contador contador = null;

	private ContadorServlet servlet = null;

	public void start(BundleContext context) throws Exception {
		this.context = context;
		httpRef = context.getServiceReference(HttpService.class.getName());
		if (httpRef == null) {
			System.err.println("No se encontró ningún servicio Http");
			return;
		}
		contRef = context.getServiceReference(Contador.class.getName());
		if (contRef == null) {
			System.err.println("No se encontró ningún servicio Contador");
			return;
		}
		contador = (Contador) context.getService(contRef);
		context.addServiceListener(this);

		HttpService srvc = (HttpService) context.getService(httpRef);
		srvc.registerResources("/index.html", "/index.html", null);
		srvc.registerResources("/top.html", "/top.html", null);

		servlet = new ContadorServlet();
		servlet.setContador(contador);
		srvc.registerServlet("/contador", servlet, null, null);
	}

	public void stop(BundleContext context) throws Exception {
		if (httpRef != null) {
			HttpService srvc = (HttpService) context.getService(httpRef);
			srvc.unregister("/index.html");
			srvc.unregister("/top.html");
			srvc.unregister("/contador");
			context.ungetService(httpRef);
			System.out.println("Liberada referencia a servicio " + HttpService.class.getName());
		}
	}

	public void serviceChanged(ServiceEvent event) {
		ServiceReference sr = event.getServiceReference();
		Object o = context.getService(sr);
		if (o instanceof Contador) {
			switch (event.getType()) {
			case ServiceEvent.REGISTERED:
				contador = (Contador) o;
				servlet.setContador(contador);
				System.out.println("Registrando servicio contador");
				break;
			case ServiceEvent.UNREGISTERING:
				contador = null;
				servlet.setContador(contador);
				System.out.println("Servicio contador no disponible");
				break;
			default:
				break;
			}

		}
	}
}
