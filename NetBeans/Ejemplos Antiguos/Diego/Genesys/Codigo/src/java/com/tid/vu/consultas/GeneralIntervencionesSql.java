package com.tid.vu.consultas;

import com.tid.vu.datos.ParametrosConfiguracion;
import com.tid.vu.datos.ParametrosSeleccion;
import com.tid.vu.datos.Tabla;
import com.tid.vu.sql.SqlConnection;
import com.tid.vu.sql.SqlRow;
import com.tid.vu.sql.SqlSentencia;

/**
 *
 * @author t610908
 */
public class GeneralIntervencionesSql {
    
    private ParametrosSeleccion parametros;
    private ParametrosConfiguracion config_params;
    private static SqlConnection conexionBaseDatos;
    private int numeroFilas = 0;
    private final int numeroColumnas = 28;
    
    /** Creates a new instance of GeneralLlamadasSql */
    public GeneralIntervencionesSql(ParametrosSeleccion parametros) {
        this.parametros = parametros;
        config_params = ParametrosConfiguracion.getInstance();
    }
    
    public Tabla realizaConsulta(){
        //Meteriamos ahora los datos que nos de la SELECT
        conexionBaseDatos = config_params.getConexion();
        //SqlSentencia datos = conexionBaseDatos.realizarConsulta(this.generaSql());
        SqlSentencia datos = null;
        //this.numeroFilas = datos.getNumeroFilas();
        this.numeroFilas = 5;
        return new Tabla(this.numeroFilas, this.numeroColumnas, this.tratamientoDatos(datos));
    }
    
    private String generaSql(){
        String sql = new String();
        sql = "select i003, i004, i005, i006, i008, i011, i012, i017, i018, i019, i026, i027, i032, i033, i034, i035, i036, i037, i067, i068, i067, i068, i069, i070 from ResumenGlobal";
        return sql;
    }
    
    private double[][] tratamientoDatos(SqlSentencia datos){        
        //Tabla para realizar los calculos
        double[][] tabla = new double[numeroFilas+1][numeroColumnas];        
        //Para cada fila
        for (int i=0; i<numeroFilas; i++){
            //Para cada columna            
            for (int j=0; j<numeroColumnas;j++){
                tabla[numeroFilas][j] += tabla[i][j] = 10;
            }
        }               
        return tabla;        
    }    
    
    public int getNumeroColumnas(){
        return this.numeroColumnas;
    }
     
    public int getNumeroFilas(){
        return this.numeroFilas;
    }
}

