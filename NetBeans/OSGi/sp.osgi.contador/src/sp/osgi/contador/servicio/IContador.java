package sp.osgi.contador.servicio;


public class IContador implements Contador {
	
	private int value = 0;

	public int getValue() {
		return value;
	}

	public synchronized int incr(int val) {
		value+=val; 
		return value;
	}

}
