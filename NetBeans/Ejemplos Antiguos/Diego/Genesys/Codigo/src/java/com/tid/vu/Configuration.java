package com.tid.vu;

import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Configuration 
extends	     Properties
implements   HttpSessionBindingListener, Serializable {
    
    private Logger logger = new Logger();
    private String propertiesFilePath = null;
    private boolean loaded = false;
    private String errMsg;
    
    public Configuration() {
    }
    
    public Configuration(String file) throws IOException {
        InputStream in = getClass().getResourceAsStream(file);
        this.load(in);
        this.loaded = true;
    }
    
    public String getPropertiesFilePath() {
	return propertiesFilePath;
    }
    
    public void setLogger(Logger aLogger) {
	logger = aLogger;
    }
    
    public boolean isLoaded() {
	return loaded;
    }
    
    public String getErrorMessage() {
        return this.errMsg;
    }
    
    private void testWebConfiguration() throws IllegalArgumentException {
        if(!(new File(this.getProperty("logPath")).exists()))
            throw new IllegalArgumentException("El directorio de log indicado en la configuraci�n no existe.");
    }
    
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
	HttpSession session = httpSessionBindingEvent.getSession();
	InputStream in = null;
	if(session.getAttribute("logger") != null)
	    this.setLogger((Logger)session.getAttribute("logger"));
	
	logger.print("Tratando de leer la configuraci�n de la sesi�n...", this);
	try {
	    if((propertiesFilePath = (String)session.getAttribute("configFile")) != null) {
		in = getClass().getResourceAsStream(propertiesFilePath);
           	this.load(in);
                this.testWebConfiguration();
		loaded = true;
	    } else {
		loaded = false;
		logger.println("Fall�");
		logger.println("La sesi�n no tiene la propiedad \"configFile\".", this);
	    }
	} catch(Exception ex) {
	    loaded = false;
	    logger.println("Fall�");
	    logger.println("No ha sido posible leer la configuraci�n desde " + 
			    propertiesFilePath, this
	    );
            this.errMsg = ex.getLocalizedMessage();
	}	
    }
    
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
    }
    
}
