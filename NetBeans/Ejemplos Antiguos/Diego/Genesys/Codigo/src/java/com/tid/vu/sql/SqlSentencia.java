/*
 * SqlSentencia.java
 *
 * Created on 14 de febrero de 2007, 17:19
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.tid.vu.sql;

import java.util.Vector;

/**
 *
 * @author t610908
 */
public class SqlSentencia {
    
    protected Vector filas = null;
    private int numeroColumnas = 0;
    
    /** Creates a new instance of SqlSentencia */
    public SqlSentencia(int numeroColumnas) {
        filas = new Vector();
        filas.removeAllElements();
        this.numeroColumnas = numeroColumnas;
    }
    
    /**
     * Añade fila a partir de Object[]    
     */
    public void addFila(Object[] pObj)
    {
        if (pObj!=null && filas!=null)
        {
                SqlRow newrow = new SqlRow(pObj);
                filas.addElement(newrow);
        }	
        return;
    }
    
    public int getNumeroColumnas()
    {
        return this.numeroColumnas;
    }
    
   public SqlRow getRow(int i)
   {
        if (filas!=null && i<filas.size())
        {
            return (SqlRow)filas.elementAt(i);	
        }
        else
        {
            return null;
        }		
    }
	
    public int getNumeroFilas()
    {
        return filas.size();
    }
    
    
    
}
