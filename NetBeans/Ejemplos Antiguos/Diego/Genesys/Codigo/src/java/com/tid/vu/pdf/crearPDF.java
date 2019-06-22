package com.tid.vu.pdf;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.xml.sax.InputSource;

import org.apache.fop.apps.Driver;
import org.apache.fop.apps.XSLTInputHandler;
import org.apache.fop.messaging.MessageHandler;

import org.apache.avalon.framework.logger.ConsoleLogger;
import org.apache.avalon.framework.logger.Logger;

/**
 *
 * @author Propietario
 */
public class crearPDF extends HttpServlet {
    public static final String FO_REQUEST_PARAM = "fo";
    public static final String XML_REQUEST_PARAM = "xml";
    public static final String XSL_REQUEST_PARAM = "xsl";
    Logger log = null;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException {
        if (log == null) {
            log = new ConsoleLogger(ConsoleLogger.LEVEL_WARN);
            MessageHandler.setScreenLogger(log);
        }
        try {
            String foParam = request.getParameter(FO_REQUEST_PARAM);
            String xmlParam = request.getParameter(XML_REQUEST_PARAM);
            String xslParam = request.getParameter(XSL_REQUEST_PARAM);

            if (foParam != null) {
                File fofile = new File(foParam);
                //log.warn("FO: "+fofile.getCanonicalPath());
                FileInputStream file = new FileInputStream(fofile);
                renderFO(new InputSource(file), response);              
                boolean borrado = fofile.delete();
            } else if ((xmlParam != null) && (xslParam != null)) {
                File ficheroXml = new File(xmlParam);
                XSLTInputHandler input =
                  new XSLTInputHandler(ficheroXml,
                                       new File(xslParam));
                renderXML(input, response);
                ficheroXml.delete();
            } else {
                PrintWriter out = response.getWriter();
                out.println("FopServlet Error No fo "+
                            "request param given.");
            }
        } catch (ServletException ex) {
            throw ex;
        }
        catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    /**
     * Renders an FO inputsource into a PDF file which is written
     * directly to the response object's OutputStream
     */
    public void renderFO(InputSource foFile,
                         HttpServletResponse response) throws ServletException {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            response.setContentType("application/pdf");

            Driver driver = new Driver(foFile, out);
            driver.setLogger(log);
            driver.setRenderer(Driver.RENDER_PDF);
            driver.run();

            byte[] content = out.toByteArray();
            response.setContentLength(content.length);
            response.getOutputStream().write(content);
            response.getOutputStream().flush();
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    /**
     * Renders an XML file into a PDF file by applying a stylesheet
     * that converts the XML to XSL:FO. The PDF is written
     * directly to the response object's OutputStream
     */
    public void renderXML(XSLTInputHandler input,
                          HttpServletResponse response) throws ServletException {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            response.setContentType("application/pdf");

            Driver driver = new Driver();
            driver.setLogger(log);
            driver.setRenderer(Driver.RENDER_PDF);
            driver.setOutputStream(out);
            driver.render(input.getParser(), input.getInputSource());

            byte[] content = out.toByteArray();
            response.setContentLength(content.length);
            response.getOutputStream().write(content);
            response.getOutputStream().flush();
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

}
