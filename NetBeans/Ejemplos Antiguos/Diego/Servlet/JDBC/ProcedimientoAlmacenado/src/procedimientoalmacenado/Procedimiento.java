package procedimientoalmacenado;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Procedimiento {

    public static void main(String[] args) throws ClassNotFoundException, 
                                                  SQLException { 
    
        Class.forName("oracle.jdbc.OracleDriver");
        String ip = "172.16.30.90";
        String usuario = "CURSO1";
        String password = "CURSO1";
        String sid = "GLOBALDB";
        
        
        //URL de conexion a BBDD
        String url = "jdbc:oracle:thin:@"+ip+":1521:"+sid;
        Connection conexion = DriverManager.getConnection(url, usuario, password);
        
        CallableStatement llamada = conexion.prepareCall("{call inserta(?, ?)}");
        
        llamada.setString(1,"NOMBRE");
        llamada.setInt(2, 20);
        llamada.execute();
        
        llamada.close();
        conexion.close();
    }
}
