package ejercicioconweb;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import modelo.ConexionBBDD;
import modelo.Libro;
import modelo.Usuario;

public class s1 extends HttpServlet {   

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request, 
                       HttpServletResponse response) throws ServletException, IOException {
       
       //recuperamos la sesión
       HttpSession sesion = request.getSession();
       
       //Recogemos el RequestDispatcher para redirigir a las diferentes paginas
       RequestDispatcher rd=null;
       
       //Logica de navegacion
       String accion = request.getServletPath();
       if (accion.equals("/nuevousuario.do")){
           //Redirigimos a la pantalla nuevo usuario
            rd = this.getServletContext().getRequestDispatcher("/nuevoUsuario.jsp");           
       }else if (accion.equals("/acceso.do")){
           //El usuario se esta identificando
           Usuario usuario = new Usuario();
           usuario.setPassword(request.getParameter("password"));
           usuario.setUsuario(request.getParameter("usuario"));
           if (usuario.validarUsuario()){
               rd = this.getServletContext().getRequestDispatcher("/menu.jsp");
           }else{
               rd = this.getServletContext().getRequestDispatcher("/nuevoUsuario.jsp");
           }           
       }else if (accion.equals("/altausuario.do")){
           //Recogemos todos los parametros del nuevo usuario
           Usuario usuario = new Usuario();
           usuario.setPassword(request.getParameter("password"));
           usuario.setUsuario(request.getParameter("usuario"));
           if (usuario.altaUsuario()){   
                rd = this.getServletContext().getRequestDispatcher("/menu.jsp");
           }else{
               rd = this.getServletContext().getRequestDispatcher("/error.jsp");
           }
       }else if (accion.equals("/visualizar.do")){
           rd = this.getServletContext().getRequestDispatcher("/buscarLibro.jsp");
       }else if (accion.equals("/buscarLibro.do")){
            ConexionBBDD conexion = null;
            Libro lib = null;
            try {
                conexion = new ConexionBBDD();
                lib = conexion.buscarLibro(request.getParameter("titulo"));                
            } catch (ClassNotFoundException e) {
                 rd = this.getServletContext().getRequestDispatcher("/error.jsp");
            } catch (SQLException e) {
                 rd = this.getServletContext().getRequestDispatcher("/error.jsp");
            } finally{
                conexion.cerrarConexion();
            }
            if (lib!=null){
                //Redirigimos a la pagina de datos de libro con el libro en la sesion
               sesion.setAttribute("libro", lib);
               rd = this.getServletContext().getRequestDispatcher("/datosLibro.jsp");
           }else{
               rd = this.getServletContext().getRequestDispatcher("/NOdatosLibro.jsp");
           }
           
       }       
       rd.forward(request, response);
    }
}
