import java.io.IOException;


import javax.servlet.*;
import javax.servlet.http.*;

public class s1 extends HttpServlet {
    

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request, 
                       HttpServletResponse response) throws ServletException, IOException {
                       
      
        //recogemos los parametros del formulario
        int parteX = Integer.parseInt(request.getParameter("partex"));
        int parteY = Integer.parseInt(request.getParameter("partey"));
        
        //Calculamos el modulo del numero
        double modulo = Math.sqrt((parteX*parteX)+(parteY*parteY));
        String mod = String.valueOf(modulo);
        //metemos el modulo en la sesion para pasarselo al siguiente servlet
        HttpSession sesion = request.getSession();
        sesion.setAttribute("modulo",mod);
        
        if (modulo<10.0){
            this.getServletContext().getRequestDispatcher("/error.html").forward(request, response);
        }else{
            this.getServletContext().getNamedDispatcher("s2").forward(request, response);
        }      
        
    }
}