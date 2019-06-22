package com.tid.vu.consultas;

import com.tid.vu.datos.ParametrosSeleccion;
import com.tid.vu.datos.ParametrosConfiguracion;
import com.tid.vu.datos.Tabla;
import com.tid.vu.sql.SqlConnection;
import com.tid.vu.sql.SqlRow;
import com.tid.vu.sql.SqlSentencia;
import com.tid.vu.Logger;

/**
 *
 * @author t610908
 */
public class AtencionServicioSql {
    
    private ParametrosSeleccion parametros;
    private ParametrosConfiguracion config_params;
    private static SqlConnection conexionBaseDatos;
    private int numeroFilas = 0;
    private final int numeroColumnas = 46;
    private Logger logger = null;
    
    /** Creates a new instance of TiempoMedioOperacionSql */
    public AtencionServicioSql(ParametrosSeleccion parametros, Logger logger) {
        this.parametros = parametros;
        config_params = ParametrosConfiguracion.getInstance();
        this.logger = logger;
    }
    
    public Tabla realizaConsulta(){
        //Meteriamos ahora los datos que nos de la SELECT
        conexionBaseDatos = config_params.getConexion();
        SqlSentencia datos = conexionBaseDatos.realizarConsulta(this.generaSql());        
        this.numeroFilas = datos.getNumeroFilas();        
        return new Tabla(this.numeroFilas, this.numeroColumnas, this.tratamientoDatos(datos));
    }
    
    private String generaSql(){
/* ORDEN DE INDICADORES:
 i003,i005,i040,i041,
 i044,i042,i043,i004,
 i006,i045,i046,i049,
i048,i017,i018,i050,i051,
i054,i052,i053,i013,i098
*/
       String sql = new String();
       sql="SELECT sum(ll_at_ent_dir_ag)," +
               " sum(d_at_ent_dir)," +
               " sum(d_total_timbre_at_ent_dir)," +
               " sum(d_total_conv_at_ent_dir),"+
               " sum(d_total_ret_at_ent_dir)," +
               " sum(d_total_cons_at_ent_dir)," +
               " sum(d_total_adm_at_ent_dir)," +
               " sum(ll_at_ent_int_ag),"+
               " sum(d_at_ent_int)," +
               " sum(d_total_timbre_at_ent_int)," +
               " sum(d_total_conv_at_ent_int)," +
               " sum(d_total_ret_at_ent_int),"+
               " sum(d_total_adm_at_ent_int)," +
               " sum(ll_at_sal)," +
               " sum(d_at_sal)," +
               " sum(d_total_timbre_at_sal)," +
               " sum(d_total_conv_at_sal),"+
               " sum(d_total_ret_at_sal)," +
               " sum(d_total_cons_at_sal)," +
               " sum(d_total_adm_at_sal)," +
               " sum(ll_dif)," +
               " sum(d_dif)"+
               " from IND_INTERVENCIONES, IND_IVR, PROVINCIA, AGENTE, OFICINA"+
               " where IND_INTERVENCIONES.ID_IVR=IND_IVR.ID_IVR ";
        sql += " where IND_INTERVENCIONES.ID_TRAMO >="+this.parametros.getHoraInicial()+" and IND_INTERVENCIONES.ID_TRAMO <="+this.parametros.getHoraFinal();
        sql += " and IND_INTERVENCIONES.ID_DIA >="+this.parametros.getAnioInicial()+this.parametros.getMesInicial()+this.parametros.getDiaInicial();
        sql += " and IND_INTERVENCIONES.ID_DIA <="+this.parametros.getAnioFinal()+this.parametros.getMesFinal()+this.parametros.getDiaFinal();
        //Añadimos el join de las tablas.
        sql += " and IND_INTERVENCIONES.ID_IVR=IND_IVR.ID_IVR";        
        if (!this.parametros.getProvinciaOrigen().equalsIgnoreCase("Todos"))       
            sql+= " and IND_INTERVENCIONES.ID_PROVINCIA_ORIGEN IN ("+this.parametros.getProvinciaOrigen()+")";
        if (!this.parametros.getProvinciaDestino().equalsIgnoreCase("Todos"))            
            sql += " and IND_INTERVENCIONES.ID_PROVINCIA_DESTINO IN ("+this.parametros.getProvinciaDestino()+")";
        if (!this.parametros.getTerritorioOrigen().equalsIgnoreCase("Todos"))
            sql += " and IND_INTERVENCIONES.ID_PROVINCIA_ORIGEN IN (SELECT PROVINCIA.ID_PROVINCIA FROM PROVINCIA WHERE PROVINCIA.ID_TERRITORIO IN("+this.parametros.getTerritorioOrigen()+"))";
        if (!this.parametros.getTerritorioDestino().equalsIgnoreCase("Todos"))
            sql += " and IND_INTERVENCIONES.ID_PROVINCIA_DESTINO IN (SELECT PROVINCIA.ID_PROVINCIA FROM PROVINCIA WHERE PROVINCIA.ID_TERRITORIO IN("+this.parametros.getTerritorioDestino()+"))";
        if (!this.parametros.getTratamiento().equalsIgnoreCase("Todos"))
            sql += " and IND_INTERVENCIONES.ID_TRATAMIENTO IN ("+this.parametros.getTratamiento()+")";
        if (!this.parametros.getServicio().equalsIgnoreCase("Todos"))            
            sql += " and IND_INTERVENCIONES.ID_SERVICIO IN ("+this.parametros.getServicio()+")";                    
        if (!this.parametros.getPuesto().equalsIgnoreCase(""))            
            sql += " and IND_INTERVENCIONES.ID_PUESTO IN ("+this.parametros.getPuesto()+")";                    
        if (!this.parametros.getProvinciaDestino().equalsIgnoreCase("Todos"))
            sql += " and IND_INTERVENCIONES.ID_TIPO_SERVICIO IN ("+this.parametros.getTipoServicio()+")";
        if (!this.parametros.getNodoRed().equalsIgnoreCase("Todos"))           
            sql += " and IND_INTERVENCIONES.ID_NODO IN ("+this.parametros.getNodoRed()+")";
        if (!this.parametros.getAgentes().equalsIgnoreCase(""))            
            sql += " and IND_INTERVENCIONES.ID_AGENTE IN ("+this.parametros.getAgentes()+")";
        if (!this.parametros.getSubSegmentoEntrada().equalsIgnoreCase("Todos"))            
            sql += " and IND_INTERVENCIONES.ID_SUBSEGMENTO_ENTRADA IN ("+this.parametros.getSubSegmentoEntrada()+")";
        if (!this.parametros.getSubSegmentoSalida().equalsIgnoreCase("Todos"))            
            sql += " and IND_INTERVENCIONES.ID_SUBSEGMENTO_SALIDA IN ("+this.parametros.getSubSegmentoSalida()+")";
        if (!this.parametros.getValorLlamada().equalsIgnoreCase("Todos"))
            sql += " and IND_INTERVENCIONES.ID_VALOR_LLAMADA IN ("+this.parametros.getValorLlamada()+")";
        if (!this.parametros.getPerfilAtencion().equalsIgnoreCase("Todos"))
            sql += " and IND_INTERVENCIONES.ID_PERFIL_ATENCION IN ("+this.parametros.getPerfilAtencion()+")";
        if (!this.parametros.getIdiomaAtencion().equalsIgnoreCase("Todos"))
            sql += " and IND_INTERVENCIONES.ID_IDIOMA_ATENCION IN ("+this.parametros.getIdiomaAtencion()+")";
        if (!this.parametros.getModoLlamada().equalsIgnoreCase("Todos"))
            sql += " and IND_INTERVENCIONES.ID_AGENTE IN (SELECT AGENTE.ID_AGENTE FROM AGENTE WHERE AGENTE.ID_MODO_ATENCION IN ("+this.parametros.getModoLlamada()+"))";
        if (!this.parametros.getSegmentoEntrada().equalsIgnoreCase("Todos"))            
            sql += " and IND_INTERVENCIONES.ID_SEGMENTO_ENTRADA IN ("+this.parametros.getSegmentoEntrada()+")";
        if (!this.parametros.getSegmentoSalida().equalsIgnoreCase("Todos"))            
            sql += " and IND_INTERVENCIONES.ID_SEGMENTO_SALIDA IN ("+this.parametros.getSegmentoSalida()+")"; 
        if (!this.parametros.getCod_encaminadorSalida().equalsIgnoreCase("Todos"))            
            sql += " and IND_INTERVENCIONES.ID_ENCAMINADOR_SALIDA IN ("+this.parametros.getCod_encaminadorSalida()+")";        
        if (!this.parametros.getEnrutamiento().equalsIgnoreCase("Todos"))            
            sql += " and IND_INTERVENCIONES.ID_ENRUTAMIENTO IN ("+this.parametros.getEnrutamiento()+")"; 
        if (!this.parametros.getPlataforma().equalsIgnoreCase("Todos"))
            sql += " and IND_INTERVENCIONES.ID_AGENTE IN (SELECT AGENTE.ID_AGENTE FROM AGENTE WHERE AGENTE.ID_OFICINA IN (SELECT OFICINA.ID_OFICINA FROM OFICINA WHERE OFICINA.ID_PLATAFORMA IN("+this.parametros.getPlataforma()+")))";
        if (!this.parametros.getOficinas().equalsIgnoreCase("Todos"))
            sql += " and IND_INTERVENCIONES.ID_AGENTE IN (SELECT AGENTE.ID_AGENTE FROM AGENTE WHERE AGENTE.ID_OFICINA IN ("+this.parametros.getOficinas()+"))";
        //El group by dependera del desglose seleccionado.
        if (this.parametros.getDesglose().equalsIgnoreCase("ID_TERRITORIO_ORIGEN") || this.parametros.getDesglose().equalsIgnoreCase("ID_TERRITORIO_DESTINO")){
            sql += " group by PROVINCIA.ID_TERRITORIO";       
        }else if (this.parametros.getDesglose().equalsIgnoreCase("ID_PLATAFORMA")){
            sql += " group by PLATAFORMA.ID_PLATAFORMA";
        }else if (this.parametros.getDesglose().equalsIgnoreCase("ID_MODO_ATENCION")){
            sql += " group by AGENTE.ID_MODO_ATENCION";
        }else if (this.parametros.getDesglose().equalsIgnoreCase("ID_FAMILIA")){
            sql += " group by AGENTE.ID_FAMILIA";
        }else if (this.parametros.getDesglose().equalsIgnoreCase("ID_OFICINA")){
            sql += " group by AGENTE.ID_OFICINA";
        }else{
            sql += " group by IND_INTERVENCIONES."+this.parametros.getDesglose();  
        }
        logger.println("Consulta a realizar: " +sql);
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
            tabla[numeroFilas][1] += tabla[i][1] = ((Integer)fila.getField(0)).intValue();;  //i003 (Nº).
            tabla[numeroFilas][2] += tabla[i][2] = ((Integer)fila.getField(1)).intValue();  //i005 (T.T.)
            tabla[numeroFilas][3] += tabla[i][3] = (tabla[i][2]/tabla[i][1]);  // i005/i003(T.M)
            tabla[numeroFilas][4] += tabla[i][4] = ((Integer)fila.getField(2)).intValue()/tabla[i][1]; // i040/col2(T.M.)
            tabla[numeroFilas][5] += tabla[i][5] = (((Integer)fila.getField(2)).intValue()/tabla[i][2])*100; // i040/col3*100(%)
            tabla[numeroFilas][6] += tabla[i][6] = ((Integer)fila.getField(3)).intValue()+tabla[i][1]; //i041/col2(T.M.)
            tabla[numeroFilas][7] += tabla[i][7] = (((Integer)fila.getField(3)).intValue()/tabla[i][2])*100; //i041/col3*100(%)
            tabla[numeroFilas][8] += tabla[i][8] = ((Integer)fila.getField(4)).intValue()/tabla[i][1]; //i044/col2(T.M.)
            tabla[numeroFilas][9] += tabla[i][9] = (((Integer)fila.getField(4)).intValue()/tabla[i][2])*100; //i044/col3*100(%)
            tabla[numeroFilas][10] += tabla[i][10] = ((Integer)fila.getField(5)).intValue()/tabla[i][1]; //i042/col2(T.M.)
            tabla[numeroFilas][11] += tabla[i][11] = (((Integer)fila.getField(5)).intValue()/tabla[i][2])*100; //i042/col3*100(%)
            tabla[numeroFilas][12] += tabla[i][12] = ((Integer)fila.getField(6)).intValue()/tabla[i][1]; //i043/col2(T.M.)
            tabla[numeroFilas][13] += tabla[i][13] = (((Integer)fila.getField(6)).intValue()/tabla[i][12])*100; //i043/col3*100(%)
            tabla[numeroFilas][14] += tabla[i][14] = ((Integer)fila.getField(7)).intValue(); //i004(Nº)
            tabla[numeroFilas][15] += tabla[i][15] = ((Integer)fila.getField(8)).intValue(); //i006(T.T.)
            tabla[numeroFilas][16] += tabla[i][16] = tabla[i][15]/tabla[i][14]; //i006/i004(T.M)
            tabla[numeroFilas][17] += tabla[i][17] = ((Integer)fila.getField(9)).intValue()/tabla[i][14]; //i045/col15(T.M.)
            tabla[numeroFilas][18] += tabla[i][18] = (((Integer)fila.getField(9)).intValue()/tabla[i][15])*100; //i045/col16*100(%)
            tabla[numeroFilas][19] += tabla[i][19] = ((Integer)fila.getField(10)).intValue()/tabla[i][14]; //i046/col15(T.M.)
            tabla[numeroFilas][20] += tabla[i][20] = (((Integer)fila.getField(10)).intValue()/tabla[i][15])*100;  //i046/col16*100(%)
            tabla[numeroFilas][21] += tabla[i][21] = ((Integer)fila.getField(11)).intValue()/tabla[i][14]; //i049/col15(T.M.)
            tabla[numeroFilas][22] += tabla[i][22] = (((Integer)fila.getField(11)).intValue()/tabla[i][15])*100; //i049/col16*100(%)
            tabla[numeroFilas][23] += tabla[i][23] = ((Integer)fila.getField(5)).intValue()/tabla[i][14]; //i042/col15(T.M.)
            tabla[numeroFilas][24] += tabla[i][24] = (((Integer)fila.getField(5)).intValue()/tabla[i][15])*100; //i042/col16*100(%)
            tabla[numeroFilas][25] += tabla[i][25] = ((Integer)fila.getField(12)).intValue()/tabla[i][14]; //i048/col15(T.M.)
            tabla[numeroFilas][26] += tabla[i][26] = (((Integer)fila.getField(12)).intValue()/tabla[i][15])*100; //i048/col16*100(%)
            tabla[numeroFilas][27] += tabla[i][27] = tabla[i][1]/tabla[i][14]; //i003+i004(Nº)
            tabla[numeroFilas][28] += tabla[i][28] = tabla[i][2]/tabla[i][15]; //i005+i006(T.T.)
            tabla[numeroFilas][29] += tabla[i][29] = (tabla[i][28]/tabla[i][27]); //col29/col28(T.M.)
            tabla[numeroFilas][30] += tabla[i][30] = ((Integer)fila.getField(13)).intValue(); //i017 (Nº)
            tabla[numeroFilas][31] += tabla[i][31] = ((Integer)fila.getField(14)).intValue(); //i018 (T.T.)
            tabla[numeroFilas][32] += tabla[i][32] = tabla[i][31]/tabla[i][30]; //col32/col31 (T.M)
            tabla[numeroFilas][33] += tabla[i][33] = ((Integer)fila.getField(15)).intValue()/tabla[i][30]; // i050/col31 (T.M.)
            tabla[numeroFilas][34] += tabla[i][34] = (((Integer)fila.getField(15)).intValue()/tabla[i][31])*100; // i050/col32*100 (%)
            tabla[numeroFilas][35] += tabla[i][35] = ((Integer)fila.getField(16)).intValue()/tabla[i][30]; // i051/col31 (T.M.)
            tabla[numeroFilas][36] += tabla[i][36] = (((Integer)fila.getField(16)).intValue()/tabla[i][31])*100; // i051/col32*100 (%)
            tabla[numeroFilas][37] += tabla[i][37] = ((Integer)fila.getField(17)).intValue()/tabla[i][30]; // i054/col31 (T.M.)
            tabla[numeroFilas][38] += tabla[i][38] = (((Integer)fila.getField(17)).intValue()/tabla[i][31])*100; // i054/col32*100 (%)
            tabla[numeroFilas][39] += tabla[i][39] = ((Integer)fila.getField(18)).intValue()/tabla[i][30]; // i052/col31 (T.M.)
            tabla[numeroFilas][40] += tabla[i][40] = (((Integer)fila.getField(18)).intValue()/tabla[i][31])*100; // i052/col32*100 (%)
            tabla[numeroFilas][41] += tabla[i][41] = ((Integer)fila.getField(19)).intValue()/tabla[i][30]; // i053/col31 (T.M.)
            tabla[numeroFilas][42] += tabla[i][42] = (((Integer)fila.getField(19)).intValue()/tabla[i][31])*100; // i053/col32*100 (%)
            tabla[numeroFilas][43] += tabla[i][43] = ((Integer)fila.getField(20)).intValue(); // i013 (Nº)
            tabla[numeroFilas][44] += tabla[i][44] = ((Integer)fila.getField(21)).intValue(); // i098 (T.T.)
            tabla[numeroFilas][45] += tabla[i][45] = tabla[i][44]/tabla[i][43]; // col45/col44 (T.M)
          
        }
        //Calculamos los totales        
        return tabla;        
    }    
}
