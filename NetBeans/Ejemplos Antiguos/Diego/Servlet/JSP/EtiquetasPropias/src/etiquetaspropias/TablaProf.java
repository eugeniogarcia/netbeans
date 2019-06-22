package etiquetaspropias;


import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class TablaProf extends TagSupport {
    /**tag attribute: nfilas
     */
    private int nfilas;

    /**tag attribute: ncolumnas
     */
    private int ncolumnas;

    /**tag attribute: cabecera
     */
    private String cabecera = "";

    /**tag attribute: cuerpo
     */
    private String cuerpo = "";

    /**Method called at start of tag.
     * @return SKIP_BODY
     */
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.println("<table border=\"1\">");
            out.println("<th colspan="+ncolumnas+">");
            out.println(cabecera);
            out.println("</th>");
            for (int i=1; i<=nfilas;i++){
                out.println("<tr>");
                for (int j=0; j<=ncolumnas; j++){
                    out.println("<td>");
                    out.println(cuerpo);
                    out.println("</td>");
                }
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

    public void setNfilas(int value) {
        nfilas = value;
    }

    public int getNfilas() {
        return nfilas;
    }

    public void setNcolumnas(int value) {
        ncolumnas = value;
    }

    public int getNcolumnas() {
        return ncolumnas;
    }

    public void setCabecera(String value) {
        cabecera = value;
    }

    public String getCabecera() {
        return cabecera;
    }

    public void setCuerpo(String value) {
        cuerpo = value;
    }

    public String getCuerpo() {
        return cuerpo;
    }
}
