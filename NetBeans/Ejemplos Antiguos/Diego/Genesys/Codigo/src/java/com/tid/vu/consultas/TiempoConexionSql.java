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
public class TiempoConexionSql {
    
    private ParametrosSeleccion parametros;
    private ParametrosConfiguracion config_params;
    private SqlConnection conexionBaseDatos;
    private int numeroFilas = 0;
    private final int numeroColumnas = 14;
    private Logger logger = null;
    
    /** Creates a new instance of TiempoConexionSql */
    public TiempoConexionSql(ParametrosSeleccion parametros, Logger logger) {
        this.parametros = parametros;
        config_params = ParametrosConfiguracion.getInstance();
        this.logger = logger;
    }
    
    public Tabla realizaConsulta(){
        //Meteriamos ahora los datos que nos de la SELECT
        logger.println("Consultando a la tabla Intervenciones...");
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
        sql += "select sum(IND_INTERVENCIONES.ll_rec_dir_ag)," +
                " sum(IND_INTERVENCIONES.ll_at_ent_dir_ag)," +
                " sum(IND_INTERVENCIONES.ll_at_ent_int_ag)," +
                " sum(IND_INTERVENCIONES.d_at_ent_dir)," +
                " sum(IND_INTERVENCIONES.d_at_ent_int)," +
                " sum(IND_IVR.d_rep)," +
                " sum(IND_IVR.d_pausa),"+                
                " from IND_INTERVENCIONES, IND_IVR, AGENTE, OFICINA";
        sql += " where IND_INTERVENCIONES.ID_TRAMO IN ("+calculaTramo(this.parametros.getHoraInicial(), this.parametros.getHoraFinal())+")";
        sql += " and IND_INTERVENCIONES.ID_DIA >="+this.parametros.getAnioInicial()+this.parametros.getMesInicial()+this.parametros.getDiaInicial();
        sql += " and IND_INTERVENCIONES.ID_DIA <="+this.parametros.getAnioFinal()+this.parametros.getMesFinal()+this.parametros.getDiaFinal();       
        //Añadimos el join de las tablas.
        sql += " and IND_INTERVENCIONES.ID_IVR=IND_IVR.ID_IVR";        
        if (!this.parametros.getPuesto().equalsIgnoreCase(""))            
            sql += " and IND_INTERVENCIONES.ID_PUESTO IN ("+this.parametros.getPuesto()+")";        
        if (!this.parametros.getValorLlamada().equalsIgnoreCase("Todos"))
            sql += " and IND_INTERVENCIONES.ID_VALOR_LLAMADA IN ("+this.parametros.getValorLlamada()+")";
        if (!this.parametros.getPerfilAtencion().equalsIgnoreCase("Todos"))
            sql += " and IND_INTERVENCIONES.ID_PERFIL_ATENCION IN ("+this.parametros.getPerfilAtencion()+")";        
        if (!this.parametros.getModoLlamada().equalsIgnoreCase("Todos"))
            sql += " and IND_INTERVENCIONES.ID_AGENTE IN (SELECT AGENTE.ID_AGENTE FROM AGENTE WHERE AGENTE.ID_MODO_ATENCION IN ("+this.parametros.getModoLlamada()+"))";
        if (!this.parametros.getOficinas().equalsIgnoreCase("Todos"))
            sql += " and IND_INTERVENCIONES.ID_AGENTE IN (SELECT AGENTE.ID_AGENTE FROM AGENTE WHERE AGENTE.ID_OFICINA IN ("+this.parametros.getOficinas()+"))";
        //El group by dependera del desglose seleccionado.
        if (this.parametros.getDesglose().equalsIgnoreCase("ID_MODO_ATENCION")){
            sql += " group by AGENTE.ID_MODO_ATENCION";
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
            tabla[numeroFilas][1] += tabla[i][1] = ((Integer)fila.getField(1)).intValue()+((Integer)fila.getField(2)).intValue(); //i003+i004            
            tabla[numeroFilas][2] += tabla[i][2] = ((Integer)fila.getField(3)).intValue()+((Integer)fila.getField(4)).intValue(); //i005+i006             
            tabla[numeroFilas][3] += tabla[i][3] = ((Integer)fila.getField(3)).intValue()/tabla[i][1]; //i005/col2 
            tabla[numeroFilas][4] += tabla[i][4] = ((Integer)fila.getField(5)).intValue(); //i081
            tabla[numeroFilas][5] += tabla[i][5] = ((Integer)fila.getField(0)).intValue(); //i001
            tabla[numeroFilas][6] += tabla[i][6] = tabla[i][2]+tabla[i][4]; //col3+col5
            tabla[numeroFilas][7] += tabla[i][7] = tabla[i][6]+tabla[i][11]; //col7/col2
            tabla[numeroFilas][8] += tabla[i][8] = tabla[i][2]+tabla[i][6]*100; //col3/col7*100
            tabla[numeroFilas][9] += tabla[i][9] = tabla[i][1]+tabla[i][6]/3600; //col2/(col7/3600)
            tabla[numeroFilas][10] += tabla[i][10] = ((Integer)fila.getField(6)).intValue(); //i082
            tabla[numeroFilas][11] += tabla[i][11] = tabla[i][10]/tabla[i][1]; //col11/col2
            tabla[numeroFilas][12] += tabla[i][12] = tabla[i][2]+tabla[i][4]+tabla[i][10]; //col3+col5+col11
            tabla[numeroFilas][13] += tabla[i][13] = tabla[i][2]/tabla[i][1]; //col13/col2            
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
     
     /**
     * Metodo para calcular el tramo horario entre inicio y fin
     */
    public String calculaTramo(String ini, String fin){
        String cadena = new String();        
        int aux1 = Integer.parseInt(ini);
        int aux2 = Integer.parseInt(fin);
        if (aux1==aux2)
            cadena += ini;
        else{
            for (int i=aux1; i<=aux2; i++){
                if (i==aux2){
                    cadena += String.valueOf(i);
                }else
                    cadena += String.valueOf(i)+" , ";
            }
        }
        return cadena;
    }
    
}
