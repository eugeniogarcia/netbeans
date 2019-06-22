/*
 * ConsultaGeneral.java
 *
 * Created on 6 de marzo de 2007, 14:46
 *
 */

package com.tid.vu.consultas;

import com.tid.vu.datos.ParametrosSeleccion;
import com.tid.vu.datos.Tabla;
import com.tid.vu.*;

/**
 * Clase que realizara la consulta dependiendo del tipo de informe a realizar
 * devolviendo el tipo de datos Tabla resultante de la consulta.
 * @author t610908
 */
public class ConsultaGeneral {    
    
    private ParametrosSeleccion seleccion;
    
    private Tabla tablaAuxiliar;
    private Logger logger = null;
    
    /**
     * Constructor de la clase ConsultaGeneral, se le pasan los parametros de 
     * seleccion
     */ 
    public ConsultaGeneral(ParametrosSeleccion seleccion, Logger logger) {
        this.seleccion = seleccion;
        this.logger = logger;
    }    
    
    /**
     * Metodo realizaConsulta, metodo que realizara la consulta dependiendo del
     * tipo de informe que se requiera
     */
    public Tabla realizaConsulta(){
        Tabla informe = null;
        if (seleccion.getTipoInforme().equalsIgnoreCase("Tiempos de Atencion de Servicio")){
            AtencionServicioSql consulta = new AtencionServicioSql(this.seleccion);            
            informe = consulta.realizaConsulta();
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Informe general de intervenciones")){
            GeneralIntervencionesSql consulta = new GeneralIntervencionesSql(this.seleccion);
            informe = consulta.realizaConsulta();
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Familias")){
            InformeFamiliasSql consulta = new InformeFamiliasSql(this.seleccion);
            informe = consulta.realizaConsulta();
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Intervenciones de clientes")){
            IntervencionesClientesSql consulta = new IntervencionesClientesSql(this.seleccion);
            informe = consulta.realizaConsulta();
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Informe de Intervenciones")){
            IntervencionesSql consulta = new IntervencionesSql(this.seleccion, this.logger);
            informe = consulta.realizaConsulta();
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Tiempos de Conexion")){
            TiempoConexionSql consulta = new TiempoConexionSql(this.seleccion, this.logger);
            informe = consulta.realizaConsulta();
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Tiempos de Respuesta")){
            TiempoRespuestaSql consulta = new TiempoRespuestaSql(this.seleccion, this.logger);
            informe = consulta.realizaConsulta();
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Informe de Transferencias")){
            TransferenciasSql consulta = new TransferenciasSql(this.seleccion);
            informe = consulta.realizaConsulta();
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Navegacion IVR")){
            NavegacionIVRSql consulta = new NavegacionIVRSql(this.seleccion, this.logger);
            informe = consulta.realizaConsulta();
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Bloques IVR")){
            BloquesIVRSql consulta = new BloquesIVRSql(this.seleccion,  this.logger);
            informe = consulta.realizaConsulta();
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Tiempos de Atencion de Agente")){
            TiempoOperacionAgenteSql consulta = new TiempoOperacionAgenteSql(this.seleccion);
            consulta.realizaConsulta();
            informe = consulta.getResultadoSentencia1();
            tablaAuxiliar = consulta.getResultadoSentencia2();
        }
        return informe;
    } 
    
    public Tabla getTablaInforme2(){
        return tablaAuxiliar;
    }
}
