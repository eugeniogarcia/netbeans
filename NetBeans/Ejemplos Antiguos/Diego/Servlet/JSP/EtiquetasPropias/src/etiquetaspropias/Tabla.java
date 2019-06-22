package etiquetaspropias;


import java.util.ArrayList;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class Tabla extends TagSupport {
    /**tag attribute: array
     */
    private String nombre = new String();

    /**Method called at start of tag.
     * @return SKIP_BODY
     */
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.println("<table border=\"2\">");            
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

    public void setArray(String value) {
        nombre = value;
    }

    public String getArray() {
        return nombre;
    }
    
    public void addColumn(String columna){
        
    }
}
