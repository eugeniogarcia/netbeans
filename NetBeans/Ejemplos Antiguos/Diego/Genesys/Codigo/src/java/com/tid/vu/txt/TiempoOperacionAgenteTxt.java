package com.tid.vu.txt;

import com.tid.vu.datos.ParametrosSeleccion;
import com.tid.vu.datos.Tabla;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author t610908
 */
public class TiempoOperacionAgenteTxt {
    
    private BufferedWriter escritor;        
    private ParametrosSeleccion parametros;
    
    /** Creates a new instance of TiemposOperacion */
    public TiempoOperacionAgenteTxt(File fichero, ParametrosSeleccion parametros, Tabla consulta1, Tabla consulta2) throws IOException {
        escritor = new BufferedWriter(new FileWriter(fichero));       
        this.parametros = parametros;
        this.generaCabecera1();
        this.generaCabecera2();
        this.rellenaDatos(consulta1);
    }
    
    private void generaCabecera1() throws IOException{
        escritor.write(";Tiempo Efectivo de Conexion;Tiempo de Reposo;INTERVENCIONES DE ASIGNACION AUTOMATICA;;;;;;INTERVENCIONES DE ASIGNACION DE AGENTE;;;;;;\n");
        escritor.write(this.parametros.getDesglose()+";;;Tiempo de Atencion;;Tiempo de Timbre;;Tiempo Administrativo;;Tiempo de Atencion;;Tiempo de Timbre;;Tiempo Administrativo;;\n");
        escritor.write(";;;Total;Medio(s);Total;Medio(s);Total;Medio(s);Total;Medio(s);Total;Medio(s);Total;Medio(s);\n");
    }
    
    private void generaCabecera2() throws IOException{
        escritor.write(";INTERVENCIONES DE ASIGNACION AUTOMATICA;;;;;;;;INTERVENCIONES DE ASIGNACION DE AGENTE;;;;;;\n");
        escritor.write(this.parametros.getDesglose()+";Tiempo de Conversación;;Tiempo de Estudio;;Tiempo de Consulta;;Tiempo de Retención;;Tiempo de Conversación;;Tiempo de Estudio;;Tiempo de Consulta;;Tiempo de Retención;\n");
        escritor.write(";Total;Medio(s);Total;Medio(s);Total;Medio(s);Total;Medio(s);Total;Medio(s);Total;Medio(s);Total;Medio(s);\n");
    }    
    /*
     * Metodo que rellena la tabla del fichero .txt para completarla con los
     * datos extraidos de la base de datos y completados con los calculos
     * necesarios.
     */
    private void rellenaDatos(Tabla consulta) throws IOException{
       double[][] datos = consulta.getDatos();
       for (int i=0; i<consulta.getFila();i++){
           for (int j=0;j<consulta.getColumna(); j++){
               escritor.write(datos[i][j]+";");
           }
           escritor.write("\n");
       }
       escritor.flush();
       escritor.close();
    }
    
}
