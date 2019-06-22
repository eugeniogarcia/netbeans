/*
 * creaXML.java
 *
 * Created on 5 de marzo de 2007, 15:53
 *
 *
 */
package com.tid.vu.pdf;

import com.tid.vu.datos.ParametrosConfiguracion;
import com.tid.vu.datos.ParametrosSeleccion;
import com.tid.vu.datos.Tabla;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
import org.w3c.dom.Element;

public class CreaXML {
    
    private Document xmlDoc = null;
    private Element datosTabla = null;
    private ParametrosSeleccion selec_params;
    private ParametrosConfiguracion config_params;
    
    /** Creates a new instance of creaXML */
    public CreaXML(ParametrosSeleccion selec_params){
        this.selec_params = selec_params; 
        config_params = ParametrosConfiguracion.getInstance();
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
    }    
    
    /**
     * Metodo que genera el XML con los datos de la base de datos
     */
    public String generaXML(Tabla informe, Tabla informe2){        
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactoryImpl.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();            
            xmlDoc = docBuilder.newDocument();
        } catch(Exception e) {
           System.out.println("Error : " + e);
        }
        datosTabla = xmlDoc.createElement("datosTabla");    //<datosTabla>    
        
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
        
        //Incluimos tag desglose
        Element desglose = xmlDoc.createElement("desglose"); //<desglose>
        desglose.appendChild(xmlDoc.createTextNode(selec_params.getDesglose()));
        datosTabla.appendChild(desglose);
        
        //Incluimos los elementos de seleccion
        Element seleccion = xmlDoc.createElement("seleccion"); // <seleccion>      
        
        seleccion.appendChild(escribeParametros("Fechas: ",
                selec_params.getFechaInicio()+" - "+selec_params.getFechaFinalizacion(),
                "Periodo Horario: ",
                selec_params.getHoraInicialDescr()+" - "+selec_params.getHoraFinalDescr()));
        
        seleccion.appendChild(escribeParametros("Territorio Origen: ",
                selec_params.getTerritorioOrigen(),
                "Provincia Origen: ",
                selec_params.getProvinciaOrigen()));
        
        seleccion.appendChild(escribeParametros("Territorio Destino: ",
                selec_params.getTerritorioDestino(),
                "Provincia Destino: ",
                selec_params.getProvinciaDestino()));
        
        seleccion.appendChild(escribeParametros("Oficinas: ",
                selec_params.getOficinas(),
                "Puestos: ",
                selec_params.getPuesto()));
        
        seleccion.appendChild(escribeParametros("Tratamientos: ",
                selec_params.getTratamiento(),
                "Valor de la llamada: ",
                selec_params.getValorLlamada()));
        
        seleccion.appendChild(escribeParametros("Plataformas: ",
                selec_params.getPlataforma(),
                "Idioma de la Llamada: ",
                selec_params.getIdiomaAtencion()));
        
        seleccion.appendChild(escribeParametros("Modo Atencion: ",
                selec_params.getModoLlamada(),
                "Perfil Antencion",
                selec_params.getPerfilAtencion()));
        
        seleccion.appendChild(escribeParametros("Segmentos Entrada: ",
                selec_params.getSegmentoEntrada(),
                "Segmento Salida: ",
                selec_params.getSegmentoSalida()));
        
        seleccion.appendChild(escribeParametros("Subsegmento Entrada: ",
                selec_params.getSubSegmentoEntrada(),
                "Subsegmento Salida: ",
                selec_params.getSubSegmentoSalida()));  
        
        seleccion.appendChild(escribeParametros("Cod Encaminador Entrada: ",
                selec_params.getCod_encaminadorEntrada(),               
                "Cod Encaminador Salida: ",
                selec_params.getCod_encaminadorSalida()));
        
        seleccion.appendChild(escribeParametros("Familias: ",
                selec_params.getFamilia(),
                "Agentes: ",
                selec_params.getAgentes()));
        
        seleccion.appendChild(escribeParametros("Servicio: ",
                selec_params.getServicio(),
                "Enrutamiento: ",
                selec_params.getEnrutamiento()));
        
        seleccion.appendChild(escribeParametros("Tipo de Servicio: ",
                selec_params.getTipoServicio(),
                "Nodo de Red: ",
                selec_params.getNodoRed()));
        
        datosTabla.appendChild(seleccion);     
        
        Element tabla = xmlDoc.createElement("tabla");        
        for (int i=0; i<informe.getFila();i++){
            Element fila = xmlDoc.createElement("fila");
            for (int j=0; j<informe.getColumna(); j++){
                Element columna = xmlDoc.createElement("columna");
                columna.appendChild(xmlDoc.createTextNode(String.valueOf(informe.getDatos()[i][j])));
                fila.appendChild(columna);
            }
            tabla.appendChild(fila);
        }
        
        
        
        Element total = xmlDoc.createElement("total");
        //Para el primer elemento se pone siempre TOTAL
        Element primeraColumna = xmlDoc.createElement("columna");
        primeraColumna.appendChild(xmlDoc.createTextNode("TOTAL"));
        total.appendChild(primeraColumna);
        //la ultima fila son los totales
        for (int j = 1; j<informe.getColumna(); j++){
            Element columna = xmlDoc.createElement("columna");
            columna.appendChild(xmlDoc.createTextNode(String.valueOf(informe.getDatos()[informe.getFila()][j])));
            total.appendChild(columna);
        }
        tabla.appendChild(total);
        datosTabla.appendChild(tabla);
        
        
        //Si viene una segunda tabla la introducimos aqui
        if (informe2!=null){
            Element tabla2 = xmlDoc.createElement("tabla2");
            for (int i=0; i<informe2.getFila();i++){
            Element fila = xmlDoc.createElement("fila");
                for (int j=0; j<informe2.getColumna(); j++){
                    Element columna = xmlDoc.createElement("columna");
                    columna.appendChild(xmlDoc.createTextNode(String.valueOf(informe2.getDatos()[i][j])));
                    fila.appendChild(columna);
                }
            tabla2.appendChild(fila);
            }        
        
            total = xmlDoc.createElement("total");
            //la ultima fila son los totales
            for (int j = 0; j<informe2.getColumna(); j++){
                Element columna = xmlDoc.createElement("columna");
                columna.appendChild(xmlDoc.createTextNode(String.valueOf(informe2.getDatos()[informe2.getFila()][j])));
                total.appendChild(columna);
            }
            tabla2.appendChild(total);
            datosTabla.appendChild(tabla2);
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
    
    private Element escribeParametros(String texto1, String texto1valor, String texto2, String texto2valor){
        
        Element filaSeleccion = xmlDoc.createElement("filaSeleccion"); //<filaSeleccion>
        
        if (texto1valor!=null){
            Element parametro = xmlDoc.createElement("parametro"); // <parametro>        
            filaSeleccion.appendChild(parametro);
            Element nombre = xmlDoc.createElement("nombre"); //<nombre>
            nombre.appendChild(xmlDoc.createTextNode(texto1));
            Element valor = xmlDoc.createElement("valor"); //<valor>
            String auxiliar = texto1valor.replaceAll("'", "");
            valor.appendChild(xmlDoc.createTextNode(auxiliar.replaceAll(",", " ")));
            parametro.appendChild(nombre);
            parametro.appendChild(valor);               
            
        }
        
        if (texto2valor!=null){
            Element segundo_parametro = xmlDoc.createElement("parametro"); //<parametro>
            filaSeleccion.appendChild(segundo_parametro);
            Element nombre2 = xmlDoc.createElement("nombre"); //<nombre>
            nombre2.appendChild(xmlDoc.createTextNode(texto2));
            Element valor2 = xmlDoc.createElement("valor");
            String auxiliar = texto2valor.replaceAll("'", "");
            valor2.appendChild(xmlDoc.createTextNode(auxiliar.replaceAll(",", " ")));
            segundo_parametro.appendChild(nombre2);
            segundo_parametro.appendChild(valor2);
        }
        
        
        return filaSeleccion;
    }
    
}
