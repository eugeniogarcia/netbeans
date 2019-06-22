package etiquetaspropias;


import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class S1 extends TagSupport {
    /**tag attribute: condicion
     */
    private boolean condicion;

    /**Method called at start of tag.
     * @return SKIP_BODY
     */    
    public int doStartTag() throws JspException {
        /*try {
            JspWriter out = pageContext.getOut();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        int aux = SKIP_BODY;
        if (condicion==true){
            aux = EVAL_BODY_INCLUDE;
        }
        return aux;
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

    public void setCondicion(boolean value) {
        condicion = value;
    }

    public boolean getCondicion() {
        return condicion;
    }
}
