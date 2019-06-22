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
public class TiempoRespuestaTxt {
    
    private BufferedWriter escritor;
    private ParametrosSeleccion parametros;        
    
    public TiempoRespuestaTxt(File fichero, ParametrosSeleccion parametros, Tabla consulta) throws IOException {
        escritor = new BufferedWriter(new FileWriter(fichero));       
        this.parametros = parametros;        
        this.generaCabecera();
        this.rellenaDatos(consulta);
    }
    
    private void generaCabecera() throws IOException{
        escritor.write(";Total Recibidas;Atendidas;;;;;;;;;;;;;;;;;;Perdidas en Campo de Espera;;;;;;;;;;;;;Saturación;;Desbordadas;\n");
        escritor.write(this.parametros.getDesglose()+";;Total;;S.E.;;S.E. y < 20;;<= 20s;;20s-30s;;30s-60s;;60s-120s;;>120s;;T.M.E.;T.M.E. c.e.;Total;;< 5s;;5-15s;;15s-45s;;45s-120s;;>120s;;T.M.E.;Total;;Total;\n");
        escritor.write(";;Nº;%;Nº;%;Nº;%;Nº;%;Nº;%;Nº;%;Nº;%;Nº;%;;;Nº;%;Nº;%;Nº;%;Nº;%;Nº;%;Nº;%;;Nº;%;Nº;\n");
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
