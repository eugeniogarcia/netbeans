package tablamejorada;


import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import tablamejoradamodelo.Persona;

public class Tabla extends TagSupport {
    /**tag attribute: arrayList
     */
    private String arrayList;

    /**Method called at start of tag.
     * @return SKIP_BODY
     */
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            HttpSession sesion= pageContext.getSession();
            ArrayList datos = (ArrayList)sesion.getAttribute(arrayList);
            out.println("<table border=\"2\">");
            out.println("<th>Nombre</th><th>Apellidos</th><th>Edad</th>");
            
            for (int i=0; i<datos.size(); i++){
                out.println("<tr>");
                Persona p = (Persona)datos.get(i);                
                out.println("<td>"+p.getNombre()+"</td>");
                out.println("<td>"+p.getApellidos()+"</td>");
                out.println("<td>"+p.getEdad()+"</td>");
                out.println("</tr>");
            }
            
            out.println("</table>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    /**Method is invoked after every body evaluation to control whether the body will be reevaluated or not.
     * @return SKIP_BODY
     */
    public int doAfterBody() throws JspException {
        return SKIP_BODY;
    }

    /**Method called at end of tag.
     * @return EVAL_PAGE
     */
    public int doEndTag() {
        return EVAL_PAGE;
    }

    public void setArrayList(String value) {
        arrayList = value;
    }

    public String getArrayList() {
        return arrayList;
    }
}
