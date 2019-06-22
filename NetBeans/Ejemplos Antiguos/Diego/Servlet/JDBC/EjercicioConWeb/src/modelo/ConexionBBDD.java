package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionBBDD {

    private static final String usuario="CURSO1";
    private static final String password="CURSO1";
    private static final String url="jdbc:oracle:thin:@172.16.30.90:1521:GLOBALDB";    
    
    private static Connection conexion = null;  
    

    public ConexionBBDD() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        conexion = DriverManager.getConnection(url, usuario, password);        
    }
    
    public boolean validarUsuario(String usu, String pass){
        boolean valido = true;
        ResultSet rs = null;
        try {
            rs = conexion.createStatement().executeQuery("SELECT * FROM USUARIO WHERE USUARIO='"+usu+"' AND CLAVE='"+pass+"'");
            valido = rs.next();
            rs.close();
        } catch (SQLException e) {
            valido = false;            
        }        
        return valido;
    }
    
    public boolean altaUsuario(String usu, String pass){      
        boolean valido = true;
        try {
            int num = conexion.createStatement().executeUpdate("INSERT INTO USUARIO VALUES ('"+usu+"', '"+pass+"')");
            valido = num>=1;            
        } catch (SQLException e) {
            System.out.println("Error al insertar el usuario nuevo: "+e.getMessage());
            valido = false;            
        }        
        return valido;
    }   
    
    public Libro buscarLibro(String titulo){
        Libro lib = new Libro();        
        ResultSet rs;
        try {
            rs = conexion.createStatement().executeQuery("SELECT * FROM LIBROS WHERE TITULO='"+titulo+"'");
            while (rs.next()) {
                lib.setTitulo(titulo);
                lib.setAutor(rs.getString("AUTOR"));
                lib.setEditorial(rs.getString("EDITORIAL"));
                lib.setNacionalidad(rs.getString("NACIONALIDAD"));
                lib.setNumeroPaginas(rs.getInt("NUMPAGS"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar query de buscar libro");            
        } 
        return lib;
    }
    
    public void cerrarConexion(){
        try {
            this.conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexion se queda abierta");
        }
    }
    
}
