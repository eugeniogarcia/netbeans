<%@page contentType="text/html"
        pageEncoding="Windows-1252"
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Windows-1252">
        <link  href="./share/css/core.css" rel="stylesheet" type="text/css">
        <SCRIPT LANGUAGE="javascript" 
                type="text/javascript" 
                SRC="/vu/share/js/calendar/calendar.js">                    
        </SCRIPT>
        <script LANGUAGE="JavaScript"
                SRC="share/js/common.js"
                type="text/javascript"
        ></script>
        <title>Servicio Ventanilla Unica</title>
    </head>
    <body onload="document.generar.submit();">
    <%@include file="/WEB-INF/jspf/appHeader.jspf" %>

    <div id='contenido' style="MARGIN: 0px auto; WIDTH: 980px">           
            <div style='float: left; text-align: left; margin-left: 180px; margin-top: 15px'>
             <label class='informe' for='INFORME'><%=request.getParameter("tipoInforme")%></label>               
            </div>           
            <div style='float: left; margin-left: 100px; margin-top: 15px'>
                <form action="/vu/informes.jsp" method="post">
                <input type="hidden" name='section' value="<%=request.getParameter("section")%>">
                <input type="hidden" name='tipoInforme' value="<%=request.getParameter("tipoInforme")%>">
                <input type="submit" class='button' value="Volver">
                </form>                
            </div>            
            
            <div style='clear: both'></div>         
          <div id='workspace' style='height: 425px'>
            <form name="generar" method='get' action='/vu/crearPDF' target="myIframe">
                <input type="hidden" name="xml" value='<%= request.getParameter("ficheroXml") %>'>
                <input type="hidden" name="xsl" value='<%= request.getParameter("ficheroXsl") %>'>
            </form>
            <iframe name="myIframe" height="100%" width="100%" frameborder="1"></iframe>
          </div>             
    
</div>
     
    </body>
</html>
