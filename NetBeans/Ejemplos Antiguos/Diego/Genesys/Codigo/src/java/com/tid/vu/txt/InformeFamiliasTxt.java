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
public class InformeFamiliasTxt {
    
    private BufferedWriter escritor;    
    private ParametrosSeleccion parametros;    
    
    /** Creates a new instance of InformeFamiliasTxt */
    public InformeFamiliasTxt(File fichero, ParametrosSeleccion parametros, Tabla consulta) throws IOException {
        escritor = new BufferedWriter(new FileWriter(fichero));       
        this.parametros = parametros;        
        this.generaCabecera();
        this.rellenaDatos(consulta);
    }
    
    private void generaCabecera() throws IOException{
        escritor.write(";INTERVENCIONES DE AGENTE;;;;;;;;;;TIEMPO DE ATENCION;;;;;;Tiempo de Reposo;Tiempo Efectivo de Conexion;Tiempo de Pausa;Numero de Pausas; \n");
        escritor.write(this.parametros.getDesglose()+";Asignaciones Automaticas;;;;;Asignaciones de Agente;;;;;Asignaciones Automaticas;;;Asignaciones de Agente;;;\n");        
        escritor.write(";Recib;Atend;Tranf;Difer;Lib Op;Realiz;Atend;Transf;Difer;Lib Op;Total;Medio(s);Maximo;Total;Medio(s);Maximo;;;;\n");
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
