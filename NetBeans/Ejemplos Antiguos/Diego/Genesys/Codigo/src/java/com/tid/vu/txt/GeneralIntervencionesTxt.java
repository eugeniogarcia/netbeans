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
public class GeneralIntervencionesTxt {
    
    private BufferedWriter escritor;        
    private ParametrosSeleccion parametros;    
    
    /** Creates a new instance of GeneralLlamadasTxt */
    public GeneralIntervencionesTxt(File fichero, ParametrosSeleccion parametros, Tabla consulta) throws IOException {
        escritor = new BufferedWriter(new FileWriter(fichero));       
        this.parametros = parametros;        
        this.generaCabecera();
        this.rellenaDatos(consulta);
    }
    
    private void generaCabecera() throws IOException{
        escritor.write(";Nº TOTAL RECIBIDAS;INTERVENCIONES ATENDIDAS;;;;;;;;;;;;;INTERVENCIONES ABANDONADAS;;;;;;Desbordes;Tiempo Efectivo de Conexion;Atendidas por hora;% Speaking;Interv. atendidas <= 20 seg.;;Tiempo Medio Espera;\n");
        escritor.write(this.parametros.getDesglose()+";;Total;;;Resueltas sin Intento de Transferencia;;;Resueltas con Intento de Transferencia;;;Transferidas;;;Intentos de Transferencia sin Exito;Total;;Aband < 1 seg.;;Aband < 5 seg;;Nº;;;;;\n");
        escritor.write(";;Nº;%;T.M.O.;Nº;%;T.M.O.;Nº;%;T.M.O.;Nº;%;T.M.O.;;Nº;%;Nº;%;Nº;%;;;;;Nº;%;\n");
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
