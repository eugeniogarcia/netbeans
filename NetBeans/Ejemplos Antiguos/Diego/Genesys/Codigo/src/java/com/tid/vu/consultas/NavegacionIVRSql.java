package com.tid.vu.consultas;

import com.tid.vu.Logger;
import com.tid.vu.datos.ParametrosSeleccion;
import com.tid.vu.datos.ParametrosConfiguracion;
import com.tid.vu.datos.Tabla;
import com.tid.vu.sql.SqlConnection;
import com.tid.vu.sql.SqlRow;
import com.tid.vu.sql.SqlSentencia;

/**
 *
 * @author t610908
 */
public class NavegacionIVRSql {
    
    private ParametrosSeleccion parametros;
    private ParametrosConfiguracion config_params;
    private static SqlConnection conexionBaseDatos;
    private int numeroFilas = 0;
    private final int numeroColumnas = 12;
    private Logger logger = null;
    
    /** Creates a new instance of TiempoMedioOperacionSql */
    public NavegacionIVRSql(ParametrosSeleccion parametros, Logger logger) {
        this.parametros = parametros;
        config_params = ParametrosConfiguracion.getInstance();
        this.logger = logger;
    }
    
    public Tabla realizaConsulta(){
        logger.println("Realizando el informe de Tiempos de Respuesta");
        try{
        //Meteriamos ahora los datos que nos de la SELECT
            conexionBaseDatos = config_params.getConexion();
            SqlSentencia datos = conexionBaseDatos.realizarConsulta(this.generaSql());
            this.numeroFilas = datos.getNumeroFilas();
            return new Tabla(this.numeroFilas, this.numeroColumnas, this.tratamientoDatos(datos));
         }catch(Exception e){
            logger.println("Error en la consulta: "+e.getMessage());
            return null;
        }
    }
    
    private String generaSql(){
        String sql = new String();
        sql = "select sum(IND_IVR.ll_rec_dir_ag)," + //i001
                " sum(ll_res_ivr_li)," + //i086
                " sum(ll_res_ivr_cc)," + //i087
                " sum(IND_IVR.ll_per_sat)"; //i088   
        if (this.parametros.getDesglose().equalsIgnoreCase("ID_TERRITORIO_ORIGEN")){
            sql += " ,PROVINCIA.ID_TERRITORIO";
        }else
            sql += " , IND_IVR."+this.parametros.getDesglose();        
        sql +=  " from IND_IVR, IND_INTERVENCIONES";
        if (this.parametros.getDesglose().equalsIgnoreCase("ID_TERRITORIO_ORIGEN")){
            sql += " ,PROVINCIA";
        }
        sql += " where IND_IVR.ID_TRAMO >="+this.parametros.getHoraInicial()+" and IND_IVR.ID_TRAMO <="+this.parametros.getHoraFinal();
        sql += " and IND_IVR.ID_DIA >="+this.parametros.getAnioInicial()+this.parametros.getMesInicial()+this.parametros.getDiaInicial();
        sql += " and IND_IVR.ID_DIA <="+this.parametros.getAnioFinal()+this.parametros.getMesFinal()+this.parametros.getDiaFinal();        
        if (this.parametros.getDesglose().equalsIgnoreCase("ID_TERRITORIO_ORIGEN")){
            if (!this.parametros.getTerritorioOrigen().equalsIgnoreCase("Todos"))       
                sql+= " and IND_IVR.ID_PROVINCIA_ORIGEN IN (SELECT ID_PROVINCIA FROM PROVINCIA WHERE ID_TERRITORIO IN ("+this.parametros.getTerritorioOrigen()+"))"; 
        }else{        
            if (!this.parametros.getProvinciaOrigen().equalsIgnoreCase("Todos"))       
                sql+= " and IND_IVR.ID_PROVINCIA_ORIGEN IN ("+this.parametros.getProvinciaOrigen()+")";        
        }
        if (!this.parametros.getSubSegmentoEntrada().equalsIgnoreCase("Todos"))            
            sql += " and IND_IVR.ID_SUBSEGMENTO_ENTRADA IN ("+this.parametros.getSubSegmentoEntrada()+")";
        if (!this.parametros.getSubSegmentoSalida().equalsIgnoreCase("Todos"))            
            sql += " and IND_IVR.ID_SUBSEGMENTO_SALIDA IN ("+this.parametros.getSubSegmentoSalida()+")";
         if (!this.parametros.getSegmentoEntrada().equalsIgnoreCase("Todos"))            
            sql += " and IND_IVR.ID_SEGMENTO_ENTRADA IN ("+this.parametros.getSegmentoEntrada()+")";
        if (!this.parametros.getSegmentoSalida().equalsIgnoreCase("Todos"))            
            sql += " and IND_IVR.ID_SEGMENTO_SALIDA IN ("+this.parametros.getSegmentoSalida()+")"; 
        if (!this.parametros.getCod_encaminadorEntrada().equalsIgnoreCase("Todos"))
            sql += " and IND_INTERVENCIONES.ID_ENCAMINADOR_ENTRADA IN ("+this.parametros.getCod_encaminadorEntrada()+")";
        if (!this.parametros.getCod_encaminadorSalida().equalsIgnoreCase("Todos"))
            sql += " and IND_INTERVENCIONES.ID_ENCAMINADOR_SALIDA IN ("+this.parametros.getCod_encaminadorSalida()+")";
         if (!this.parametros.getPlataforma().equalsIgnoreCase("Todos"))
            sql += " and ID_PLATAFORMA IN ("+this.parametros.getPlataforma()+")";
        if (!this.parametros.getValorLlamada().equalsIgnoreCase("Todos"))
            sql += " and IND_IVR.ID_VALOR_LLAMADA IN ("+this.parametros.getValorLlamada()+")";
        if (!this.parametros.getPerfilAtencion().equalsIgnoreCase("Todos"))
            sql += " and IND_IVR.ID_PERFIL_ATENCION IN ("+this.parametros.getPerfilAtencion()+")";
        if (!this.parametros.getIdiomaAtencion().equalsIgnoreCase("Todos"))
            sql += " and IND_IVR.ID_IDIOMA_ATENCION IN ("+this.parametros.getIdiomaAtencion()+")";
        if (!this.parametros.getModoLlamada().equalsIgnoreCase("Todos"))
            sql += " and IND_IVR.ID_MODO_ATENCION IN ("+this.parametros.getModoLlamada()+")";
        if (this.parametros.getDesglose().equalsIgnoreCase("ID_TERRITORIO_ORIGEN")){
            sql += " GROUP BY PROVINCIA.ID_TERRITORIO";
        }else
            sql += " GROUP BY IND_IVR."+this.parametros.getDesglose();
        logger.println("Consulta a realizar: " +sql);
        return sql;
    }
    
    private double[][] tratamientoDatos(SqlSentencia datos){        
        double[][] tabla = new double[numeroFilas+1][numeroColumnas];        
        //Para cada fila
        for (int i=0; i<numeroFilas; i++){
            //Para cada columna
            SqlRow fila = datos.getRow(i);            
            tabla[numeroFilas][4] += tabla[i][4] = ((Integer)fila.getField(1)).intValue();//i086            
            tabla[numeroFilas][6] += tabla[i][6] = ((Integer)fila.getField(2)).intValue();//i087
            tabla[numeroFilas][2] += tabla[i][2] = tabla[i][4]+tabla[i][6];//col5+col7
            tabla[numeroFilas][5] += tabla[i][5] = (tabla[i][4]/tabla[i][2])*100;//col5/col3*100
            tabla[numeroFilas][7] += tabla[i][7] = (tabla[i][6]/tabla[i][2])*100;//col7/col3*100
            tabla[numeroFilas][8] += tabla[i][8] = ((Integer)fila.getField(3)).intValue();//i088
            tabla[numeroFilas][9] += tabla[i][9] = (tabla[i][8]/tabla[i][2])*100;//col9/col3*100
            tabla[numeroFilas][10] += tabla[i][10] = ((Integer)fila.getField(0)).intValue();//i001
            tabla[numeroFilas][1] += tabla[i][1] = tabla[i][2]+tabla[i][10];//col3+col11
            tabla[numeroFilas][3] += tabla[i][3] = (tabla[i][2]/tabla[i][1])*100;//col3/col2*100
            tabla[numeroFilas][11] += tabla[i][11] = (tabla[i][10]/tabla[i][1])*100;//col11/col2*100
            
        }
        //Calculamos los totales        
        return tabla;        
    }   
}
