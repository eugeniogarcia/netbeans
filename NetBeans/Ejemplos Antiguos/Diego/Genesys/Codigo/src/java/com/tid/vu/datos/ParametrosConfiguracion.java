package com.tid.vu.datos;

import com.tid.vu.sql.SqlConnection;

/**
 *
 * @author t610908
 */
public class ParametrosConfiguracion {
    
    //Dias maximos de la tabla de agregados
    private int diasTablaAgregados = 45;
    //Cadena del pool de conexiones
    private String cadena_pool;
    //Fichero para informe General de intervenciones
    private String general_intervenciones;
    //Fichero para informe de familias
    private String informe_familias;
    //Fichero para informe de llamadas de cliente
    private String intervenciones_cliente;
    //Fichero para informe de navegacion IVR
    private String navegacion_IVR;
    //Fichero para informe intervenciones
    private String intervenciones;
    //Fichero para informe de tiempo de conexion
    private String tiempo_conexion;
    //Fichero para informe de atencion de servicio
    private String atencion_servicio;    
    //Fichero para informe tiempo de respuesta
    private String tiempo_respuesta;
    //Fichero para informes graficos
    private String grafico;   
    //Fichero para informe de atencion agente
    private String operacion_agente;    
    //Fichero para informe de transferencias
    private String transferencias;    
    //Conexion a la base de datos
    private SqlConnection conexion;
    //Descripcion del error de la conexion en caso que haya error
    private String descripcionError;
    //Path de los ficheros de texto que hacen de plantilla
    private String path_plantillas;
    
    private String bloque_ivr;
    
    private String path_imagen1;
    private String path_imagen2;
    
    private static ParametrosConfiguracion config_params;
    
    private ParametrosConfiguracion() {        
    }
    
    public static ParametrosConfiguracion getInstance(){
        if (config_params==null){
            return config_params = new ParametrosConfiguracion();
        }else
            return config_params;  
    }   
    
    public String getCadena_pool() {
        return cadena_pool;
    }

    public String getGeneral_Intervencion() {
        return general_intervenciones;
    }

    public int getDiasTablaAgregados() {
        return diasTablaAgregados;
    }

    public String getInforme_familias() {
        return informe_familias;
    }

    public String getIntervenciones_cliente() {
        return intervenciones_cliente;
    }

    public String getNavegacion_IVR() {
        return navegacion_IVR;
    }

    public String getIntervenciones() {
        return intervenciones;
    }

    public String getTiempo_conexion() {
        return tiempo_conexion;
    }    

    public String getTiempo_respuesta() {
        return tiempo_respuesta;
    }

    public void setCadena_pool(String cadena_pool) {
        this.cadena_pool = cadena_pool;
    }

    public void setDiasTablaAgregados(int diasTablaAgregados) {
        this.diasTablaAgregados = diasTablaAgregados;
    }

    public void setGeneral_Intervencion(String general_intervenciones) {
        this.general_intervenciones = general_intervenciones;
    }

    public void setInforme_familias(String informe_familias) {
        this.informe_familias = informe_familias;
    }

    public void setIntervenciones_cliente(String intervenciones_cliente) {
        this.intervenciones_cliente = intervenciones_cliente;
    }

    public void setNavegacion_IVR(String navegacion_IVR) {
        this.navegacion_IVR = navegacion_IVR;
    }

    public void setIntervenciones(String intervenciones) {
        this.intervenciones = intervenciones;
    }

    public void setTiempo_conexion(String tiempo_conexion) {
        this.tiempo_conexion = tiempo_conexion;
    }

    public void setAtencion_Servicio(String atencion_servicio) {
        this.atencion_servicio = atencion_servicio;
    }
    
    public void setOperacion_agente(String operacion_agente) {
        this.operacion_agente = operacion_agente;
    }    

    public void setTiempo_respuesta(String tiempo_respuesta) {
        this.tiempo_respuesta = tiempo_respuesta;
    }

    public String getGrafico() {
        return grafico;
    }

    public void setGrafico(String grafico) {
        this.grafico = grafico;
    }

    public String getAtencion_Servicio() {
        return atencion_servicio;
    }

    public String getOperacion_Agente() {
        return operacion_agente;
    }   
    
    public boolean conectarBaseDatos(){
        conexion = new SqlConnection(this.cadena_pool);
        this.descripcionError = conexion.getDescripcionErrorConexion();
        return conexion.isErrorConexion();
    }
    
    public SqlConnection getConexion(){
        return this.conexion;
    }    
    
    public String errorConexionBaseDatos(){
        return this.descripcionError;
    }
    
    public String getPathPlantillas(){
        return this.path_plantillas;
    }
    
    public void setPathPlantillas(String path_plantillas){
        this.path_plantillas = path_plantillas;
    }

    public String getBloque_ivr() {
        return bloque_ivr;
    }

    public void setBloque_ivr(String bloque_ivr) {
        this.bloque_ivr = bloque_ivr;
    }

    public String getTransferencias() {
        return transferencias;
    }

    public void setTransferencias(String transferencias) {
        this.transferencias = transferencias;
    }  

    public String getPath_imagen1() {
        return path_imagen1;
    }

    public String getPath_imagen2() {
        return path_imagen2;
    }

    public void setPath_imagen1(String path_imagen1) {
        this.path_imagen1 = path_imagen1;
    }

    public void setPath_imagen2(String path_imagen2) {
        this.path_imagen2 = path_imagen2;
    }
    

}
