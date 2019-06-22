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
public class TiempoOperacionAgenteSql {
    
    private ParametrosSeleccion parametros;
    private ParametrosConfiguracion config_params;
    private SqlConnection conexionBaseDatos;
    private int numeroFilasPrimeraTabla = 0;
    private int numeroFilasSegundaTabla = 0;
    private final int numeroColumnasPrimeraTabla = 15;
    private final int numeroColumnasSegundaTabla = 17;
    private SqlSentencia datos = null;
    private Tabla resultadoConsulta1;
    private Tabla resultadoConsulta2;
    
    /** Creates a new instance of TiemposOperacionSql */
    public TiempoOperacionAgenteSql(ParametrosSeleccion parametros) {
        this.parametros = parametros;  
        config_params = ParametrosConfiguracion.getInstance();
    }
    
    public void realizaConsulta(){
        //Meteriamos ahora los datos que nos de la SELECT
        conexionBaseDatos = config_params.getConexion();                
        //SqlSentencia datos = conexionBaseDatos.realizarConsulta(this.generaSql1());
        SqlSentencia datos = null;
        //this.numeroFilasPrimeraTabla = datos.getNumeroFilas();
        this.numeroFilasPrimeraTabla = 5;
        resultadoConsulta1 = new Tabla(this.numeroFilasPrimeraTabla, this.numeroColumnasPrimeraTabla, this.tratamientoDatos1(datos)); 
        //datos = conexionBaseDatos.realizarConsulta(this.generaSql2());
        datos = null;
        //this.numeroFilasSegundaTabla = datos.getNumeroFilas();
        this.numeroFilasSegundaTabla = 7;
        resultadoConsulta2 = new Tabla(this.numeroFilasSegundaTabla, this.numeroColumnasSegundaTabla, this.tratamientoDatos2(datos)); 
    }
    
    private String generaSql1(){
        String sql = new String();
        sql = "select i001, i002, i005, i006, i040, i041, i042, i043, i044, i045, i046, i047, i048, i049, i081, i084, i085 from ResumenGlobal";
        return sql;
    }    
    
    private String generaSql2(){
        String sql = new String();
        sql = "select i001, i002, i005, i006, i040, i041, i042, i043, i044, i045, i046, i047, i048, i049, i081, i084, i085 from ResumenGlobal";
        return sql;
    }    
    
     private double[][] tratamientoDatos1(SqlSentencia datos){        
        double[][] tabla = new double[numeroFilasPrimeraTabla+1][numeroColumnasPrimeraTabla];        
        //Para cada fila
        for (int i=0; i<numeroFilasPrimeraTabla; i++){
            //Para cada columna            
           for (int j=0; j<numeroColumnasPrimeraTabla;j++){
                tabla[numeroFilasPrimeraTabla][j] += tabla[i][j] = 10;
            }
        }
        
        //Calculamos los totales        
        return tabla;        
    }
     
     private double[][] tratamientoDatos2(SqlSentencia datos){        
        double[][] tabla = new double[numeroFilasSegundaTabla+1][numeroColumnasSegundaTabla];        
        //Para cada fila
        for (int i=0; i<numeroFilasSegundaTabla; i++){
            for (int j=0; j<numeroColumnasSegundaTabla;j++){
                tabla[numeroFilasSegundaTabla][j] += tabla[i][j] = 10;
            }
        }      
           
        return tabla;        
    }
     
     public int getNumeroColumnasPrimeraTabla(){
         return this.numeroColumnasPrimeraTabla;
     }
     
     public int getNumeroColumnasSegundaTabla(){
         return this.numeroColumnasSegundaTabla;
     }
     
     public int getNumeroFilasPrimeraTabla(){
         return this.numeroFilasPrimeraTabla;
     }
     
     public int getNumeroFilasSegundaTabla(){
         return this.numeroFilasSegundaTabla;
     }
     
     public Tabla getResultadoSentencia1(){
         return this.resultadoConsulta1;
     }
     
     public Tabla getResultadoSentencia2(){
         return this.resultadoConsulta2;
     }
    
}
