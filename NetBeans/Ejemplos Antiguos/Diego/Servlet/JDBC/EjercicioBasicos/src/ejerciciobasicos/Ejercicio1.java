package ejerciciobasicos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio1 {
        
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
        
        Statement smt = conexion.createStatement();
        
        String sql = "INSERT INTO DEPARTMENTS VALUES(2000, 'DepartamentoPruebaInsert', null, null)";
        
        int num = smt.executeUpdate(sql);
        System.out.println("Insertadas: "+num);
        
        sql = "SELECT DEPARTMENT_ID, DEPARTMENT_NAME FROM DEPARTMENTS";
        
        
        
        ResultSet rs = smt.executeQuery(sql);
        
        while (rs.next()){
            System.out.print(rs.getString(1)+ "\t");
            System.out.println(rs.getString(2));
        }
        
        rs.close();
        smt.close();
        conexion.close();
        
    
    }
}
