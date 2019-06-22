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
public class IntervencionesClientesSql {
    
    private ParametrosSeleccion parametros;
    private ParametrosConfiguracion config_params;
    private static SqlConnection conexionBaseDatos;
    private int numeroFilas = 0;
    private final int numeroColumnas = 28;
    
    /** Creates a new instance of LlamadasClientesSql */
    public IntervencionesClientesSql(ParametrosSeleccion parametros) {
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
 
//EL ORDEN DE LOS CAMPOS ESTA DE ACUERDO AL TRATAMIENTO DE DATOS
        String sql = new String();
          sql="SELECT ll_res_auto,ll_sat_auto,ll_dif_auto,ll_aband_1nivel,ll_perd_1nivel,"+
                "ll_res_1nivel,ll_res_1nivel_con2,ll_aband_2nivel,ll_perd_2nivel, "+
                "ll_trans_2nivel,ll_dif_2nivel"+
                "FROM IND_INTERVENCIONES";
//falta lo de Tramo horario/día/semana/mes/año

            sql += " where ID_PROVINCIA_ORIGEN IN ("+this.parametros.getProvinciaOrigen()+")";
            sql+= "AND ID_PROVINCIA_DESTINO IN ("+this.parametros.getProvinciaDestino()+")";
// en la tabla IND_INTERVENCIONES no esta lo de Territorio
//            sql += " AND ID_TERRITORIO_ORIGEN IN ("+this.parametros.get ()+")";
//            sql += " AND ID_TERRITORIO_DESTINO IN ("+this.parametros.get ()+")";

            sql += " and ID_AGENTE IN ("+this.parametros.getAgentes()+")";
            sql += " and ID_FAMILIA IN ("+this.parametros.getFamilia()+")";
            sql += " and ID_SERVICIO IN ("+this.parametros.getServicio()+")";
            sql += " and ID_ENRUTAMIENTO IN ("+this.parametros.getEnrutamiento()+")";    
            sql += " and ID_TRATAMIENTO IN ("+this.parametros.getTratamiento()+")";
            sql += " and ID_SEGMENTO_ENTRADA IN ("+this.parametros.getSegmentoEntrada()+")";
            sql += " and ID_SEGMENTO_SALIDA IN ("+this.parametros.getSegmentoSalida()+")";
            sql += " and ID_SUBSEGMENTO_ENTRADA IN ("+this.parametros.getSubSegmentoEntrada()+")";
            sql += " and ID_SUBSEGMENTO_SALIDA IN ("+this.parametros.getSubSegmentoSalida()+")";
            sql += " and ID_ENCAMINADOR_ENTRADA IN ("+this.parametros.getCod_encaminadorEntrada()+")";
            sql += " and ID_ENCAMINADOR_SALIDA IN ("+this.parametros.getCod_encaminadorSalida()+")";
            sql += " and ID_PUESTO IN ("+this.parametros.getPuesto()+")";
            sql += " and ID_OFICINA IN ("+this.parametros.getOficinas()+")";
//verificar si este ModoLlamada es su parametro
            sql += " and ID_MODO_ATENCION IN ("+this.parametros.getModoLlamada()+")";
//falta poner parametro para VALOR_LLAMADA
            //sql += " and ID_VALOR_LLAMADA IN ("+this.parametros.get+")";
            sql += " and ID_PERFIL_ATENCION IN ("+this.parametros.getPerfilAtencion()+")";
            sql += " and PLATAFORMA IN ("+this.parametros.getPlataforma()+")";
            sql += " and ID_IDIOMA_ATENCION IN ("+this.parametros.getIdiomaAtencion()+")";
//falta poner parametro para TIPO_SERVICIO
//          sql += " and ID_TIPO_SERVICIO IN ("+this.parametros.get+")";
            sql += " and ID_NODO IN ("+this.parametros.getNodoRed()+")";
            sql += " group by ";          
          return sql;
    }
    
    private double[][] tratamientoDatos(SqlSentencia datos){        
        double[][] tabla = new double[numeroFilas+1][numeroColumnas];        
        //Para cada fila
        for (int i=0; i<numeroFilas; i++){
            //Para cada columna
/*            for (int j=0; j<numeroColumnas;j++){
                tabla[numeroFilas][j] += tabla[i][j] = 10;
            }  */          
            SqlRow fila = datos.getRow(i);
            tabla[numeroFilas][2] += tabla[i][2] = ((Integer)fila.getField(0)).intValue();;  // i056,	col3
            tabla[numeroFilas][4] += tabla[i][4] = ((Integer)fila.getField(1)).intValue();  // i057,	col5
            tabla[numeroFilas][6] += tabla[i][6] = ((Integer)fila.getField(2)).intValue(); // i058,	col7
            tabla[numeroFilas][10] += tabla[i][10] = ((Integer)fila.getField(3)).intValue(); // i059,	col11
            tabla[numeroFilas][12] += tabla[i][12] = ((Integer)fila.getField(4)).intValue(); // I060,	col13
            tabla[numeroFilas][14] += tabla[i][14] = ((Integer)fila.getField(5)).intValue(); // I061,	col15
            tabla[numeroFilas][18] += tabla[i][18] = ((Integer)fila.getField(6)).intValue(); // i062,S	col19
            tabla[numeroFilas][20] += tabla[i][20] = ((Integer)fila.getField(7)).intValue(); // i063,	col21
            tabla[numeroFilas][22] += tabla[i][22] = ((Integer)fila.getField(8)).intValue(); // i064,	col23
            tabla[numeroFilas][24] += tabla[i][24] = ((Integer)fila.getField(9)).intValue(); // i065,	col25
            tabla[numeroFilas][26] += tabla[i][26] = ((Integer)fila.getField(10)).intValue(); // i066,	col27
            tabla[numeroFilas][16] += tabla[i][16] = tabla[i][18]+tabla[i][20]+tabla[i][22]+tabla[i][24]+tabla[i][26]; // col19+col21+col23+col25+col27, col17
            tabla[numeroFilas][8] += tabla[i][8] = tabla[i][10]+tabla[i][12]+tabla[i][14]+tabla[i][16]; // col11+col113+col15+col17,	col9 
            tabla[numeroFilas][1] += tabla[i][1] = tabla[i][2]+tabla[i][4]+tabla[i][6]+tabla[i][18]; // i056+i057+i058+col9,	col2
            tabla[numeroFilas][3] += tabla[i][3] = (tabla[i][2]/tabla[i][1])*100; // col3/col2*100,	col4
            tabla[numeroFilas][5] += tabla[i][5] = (tabla[i][4]/tabla[i][1])*100; // col5/col2*100,	col6
            tabla[numeroFilas][7] += tabla[i][7] = (tabla[i][6]/tabla[i][1])*100; // col7/col2*100,	col8
            tabla[numeroFilas][9] += tabla[i][9] = (tabla[i][8]/tabla[i][1])*100; // col9/col2*100,	col10
            tabla[numeroFilas][11] += tabla[i][11] = (tabla[i][10]/tabla[i][18])*100; // col11/col19*100, col12
            tabla[numeroFilas][13] += tabla[i][13] = (tabla[i][12]/tabla[i][8])*100;  // col13/col9*100,	col14
            tabla[numeroFilas][15] += tabla[i][15] = (tabla[i][14]/tabla[i][8])*100; // col15/col9*100,	col16            
            tabla[numeroFilas][17] += tabla[i][17] = (tabla[i][16]/tabla[i][8])*100; // col17/col9*100,	col18            
            tabla[numeroFilas][19] += tabla[i][19] = (tabla[i][18]/tabla[i][16])*100; // col19/col17*100,	col20
            tabla[numeroFilas][21] += tabla[i][21] = (tabla[i][20]/tabla[i][16])*100; // col21/col17*100,	col22
            tabla[numeroFilas][23] += tabla[i][23] = (tabla[i][22]/tabla[i][16])*100; // col23/col17*100,	col24
            tabla[numeroFilas][25] += tabla[i][25] = (tabla[i][24]/tabla[i][16])*100; // col25/col17*100,	col26
            tabla[numeroFilas][27] += tabla[i][27] = (tabla[i][26]/tabla[i][16])*100; // col27/col17*100,	col28
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
