
import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import oracle.jsp.el.*;
import javax.servlet.jsp.el.*;


public class _nuevoUsuario extends com.orionserver.http.OrionHttpJspPage {


  // ** Begin Declarations


  // ** End Declarations

  public void _jspService(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException, ServletException {

    response.setContentType( "text/html;charset=windows-1252");
    /* set up the intrinsic variables using the pageContext goober:
    ** session = HttpSession
    ** application = ServletContext
    ** out = JspWriter
    ** page = this
    ** config = ServletConfig
    ** all session/app beans declared in globals.jsa
    */
    PageContext pageContext = JspFactory.getDefaultFactory().getPageContext( this, request, response, null, true, JspWriter.DEFAULT_BUFFER, true);
    // Note: this is not emitted if the session directive == false
    HttpSession session = pageContext.getSession();
    int __jsp_tag_starteval;
    ServletContext application = pageContext.getServletContext();
    JspWriter out = pageContext.getOut();
    _nuevoUsuario page = this;
    ServletConfig config = pageContext.getServletConfig();
    javax.servlet.jsp.el.VariableResolver __ojsp_varRes = (VariableResolver)new OracleVariableResolverImpl(pageContext);

    try {


      out.write(__oracle_jsp_text[0]);
      out.write(__oracle_jsp_text[1]);


    }
    catch( Throwable e) {
      if (!(e instanceof javax.servlet.jsp.SkipPageException)){
        try {
          if (out != null) out.clear();
        }
        catch( Exception clearException) {
        }
        pageContext.handlePageException( e);
      }
    }
    finally {
      OracleJspRuntime.extraHandlePCFinally(pageContext,false);
      JspFactory.getDefaultFactory().releasePageContext(pageContext);
    }

  }
  private static final char __oracle_jsp_text[][]=new char[2][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n\"http://www.w3.org/TR/html4/loose.dtd\">\n".toCharArray();
    __oracle_jsp_text[1] = 
    "\n<html>\n  <head>\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\"/>\n    <title>nuevoUsuario</title>\n  </head>\n  <body><form action=\"altausuario.do\" method=\"post\">\n      <p align=\"center\">\n        <strong>INTRODUZCA SUS DATOS PERSONALES</strong>\n      </p>\n      <p>\n        &nbsp;\n      </p>\n      <p>\n        Nombre: &nbsp;&nbsp;&nbsp;<input type=\"text\" name=\"nombre\"/>\n      </p>\n      <p>\n        Apellidos: &nbsp;\n        <input type=\"text\" name=\"apellidos\"/>\n      </p>\n      <p>\n        Localidad: \n        <input type=\"text\" name=\"localidad\"/>\n      </p>\n      <p>\n        Usuario: &nbsp;&nbsp;&nbsp;\n        <input type=\"text\" name=\"usuario\"/>\n      </p>\n      <p>\n        Password: \n        <input type=\"password\" name=\"password\"/>\n      </p>\n      <p>\n        &nbsp;\n      </p>\n      <p>\n        <input type=\"submit\" value=\"Aceptar\"/>\n      </p>\n    </form></body>\n</html>".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}