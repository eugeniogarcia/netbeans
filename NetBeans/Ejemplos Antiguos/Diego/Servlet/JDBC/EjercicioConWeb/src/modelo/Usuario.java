package modelo;

import java.sql.SQLException;

public class Usuario {

    private String usuario;
    private String password;

    public Usuario() {
    }


    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    
    public boolean validarUsuario(){        
        ConexionBBDD conexion = null;
        try {
            conexion = new ConexionBBDD();
            boolean retorno = conexion.validarUsuario(usuario, password);
            conexion.cerrarConexion();
            return retorno;
        } catch (ClassNotFoundException e) {
            //El driver no esta cargado
            System.out.println("El driver no esta cargado correctamente");
            return false;
        } catch (SQLException e) {
            //No se ha producido la conexion
             System.out.println("No se ha podido conectar con la BBDD");
            return false;
        } finally{
            conexion.cerrarConexion();            
        }        
    }
    
    public boolean altaUsuario(){
        ConexionBBDD conexion = null;
        try {
            conexion = new ConexionBBDD();
            boolean retorno = conexion.altaUsuario(usuario, password);            
            conexion.cerrarConexion();
            return retorno;
        } catch (ClassNotFoundException e) {
            //El driver no esta cargado
            System.out.println("El driver no esta cargado correctamente");
            return false;
        } catch (SQLException e) {
            //No se ha producido la conexion
            System.out.println("No se ha podido conectar con la BBDD");
            return false;
        } finally{
            conexion.cerrarConexion();            
        }        
    }

}
