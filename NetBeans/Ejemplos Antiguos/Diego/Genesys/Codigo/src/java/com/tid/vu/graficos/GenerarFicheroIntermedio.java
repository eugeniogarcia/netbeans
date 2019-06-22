package com.tid.vu.graficos;

import com.tid.vu.consultas.SeguimientoAtencionSql;
import com.tid.vu.datos.ParametrosConfiguracion;
import com.tid.vu.datos.ParametrosSeleccion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.xerces.jaxp.DocumentBuilderFactoryImpl;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;

/**
 *
 * @author t610908
 */
public class GenerarFicheroIntermedio {
    
    private ParametrosSeleccion selec_params;
    private ParametrosConfiguracion config_params;
    
    private final String SEGUIMIENTO_ATENCION = "Seguimiento por atencion";
    private final String SEGUIMIENTO_MODO = "Seguimiento por modos";
    
    private File ficheroJPG;
    
    /** Creates a new instance of GenerarFicheroIntermedio */
    public GenerarFicheroIntermedio(ParametrosSeleccion selec_params) {
        this.selec_params = selec_params;
        config_params = ParametrosConfiguracion.getInstance();
    }    
    
    /**
     * Metodo que nos devolvera el path al fichero XML generado
     */
    public void generaInformeFinal() throws IOException{        
        if (this.selec_params.getTipoInforme().equalsIgnoreCase(SEGUIMIENTO_ATENCION)){
            SeguimientoAtencionSql consulta = new SeguimientoAtencionSql(this.selec_params);
            double[] datos = consulta.realizaConsulta();
            if (datos!=null){
                SeguimientoAtencion informe = new SeguimientoAtencion(this.selec_params, datos);        
                //retorno = this.escribeXml(informe.getListaFicheros());
                ficheroJPG = informe.getFicheroJPG();            
            }else ficheroJPG = null;
        }else if (this.selec_params.getTipoInforme().equalsIgnoreCase(SEGUIMIENTO_MODO)){            
            SeguimientoModos informe = new SeguimientoModos(this.selec_params, null);
            //retorno = this.escribeXml(informe.getListaFicheros());
            ficheroJPG = informe.getFicheroJPG();
        }                    
    }
    
    /*private String escribeXml(ArrayList listaFicheros){
        Document xmlDoc = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactoryImpl.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();            
            xmlDoc = docBuilder.newDocument();
        } catch(Exception e) {
           System.out.println("Error : " + e);
        }
        Element datosTabla = xmlDoc.createElement("datosTabla");    //<datosTabla> 
        
        //Incluimos el tag imagen1
        Element imagen1 = xmlDoc.createElement("imagen1");
        imagen1.appendChild(xmlDoc.createTextNode(config_params.getPath_imagen1()));
        datosTabla.appendChild(imagen1);
        
        //Incluimos el tag imagen2
        Element imagen2 = xmlDoc.createElement("imagen2");
        imagen2.appendChild(xmlDoc.createTextNode(config_params.getPath_imagen2()));
        datosTabla.appendChild(imagen2);
        
        //Incluimos tag fechaImpresion
        Element fechaImpresion = xmlDoc.createElement("fechaImpresion"); //<fechaImpresion>
        fechaImpresion.appendChild(xmlDoc.createTextNode(selec_params.getFechaImpresion()));
        datosTabla.appendChild(fechaImpresion);
        
        ArrayList territorios = new ArrayList();
        for (int i=0; i<listaFicheros.size();i++){
            Element fila = xmlDoc.createElement("fila");            
            Element territorio = xmlDoc.createElement("territorio");
            territorio.appendChild(xmlDoc.createTextNode((String)listaFicheros.get(i)));
            territorios.add(territorio);
            //Si tenemos 3 elementos ya en el arrayList, hay que añadirselos, si estamos al final de listaFicheros hay aque añadirlos
            if (territorios.size()==3 || i==listaFicheros.size()-1){
                for (int j=0; j<territorios.size();j++){
                    fila.appendChild((Element)territorios.get(j));
                }
                territorios = new ArrayList();
                datosTabla.appendChild(fila);
            }             
        }        
        xmlDoc.appendChild(datosTabla);
        String fichero = null;
        try {            
            fichero =  this.salvarXML(xmlDoc);
        } catch (TransformerConfigurationException ex) {
            ex.printStackTrace();
        }
        return fichero;        
    }
   
    
    private String salvarXML(Document doc) throws TransformerConfigurationException{
        //Cogemos la instancia del calendario
        Calendar cal=Calendar.getInstance();
        //Obtenemos la fecha del servidor
        Date date=cal.getTime();        
        //La formateamos
        DateFormat dateFormatter=DateFormat.getDateInstance(DateFormat. FULL, Locale.getDefault());
        //Le ponemos formato que queremos que tenga
        dateFormatter = new SimpleDateFormat("dd_MM_yyyyhh_mm_ss");
        String fecha=dateFormatter.format(date);
        File fichero = new File("/vu"+fecha+".xml");        
        Source source = new DOMSource(doc);
        Result result = null;        
        try {
            result = new StreamResult(new FileOutputStream(fichero));
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado "+ex.getMessage());
        }
        // Escribe el DOM document al fichero
        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        try {
            xformer.transform(source, result);
        } catch (TransformerException ex) {
            System.out.println("ERROR: "+ex.getMessage());            
        }
        return fichero.getAbsolutePath();
    }*/
    
    public File getFicheroJPG(){
        return this.ficheroJPG;
    }
    
}
