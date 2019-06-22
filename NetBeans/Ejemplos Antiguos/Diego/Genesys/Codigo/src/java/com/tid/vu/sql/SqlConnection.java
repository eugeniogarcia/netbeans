/*
 * sqlConnection.java
 *
 * Created on 14 de febrero de 2007, 16:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.tid.vu.sql;

import java.math.BigDecimal;
import java.sql.*;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.Context;
import javax.sql.DataSource;


/**
 *
 * @author t610908
 */
public class SqlConnection {
    
    protected DriverManager Manager;
    protected Connection Conexion;
    private Context contextoInicial = null;
    private String cadenaPoolConexiones = null;
    private static DataSource fuenteDatos=null;
    
    private boolean errorConexion = false;
    private String descripcionErrorConexion;
    
    /** Creates a new instance of sqlConnection */
    public SqlConnection(String pool) {
        try {
	 Context contextoInicial = new InitialContext(); 
	 fuenteDatos = (DataSource)contextoInicial.lookup(pool);
      }
      catch(NameNotFoundException e) { 
          errorConexion = true;
          descripcionErrorConexion = new String(e.toString());
      }
      catch(Exception e) {
          errorConexion = true;
          descripcionErrorConexion = new String(e.toString());
      }
    }

    public boolean isErrorConexion() {
        return errorConexion;
    }

    public String getDescripcionErrorConexion() {
        return descripcionErrorConexion;
    }
    
    public SqlSentencia realizarConsulta(String sql){       
        Statement sentencia = null;
        ResultSet rs = null;
        Connection con = null;
        ResultSetMetaData ResultadoMetaData;
        SqlSentencia datosRecuperados = new SqlSentencia(0);
        //Si no hay problemas con la conexion solicitamos conexion al pool
        if (!errorConexion) {
            synchronized (fuenteDatos) {
                try {
                    con = fuenteDatos.getConnection();
                } catch (SQLException ex) {
                    System.out.println("Error conecta a BBDD: "+ex.getMessage());
                    ex.printStackTrace();
                    return datosRecuperados;
                }
	    }
        }
        try {
	    sentencia = con.createStatement();            
	    rs = sentencia.executeQuery(sql);
            ResultadoMetaData = rs.getMetaData();
	    int	num_columnas = ResultadoMetaData.getColumnCount();
            //--------------------------------------------------
            // Generacion de SqlSentencia
            //--------------------------------------------------
            datosRecuperados = new SqlSentencia(num_columnas);
            // Recuperacion de filas
            int k=0;
            while (rs.next())
            {
                Object pObj[] = new Object[num_columnas];
                // - obtención de la fila
                for (int i=0; i<num_columnas; i++)
                {
                        if (ResultadoMetaData.getColumnType(i+1) == Types.TIMESTAMP )
                        {
                                // Caso especial para TIMESTAMP                                
                                try
                                {
                                        pObj[i] = new Timestamp(rs.getTimestamp(i+1).getTime());
                                }
                                catch (NullPointerException e)
                                {
                                        pObj[i] = null;
                                }
                        }
                        else if (ResultadoMetaData.getColumnType(i+1) == Types.DECIMAL ||
                                     ResultadoMetaData.getColumnType(i+1) == Types.NUMERIC   )
                        {
                                // Caso especial para DECIMAL - se pasa a Long (ojo!!)
                                try
                                {
                                        BigDecimal bd = new BigDecimal(rs.getObject(i+1).toString());
                                        pObj[i] = new Long(bd.longValue());

                                }
                                catch (Exception e)
                                {
                                        pObj[i] = null;
                                }	
                        }	
                        else
                        {
                                pObj[i] = rs.getObject(i+1);
                        }		
                }
                // - creacion de la fila
                datosRecuperados.addFila(pObj);	
            }
            
            
	    rs.close();
	    rs = null;
	    sentencia.close();
	    sentencia = null;
	    con.close();        // Se devuelve la conexión al pool
	    con = null;         // Nos aseguramos que no se cierra dos veces (Random Connection Closed Exceptions)).
	 }
	 catch (Exception e) {	    
            //// Asegurar que result sets and statements son cerrados,
	    //// y que la conexión retorna al  pool
            e.printStackTrace();
	    if (rs != null) {
	       try { rs.close(); } catch (SQLException sqle) {}
	       rs = null;
	    }
	    if (sentencia != null) {
	       try { sentencia.close(); } catch (SQLException sqle) {}
	       sentencia = null;
	    }
	    if (con != null) {
		try { con.close(); } catch (SQLException sqle) {}
		con = null;
	    }
	 }
        return datosRecuperados;
    }
    
}
