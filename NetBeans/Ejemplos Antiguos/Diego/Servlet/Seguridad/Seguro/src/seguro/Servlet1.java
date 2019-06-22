package seguro;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.CreateException;

import javax.naming.InitialContext;

import javax.naming.NamingException;

import javax.rmi.PortableRemoteObject;

import javax.servlet.*;
import javax.servlet.http.*;

public class Servlet1 extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request, 
                       HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        InitialContext nic;
        Info datos = null;
        try {
            nic = new InitialContext();
            InfoHome home = (InfoHome)PortableRemoteObject.narrow(nic.lookup("java:comp/env/ejb/Info"),
                                                        InfoHome.class);            
            datos = home.create();
        } catch (NamingException e) {
            // TODO
        } catch (CreateException e) {
            // TODO
        }
        out.println("<html>");
        out.println("<head><title>Servler1</title></head>");
        out.println("<p>"+datos.suma(3, 5)+"</p>");
        out.println("</html>");
    }
}
