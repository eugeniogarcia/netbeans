package com.tid.vu.consultas;

import com.tid.vu.datos.ParametrosConfiguracion;
import com.tid.vu.datos.ParametrosSeleccion;
import com.tid.vu.datos.Tabla;
import com.tid.vu.sql.SqlConnection;
import com.tid.vu.sql.SqlRow;
import com.tid.vu.sql.SqlSentencia;
import com.tid.vu.*;

/**
 *
 * @author t610908
 */
public class IntervencionesSql {
    
    private ParametrosSeleccion parametros;
    private ParametrosConfiguracion config_params;
    private static SqlConnection conexionBaseDatos;
    private int numeroFilas = 0;
    private final int numeroColumnas = 28;
    private String idDesglose = "ID_TRAMO";
    private Logger logger = null;
    
    /** Creates a new instance of ResumenGlobal */
    public IntervencionesSql(ParametrosSeleccion parametros, Logger logger) {
        this.parametros = parametros;
        config_params = ParametrosConfiguracion.getInstance();
        this.logger = logger;
    }
    
    public Tabla realizaConsulta(){
        //Meteriamos ahora los datos que nos de la SELECT
        logger.println("Consultando a la tabla Intervenciones...");
        conexionBaseDatos = config_params.getConexion();
        String select = this.generaSql();
        SqlSentencia datos = null;
        try {
            if (select.compareTo("ERROR") !=0) 
                datos = conexionBaseDatos.realizarConsulta(this.generaSql());          

        if (datos != null) {
                logger.println("OK y hay datos");
            this.numeroFilas = datos.getNumeroFilas();
            return new Tabla(this.numeroFilas, this.numeroColumnas, this.tratamientoDatos(datos));
        } else {
                logger.println("OK y no hay datos");
                return new Tabla(this.numeroFilas, this.numeroColumnas, this.datosVacios(datos));
            }
        } catch (Exception e) {
            logger.println("Error en la consulta");
            return null;
        }
    }
    
    private String generaSql(){
        String sql = new String();
        try {
        sql = "select sum(ll_rec_dir_ag), sum(ll_rec_int_ag), sum(ll_at_ent_dir_ag), sum(ll_at_ent_int_ag), sum(d_at_ent_dir), sum(d_at_ent_int), sum(ll_per_sat), sum(ll_per_int), sum(ll_desb), sum(ll_dif), sum(ll_no_at_op), sum(ll_con_int), sum(d_con_int), sum(ll_at_sal), sum(d_at_sal), sum(ll_per_cola0_5), sum(ll_per_cola5_15), sum(ll_per_cola15_45), sum(ll_per_cola45_120), sum(ll_per_cola120), ";
        sql += idDesglose;
        sql += " from Ind_Intervenciones ";
        sql += "where ";
        //hay que tener en cuenta que el primero no lleva AND
        if (idDesglose.compareTo("ID_TRAMO") == 0)  {
            sql += "ID_DIA >= " + parametros.getFechaInicio() + " AND ID_DIA <= " + parametros.getFechaFinalizacion();
        }
        else  {
            sql += "ID_TRAMO <= " + parametros.getHoraFinal() + " AND ID_TRAMO >=" + parametros.getHoraInicial();
            if (idDesglose.compareTo("ID_DIA") != 0) 
                sql += " AND ID_DIA >= " + parametros.getFechaInicio() + " AND ID_DIA <= " + parametros.getFechaFinalizacion();
        }
        
        if ((idDesglose.compareTo("ID_AGENTE") != 0) && (parametros.getAgentes() != null))
            sql += " AND ID_AGENTE IN (" + parametros.getAgentes() + ") ";
        //if (idDesglose.compareTo("ID_FAMILIA") != 0) sql += "AND ID_FAMILIA IN (" + parametros.getFamilia() + ") ";
        if ((idDesglose.compareTo("ID_SERVICIO") != 0) && (parametros.getServicio().compareTo("Todos")!=0)) 
            sql += "AND ID_SERVICIO IN (" + parametros.getServicio() + ") ";
        if ((idDesglose.compareTo("ID_PROVINCIA_ORIGEN") != 0) && (parametros.getProvinciaOrigen().compareTo("Todos")!=0)) 
            sql += "AND ID_PROVINCIA_ORIGEN IN (" + parametros.getProvinciaOrigen() + ") ";
        if ((idDesglose.compareTo("ID_PROVINCIA_DESTINO") != 0) && (parametros.getProvinciaDestino().compareTo("Todos")!=0)) 
            sql += "AND ID_PROVINCIA_DESTINO IN (" + parametros.getProvinciaDestino() + ") ";
        //if ((idDesglose.compareTo("ID_ENRUTAMIENTO") != 0) && (parametros.getEnrutamiento().compareTo("Todos")!=0))
          //  sql += "AND ID_ENRUTAMIENTO IN (" + parametros.getEnrutamiento() + ") ";
        if ((idDesglose.compareTo("ID_TRATAMIENTO") != 0) && (parametros.getTratamiento().compareTo("Todos")!=0)) 
            sql += "AND ID_TRATAMIENTO IN (" + parametros.getTratamiento() + ") ";
        if ((idDesglose.compareTo("ID_SEGMENTO_ENTRADA") != 0) && (parametros.getSegmentoEntrada().compareTo("Todos")!=0)) 
            sql += "AND ID_SEGMENTO_ENTRADA IN (" + parametros.getSegmentoEntrada() + ") ";
        if ((idDesglose.compareTo("ID_SUBSEGMENTO_ENTRADA") != 0) && (parametros.getSubSegmentoEntrada().compareTo("Todos")!=0)) 
            sql += "AND ID_SUBSEGMENTO_ENTRADA IN (" + parametros.getSubSegmentoEntrada() + ") ";
        if ((idDesglose.compareTo("ID_SEGMENTO_SALIDA") != 0) && (parametros.getSegmentoSalida().compareTo("Todos")!=0)) 
            sql += "AND ID_SEGMENTO_SALIDA IN (" + parametros.getSegmentoSalida() + ") ";
        if ((idDesglose.compareTo("ID_SUBSEGMENTO_SALIDA") != 0) && (parametros.getSubSegmentoSalida().compareTo("Todos")!=0)) 
            sql += "AND ID_SUBSEGMENTO_SALIDA IN (" + parametros.getSubSegmentoSalida() + ") ";
        if ((idDesglose.compareTo("ID_ENCAMINADOR_ENTRADA") != 0) && (parametros.getCod_encaminadorEntrada().compareTo("Todos")!=0))
            sql += "AND ID_ENCAMINADOR_ENTRADA IN (" + parametros.getCod_encaminadorEntrada() + ") ";
        if ((idDesglose.compareTo("ID_ENCAMINADOR_SALIDA") != 0) && (parametros.getCod_encaminadorSalida().compareTo("Todos")!=0)) 
            sql += "AND ID_ENCAMINADOR_SALIDA IN (" + parametros.getCod_encaminadorSalida() + ") ";
        if ((idDesglose.compareTo("ID_PUESTO") != 0) && (parametros.getPuesto() != null)) 
            sql += "AND ID_PUESTO IN (" + parametros.getPuesto() + ") ";
        //NO VEO OFICINA EN LA BD
        //if (idDesglose.compareTo("ID_OFICINA") != 0) sql += "AND ID_OFICINA IN (" + parametros.getOficinas() + ") ";
        //if (idDesglose.compareTo("ID_MODO_ATENCION") != 0) sql += "AND ID_MODO_ATENCION IN (" + parametros.getProvinciaOrigen() + ") ";
        //if (idDesglose.compareTo("ID_VALOR_LLAMADA") != 0) sql += "AND ID_VALOR_LLAMADA IN (" + parametros. + ") ";
        if ((idDesglose.compareTo("ID_PERFIL_ATENCION") != 0)  && (parametros.getPerfilAtencion().compareToIgnoreCase("Todos")!=0)) 
            sql += "AND ID_PERFIL_ATENCION IN (" + parametros.getPerfilAtencion()  + ") ";
        //if (idDesglose.compareTo("ID_PLATAFORMA") != 0) sql += "AND ID_PLATAFORMA IN (" + parametros.getPlataforma() + ") ";
        if ((idDesglose.compareTo("ID_IDIOMA_ATENCION") != 0) && (parametros.getIdiomaAtencion().compareToIgnoreCase("Todos")!=0)) 
            sql += "AND ID_IDIOMA_ATENCION IN (" + parametros.getIdiomaAtencion() + ") ";        
        //if ((idDesglose.compareTo("ID_TIPO_SERVICIO") != 0) && (parametros.getTipoServicio().compareToIgnoreCase("Todos")!=0))  
            //sql += "AND ID_TIPO_SERVICIO IN (" + parametros.getTipoServicio() + ") "; //OJO EL TIPO SERVICIO DE PARAMETRO        
        if ((idDesglose.compareTo("ID_NODO") != 0) && (parametros.getNodoRed().compareToIgnoreCase("Todos"))!=0) 
            sql += "AND ID_NOD0 IN (" + parametros.getNodoRed() + ") ";        
        
        sql += "group by " + idDesglose;
        
        return sql;
        
        } catch (Exception e) {
            e.printStackTrace(logger.getOutPrinter());
            logger.println("Error en la consulta: " +sql);
            return "ERROR";
        }
        
    }
   
    private double[][] datosVacios(SqlSentencia datos){        
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
    
    
     private double[][] tratamientoDatos(SqlSentencia datos){        
        double[][] tabla = new double[numeroFilas+1][numeroColumnas];        
        //Para cada fila
        for (int i=0; i<numeroFilas; i++){
            //Para cada columna
            //for (int j=0; j<numeroColumnas;j++){
                //tabla[numeroFilas][j] += tabla[i][j] = 10;               
            //}
            //Para cada columna
            SqlRow fila = datos.getRow(i); 
            tabla[numeroFilas][2] += tabla[i][2] = ((Integer)fila.getField(0)).intValue();  
            tabla[numeroFilas][4] += tabla[i][4] = ((Integer)fila.getField(1)).intValue();            
            tabla[numeroFilas][10] += tabla[i][10] = ((Integer)fila.getField(2)).intValue();
            tabla[numeroFilas][12] += tabla[i][12] = ((Integer)fila.getField(3)).intValue();
            tabla[numeroFilas][17] += tabla[i][17] = ((Integer)fila.getField(6)).intValue();
            tabla[numeroFilas][18] += tabla[i][18] = ((Integer)fila.getField(7)).intValue();
            tabla[numeroFilas][19] += tabla[i][19] = ((Integer)fila.getField(8)).intValue();
            tabla[numeroFilas][20] += tabla[i][20] = ((Integer)fila.getField(9)).intValue();
            tabla[numeroFilas][21] += tabla[i][21] = ((Integer)fila.getField(10)).intValue();
            tabla[numeroFilas][22] += tabla[i][22] = ((Integer)fila.getField(11)).intValue();
            tabla[numeroFilas][23] += tabla[i][23] = ((Integer)fila.getField(12)).intValue();
            tabla[numeroFilas][24] += tabla[i][24] = tabla[i][23]/tabla[i][22];
            tabla[numeroFilas][25] += tabla[i][25] = ((Integer)fila.getField(13)).intValue();
            tabla[numeroFilas][26] += tabla[i][26] = ((Integer)fila.getField(14)).intValue();            
            tabla[numeroFilas][27] += tabla[i][24] = tabla[i][26]/tabla[i][25];
            tabla[numeroFilas][3] += tabla[i][3] = tabla[i][2]/(tabla[i][2]+tabla[i][4])*100;
            tabla[numeroFilas][5] += tabla[i][5] = tabla[i][4]/(tabla[i][2]+tabla[i][4])*100;
            tabla[numeroFilas][6] += tabla[i][6] = tabla[i][10]+tabla[i][12];            
            //esta es la columna de suma de tiempos... al final hay que pasar a minutos y horas:
            tabla[numeroFilas][8] += tabla[i][8] = ((Integer)fila.getField(4)).intValue()+((Integer)fila.getField(5)).intValue();
            //esta es tiempo medio, que también hay que pasar luego a minutos y horas:
            tabla[numeroFilas][9] += tabla[i][9] = tabla[i][8]/tabla[i][6];            
            //las dos que siguen también son tiempo medio, se convierten a minutos y horas:
            tabla[numeroFilas][11] += tabla[i][11] = ((Integer)fila.getField(4)).intValue()/tabla[i][10];            
            tabla[numeroFilas][13] += tabla[i][13] = ((Integer)fila.getField(5)).intValue()/tabla[i][12];            
            
            tabla[numeroFilas][16] += tabla[i][16] = ((Integer)fila.getField(15)).intValue()+((Integer)fila.getField(16)).intValue()+((Integer)fila.getField(17)).intValue()+((Integer)fila.getField(18)).intValue()+((Integer)fila.getField(19)).intValue();            
            tabla[numeroFilas][14] += tabla[i][14] = tabla[i][16]+tabla[i][17]+tabla[i][18];
            tabla[numeroFilas][1] += tabla[i][1] = tabla[i][6]+tabla[i][14]+tabla[i][19];
            tabla[numeroFilas][7] += tabla[i][7] = tabla[i][6]/tabla[i][1]*100;
            tabla[numeroFilas][15] += tabla[i][15] = tabla[i][14]/tabla[i][1]*100;            
            
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
