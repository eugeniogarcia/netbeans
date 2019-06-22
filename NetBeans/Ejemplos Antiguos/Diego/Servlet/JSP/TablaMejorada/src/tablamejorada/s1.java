package tablamejorada;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import tablamejoradamodelo.Persona;

public class s1 extends HttpServlet {
    
    private static ArrayList datos = new ArrayList();

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request, 
                       HttpServletResponse response) throws ServletException, IOException {
                       
        //Recogemos la sesion
        HttpSession sesion = request.getSession();
        //Creamos el requestDispatcher para redirigir
        RequestDispatcher rd = null;
        
        //Recogemos la accion enviada por el formulario
        String accion = request.getServletPath();
        
        //Logica de navegacion
        if (accion.indexOf("insertarDatos.do")!=-1){
            //Vamos a insertar datos en el arrayList que creamos
            Persona p = new Persona();
            p.setNombre(request.getParameter("nombre"));
            p.setApellidos(request.getParameter("apellidos"));
            p.setEdad(Integer.parseInt(request.getParameter("edad")));
            datos.add(p);
            //Reenviamos al jsp de introduccion de datos
            rd = request.getRequestDispatcher("/inicio.jsp");
        }else if (accion.indexOf("mostrar.do")!=-1){
            //Introducimos en la sesion el arrayList
            String nombre = "datos";
            sesion.setAttribute(nombre,datos);
            sesion.setAttribute("objeto", nombre);
            //Reenviamos a mostrar datos
            rd = request.getRequestDispatcher("/muestraDatos.jsp");
            
        }
        
        rd.forward(request, response);
    }
}
