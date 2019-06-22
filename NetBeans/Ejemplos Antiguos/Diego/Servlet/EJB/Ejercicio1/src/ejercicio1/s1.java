package ejercicio1;

import java.io.IOException;

import javax.ejb.CreateException;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.rmi.PortableRemoteObject;

import javax.servlet.*;
import javax.servlet.http.*;

public class s1 extends HttpServlet {
    

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request, 
                       HttpServletResponse response) throws ServletException, IOException {
        
        RequestDispatcher rd=null;
        
        String accion = request.getServletPath();
        
        if (accion.indexOf("datos.do")!=-1){           
        //Referencia al EJB mediante JNDI
        InitialContext nic;
        try{
            nic = new InitialContext();            
            InfoHome home = (InfoHome)PortableRemoteObject.narrow(nic.lookup("java:comp/env/ejb/Info"),
                                                        InfoHome.class);
            Info datos;
            datos = home.create();           
            String nombre = datos.recuperarInfo(Integer.parseInt(request.getParameter("idEmpleado")));
            request.setAttribute("nombreEmpleado", nombre);
            rd = this.getServletContext().getRequestDispatcher("/muestraDatos.jsp");
        } catch(NamingException e){
            System.out.println("Error al referenciar el EJB");            
        } catch (CreateException ce) {
            System.out.println("Error al crear el objeto remoto");
        }
        }
        rd.forward(request, response);
    }
}
