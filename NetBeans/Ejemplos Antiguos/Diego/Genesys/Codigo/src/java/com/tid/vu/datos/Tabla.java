package com.tid.vu.datos;

/**
 * Clase que representa los datos que se van a devolver en las consultas a BBDD
 * @author t610908
 */
public class Tabla {
    
    private double[][] informe;
    private int numeroFilas;
    private int numeroColumnas;
    
    /**
     * Constructor de la clase Tabla, se le pasan el numero de filas 
     * y el numero de columnas de la tabla.
     */
    public Tabla(int numeroFilas,
                 int numeroColumnas,
                 double[][] informe) {
        this.numeroFilas = numeroFilas;
        this.numeroColumnas = numeroColumnas;
        this.informe = informe;
    }
    
    /**
     * Metodo para recuperar el numero de columnas que tiene la tabla
     */
    public int getColumna(){
        return this.numeroColumnas;
    }
    
    /**
     * Metodo para recuperar el numero de filas que tiene la tabla
     */
    public int getFila(){
        return this.numeroFilas;
    }
    
    /**
     * Metodo para recuperar los datos contenidos en la tabla.
     */
    public double[][] getDatos(){
        return this.informe;
    }
    
    /*public void setDatos(double[][] informe){
        this.informe = informe;
    }
    
    public void setFilas(int filas){
        this.numeroFilas = filas;
    }
    
    public void setColumnas(int columnas){
        this.numeroColumnas = columnas;
    }*/
    
}
