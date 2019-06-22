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
public class InformeFamiliasSql {
    
    private ParametrosSeleccion parametros;
    private ParametrosConfiguracion config_params;
    private static SqlConnection conexionBaseDatos;
    private int numeroFilas = 0;
    private final int numeroColumnas = 21;
    
    /** Creates a new instance of InformeFamiliasSql */
    public InformeFamiliasSql(ParametrosSeleccion parametros) {
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
          sql="SELECT I.ll_rec_dir_ag,ll_at_ent_dir_ag,ll_trans_dir_aut,ll_dif_dir_aut,"+
                "ll_dir_lib_op,ll_ag_sal_int,ll_ag_sal_int_notrans,ll_trans_int_sal,"+
                "ll_dif_sal,d_at_ent_dir,d_max_at_aut,d_ag_sal_int,"+
                "d_max_at_ag,d_rep,d_efect_conex,d_pausa,n_pausas"+
                "FROM IND_INTERVENCIONES I, IND_IVR V, IND_AGENTE A"+
                "WHERE I.ID_AGENTE=A.ID_AGENTE"+
                "AND I.ID_IVR=V.ID_IVR";

//falta lo de Tramo horario/día/semana/mes/año

            sql += " WHERE ID_FAMILIA IN ("+this.parametros.getFamilia()+")";
//verificar si este ModoLlamada es su parametro
            sql += " and ID_MODO_ATENCION IN ("+this.parametros.getModoLlamada()+")";
//falta poner parametro para VALOR_LLAMADA
            //sql += " and ID_VALOR_LLAMADA='"+this.parametros.get+"'";
            sql += " and ID_PERFIL_ATENCION IN ("+this.parametros.getPerfilAtencion()+")";

            sql += " group by ";          
          return sql;
    }
    
    private double[][] tratamientoDatos(SqlSentencia datos){        
        double[][] tabla = new double[numeroFilas+1][numeroColumnas];        
        //Para cada fila
        for (int i=0; i<numeroFilas; i++){

// NO HAY COLUMNA 11 EN LA XLS DE DOCUMENTACION

            SqlRow fila = datos.getRow(i);
            tabla[numeroFilas][1] += tabla[i][1] = ((Integer)fila.getField(0)).intValue();  // i001
            tabla[numeroFilas][2] += tabla[i][2] = ((Integer)fila.getField(1)).intValue();  // i003
            tabla[numeroFilas][3] += tabla[i][3] = ((Integer)fila.getField(2)).intValue();  // i073
            tabla[numeroFilas][4] += tabla[i][4] = ((Integer)fila.getField(3)).intValue(); // i074
            tabla[numeroFilas][5] += tabla[i][5] = ((Integer)fila.getField(4)).intValue(); // i075
            tabla[numeroFilas][6] += tabla[i][6] = ((Integer)fila.getField(5)).intValue(); // i098
            tabla[numeroFilas][7] += tabla[i][7] = ((Integer)fila.getField(6)).intValue(); // i099
            tabla[numeroFilas][8] += tabla[i][8] = ((Integer)fila.getField(7)).intValue(); // i076
            tabla[numeroFilas][9] += tabla[i][9] = ((Integer)fila.getField(8)).intValue(); // i077
            tabla[numeroFilas][10] += tabla[i][10] = ((Integer)fila.getField(9)).intValue(); // i005
            tabla[numeroFilas][11] += tabla[i][11] = tabla[i][10]/tabla[i][1]; // col12/col2
            tabla[numeroFilas][12] += tabla[i][12] = ((Integer)fila.getField(10)).intValue(); // i079
            tabla[numeroFilas][13] += tabla[i][13] = ((Integer)fila.getField(11)).intValue();// i100
            tabla[numeroFilas][14] += tabla[i][14] = tabla[i][13]/tabla[i][6]; // col15/col7
            tabla[numeroFilas][15] += tabla[i][15] = ((Integer)fila.getField(12)).intValue(); // i080
            tabla[numeroFilas][16] += tabla[i][16] = ((Integer)fila.getField(13)).intValue(); // i081
            tabla[numeroFilas][17] += tabla[i][17] = ((Integer)fila.getField(14)).intValue();  // i072
            tabla[numeroFilas][18] += tabla[i][18] = ((Integer)fila.getField(15)).intValue(); // i082
            tabla[numeroFilas][19] += tabla[i][19] = ((Integer)fila.getField(16)).intValue(); // i083
        }
        //Calculamos los totales        
        return tabla;        
    }    
}
