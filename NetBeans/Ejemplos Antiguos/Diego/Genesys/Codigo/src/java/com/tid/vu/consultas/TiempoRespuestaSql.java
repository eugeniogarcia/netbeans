package com.tid.vu.consultas;

import com.tid.vu.Logger;
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
public class TiempoRespuestaSql {
    
    private ParametrosSeleccion parametros;
    private ParametrosConfiguracion config_params;
    private static SqlConnection conexionBaseDatos;
    private final int numeroColumnas = 36;
    private int numeroFilas = 0; 
    private Logger logger = null;
    
    /** Creates a new instance of TiempoMedioRespuestaSql */
    public TiempoRespuestaSql(ParametrosSeleccion parametros, Logger logger) {
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
        sql = "select sum(IND_INTERVENCIONES.ll_at_ent_dir_ag)," +
                " sum(IND_INTERVENCIONES.ll_at_ent_int_ag)," +
                " sum(IND_INTERVENCIONES.ll_at_se)," +
                " sum(IND_INTERVENCIONES.ll_at_ce0_20)," +
                " sum(IND_INTERVENCIONES.ll_at_ce20_30)," +
                " sum(IND_INTERVENCIONES.ll_at_ce30_60)," +
                " sum(IND_INTERVENCIONES.ll_at_ce60_120), " +
                " sum(IND_INTERVENCIONES.ll_at_ce_120)," +
                " sum(IND_INTERVENCIONES.d_tot_esp_cola_at)," +
                " sum(IND_INTERVENCIONES.ll_per_cola0_5)," +
                " sum(IND_INTERVENCIONES.ll_per_cola5_15)," +
                " sum(IND_INTERVENCIONES.ll_per_cola15_45)," +
                " sum(IND_INTERVENCIONES.ll_per_cola45_120)," +
                " sum(IND_INTERVENCIONES.ll_per_cola120)," +
                " sum(IND_INTERVENCIONES.d_tot_esp_cola_per)," +
                " sum(IND_INTERVENCIONES.ll_per_sat)," +
                " sum(IND_INTERVENCIONES.d_con_int)"+               
                " from IND_INTERVENCIONES, IND_IVR, PROVINCIA, AGENTE, OFICINA";        
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
            SqlRow fila = datos.getRow(i);
            
            tabla[numeroFilas][2] += tabla[i][2] = ((Integer)fila.getField(0)).intValue()+((Integer)fila.getField(1)).intValue();  //i003+i004 (Nº)            
            tabla[numeroFilas][4] += tabla[i][4] = ((Integer)fila.getField(2)).intValue(); //i026 (Nº)
            tabla[numeroFilas][5] += tabla[i][5] = (tabla[i][4]/tabla[i][2])*100; //i026/col3*100 (%)
            tabla[numeroFilas][6] += tabla[i][6] = tabla[i][4]+tabla[i][8]; //col5+col9 (Nº)
            tabla[numeroFilas][7] += tabla[i][7] = (tabla[i][6]/tabla[i][2])*100; //col7/col3*100 (%)
            tabla[numeroFilas][8] += tabla[i][8] = ((Integer)fila.getField(3)).intValue(); //i027 (Nº)
            tabla[numeroFilas][9] += tabla[i][9] = (tabla[i][8]/tabla[i][2])*100; //i027/col3*100 (%)
            tabla[numeroFilas][10] += tabla[i][10] = ((Integer)fila.getField(4)).intValue(); //i028 (Nº)
            tabla[numeroFilas][11] += tabla[i][11] = (tabla[i][10]/tabla[i][2])*100; //i028/col3*100 (%)
            tabla[numeroFilas][12] += tabla[i][12] = ((Integer)fila.getField(5)).intValue(); //i029 (Nº)
            tabla[numeroFilas][13] += tabla[i][13] = (tabla[i][12]/tabla[i][2])*100; //i029/col3*100 (%)
            tabla[numeroFilas][14] += tabla[i][14] = ((Integer)fila.getField(6)).intValue(); //i030 (Nº)
            tabla[numeroFilas][15] += tabla[i][15] = (tabla[i][14]/tabla[i][2])*100; //i030/col3*100 (%)
            tabla[numeroFilas][16] += tabla[i][16] = ((Integer)fila.getField(7)).intValue(); //i031 (Nº)
            tabla[numeroFilas][17] += tabla[i][17] = (tabla[i][16]/tabla[i][2])*100; //i031/col3*100 (%)
            tabla[numeroFilas][18] += tabla[i][18] = ((Integer)fila.getField(8)).intValue()/tabla[i][2]; //i032/col3 (T.M)
            
            
            
            tabla[numeroFilas][22] += tabla[i][22] = ((Integer)fila.getField(9)).intValue(); //i033 (Nº)            
            tabla[numeroFilas][24] += tabla[i][24] = ((Integer)fila.getField(10)).intValue(); //i034 (Nº)            
            tabla[numeroFilas][26] += tabla[i][26] = ((Integer)fila.getField(11)).intValue(); //i035 (Nº)            
            tabla[numeroFilas][28] += tabla[i][28] = ((Integer)fila.getField(12)).intValue(); //i036 (Nº)            
            tabla[numeroFilas][30] += tabla[i][30] = ((Integer)fila.getField(13)).intValue(); //i037 (Nº)
            
            tabla[numeroFilas][20] += tabla[i][20] = tabla[i][22]+tabla[i][24]+tabla[i][26]+tabla[i][28]+tabla[i][30];  //col23+col25+col27+col29+col31 (Nº)
            tabla[numeroFilas][21] += tabla[i][21] = (tabla[i][20]/tabla[i][1])*100; //col21/col2*100 (%)
            tabla[numeroFilas][23] += tabla[i][23] = (tabla[i][22]/tabla[i][20])*100; //col23/col21*100 (%)
            tabla[numeroFilas][25] += tabla[i][25] = (tabla[i][24]/tabla[i][20])*100; //col25/col21*100 (%)
            tabla[numeroFilas][27] += tabla[i][27] = (tabla[i][26]/tabla[i][20])*100; //col27/col21*100 (%)
            tabla[numeroFilas][29] += tabla[i][29] = (tabla[i][28]/tabla[i][20])*100; //col29/col21*100 (%)
            
            tabla[numeroFilas][19] += tabla[i][19] = ((Integer)fila.getField(8)).intValue()/
                    (tabla[i][26]+tabla[i][27]+tabla[i][28]+tabla[i][29]+tabla[i][30]); //i032/(i027+i028+i029+i030+i031) (T.M.E)
            
            tabla[numeroFilas][31] += tabla[i][31] = (tabla[i][30]/tabla[i][20])*100; //col31/col21*100 (%)
            tabla[numeroFilas][32] += tabla[i][32] = ((Integer)fila.getField(14)).intValue()/tabla[i][20]; //i038/col21 (T.M.E)
            tabla[numeroFilas][33] += tabla[i][33] = ((Integer)fila.getField(15)).intValue(); //i008 (Nº)
            tabla[numeroFilas][34] += tabla[i][34] = (tabla[i][33]/tabla[i][1])*100; //i008/col2*100 (%)
            tabla[numeroFilas][35] += tabla[i][35] = ((Integer)fila.getField(16)).intValue(); //i011    (Nº)        
            
            tabla[numeroFilas][1] += tabla[i][1] = tabla[i][2]+tabla[i][20]+tabla[i][33]+tabla[i][35];  //col3+col21+col34+col36 (Total Nº).            
            tabla[numeroFilas][3] += tabla[i][3] = (tabla[i][2]+tabla[i][1])/100;  //col3/col2*100 (%)
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
