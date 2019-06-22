package com.tid.vu.graficos;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.tid.vu.datos.ParametrosSeleccion;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author t610908
 */
public class SeguimientoAtencion {    
    
    private ParametrosSeleccion parametros;  
    private FileOutputStream out;    
    private ArrayList listaFicheros;
    private File ficheroJPG;
    private double[] datos;
    
    /** Creates a new instance of SeguimientoAtencion */
    public SeguimientoAtencion(ParametrosSeleccion parametros, double[] datos) throws FileNotFoundException, IOException {        
        this.parametros = parametros;
        this.datos = datos;
        listaFicheros = new ArrayList();  
        String territorios = this.parametros.getOficinas();        
        ficheroJPG = this.generaFicheroJPG();
        listaFicheros.add(ficheroJPG.getAbsolutePath());
        out = new FileOutputStream(ficheroJPG);
        this.generaGrafica(territorios);        
    }        
    
    /**
     * Metodo que genera la grafica a mostrar
     */
    private void generaGrafica(String territorio) throws IOException{
        DefaultPieDataset tarta = new DefaultPieDataset();
        tarta.setValue("Atendidas",datos[0]);
        tarta.setValue("Recibidas",datos[1]);
        tarta.setValue("Porcentaje", datos[2]);        
    
        JFreeChart objGrafico = ChartFactory.createPieChart3D(territorio.replaceAll(",", ""),
                                                            tarta,
                                                            true,
                                                            true,
                                                            false);     
        objGrafico.setBorderVisible(false);
        PiePlot3D pieplot3d = (PiePlot3D)objGrafico.getPlot();
        pieplot3d.setStartAngle(100D);  //Donde comienza la grafica, giro.      
        pieplot3d.setDirection(Rotation.CLOCKWISE);
        pieplot3d.setBackgroundAlpha(1.0F); //Define el color de fondo de la grafica, 0 gris 1 blanco        
        pieplot3d.setForegroundAlpha(1.0F); //Define la transparencia de los colores 1 colores opacos 0 colores transparentes     
        
            
        BufferedImage imgPantalla = objGrafico.createBufferedImage(350,250);
        JPEGImageEncoder objCodifica = JPEGCodec.createJPEGEncoder(out);
        objCodifica.encode(imgPantalla);
        out.close();       
    }
    
    /**
     * Metodo que da nombre a los ficheros temporales de graficas.
     */
    private File generaFicheroJPG() throws IOException{
         //Cogemos la instancia del calendario
        Calendar cal=Calendar.getInstance();
        //Obtenemos la fecha del servidor
        Date date=cal.getTime();        
        //La formateamos
        DateFormat dateFormatter=DateFormat.getDateInstance(DateFormat. FULL, Locale.getDefault());
        //Le ponemos formato que queremos que tenga
        dateFormatter = new SimpleDateFormat("dd_MM_yyyyhh_mm_ss");
        String fecha=dateFormatter.format(date);
        File fichero = new File("vu"+fecha+".jpg");
        fichero.createNewFile();
        return fichero;        
    }
    
    /*protected ArrayList getListaFicheros(){
        return this.listaFicheros;
    }*/
    
    public File getFicheroJPG(){
        return this.ficheroJPG;
    }
    
    
    
    
}
