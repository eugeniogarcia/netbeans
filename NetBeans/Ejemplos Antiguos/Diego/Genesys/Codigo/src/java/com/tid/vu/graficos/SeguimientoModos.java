package com.tid.vu.graficos;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.tid.vu.datos.ParametrosSeleccion;
import java.awt.Color;
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
import org.jfree.chart.*;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.BorderArrangement;
import org.jfree.chart.block.EmptyBlock;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.CompositeTitle;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

/**
 *
 * @author t610908
 */
public class SeguimientoModos {   
    
    private ParametrosSeleccion parametros;  
    private FileOutputStream out;        
    private ArrayList listaFicheros;
    private File ficheroJPG;
    
    /** Creates a new instance of SeguimientoModos */
    public SeguimientoModos(ParametrosSeleccion parametros, double[] datos) throws FileNotFoundException, IOException {        
        this.parametros = parametros;
        listaFicheros = new ArrayList();   
        String territorios = this.parametros.getOficinas();
        ficheroJPG = this.generaFicheroJPG();
        listaFicheros.add(ficheroJPG.getAbsolutePath());
        out = new FileOutputStream(ficheroJPG);
        this.generaGrafica(territorios);        
    }   
    
    private void generaGrafica(String territorio) throws IOException{
        String s = "DEMANDA";
        String s1 = "ATEND";        
        String s3 = "SHERPA";
        String s4 = "AVANCE";
        String s5 = "DELTA";
        String s6 = "RESCATE";        
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        data.addValue(100D, s, s3);
        data.addValue(444D, s, s4);
        data.addValue(358D, s, s5);
        data.addValue(585D, s, s6);
        
        data.addValue(595D, s1, s3);
        data.addValue(733D, s1, s4);
        data.addValue(600D, s1, s5);
        data.addValue(812D, s1, s6);                      
        
        
        DefaultCategoryDataset data1 = new DefaultCategoryDataset();
        data1.addValue((100D/595D)*100, "Atencion %", s3);
        data1.addValue((444D/733D)*100, "Atencion %", s4);
        data1.addValue((358D/600D)*100, "Atencion %", s5);
        data1.addValue((585D/812D)*100, "Atencion %", s6);       
                
    
        JFreeChart objGrafico = ChartFactory.createBarChart(territorio.replaceAll(",", ""),
                                                                "", 
                                                                "", 
                                                                data, 
                                                                PlotOrientation.VERTICAL, 
                                                                false, 
                                                                true, 
                                                                false);
        
        CategoryPlot categoryplot = (CategoryPlot)objGrafico.getPlot();
        //categoryplot.setBackgroundPaint(new Color(238, 238, 255));
        categoryplot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        categoryplot.setDataset(1, data1);
        categoryplot.mapDatasetToRangeAxis(1, 1);
        
        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
        
        NumberAxis numberaxis = new NumberAxis("%");
        categoryplot.setRangeAxis(1, numberaxis);
        
        LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();
        lineandshaperenderer.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        
        categoryplot.setRenderer(1, lineandshaperenderer);
        categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        
        LegendTitle legendtitle = new LegendTitle(categoryplot.getRenderer(0));
        legendtitle.setMargin(new RectangleInsets(2D, 2D, 2D, 2D));
        legendtitle.setBorder(new BlockBorder());
        LegendTitle legendtitle1 = new LegendTitle(categoryplot.getRenderer(1));
        legendtitle1.setMargin(new RectangleInsets(2D, 2D, 2D, 2D));
        legendtitle1.setBorder(new BlockBorder());
        
        BlockContainer blockcontainer = new BlockContainer(new BorderArrangement());
        blockcontainer.add(legendtitle, RectangleEdge.LEFT);
        blockcontainer.add(legendtitle1, RectangleEdge.RIGHT);
        blockcontainer.add(new EmptyBlock(2000D, 0.0D));
        
        CompositeTitle compositetitle = new CompositeTitle(blockcontainer);
        compositetitle.setPosition(RectangleEdge.BOTTOM);
        objGrafico.addSubtitle(compositetitle);

        
        BufferedImage imgPantalla = objGrafico.createBufferedImage(300,250);
        JPEGImageEncoder objCodifica = JPEGCodec.createJPEGEncoder(out);
        objCodifica.encode(imgPantalla);
        out.close();
    }   
    
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
    
    protected ArrayList getListaFicheros(){
        return this.listaFicheros;
    }
    
    public File getFicheroJPG(){
        return this.ficheroJPG;
    }
}
