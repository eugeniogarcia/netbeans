/*
 * HTMLFactory.java
 *
 * Created on 2 de enero de 2007, 10:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.tid.vu;

import com.tid.vu.datos.ParametrosConfiguracion;
import com.tid.vu.sql.SqlConnection;
import com.tid.vu.sql.SqlRow;
import com.tid.vu.sql.SqlSentencia;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.http.*;
import java.util.*;

/**
 *
 * @author Propietario
 */
public class HTMLFactory 
implements   HttpSessionBindingListener {
    
    private HttpSession session = null;
    private ParametrosConfiguracion config_params;
    private SqlConnection sqlCon = null;
    
    /********* CONSTANTES *****************/
    public static final int LEAFS_SELECTABLE = 0;
    public static final int NODES_SELECTABLE = 1;
    public static final int ALL_SELECTABLE = 2;
    
    public String produceStartComment() {
	return "\n<!-- <HTMLFactory> -->\n";
    }

    public String produceEndComment() {
	return "\n<!-- </HTMLFactory> -->\n";
    }    

    
    public synchronized String generateOptions(String sql, 
                                                boolean code, 
                                                boolean desc
    ) throws Exception {
	
	// code = true: el texto del Option incluye el código de la entidad.
	// desc = true: el texto del Option incluye la descripción de la entidad.
	int numberOfRows = 0;
	StringBuffer htmlString = new StringBuffer();
	StringBuffer rawDesc = null;
        rawDesc = null;
	htmlString.append("\n\t\t\t<option value='"); 
	htmlString.append("title=\"" + (rawDesc == null ? "" : rawDesc.toString()) + "\">");
	htmlString.append(code && desc ? " - " : "");
	htmlString.append(desc && rawDesc != null ? rawDesc.toString() : "");
	htmlString.append("</option>");
	    numberOfRows++;
	
	//logger.println("OK [" + numberOfRows + " resultado(s)]");
	
	return	this.produceStartComment() + 
		htmlString.toString() + 
		this.produceEndComment();	
    }

    public synchronized String generarSelect(String sql, 
                                                boolean code, 
                                                boolean desc
    ) throws Exception {
	
	// code = true: el texto del Option incluye el código de la entidad.
	// desc = true: el texto del Option incluye la descripción de la entidad.
        config_params = ParametrosConfiguracion.getInstance();
        sqlCon = config_params.getConexion();          
	int numberOfRows = 0;
	StringBuffer htmlString = new StringBuffer();
	StringBuffer rawDesc = null;
        
        ArrayList cod= new ArrayList();
        ArrayList des= new ArrayList();   
        
        if (sql.compareTo("intervenciones")==0)
            this.auxDesglose(cod,des);
        if (sql.compareTo("transferencias")==0)
            this.auxDesglose(cod,des);        
        if (sql.compareTo("tiempo_respuesta")==0)
            this.auxDesglose(cod,des);
        if (sql.compareTo("atencion_servicio")==0)
            this.auxDesglose(cod,des);
        if (sql.compareTo("intervencion_cliente")==0)
            this.auxDesglose(cod,des);
        if (sql.compareTo("general_intervenciones")==0)
            this.auxDesglose(cod,des);
        if (sql.compareTo("familias")==0)
            this.auxDesgloseFamilias(cod,des);
        if (sql.compareTo("tiempo_conexion")==0)
            this.auxDesgloseConexion(cod,des);
        if (sql.compareTo("atencion_agente")==0)
            this.auxDesgloseFamilias(cod,des);        
        if (sql.compareTo("navegacion_ivr")==0)
            this.auxDesgloseIVR(cod,des);        
        if (sql.compareTo("bloques_ivr")==0)
            this.auxDesgloseIVR(cod,des);                
        if (sql.compareTo("SERVICIOS")==0)
            this.auxServicios(cod,des);     
        if (sql.compareTo("ESPECTAS")==0)
            this.auxEspecialistas(cod,des);          
        if (sql.compareTo("TTOS")==0)
            this.auxTratamientos(cod,des);   
        if (sql.compareTo("TERRITORIOS")==0)
            this.auxTerritorios(cod,des);           
        if (sql.compareTo("PROVINCIAS")==0)
            this.auxProvincias(cod,des);       
        if (sql.compareTo("AREAS")==0)
            this.auxAreas(cod,des);         
        if (sql.compareTo("SEGMENTOS")==0)
            this.auxSegmentos(cod,des);   
        if (sql.compareTo("ENCAMINADOR")==0)
            this.auxEncaminador(cod,des);   
        if (sql.compareTo("PLATAFORMAS")==0)
            this.auxPlataformas(cod,des);        
        if (sql.compareTo("GRUPATEN")==0)
            this.auxGrupAten(cod,des);         
        if (sql.compareTo("ELEMRED")==0)
            this.auxElemRed(cod,des);   
        if (sql.compareTo("HORAINI")==0)
            this.auxHoraInicial(cod,des);
        if (sql.compareTo("HORAFIN")==0)
            this.auxHoraFinal(cod, des);
        if (sql.compareTo("TIPOSERVICIO")==0)
            this.auxTipoServ(cod,des);
        if (sql.compareTo("ENRUTAMIENTO")==0)
            this.auxEnrutamiento(cod,des);
        if (sql.compareTo("MODOATEN")==0)
            this.auxModoAtencion(cod,des);
        if (sql.compareTo("IDIATEN")==0)
            this.auxIdiomaAten(cod,des);
        if (sql.compareTo("OFICINAS")==0)
            this.auxOficinas(cod,des);
        if (sql.compareTo("PERFILATEN")==0)
            this.auxEspecialistas(cod,des);
        
	while(numberOfRows<cod.size()) {
	    if(des.get(numberOfRows) != null) {
		rawDesc = new StringBuffer(des.get(numberOfRows).toString());
		int quoteOffset = -2;
		while((quoteOffset = rawDesc.toString().indexOf('"', quoteOffset + 2)) > -1)
		    rawDesc.insert(quoteOffset, '\\');
	    } else
		rawDesc = null;           
            if ((sql.compareTo("HORAFIN")==0) && (des.get(numberOfRows).toString().compareTo("23:30")==0)){
                 htmlString.append("\n\t\t\t<option SELECTED value='");
            }
            else{
                htmlString.append("\n\t\t\t<option value='");             
            }
	    htmlString.append(cod.get(numberOfRows).toString() + "' ");            
	    htmlString.append("title=\"" + (rawDesc == null ? "" : rawDesc.toString()) + "\">");
	    htmlString.append((code ? cod.get(numberOfRows).toString() : "" ));
	    htmlString.append(code && desc ? " - " : "");
	    htmlString.append(desc && rawDesc != null ? rawDesc.toString() : "");
	    htmlString.append("</option>");
	    numberOfRows++;
	}
	
	//logger.println("OK [" + numberOfRows + " resultado(s)]");
	String prueba = htmlString.toString();
	return	this.produceStartComment() + 
		htmlString.toString() + 
		this.produceEndComment();	
    }  
    
    private void auxOficinas(ArrayList cod, ArrayList des) throws Exception{
        String sql = "SELECT ID_OFICINA, DS_OFICINA FROM OFICINA";
        SqlSentencia datos = sqlCon.realizarConsulta(sql);
        for (int i=0;datos!=null && i<datos.getNumeroFilas();i++){
            SqlRow fila = datos.getRow(i);
            cod.add(fila.getField(0));
            des.add(fila.getField(1));
        } 
    }
    
    private void auxIdiomaAten(ArrayList cod, ArrayList des) throws Exception{
        String sql = "SELECT ID_IDIOMA_ATENCION, DS_IDIOMA_ATENCION FROM IDIOMA_ATENCION";
        SqlSentencia datos = sqlCon.realizarConsulta(sql);
        for (int i=0;datos!=null && i<datos.getNumeroFilas();i++){
            SqlRow fila = datos.getRow(i);
            cod.add(fila.getField(0));
            des.add(fila.getField(1));
        } 
    }
    
    private void auxModoAtencion(ArrayList cod, ArrayList des) throws Exception{
        String sql = "SELECT ID_MODO_ATENCION, DS_MODO_ATENCION FROM MODO_ATENCION";
        SqlSentencia datos = sqlCon.realizarConsulta(sql);
        for (int i=0;datos!=null && i<datos.getNumeroFilas();i++){
            SqlRow fila = datos.getRow(i);
            cod.add(fila.getField(0));
            des.add(fila.getField(1));
        }
    }
    
    private void auxEnrutamiento(ArrayList cod, ArrayList des) throws Exception{
        String sql = "SELECT ID_ENRUTAMIENTO, DS_ENRUTAMIENTO FROM ENRUTAMIENTO";
        SqlSentencia datos = sqlCon.realizarConsulta(sql);
        for (int i=0;datos!=null && i<datos.getNumeroFilas();i++){
            SqlRow fila = datos.getRow(i);
            cod.add(fila.getField(0));
            des.add(fila.getField(1));
        }
    }
    
    private void auxTipoServ(ArrayList cod, ArrayList des) throws Exception {
        String sql = "SELECT ID_TIPO_SERVICIO, DS_TIPO_SERVICIO FROM TIPO_SERVICIO";
        SqlSentencia datos = sqlCon.realizarConsulta(sql);
        for (int i=0;datos!=null && i<datos.getNumeroFilas();i++){
            SqlRow fila = datos.getRow(i);
            cod.add(fila.getField(0));
            des.add(fila.getField(1));
        }
    }
    
    private void auxDesgloseFamilias(ArrayList cod, ArrayList des) throws Exception {
        cod.add("Jornada");
        cod.add("Jornada y Tipo de servicio");
        cod.add("Día");
        cod.add("Día y Tipo de servicio");
        cod.add("Mes");
        cod.add("Mes y Tipo de Servicio");
        cod.add("Año");
        cod.add("Año y Tipo de Servicio");
        cod.add("Agente");
        cod.add("Agente y Tipo de Servicio");
        cod.add("Tipo de Servicio");
        cod.add("Puesto");
        cod.add("Modo Atención");
        cod.add("Valor de la llamada");		
        cod.add("Perfil de atención");
        cod.add("Plataforma");		
        cod.add("Oficina");      
    }
    
    private void auxDesglose(ArrayList cod, ArrayList des) throws Exception {
        cod.add("ID_TRAMO");
        cod.add("ID_DIA");          
        cod.add("ID_DIA"); 
        cod.add("ID_DIA"); 
        cod.add("ID_DIA");         
        cod.add("ID_AGENTE");			
        cod.add("ID_FAMILIA");				
        cod.add("ID_SERVICIO");				
        cod.add("ID_PROVINCIA_ORIGEN");	
        cod.add("ID_TERRITORIO_ORIGEN");
        cod.add("ID_PROVINCIA_DESTINO");				
        cod.add("ID_TERRITORIO_DESTINO");
        cod.add("ID_ENRUTAMIENTO");			
        cod.add("ID_TRATAMIENTO");			
        cod.add("ID_SEGMENTO_ENTRADA");
        cod.add("ID_SUBSEGMENTO_ENTRADA");
        cod.add("ID_SEGMENTO_SALIDA");
        cod.add("ID_SUBSEGMENTO_SALIDA");			
        cod.add("ID_ENCAMINADOR_ENTRADA");			
        cod.add("ID_ENCAMINADOR_SALIDA");			
        cod.add("ID_PUESTO");
        cod.add("ID_OFICINA");
        cod.add("ID_MODO_ATENCION");			
        cod.add("ID_VALOR_LLAMADA");			
        cod.add("ID_PERFIL_ATENCION");			
        cod.add("ID_PLATAFORMA");				
        cod.add("ID_IDIOMA_ATENCION");			
        cod.add("ID_TIPO_SERVICIO");			
        cod.add("ID_NODO");
        
        des.add("HORA");
        des.add("DIA");  
        des.add("SEMANA");
        des.add("MES");
        des.add("AÑO");
        des.add("AGENTE");			
        des.add("FAMILIA");				
        des.add("SERVICIO");				
        des.add("PROVINCIA ORIGEN");
        des.add("TERRITORIO ORIGEN");				
        des.add("PROVINCIA ORIGEN");
        des.add("TERRITORIO DESTINO");
        des.add("ENRUTAMIENTO");			
        des.add("TRATAMIENTO");			
        des.add("SEGMENTO ENTRADA");
        des.add("SUBSEGMENTO ENTRADA");
        des.add("SEGMENTO SALIDA");
        des.add("SUBSEGMENTO SALIDA");
        des.add("CODIGO ENC ENTRADA");			
        des.add("CODIGO ENC SALIDA");			
        des.add("PUESTO");
        des.add("OFICINA");
        des.add("MODO ATENCION");			
        des.add("VALOR DE LA LLAMADA");			
        des.add("PERFIL DE ATENCION");			
        des.add("PLATAFORMA");				
        des.add("IDIOMA DE ATENCION");			
        des.add("TIPO SERVICIO");			
        des.add("NODO DE RED");
    }
    
     private void auxDesgloseConexion(ArrayList cod, ArrayList des) throws Exception {
        cod.add("ID_TRAMO");
        cod.add("ID_DIA");
        cod.add("ID_DIA");
        cod.add("ID_DIA");
        cod.add("ID_DIA");
        cod.add("ID_PUESTO");
        cod.add("ID_MODO_ATENCION");
        cod.add("ID_VALOR_LLAMADA");
        cod.add("ID_PERFIL_ATENCION");
        cod.add("ID_IDIOMA_ATENCION");        
        des.add("HORA");
        des.add("DIA");  
        des.add("SEMANA");
        des.add("MES");
        des.add("AÑO");        		
        des.add("PUESTO/ OFICINA");			
        des.add("MODO ATENCION");			
        des.add("VALOR DE LA LLAMADA");			
        des.add("PERFIL DE ATENCION");			    			
        des.add("IDIOMA DE ATENCION");        
    }
    
     private void auxDesgloseIVR(ArrayList cod, ArrayList des) throws Exception {		
        			
        //Puntos desalida
        cod.add("ID_TRAMO");
        cod.add("ID_DIA");          
        cod.add("ID_DIA"); 
        cod.add("ID_DIA"); 
        cod.add("ID_DIA");                 
        cod.add("ID_SERVICIO");				
        cod.add("ID_PROVINCIA_ORIGEN");        
        cod.add("ID_TERRITORIO_ORIGEN");
        cod.add("ID_ENRUTAMIENTO");	        			
        cod.add("ID_SEGMENTO_ENTRADA");
        cod.add("ID_SUBSEGMENTO_ENTRADA");
        cod.add("ID_SEGMENTO_SALIDA");
        cod.add("ID_SUBSEGMENTO_SALIDA");			
        cod.add("ID_ENCAMINADOR_ENTRADA");			
        cod.add("ID_ENCAMINADOR_SALIDA");  
        cod.add("ID_IVR");
        
        des.add("HORA");
        des.add("DIA");  
        des.add("SEMANA");
        des.add("MES");
        des.add("AÑO");        				
        des.add("SERVICIO");
        des.add("PROVINCIA ORIGEN");
        des.add("TERRITORIO ORIGEN");
        des.add("ENRUTAMIENTO");        			
        des.add("SEGMENTO ENTRADA");
        des.add("SUBSEGMENTO ENTRADA");
        des.add("SEGMENTO SALIDA");
        des.add("SUBSEGMENTO SALIDA");
        des.add("CODIGO ENC ENTRADA");			
        des.add("CODIGO ENC SALIDA");	
        des.add("IVR");
       
    }
    
    private void auxElemRed(ArrayList cod, ArrayList des) throws Exception {
        String sql = "SELECT ID_NODO, DS_NODO FROM NODO_RED";
        SqlSentencia datos = sqlCon.realizarConsulta(sql);
        for (int i=0;datos!=null && i<datos.getNumeroFilas();i++){
            SqlRow fila = datos.getRow(i);
            cod.add(fila.getField(0));
            des.add(fila.getField(1));
        }       
    }
    
    private void auxEncaminador(ArrayList cod, ArrayList des) throws Exception {
        String sql = "SELECT ID_ENCAMINADOR, DS_ENCAMINADOR FROM ENCAMINADOR";
        SqlSentencia datos = sqlCon.realizarConsulta(sql);
        for (int i=0;datos!=null && i<datos.getNumeroFilas();i++){
            SqlRow fila = datos.getRow(i);
            cod.add(fila.getField(0));
            des.add(fila.getField(1));
        }                        
    }
    
    private void auxServicios(ArrayList cod, ArrayList des) throws Exception {        
        String sql = "SELECT ID_SERVICIO, DS_SERVICIO FROM SERVICIO";
        SqlSentencia datos = sqlCon.realizarConsulta(sql);
        for (int i=0;datos!=null && i<datos.getNumeroFilas();i++){
            SqlRow fila = datos.getRow(i);
            cod.add(fila.getField(0));
            des.add(fila.getField(1));
        }        
    }
    
    private void auxEspecialistas(ArrayList cod, ArrayList des) throws Exception {
        String sql = "SELECT ID_SKILL_LLAMADA, DS_SKILL_LLAMADA FROM SKILL_LLAMADA";
        SqlSentencia datos = sqlCon.realizarConsulta(sql);
        for (int i=0;datos!=null && i<datos.getNumeroFilas();i++){
            SqlRow fila = datos.getRow(i);
            cod.add(fila.getField(0));
            des.add(fila.getField(1));
        }     
    }

    private void auxTratamientos(ArrayList cod, ArrayList des) throws Exception {
       String sql = "SELECT ID_ENRUTAMIENTO, DS_ENRUTAMIENTO FROM ENRUTAMIENTO";
        SqlSentencia datos = sqlCon.realizarConsulta(sql);
        for (int i=0;datos!=null && i<datos.getNumeroFilas();i++){
            SqlRow fila = datos.getRow(i);
            cod.add(fila.getField(0));
            des.add(fila.getField(1));
        }        
    }
    private void auxSegmentos(ArrayList cod, ArrayList des) throws Exception {
        String sql = "SELECT ID_SEGMENTO, DS_SEGMENTO FROM SEGMENTO";
        SqlSentencia datos = sqlCon.realizarConsulta(sql);
        for (int i=0;datos!=null && i<datos.getNumeroFilas();i++){
            SqlRow fila = datos.getRow(i);
            cod.add(fila.getField(0));
            des.add(fila.getField(1));
        }        
        
    }
    private void auxAreas(ArrayList cod, ArrayList des) throws Exception {       
        String sql = "SELECT ID_SUBSEGMENTO, DS_SUBSEGMENTO FROM SUBSEGMENTO";
        SqlSentencia datos = sqlCon.realizarConsulta(sql);
        for (int i=0;datos!=null && i<datos.getNumeroFilas();i++){
            SqlRow fila = datos.getRow(i);
            cod.add(fila.getField(0));
            des.add(fila.getField(1));
        }                         
    }

    private void auxGrupAten(ArrayList cod, ArrayList des) throws Exception {
        boolean ok; 
        ok = cod.add("PRIMERA LINEA");
        ok = cod.add("AG. VIRTUAL");
        ok = cod.add("2ª LINEA RESIDENCIAL");
        ok = cod.add("2ª LINEA PROFESIONALES");
        ok = cod.add("CANAL ONLINE");
        ok = cod.add("CESION DE DATOS");
        ok = cod.add("LINEA INFORMATIVA");
        ok = cod.add("GRUPO ACTIVA");
        ok = cod.add("IDIOMAS");
        ok = des.add("PRIMERA LINEA");
        ok = des.add("AG. VIRTUAL");
        ok = des.add("2ª LINEA RESIDENCIAL");
        ok = des.add("2ª LINEA PROFESIONALES");
        ok = des.add("CANAL ONLINE");
        ok = des.add("CESION DE DATOS");
        ok = des.add("LINEA INFORMATIVA");
        ok = des.add("GRUPO ACTIVA");
        ok = des.add("IDIOMAS");
    }
    
    private void auxPlataformas(ArrayList cod, ArrayList des) throws Exception {
       String sql = "SELECT ID_PLATAFORMA, DS_PLATAFORMA FROM PLATAFORMA";
        SqlSentencia datos = sqlCon.realizarConsulta(sql);
        for (int i=0;datos!=null && i<datos.getNumeroFilas();i++){
            SqlRow fila = datos.getRow(i);
            cod.add(fila.getField(0));
            des.add(fila.getField(1));
        }        
    }
    
    private void auxProvincias(ArrayList cod, ArrayList des) throws Exception {
        String sql = "SELECT ID_PROVINCIA, DS_PROVINCIA FROM PROVINCIA";
        SqlSentencia datos = sqlCon.realizarConsulta(sql);
        for (int i=0;datos!=null && i<datos.getNumeroFilas();i++){
            SqlRow fila = datos.getRow(i);
            cod.add(fila.getField(0));
            des.add(fila.getField(1));
        }     
    }

    private void auxTemas(ArrayList cod, ArrayList des) throws Exception {
        boolean ok; 
        ok = cod.add("ACUERDOS CON COLECTIVOS");
        ok = cod.add("ADSL");
        ok = cod.add("ALTA DE LINEA");
        ok = cod.add("AVERIAS");
        ok = cod.add("BAJA DE ABONO");
        ok = cod.add("CAIDA DE CONSUMO");
        ok = cod.add("CALIDAD DE LA INFORMACION");
        ok = cod.add("CAMPAÑAS");
        ok = cod.add("CANGURO NET");
        ok = cod.add("CENTRO HERMES");
        ok = cod.add("CONSULTA FICHA");
        ok = cod.add("CONSULTAS FACT./COBROS");
        ok = cod.add("EQUIPOS Y SERV.SUPLEMENT.");
        ok = cod.add("GESTION SIN CLIENTE");
        ok = cod.add("GESTION TARJETAS PREFERENTES");
        ok = cod.add("IMAGENIO");
        ok = cod.add("INFORMACION/TRAMITACION");
        ok = cod.add("INFORMACION 11818/11822");
        ok = cod.add("INSTALACIONES");
        ok = cod.add("INTERNET");
        ok = cod.add("LLAMADAS ERRONEAS");
        ok = cod.add("LLAMADAS MALICIOSAS");
        ok = cod.add("MONITORIZACIÓN FICHA");
        ok = cod.add("MUNDO ADSL");
        ok = cod.add("PLANES Y PROD.FACTURACION");
        ok = cod.add("PORTABILIDAD");
        ok = cod.add("PREASIGNACION");
        ok = cod.add("RECARGA TARJETA ACTIVA (CRETA)");
        ok = cod.add("RECLAMACION FACT./COBROS");
        ok = cod.add("SERVIC.DIRECTORIO AJENOS A TDE");
        ok = cod.add("SUMA");
        ok = cod.add("TELEFONICA ON-LINE");
        ok = cod.add("TELEMARKETING DE SALIDA");
        ok = cod.add("TIENDA DIRECTA");
        ok = des.add("ACUERDOS CON COLECTIVOS");
        ok = des.add("ADSL");
        ok = des.add("ALTA DE LINEA");
        ok = des.add("AVERIAS");
        ok = des.add("BAJA DE ABONO");
        ok = des.add("CAIDA DE CONSUMO");
        ok = des.add("CALIDAD DE LA INFORMACION");
        ok = des.add("CAMPAÑAS");
        ok = des.add("CANGURO NET");
        ok = des.add("CENTRO HERMES");
        ok = des.add("CONSULTA FICHA");
        ok = des.add("CONSULTAS FACT./COBROS");
        ok = des.add("EQUIPOS Y SERV.SUPLEMENT.");
        ok = des.add("GESTION SIN CLIENTE");
        ok = des.add("GESTION TARJETAS PREFERENTES");
        ok = des.add("IMAGENIO");
        ok = des.add("INFORMACION/TRAMITACION");
        ok = des.add("INFORMACION 11818/11822");
        ok = des.add("INSTALACIONES");
        ok = des.add("INTERNET");
        ok = des.add("LLAMADAS ERRONEAS");
        ok = des.add("LLAMADAS MALICIOSAS");
        ok = des.add("MONITORIZACIÓN FICHA");
        ok = des.add("MUNDO ADSL");
        ok = des.add("PLANES Y PROD.FACTURACION");
        ok = des.add("PORTABILIDAD");
        ok = des.add("PREASIGNACION");
        ok = des.add("RECARGA TARJETA ACTIVA (CRETA)");
        ok = des.add("RECLAMACION FACT./COBROS");
        ok = des.add("SERVIC.DIRECTORIO AJENOS A TDE");
        ok = des.add("SUMA");
        ok = des.add("TELEFONICA ON-LINE");
        ok = des.add("TELEMARKETING DE SALIDA");
        ok = des.add("TIENDA DIRECTA");        
    }
    
    private void auxTerritorios(ArrayList cod, ArrayList des) throws Exception {
       String sql = "SELECT ID_TERRITORIO, DS_TERRITORIO FROM TERRITORIO";
        SqlSentencia datos = sqlCon.realizarConsulta(sql);
        for (int i=0;datos!=null && i<datos.getNumeroFilas();i++){
            SqlRow fila = datos.getRow(i);
            cod.add(fila.getField(0));
            des.add(fila.getField(1));
        }        
    }
    
    private void auxHoraInicial(ArrayList cod, ArrayList des) throws Exception{
        String sql = "SELECT ID_TRAMO, HORA_INICIO FROM TRAMOS_HORARIOS ORDER BY HORA_INICIO";
        SqlSentencia datos = sqlCon.realizarConsulta(sql);
        for (int i=0;datos!=null && i<datos.getNumeroFilas();i++){
            SqlRow fila = datos.getRow(i);
            cod.add(fila.getField(0));
            des.add(fila.getField(1));
        } 
    }
    
    private void auxHoraFinal(ArrayList cod, ArrayList des) throws Exception{
        String sql = "SELECT ID_TRAMO, HORA_FIN FROM TRAMOS_HORARIOS ORDER BY HORA_FIN";
        SqlSentencia datos = sqlCon.realizarConsulta(sql);
        for (int i=0;datos!=null && i<datos.getNumeroFilas();i++){
            SqlRow fila = datos.getRow(i);
            cod.add(fila.getField(0));
            des.add(fila.getField(1));
        }
    }
    
    
    public String getYear(){
        //Cogemos la instancia del calendario
        Calendar cal=Calendar.getInstance();
        //Obtenemos la fecha del servidor
        Date date=cal.getTime();        
        //La formateamos
        DateFormat dateFormatter=DateFormat.getDateInstance(DateFormat. FULL, Locale.getDefault());
        //Le ponemos formato que queremos que tenga
        dateFormatter = new SimpleDateFormat("yyyy");
        return dateFormatter.format(date);
    }
    
    public void valueBound(javax.servlet.http.HttpSessionBindingEvent httpSessionBindingEvent) {
	this.session = httpSessionBindingEvent.getSession();
	try {
	    //logger = (Logger)session.getAttribute("logger");	
	    //logger.print("Vinculando a la sesión...", this);
	    //databaseOperator = (DatabaseOperator)session.getAttribute("dbOp");	
	    //accessManager = (AccessManager)session.getAttribute("accessManager");
	    //logger.println("OK, oId=" + this.hashCode());
	} catch(NullPointerException npe) {
	    // Probablemente se ha intentado acceder a la sesión cuando esta
	    // ya se había cerrado (puede suceder si se tiene varias ventanas abiertas
	    // y en una de ellas se cierra la aplicación y se intenta continuar 
	    // trabajando con la otra).
	    return;
	}	    
    }
    
    public void valueUnbound(javax.servlet.http.HttpSessionBindingEvent httpSessionBindingEvent) {
	//logger.print("Cerrando...", this);
	//databaseOperator = null;
	//accessManager = null;
	//logger.println("OK, oId=" + this.hashCode());
    }
    
    public String returnFechaHoy(){
        //Cogemos la instancia del calendario
        Calendar cal=Calendar.getInstance();
        //Obtenemos la fecha del servidor
        Date date=cal.getTime();        
        //La formateamos
        DateFormat dateFormatter=DateFormat.getDateInstance(DateFormat. FULL, Locale.getDefault());
        //Le ponemos formato americano porque javascript no lo traga de otra forma
        dateFormatter = new SimpleDateFormat("yyyyMMdd");
        String fecha = dateFormatter.format(date);        
        return fecha;
        
    }
    
    public int getNumDias(){
        config_params = ParametrosConfiguracion.getInstance();
        return config_params.getDiasTablaAgregados();
    }
    
}
