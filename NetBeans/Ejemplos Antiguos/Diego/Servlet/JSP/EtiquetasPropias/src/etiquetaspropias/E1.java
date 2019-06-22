package etiquetaspropias;


import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class E1 extends TagSupport {
    /**Method called at start of tag.
     * @return SKIP_BODY
     */
    public int doStartTag() throws JspException {
        try {
            //Captura del flujo de salida
            JspWriter out = pageContext.getOut();
            out.println("<P>Prueba de etiqueta</P>");
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
}
