package com.tid.vu;

import java.io.*;
import java.util.*;
import java.util.zip.*;
import java.text.*;
//import com.tid.srac.*;
import javax.servlet.http.*;

public class Logger 
implements HttpSessionBindingListener, Serializable {
    
    private	Configuration configuration =	    null;
// Identificador de la sesión a la que se vincula el Logger.    
    private	String			sessionID =		    null;
// Nombre del fichero de salida.
    private	String			logFilename =		    null;  
// Nombre del fichero de salida comprimida.
    private	String			compressedLogFilename =	    null;     
// PrintStream del log. Se inicializa para que su salida se pierda.
// De esta manera se evita una NullPointerException si p.ej. se pasa este
// stream a la función printStackTrace(PrintStream) de las excepciones,
// antes de que el Logger sea activado.    
    private	PrintStream		outPrinter =   
	new PrintStream(new BitBucketOutputStream());  
/**********************************************************************************/    
    private	ZipOutputStream		zOut = null;
    private	ZipInputStream		zIn = null;
    private	ZipEntry		zEntry = null;
/**********************************************************************************/    
// Formateador de fechas.     
    private	SimpleDateFormat	sdf;    
// Indicador de si el Logger está activo (si no es así las llamadas
// a los métodos print y println no hacen nada).
    private	boolean			active	=		    false;
// Indica el límite máximo de días que se mantiene un 
// fichero de log antes de ser borrado.
    private	int			oldFilesThreshold;
// Indica y/o establece si el flujo de salida será o no comprimido mediante ZIP.
    private	boolean			compressed =		    true;
/************************ Constantes ***************************/    
// String separador    
    public final static String		SESSION_SEPARATOR = 
	"###############################################";    
    
    public final static String		LOG_FILE_EXTENSION = "log";
    public final static String		COMPRESSED_LOG_FILE_EXTENSION = 
					    LOG_FILE_EXTENSION + ".zip";
    
    public Logger() {
	// Construye un Logger inactivo, esto es, las llamadas a sus métodos
	// de escritura no tendrán efecto alguno.
    }
    
    public Logger(String aSessionID) {
	this.setSessionID(aSessionID);
    }
    
    public void activate() throws IOException, IllegalStateException {
	if(sessionID == null)
	    throw new IllegalStateException("No se puede activar: asigne un identificador de sesión primero.");
	if(active) // "Logger ya activo, desactívelo primero"
	    return;
	String zLogFileName = logFilename + this.COMPRESSED_LOG_FILE_EXTENSION;
	if(compressed) {
	    Calendar c = Calendar.getInstance();
	    File zLogFile = new File(zLogFileName);
	    if(zLogFile.exists()) {
		// Ya existe un fichero de log con ese nombre (esto puede suceder
		// en Mozilla porque usa el ID de sesión mientras el mozilla
		// esté ejecutándose, aunque se abran y se cierren distintas
		// sesiones de la aplicación). Esto requiere crear un nuevo fichero
		// de log con todas las entradas anteriores que tuviese más la nueva,
		// y para ello es necesario abrirlo, leer cada entrada y volver a escribirla 
		// porque la API de java no da soporte para añadir nuevas entradas a
		// un fichero zip ya existente.
		File tempZLogFile = new File(zLogFileName + ".tmp");
		zLogFile.renameTo(tempZLogFile);
		zLogFile.delete();
		zIn = new ZipInputStream(new FileInputStream(tempZLogFile));
		zOut = new ZipOutputStream(new FileOutputStream(zLogFile));
		zOut.setMethod(zOut.DEFLATED);
		// Leemos las entradas del fichero anterior y las escribimos en el nuevo.
		ZipEntry zEntryTemp = null;
		byte[] byteBuffer = new byte[1024];
		int length;
		while((zEntryTemp = zIn.getNextEntry()) != null) {
		    zOut.putNextEntry(zEntryTemp);
		    length = 0;
		    
		    while ((length = zIn.read(byteBuffer)) > 0)
			zOut.write(byteBuffer, 0, length);
		    
		    zOut.closeEntry();
		    zIn.closeEntry();
		}
		// Al llegar aquí hemos dejado el stream de salida preparado al final del
		// fichero de log y listo para añadir la nueva entrada correspondiente a 
		// la sesión de la aplicación actual.
		zIn.close();
		tempZLogFile.delete();
	    } else {
		zOut = new ZipOutputStream(new FileOutputStream(zLogFile));
		zOut.setMethod(zOut.DEFLATED);
	    }
	    zEntry = new java.util.zip.ZipEntry(configuration.getProperty("logFilesPrefix") +
						sessionID + "-" + 
						c.get(c.HOUR_OF_DAY) + "." + 
						c.get(c.MINUTE) + "." + 
						c.get(c.SECOND) + "." +
						this.LOG_FILE_EXTENSION
	    );
	    zOut.putNextEntry(zEntry);
	    outPrinter = new PrintStream(zOut);
	} else
	    outPrinter = 
		new PrintStream(
		    new FileOutputStream(logFilename + this.LOG_FILE_EXTENSION, 
					 true));	    
	active = true;	
    }
    
    public void deactivate() throws IOException {
	if(!active) // Nada que hacer.
	    return;
	if(zOut != null) {
	    zOut.closeEntry();
	    zOut.close();
	    zOut = null;
	}
	if(outPrinter != null)
	    outPrinter.close();
	// Redireccionamos la salida a la nada...
	outPrinter = new PrintStream(new BitBucketOutputStream());
	active = false;
    }

    // Función para borrar los ficheros de log más antiguos de cierto límite marcado por
    // la propiedad "oldFilesThreshold" del fichero de propiedades.
    public void removeOldFiles() {
	File logDirectory;
	File[] logFiles;
	Date fileDate;
	Date currentDate;
		
	oldFilesThreshold = Integer.parseInt(configuration.getProperty("oldFilesThreshold"));
	// Si el límite es 0 o negativo, no borramos los ficheros.
	if(oldFilesThreshold < 1)
	    return;
	
	logDirectory = new File(configuration.getProperty("logPath"));
	logFiles = logDirectory.listFiles(new FileFilter() {
						public boolean accept(File f) {
						    if(	f == null ||
							f.getName().
							    indexOf(configuration.
								getProperty(
								    "logFilesPrefix")) == -1
						       ) 
							return false;
						    else
							return true;
						}
	});
	for (int i = 0; i < logFiles.length; i++)
	    if(	(System.currentTimeMillis() - logFiles[i].lastModified()) > 
		(oldFilesThreshold * 86400000L))
		logFiles[i].delete();
    }
    
// Escribe una cadena en el fichero de log con un encabezado de clase y hora.    
    public void print(String text, Object caller) {
	if(active)
	    outPrinter.print("[" + this.getTimestamp() + "][" +
			     caller.getClass().getName() + "]- " +
			     text
	    );
    }
    
// Escribe una cadena en el fichero de log.
    public void print(String text) {
	if(active)
	    outPrinter.print(text);
    }
    
    public void println(String text, Object caller) {
	this.print(text + "\r\n", caller);
    }
    
    public void println(String text) {
	this.print(text + "\r\n");
    }    
    
    public boolean isActive() {
	return active;
    }
    
    public String getTimestamp() {
	return sdf.format(Calendar.getInstance().getTime());
    }
    
    public PrintStream getOutPrinter() {
	return outPrinter;
    }
    
    public void setSessionID(String aSessionID) {
	sessionID = aSessionID;

	logFilename =	configuration.getProperty("logPath") + 
			configuration.getProperty("logFilesPrefix") +
			sessionID + ".";
	
	sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");	
    }
    
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
	HttpSession session = httpSessionBindingEvent.getSession();
	// Activamos el Logger.
	configuration = (Configuration)session.getAttribute("configuration");
	if(configuration == null)
	    return; // El Logger no estará activado.
	this.setSessionID(session.getId());
	compressed = (configuration.getProperty("logCompression").equals("true"));
	try {
	    this.activate();
	} catch (Exception e) {
	    // El Logger no estará activado.
	    return;
	}
	this.removeOldFiles();
	this.println(SESSION_SEPARATOR, this);
	this.println("Inicio de sesión ID " + session.getId(), this);
	this.println(	"Java " + 
			System.getProperty("java.vendor") + " v" + 
			System.getProperty("java.version"), 
			this
	);
	this.println(	"Sistema Operativo " +
			System.getProperty("os.name") + " v" + 
			System.getProperty("os.version") + " " +
			System.getProperty("os.arch"), 
			this
	);	
    }
    
    public void valueUnbound(javax.servlet.http.HttpSessionBindingEvent httpSessionBindingEvent) {
	this.println("Fin de sesión ID " + httpSessionBindingEvent.getSession().getId(), this);
	this.println(SESSION_SEPARATOR, this);
	try {
	    this.deactivate();
	} catch (IOException ioe) { /* ups! */ }
    }
    
    public void setCompressed(boolean flag) throws IOException {
	if(flag == compressed) // No se hace nada.
	    return;
	IOException tmpExcp = null;
	try {
	    // Es necesario """reiniciar""" el Logger para
	    // cambiar el modo de la salida.	
	    compressed = flag;
	    this.deactivate();
	    this.activate();
	} catch (IOException ioe) {
	    tmpExcp = ioe;
	    compressed = !flag; // Retornamos el flag al estado anterior.
	}
	if(tmpExcp != null)
	    throw tmpExcp;
    }
    
    public boolean isCompressed() {
	return compressed;
    }
    
    // Implementación de un OutputStream equivalente a dirigir la salida
    // a /dev/null, pero que funcione en sistemas no-UNIX. Simplemente
    // sobrecarga la función write con una que no hace nada.
    private class BitBucketOutputStream extends OutputStream {
	public void write(int b) throws IOException { 
	    // Función write vacía: se pretende obtener un OutputStream "nulo",
	    // es decir, el mismo efecto que escribir a /dev/null
	}	
    }
}
