import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class s2 extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request, 
                       HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        
        //Recogemos los datos de la sesion
        HttpSession sesion = request.getSession();
        String modulo = (String)sesion.getAttribute("modulo");      
        
        
        out.println("<html>");
        out.println("<head><title>s2</title></head>");
        out.println("<body>");
        out.println("<p>El modulo es: "+modulo+"</p>");
        out.println("</body></html>");
        out.close();
    }
}