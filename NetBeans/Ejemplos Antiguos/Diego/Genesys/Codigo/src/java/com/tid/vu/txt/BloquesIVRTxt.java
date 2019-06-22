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
public class BloquesIVRTxt {
    
    private BufferedWriter escritor;    
    private ParametrosSeleccion parametros;  
    

    public BloquesIVRTxt(File fichero, ParametrosSeleccion parametros, Tabla consulta) throws IOException {
        escritor = new BufferedWriter(new FileWriter(fichero));       
        this.parametros = parametros;        
        this.generaCabecera();
        this.rellenaDatos(consulta);
    }
    
    private void generaCabecera() throws IOException{
        escritor.write(";TOTAL RECIBIDAS;;TRATAMIENTO AUTOMATICO;;;;;;;;;;;;TRATAMIENTO AGENTE;;;PROGRESA OTRA IVR;;;\n");
        escritor.write(this.parametros.getDesglose()+";;;Total;;;Resueltas;;;;;;Saturacion;;;;;;;;;;\n");
        escritor.write(";;;;;;Locucion Informativa;;Cliente Cuelga;\n");
        escritor.write(";N�;T.M.;N�;%;T.M.;N�;%;T.M.;N�;%;T.M.;N�;%;T.M.;N�;%;T.M.;N�;%;T.M.;\n");
        //Realizamos la consulta a BB.DD y rellenamos los datos que faltan.       
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
