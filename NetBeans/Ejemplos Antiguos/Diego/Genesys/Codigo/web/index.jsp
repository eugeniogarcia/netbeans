<%@page contentType="text/html"
        pageEncoding="Windows-1252"
%>
<%// Inicialización de atributos de la sesión
session.setAttribute("configFile", "/com/tid/vu/vu.properties");
%><jsp:useBean  id='configuration'
		scope='session'
		class='com.tid.vu.Configuration'
/><%
if(!configuration.isLoaded()) {
    session.invalidate();
%>  <jsp:forward page="share/msgPage.jsp">
    <jsp:param name="msg1" value="No es posible acceder a la aplicación:
                                  errores de configuración. 
                                  Informe al administrador del siguiente mensaje:"
    /><jsp:param name="msg2" value="<%= configuration.getErrorMessage() %>" />
    <jsp:param name="type" value="ERROR"/>
    <jsp:param name="linkURL" value="index.jsp"/>
    <jsp:param name="linkText" value="Regresar a la página de inicio"/>
    </jsp:forward>
<%  }  %>    
<jsp:useBean  id="logger"
		scope="session"
		class="com.tid.vu.Logger"
/><jsp:useBean  id="htmlFactory"
		scope="session"
		class="com.tid.vu.HTMLFactory"
/>
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
    <body>    
<%@include file="/WEB-INF/jspf/appHeader.jspf" %>
        <div style='text-align: left; margin-left: 200px; margin-top:100px'>
            <!--<img src='/vu/img/bultit.gif'>-->
            <!--<span class="titulo">ventanilla &uacute;nica - estad&iacute;sticas del servicio</span>-->
            <%--<h3>Bienvenido, <%= sessionUser.getName() %></h3>--%>
            <center><img src='/vu/img/contacto_03.jpg'></center>
        </div>      
    <%@include file="/WEB-INF/jspf/mainMenu.jspf" %>   
    </body>
</html>
