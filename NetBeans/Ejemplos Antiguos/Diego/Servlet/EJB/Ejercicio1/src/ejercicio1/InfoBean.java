package ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class InfoBean implements SessionBean {
    private SessionContext _context;

    public void ejbCreate() {
    }

    public void setSessionContext(SessionContext context) throws EJBException {
        _context = context;
    }

    public void ejbRemove() throws EJBException {
    }

    public void ejbActivate() throws EJBException {
    }

    public void ejbPassivate() throws EJBException {
    }

    public String recuperarInfo(int idEmpleado) {
        //Conexion con BBDD y recuperacion de informacion
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar driver de Oracle");
        }
        String ip = "172.16.30.90";
        String usuario = "CURSO1";
        String password = "CURSO1";
        String sid = "GLOBALDB";
         
         
        //URL de conexion a BBDD
        String url = "jdbc:oracle:thin:@"+ip+":1521:"+sid;
        Connection conexion;

        try {
            conexion = DriverManager.getConnection(url, usuario, password);        
            Statement smt = conexion.createStatement();
            ResultSet rs = smt.executeQuery("SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID='"+idEmpleado+"'");
            String retorno = new String();
            while (rs.next()){               
                retorno = rs.getString(1)+rs.getString(2);
            }
            return rs.getString(1);
        } catch (SQLException e) {
            return null;
        }        
    }
}
