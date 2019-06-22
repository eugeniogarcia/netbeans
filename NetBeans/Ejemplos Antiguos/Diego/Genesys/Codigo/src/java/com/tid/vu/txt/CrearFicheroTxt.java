package com.tid.vu.txt;


import com.tid.vu.consultas.ConsultaGeneral;
import com.tid.vu.datos.ParametrosSeleccion;
import com.tid.vu.datos.Tabla;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author t610908
 */
public class CrearFicheroTxt {
    
    private ParametrosSeleccion seleccion;   
    
    /** Creates a new instance of CrearFicheroTxt */
    public CrearFicheroTxt(ParametrosSeleccion seleccion) {        
         this.seleccion = seleccion;
    }
    
    public File generaFichero() throws IOException{
        //Cogemos la instancia del calendario
        Calendar cal=Calendar.getInstance();
        //Obtenemos la fecha del servidor
        Date date=cal.getTime();        
        //La formateamos
        DateFormat dateFormatter=DateFormat.getDateInstance(DateFormat. FULL, Locale.getDefault());
        //Le ponemos formato que queremos que tenga
        dateFormatter = new SimpleDateFormat("dd_MM_yyyyhh_mm_ss");
        String fecha=dateFormatter.format(date);
        File fichero = new File("/vu"+fecha+".txt");
        fichero.createNewFile();
        return fichero;
    } 
    
    public void generaInformeFinal(File fichero) throws IOException{
        
        ConsultaGeneral consulta = new ConsultaGeneral(this.seleccion,null);
        Tabla informe = consulta.realizaConsulta();
        if (seleccion.getTipoInforme().equalsIgnoreCase("Tiempos de Atencion de Servicio")){
            AtencionServicioTxt textFile = new AtencionServicioTxt(fichero, this.seleccion, informe);
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Informe general de intervenciones")){            
            GeneralIntervencionesTxt textFile = new GeneralIntervencionesTxt(fichero, this.seleccion, informe);
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Familias")){
            InformeFamiliasTxt textFile = new InformeFamiliasTxt(fichero, this.seleccion, informe);            
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Intervenciones de clientes")){
            IntervencionesClientesTxt textFile = new IntervencionesClientesTxt(fichero, this.seleccion, informe);
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Informe de Intervenciones")){
            IntervencionesTxt textFile = new IntervencionesTxt(fichero, this.seleccion, informe);
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Tiempos de Conexion")){
            TiempoConexionTxt textFile = new TiempoConexionTxt(fichero, this.seleccion, informe);
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Tiempos de Respuesta")){
            TiempoRespuestaTxt textFile = new TiempoRespuestaTxt(fichero, this.seleccion, informe);
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Informe de Transferencias")){
            TransferenciasTxt textFile = new TransferenciasTxt(fichero, this.seleccion, informe);
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Navegacion IVR")){
            NavegacionIVRTxt textFile = new NavegacionIVRTxt(fichero, this.seleccion, informe);
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Bloques IVR")){
            BloquesIVRTxt textFile = new BloquesIVRTxt(fichero, this.seleccion, informe);            
        }else if (seleccion.getTipoInforme().equalsIgnoreCase("Tiempos de Atencion de Agente")){
            TiempoOperacionAgenteTxt textFile = new TiempoOperacionAgenteTxt(fichero, this.seleccion, informe, consulta.getTablaInforme2());            
        }        
    }     
}
