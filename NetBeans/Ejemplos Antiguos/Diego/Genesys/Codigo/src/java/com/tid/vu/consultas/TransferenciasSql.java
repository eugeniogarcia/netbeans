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
public class TransferenciasSql {
    
    private ParametrosSeleccion parametros;
    private ParametrosConfiguracion config_params;
    private SqlConnection conexionBaseDatos;
    private int numeroFilas = 0;
    private final int numeroColumnas = 20;
    
    /** Creates a new instance of TiempoConexionSql */
    public TransferenciasSql(ParametrosSeleccion parametros) {
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
        sql = "select i001, i002, i003, i004, i005, i006, i007, i008, i009, i010, i011, i012, i013, i014, i015, i016, i017, i018, i019, i020 from ResumenGlobal";
        return sql;
    }
    
     private double[][] tratamientoDatos(SqlSentencia datos){        
        double[][] tabla = new double[numeroFilas+1][numeroColumnas];        
        //Para cada fila
        for (int i=0; i<numeroFilas; i++){
            //Para cada columna
           for (int j=0; j<numeroColumnas;j++){
                tabla[numeroFilas][j] += tabla[i][j] = 10;
            }
        }
        
        //Calculamos los totales        
        return tabla;        
    }
     
     public int getNumeroColumnas(){
         return this.numeroColumnas;
     }
     
     public int getNumeroFilas(){
         return this.numeroFilas;
     }
    
}
