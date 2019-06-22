/*
 * SeguimientoModosSql.java
 *
 * Created on 28 de marzo de 2007, 16:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.tid.vu.consultas;

import com.tid.vu.datos.ParametrosSeleccion;
import com.tid.vu.datos.ParametrosConfiguracion;
import com.tid.vu.sql.SqlConnection;
import com.tid.vu.sql.SqlRow;
import com.tid.vu.sql.SqlSentencia;

/**
 *
 * @author t610908
 */
public class SeguimientoAtencionSql {
    
    private ParametrosSeleccion parametros;
    private ParametrosConfiguracion config_params;
    private static SqlConnection conexionBaseDatos;   
    
    /** Creates a new instance of SeguimientoModosSql */
    public SeguimientoAtencionSql(ParametrosSeleccion parametros) {
        this.parametros = parametros;
        config_params = ParametrosConfiguracion.getInstance();
    }
    
    public double[] realizaConsulta(){
        //Meteriamos ahora los datos que nos de la SELECT
        conexionBaseDatos = config_params.getConexion();
        SqlSentencia datos = conexionBaseDatos.realizarConsulta(this.generaSql());
        return tratamientoDatos(datos);
    }
    
    private String generaSql(){
        String sql = new String();
        sql = "select ll_rec_dir_ag, " + //i001
                "ll_rec_int_ag," + //i002
                "ll_at_ent_dir_ag, "+ //i003
                "ll_at_ent_int_ag from IND_INTERVENCIONES"; //i004
        sql += " where ID_DIA ="+this.parametros.getAnioInicial()+this.parametros.getMesInicial()+this.parametros.getDiaInicial();        
        System.out.println(sql);
        return sql;
    }
    
    private double[] tratamientoDatos(SqlSentencia datos){        
        double[] tabla = new double[3];
        if (datos.getNumeroFilas()>0){
            SqlRow fila = datos.getRow(0); 
            tabla[0] = ((Integer)fila.getField(0)).intValue()+((Integer)fila.getField(1)).intValue();//i001+i002
            tabla[1] = ((Integer)fila.getField(2)).intValue()+((Integer)fila.getField(3)).intValue(); //i003+i004
            tabla[2] = (tabla[1]/tabla[0])*100; //(i003+i004)/(i001+i002)*100        
        }else tabla = null;
        return tabla;
    }
}
