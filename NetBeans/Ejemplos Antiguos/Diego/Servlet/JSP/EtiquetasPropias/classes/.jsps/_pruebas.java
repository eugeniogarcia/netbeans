
import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import oracle.jsp.el.*;
import javax.servlet.jsp.el.*;


public class _pruebas extends com.orionserver.http.OrionHttpJspPage {


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
    _pruebas page = this;
    ServletConfig config = pageContext.getServletConfig();
    javax.servlet.jsp.el.VariableResolver __ojsp_varRes = (VariableResolver)new OracleVariableResolverImpl(pageContext);

    try {


      out.write(__oracle_jsp_text[0]);
      out.write(__oracle_jsp_text[1]);
      out.write(__oracle_jsp_text[2]);
      {
        etiquetaspropias.E1 __jsp_taghandler_1=(etiquetaspropias.E1)OracleJspRuntime.getTagHandler(pageContext,etiquetaspropias.E1.class,"etiquetaspropias.E1");
        __jsp_taghandler_1.setParent(null);
        __jsp_tag_starteval=__jsp_taghandler_1.doStartTag();
        if (__jsp_taghandler_1.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_1,1);
      }
      out.write(__oracle_jsp_text[3]);
      {
        etiquetaspropias.E2 __jsp_taghandler_2=(etiquetaspropias.E2)OracleJspRuntime.getTagHandler(pageContext,etiquetaspropias.E2.class,"etiquetaspropias.E2 nombre");
        __jsp_taghandler_2.setParent(null);
        __jsp_taghandler_2.setNombre(OracleJspRuntime.toStr( request.getParameter("nombre")));
        __jsp_tag_starteval=__jsp_taghandler_2.doStartTag();
        if (__jsp_taghandler_2.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_2,1);
      }
      out.write(__oracle_jsp_text[4]);
      {
        etiquetaspropias.S1 __jsp_taghandler_3=(etiquetaspropias.S1)OracleJspRuntime.getTagHandler(pageContext,etiquetaspropias.S1.class,"etiquetaspropias.S1 condicion");
        __jsp_taghandler_3.setParent(null);
        __jsp_taghandler_3.setCondicion(false);
        __jsp_tag_starteval=__jsp_taghandler_3.doStartTag();
        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
        {
          do {
            out.write(__oracle_jsp_text[5]);
          } while (__jsp_taghandler_3.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
        }
        if (__jsp_taghandler_3.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_3,1);
      }
      out.write(__oracle_jsp_text[6]);
      {
        etiquetaspropias.TablaProf __jsp_taghandler_4=(etiquetaspropias.TablaProf)OracleJspRuntime.getTagHandler(pageContext,etiquetaspropias.TablaProf.class,"etiquetaspropias.TablaProf nfilas ncolumnas cabecera cuerpo");
        __jsp_taghandler_4.setParent(null);
        __jsp_taghandler_4.setNfilas(3);
        __jsp_taghandler_4.setNcolumnas(3);
        __jsp_taghandler_4.setCabecera("cabecera");
        __jsp_taghandler_4.setCuerpo("cuerpo");
        __jsp_tag_starteval=__jsp_taghandler_4.doStartTag();
        if (__jsp_taghandler_4.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_4,1);
      }
      out.write(__oracle_jsp_text[7]);


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
      OracleJspRuntime.extraHandlePCFinally(pageContext,true);
      JspFactory.getDefaultFactory().releasePageContext(pageContext);
    }

  }
  private static final char __oracle_jsp_text[][]=new char[8][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n\"http://www.w3.org/TR/html4/loose.dtd\">\n".toCharArray();
    __oracle_jsp_text[1] = 
    "\n".toCharArray();
    __oracle_jsp_text[2] = 
    "\n<html>\n  <head>\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\"/>\n    <title>pruebas</title>\n  </head>\n  <body><p>\n      ".toCharArray();
    __oracle_jsp_text[3] = 
    "\n    </p><p>\n      &nbsp;\n    </p><p>\n     ".toCharArray();
    __oracle_jsp_text[4] = 
    "\n    </p><p>\n      ".toCharArray();
    __oracle_jsp_text[5] = 
    "\n        Condicion cumplida\n      ".toCharArray();
    __oracle_jsp_text[6] = 
    "\n    </p><p>\n      ".toCharArray();
    __oracle_jsp_text[7] = 
    "\n    </p></body>\n</html>".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
