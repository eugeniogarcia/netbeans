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
public class BloquesIVRSql {
    
    private ParametrosSeleccion parametros;
    private ParametrosConfiguracion config_params;
    private static SqlConnection conexionBaseDatos;
    private int numeroFilas = 0;
    private final int numeroColumnas = 21;
    private Logger logger = null;
    
    /** Creates a new instance of TiempoMedioOperacionSql */
    public BloquesIVRSql(ParametrosSeleccion parametros, Logger logger) {
        this.parametros = parametros;
        config_params = ParametrosConfiguracion.getInstance();
        this.logger = logger;
    }
    
    public Tabla realizaConsulta(){
        //Meteriamos ahora los datos que nos de la SELECT
        logger.println("Realizando el informe de Tiempos de Respuesta");
        try{
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
        sql = "select sum(ll_res_ivr_li),"+  //i086
              " sum(d_res_ivr_loc),"+ //i093
              " sum(ll_res_ivr_cc),"+ //i087
              " sum(d_res_ivr_cuel),"+ //i094
              " sum(IND_IVR.ll_per_sat),"+ //i088
              " sum(d_per_sat),"+ //i095
              " sum(d_trat_agen),"+ //i096
              " sum(ll_prog_ivr),"+ //i090
              " sum(d_prog_otra),"+//i097
              " sum(IND_IVR.ll_rec_dir_ag),"+//i001
              " sum(d_at_ent_dir)"; //i005
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
        //Añadimos el join de las tablas.
        sql += " and IND_INTERVENCIONES.ID_IVR=IND_IVR.ID_IVR"; 
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
            tabla[numeroFilas][1] += tabla[i][1] = tabla[i][3]+tabla[i][15];//col4+col16           
            tabla[numeroFilas][2] += tabla[i][2] = ((((Integer)fila.getField(1)).intValue()+ 
                    ((Integer)fila.getField(3)).intValue()+((Integer)fila.getField(5)).intValue())+
                    ((Integer)fila.getField(10)).intValue())/tabla[i][1]; //((i093+i094+io95)+i005)/col2             
            tabla[numeroFilas][3] += tabla[i][3] = tabla[i][6]+tabla[i][9]; //col7+col10 
            tabla[numeroFilas][4] += tabla[i][4] = tabla[i][3]+tabla[i][1]*100; //col4/col2*100
            tabla[numeroFilas][5] += tabla[i][5] = (((Integer)fila.getField(1)).intValue()+ ((Integer)fila.getField(3)).intValue()
                        +((Integer)fila.getField(5)).intValue())/tabla[i][3]; //(i093+i094+i095)/col4
            
            tabla[numeroFilas][6] += tabla[i][6] = ((Integer)fila.getField(0)).intValue(); //i086
            tabla[numeroFilas][7] += tabla[i][7] = tabla[i][6]/tabla[i][13]*100; //col7/col4*100            
            tabla[numeroFilas][8] += tabla[i][8] = ((Integer)fila.getField(1)).intValue()/tabla[i][6]; //i093/col7            
            tabla[numeroFilas][9] += tabla[i][9] = ((Integer)fila.getField(2)).intValue(); //i087            
            tabla[numeroFilas][10] += tabla[i][10] = tabla[i][9]/tabla[i][3]*100; //col10/col4*100            
            tabla[numeroFilas][11] += tabla[i][11] = ((Integer)fila.getField(3)).intValue()/tabla[i][9]; //i094/col10            
            tabla[numeroFilas][12] += tabla[i][12] = ((Integer)fila.getField(4)).intValue(); //i088            
            tabla[numeroFilas][13] += tabla[i][13] = (tabla[i][2]/tabla[i][3])*100; //col13/col4*100             
            tabla[numeroFilas][14] += tabla[i][14] = ((Integer)fila.getField(5)).intValue()/tabla[i][2]; //i095/col13             
            tabla[numeroFilas][15] += tabla[i][15] = ((Integer)fila.getField(9)).intValue(); //i001             
            tabla[numeroFilas][16] += tabla[i][16] = (tabla[i][5]/tabla[i][1])*100; //col16/col2*100            
            tabla[numeroFilas][17] += tabla[i][17] = ((Integer)fila.getField(10)).intValue()/tabla[i][5]*100; //i005/col16*100             
            tabla[numeroFilas][18] += tabla[i][18] = ((Integer)fila.getField(7)).intValue(); //i090             
            tabla[numeroFilas][19] += tabla[i][19] = tabla[i][18]/tabla[i][1]*100; //col19/col2*100             
            tabla[numeroFilas][20] += tabla[i][20] = ((Integer)fila.getField(8)).intValue()/tabla[i][18]; //i097/col19
        }
        //Calculamos los totales        
        return tabla;        
    }    
     
    
}
