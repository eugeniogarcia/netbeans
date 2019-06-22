/*
 * GenerarInforme.java
 * Created on 12 de febrero de 2007, 12:23
 */

package com.tid.vu;

import com.tid.vu.consultas.ConsultaGeneral;
import com.tid.vu.datos.ParametrosConfiguracion;
import com.tid.vu.datos.ParametrosSeleccion;
import com.tid.vu.datos.Tabla;
import com.tid.vu.graficos.GenerarFicheroIntermedio;
import com.tid.vu.pdf.CreaXML;
import com.tid.vu.txt.CrearFicheroTxt;
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Clase intermedia para la generacion del informe seleccionado por el usuario
 * @author Telefonica Soluciones
 * @version
 */
public class GenerarInforme extends HttpServlet {    
       
   
    private ParametrosSeleccion seleccion = new ParametrosSeleccion();    
    private ParametrosConfiguracion config_params;
    private String formato;    
    private String msjError = "";
    private boolean hayError = false;    
    private Logger logger = null;
    
    public void init(ServletConfig config) throws ServletException
    {        
        config_params = ParametrosConfiguracion.getInstance();
        String fichero_properties = config.getInitParameter("fichero_properties");
        // Comprobacion de argumentos
	if (fichero_properties==null)  {
            this.hayError = true;
            this.msjError="Error de configuracion. No se ha especificado fichero de configuración";
            return;
        }
	// Lectura de fichero de parametros
	Properties properties = new Properties();
	FileInputStream fichero_proper = null;	
	try
	{
		fichero_proper = new FileInputStream(fichero_properties);
		properties.load(fichero_proper);
		fichero_proper.close();
	}
	catch(Exception pe)
	{
                this.hayError = true;
                this.msjError="Error: "+pe.getMessage();        
                return;
	}	
	// Comprobacion
	if (properties==null || properties.size()==0)
	{
                this.hayError = true;
                this.msjError += "Error: No hay parámetros de configuración en <"+fichero_proper+">";
                return;
	}
        
        //Obtención de los parametros de configuración
        try {
        config_params.setCadena_pool(properties.getProperty("java_pool"));
        config_params.setDiasTablaAgregados(Integer.parseInt(properties.getProperty("dias_maximo")));
        config_params.setGeneral_Intervencion(properties.getProperty("general_intervenciones"));
        config_params.setInforme_familias(properties.getProperty("informe_familias"));
        config_params.setIntervenciones_cliente(properties.getProperty("informe_intervenciones_clientes"));
        config_params.setNavegacion_IVR(properties.getProperty("informe_navegacion_ivr"));
        config_params.setIntervenciones(properties.getProperty("informe_intervenciones"));
        config_params.setTiempo_conexion(properties.getProperty("informe_tiempo_conexion"));
        config_params.setAtencion_Servicio(properties.getProperty("informe_atencion_servicio"));        
        config_params.setOperacion_agente(properties.getProperty("informe_operacion_agente"));        
        config_params.setTiempo_respuesta(properties.getProperty("informe_tiempo_respuesta"));
        config_params.setGrafico(properties.getProperty("informe_graficos"));        
        config_params.setBloque_ivr(properties.getProperty("bloque_ivr"));
        config_params.setTransferencias(properties.getProperty("informe_tranferencias"));        
        config_params.setPathPlantillas(properties.getProperty("path_plantilla"));
        config_params.setPath_imagen1(properties.getProperty("imagen1"));
        config_params.setPath_imagen2(properties.getProperty("imagen2"));        
    }
        catch(Exception pe)
	{
                this.hayError = true;
                this.msjError+="Error al configurar la aplicación ";  
                return;
        }             
        //Realizamos la conexion a la base de datos
        if (config_params.conectarBaseDatos()){
                this.hayError = true;
                this.msjError+="Error en conexion a BB.DD "+config_params.errorConexionBaseDatos();
                return;
        }
    }
            
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //primero se comprueba que no haya error en el init
        if (this.hayError) {
            String msg = "Error generando el informe. " + 
			"Informe al administrador del siguiente mensaje: " ;
            String error =  this.msjError;
            String respError = response.encodeRedirectURL("/vu/share/msgPage.jsp?type=ERROR" + 
                      "&msg1=" + msg + "&msg2=" + error + 
                      "&linkText=Regresar&linkURL=/vu/index.jsp");
            response.sendRedirect(respError);       
        
	    return;               
        }        
        
	HttpSession session = request.getSession();
        logger = (Logger)session.getAttribute("logger");
        
        //Recuperaremos los datos que nos proporciona el usuario en informes.jsp                
        try {
            logger.println("Recuperando parámetros de selección...");
        seleccion.setFechaInicio(request.getParameter("FECHA_DIA_INI")+"/"+request.getParameter("FECHA_MES_INI")+"/"+request.getParameter("FECHA_ANO_INI"));
        seleccion.setFechaFinalizacion(request.getParameter("FECHA_DIA_FIN")+"/"+request.getParameter("FECHA_MES_FIN")+"/"+request.getParameter("FECHA_ANO_FIN"));
        seleccion.setHoraInicial(request.getParameter("HORA_INI"));
        seleccion.setHoraFinal(request.getParameter("HORA_FIN"));
        seleccion.setHoraInicialDescr(request.getParameter("HORA_INI_DES"));
        seleccion.setHoraFinalDescr(request.getParameter("HORA_FIN_DES"));
        seleccion.setDiaInicial(request.getParameter("FECHA_DIA_INI"));
        seleccion.setDiaFinal(request.getParameter("FECHA_DIA_FIN"));
        seleccion.setMesInicial(request.getParameter("FECHA_MES_INI"));
        seleccion.setMesFinal(request.getParameter("FECHA_MES_FIN"));
        seleccion.setAnioFinal(request.getParameter("FECHA_ANO_FIN"));
        seleccion.setAnioInicial(request.getParameter("FECHA_ANO_INI"));
        seleccion.setServicio(request.getParameter("SERVICIOSMULTIPLES"));
        seleccion.setProvinciaOrigen(request.getParameter("PROV_ORIGMULTIPLES"));
        seleccion.setProvinciaDestino(request.getParameter("PROV_DESTMULTIPLES"));
        seleccion.setValorLlamada(request.getParameter("ESPECTASMULTIPLES"));
        seleccion.setTratamiento(request.getParameter("TTOSMULTIPLES"));
        seleccion.setTipoServicio(request.getParameter("TIPOSERVICIOMULTIPLES"));
        //Entrada
        seleccion.setSegmentoEntrada(request.getParameter("SEGMENTOSMULTIPLESENTRADA"));
        seleccion.setSubSegmentoEntrada(request.getParameter("SUBSEGMENTOSMULTIPLESENTRADA"));
        seleccion.setCod_encaminadorEntrada(request.getParameter("COD_ENC_ENTRADAMULTIPLESENTRADA"));  
        //Salida
        seleccion.setSegmentoSalida(request.getParameter("SEGMENTOS_SMULTIPLES"));
        seleccion.setSubSegmentoSalida(request.getParameter("SUGSEGMENTO_SMULTIPLES"));
        seleccion.setCod_encaminadorSalida(request.getParameter("COD_ENC_SALIDAMULTIPLES"));        
        seleccion.setPlataforma(request.getParameter("COORDMULTIPLES"));
        seleccion.setTerritorioOrigen(request.getParameter("GERE_ORIGMULTIPLES"));
        seleccion.setTerritorioDestino(request.getParameter("GERE_DESTMULTIPLES"));
        seleccion.setOficinas(request.getParameter("OFICINASMULTIPLES"));        
        seleccion.setPuesto(request.getParameter("PUESTOS"));        
        
        seleccion.setGrupoAtencion(request.getParameter("GRUPATENMULTIPLES"));        
        seleccion.setModoLlamada(request.getParameter("MODOATENMULTIPLES"));
        seleccion.setPerfilAtencion(request.getParameter("PERFILATENMULTIPLES"));
        seleccion.setIdiomaAtencion(request.getParameter("IDIOMAATENMULTIPLES"));
        
        seleccion.setFamilia(request.getParameter("FAMILIAS"));
        seleccion.setAgentes(request.getParameter("AGENTES"));
        
        seleccion.setEnrutamiento(request.getParameter("ENRUTAMIENTOMULTIPLES"));                
        seleccion.setNodoRed(request.getParameter("NODOREDMULTIPLES"));
        seleccion.setDesglose(request.getParameter("DESGLOSE"));
        formato = request.getParameter("FORMATO");        
        seleccion.setInforme(request.getParameter("tipoInforme"));        
        
        seleccion.setFechaImpresion(returnFechaHoy());   
        
        
        } 
        catch (Exception e) 
        {
            logger.println("Error al obtener los parámetros: ");
            e.printStackTrace(logger.getOutPrinter());
            String msg = "Error generando el informe. " + 
			"Informe al administrador del siguiente mensaje: " ;
            String error =  "Error al obtener los parámetros de generación del informe";
            String respError = response.encodeRedirectURL("/vu/share/msgPage.jsp?type=ERROR" + 
                      "&msg1=" + msg + "&msg2=" + error + 
                      "&linkText=Regresar&linkURL=/vu/index.jsp");
            response.sendRedirect(respError);       
            
	    return;                 
        }
        
        //Llamamos a la consulta general para traer datos de la BBDD
        ConsultaGeneral consulta = null;
        Tabla datosInforme = null;
        try {
            consulta = new ConsultaGeneral(this.seleccion, this.logger);
            datosInforme = consulta.realizaConsulta();        
        } catch (Exception e) {
            logger.println("Error al generar la consulta: ");
            e.printStackTrace(logger.getOutPrinter());            
            String msg = "Error generando el informe. " + 
			"Informe al administrador del siguiente mensaje: " ;
            String error =  "Error al generar la consulta para el informe.";
            String respError = response.encodeRedirectURL("/vu/share/msgPage.jsp?type=ERROR" + 
                      "&msg1=" + msg + "&msg2=" + error + 
                      "&linkText=Regresar&linkURL=/vu/index.jsp");
            response.sendRedirect(respError);       
            
	    return;           
        }
        
        try {
        //Si el formato elegido es PDF
        if (formato.equalsIgnoreCase("PDF")){
                logger.print("Generando informe en PDF...");
            CreaXML xml = new CreaXML(this.seleccion);
                logger.print("Llamar a crea XML...");
            String nombreFichero = new String();
            if (seleccion.getTipoInforme().equalsIgnoreCase("Tiempos de Atencion de Agente")){                
                nombreFichero = xml.generaXML(datosInforme, consulta.getTablaInforme2());
            }else{
                nombreFichero = xml.generaXML(datosInforme, null);
                    logger.print("Generar el XML...");
            }
            String hojaEstilo = this.hojaEstilo();
            String resp = response.encodeRedirectURL("informesPDF.jsp?section="+request.getParameter("section")+"&tipoInforme="+seleccion.getTipoInforme()+"&ficheroXml="+nombreFichero+"&ficheroXsl="+hojaEstilo);
                logger.println("OK");
            response.sendRedirect(resp);            
        }
        //Si es en formato TXT
        else if (formato.equalsIgnoreCase("TEXTO")){
            CrearFicheroTxt txtCreator = new CrearFicheroTxt(seleccion);               
            File fichero = txtCreator.generaFichero();
            txtCreator.generaInformeFinal(fichero);  
            response.setContentType("application");
            //leemos el fichero en un array de bytes
            byte array_bytes[]=new byte[(int)fichero.length()];
            FileInputStream fis=new FileInputStream(fichero);
            fis.read(array_bytes);
            //Obtenemos el OutputStream
            OutputStream os=response.getOutputStream();
            //envia el array de bytes al navegador
            os.write(array_bytes);
            //fuerza a enviar los datos
            os.flush();
            //Borramos el fichero txt que queda en el servidor
            fichero.delete();
        }else if (formato.equalsIgnoreCase("IMAGEN")){
            //Creamos la imagen 
            GenerarFicheroIntermedio ficheroIntermedio = new GenerarFicheroIntermedio(seleccion);                           
            String hojaEstilo = config_params.getPathPlantillas()+config_params.getGrafico();
            ficheroIntermedio.generaInformeFinal();
            File imagen = ficheroIntermedio.getFicheroJPG();
            String resp = null;
            if (imagen!=null){
                resp = response.encodeRedirectURL("informesGrafico.jsp?ficheroJPG="+imagen.getAbsolutePath()+"&section="+request.getParameter("section")+"&tipoInforme="+seleccion.getTipoInforme());
            }
            else{
                resp = response.encodeRedirectURL("informesGrafico.jsp?ficheroJPG=NOHAY&section="+request.getParameter("section")+"&tipoInforme="+seleccion.getTipoInforme());
            }
            /*String resp = response.encodeRedirectURL("informesPDF.jsp?section="+seleccion.getTipoInforme()+"&ficheroXml="+nombreFichero+"&ficheroXsl="+hojaEstilo);*/
            response.sendRedirect(resp);
        }        
        } catch (Exception e) {
            logger.println("Falló");
            e.printStackTrace(logger.getOutPrinter());            
            String msg = "Error generando el informe. " + 
			"Informe al administrador del siguiente mensaje: " ;
            String error =  "Error generando el informe en el formato " + formato ;
            String respError = response.encodeRedirectURL("/vu/share/msgPage.jsp?type=ERROR" + 
                      "&msg1=" + msg + "&msg2=" + error + 
                      "&linkText=Regresar&linkURL=/vu/index.jsp");
            response.sendRedirect(respError);            
	    return;               
        }
    }
    
    
    
    public String returnFechaHoy(){
        //Cogemos la instancia del calendario
        Calendar cal=Calendar.getInstance();
        //Obtenemos la fecha del servidor
        Date date=cal.getTime();        
        //La formateamos
        DateFormat dateFormatter=DateFormat.getDateInstance(DateFormat. FULL, Locale.getDefault());
        //Le ponemos formato que queremos que tenga
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormatter.format(date);
        
    }
    
    private String hojaEstilo(){
        String hojaEstilo = null;
        if (seleccion.getTipoInforme().equalsIgnoreCase("Informe de Intervenciones")){
            hojaEstilo = config_params.getPathPlantillas()+config_params.getIntervenciones();            
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Informe de Transferencias")){
            hojaEstilo = config_params.getPathPlantillas()+config_params.getTransferencias();
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Tiempos de Respuesta")){
            hojaEstilo = config_params.getPathPlantillas()+config_params.getTiempo_respuesta();
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Tiempos de Atencion de Servicio")){
            hojaEstilo = config_params.getPathPlantillas()+config_params.getAtencion_Servicio();
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Intervenciones de clientes")){
            hojaEstilo = config_params.getPathPlantillas()+config_params.getIntervenciones_cliente();
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Informe general de intervenciones")){
            hojaEstilo = config_params.getPathPlantillas()+config_params.getGeneral_Intervencion();
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Familias")){
            hojaEstilo = config_params.getPathPlantillas()+config_params.getInforme_familias();            
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Tiempos de Conexion")){
            hojaEstilo = config_params.getPathPlantillas()+config_params.getTiempo_conexion();
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Tiempos de Atencion de Agente")){
            hojaEstilo = config_params.getPathPlantillas()+config_params.getOperacion_Agente();        
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Navegacion IVR")){
            hojaEstilo = config_params.getPathPlantillas()+config_params.getNavegacion_IVR();
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Bloques IVR")){
            hojaEstilo = config_params.getPathPlantillas()+config_params.getBloque_ivr();
        }
        return hojaEstilo;
    }   
}
