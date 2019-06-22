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
public class TiempoConexionTxt {
    
    private BufferedWriter escritor;    
    private ParametrosSeleccion parametros;
    
    
    /** Creates a new instance of TiempoConexionTxt */
    public TiempoConexionTxt(File fichero, ParametrosSeleccion parametros, Tabla consulta) throws IOException {
        escritor = new BufferedWriter(new FileWriter(fichero));       
        this.parametros = parametros;        
        this.generaCabecera();
        this.rellenaDatos(consulta);
    }
    
    private void generaCabecera() throws IOException{
        escritor.write(";INTERVENCIONES ATENDIDAS POR ASIGNACION AUTOMATICA;;;;;;;;;;;;;\n");
        escritor.write(this.parametros.getDesglose()+";Intervenciones Atendidas;Tiempo de Operacion;;Tiempo de Reposo;;Tiempo Efectivo de Conexion;;% Speaking;Tasa Atencion;Tiempo de Pausa;;Tiempo de Conexion;;\n");
        escritor.write(";;Total;Medio;Total;Medio;Total;Medio;;;Total;Medio;Total;Medio;\n");
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
