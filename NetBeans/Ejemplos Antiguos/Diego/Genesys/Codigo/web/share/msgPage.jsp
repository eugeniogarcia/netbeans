<%@page contentType="text/html" session="true" pageEncoding="Windows-1252"%>
<% 
// Mensaje a mostrar por defecto si 'msg1' es nulo.
    String defaultMessage = "\tError en la aplicación. " +
			    "Contacte con el Administrador del Sistema."; 
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
%>
<html>
<head>
    <link rel=stylesheet href="/vu/share/css/core.css" type="text/css">
    <link rel="stylesheet" href="/vu/share/css/msgPage.css" type="text/css">
    <title>XBRL web -  Mensaje</title>
    <meta http-equiv="Content-Type" content="text/html; charset=Windows-1252">
    <!--[if gte IE 5.5000]>
    <script type="text/javascript" src="/vu/share/js/pngfix.js"></script>
    <![endif]-->
    <style type="text/css">
	BODY { 
	    background-repeat:no-repeat; 
	    background-position: center;
	    color: #000000
	    z-index: -1;
	}
    </style>
</head>
<body TOPMARGIN="0" LEFTMARGIN="0" MARGINWIDTH="0" MARGINHEIGHT="0">
<%@include file="/WEB-INF/jspf/appHeader.jspf" %>
<div class='centered'>
<%  String msgType = (String)request.getAttribute("type");
    if(msgType == null)
	msgType = request.getParameter("type");

    String imgSrc = null;
    if(msgType != null) {
	if(msgType.equals("INFO"))
	    imgSrc = "/vu/img/sign_info.png";
	else if(msgType.equals("WARNING"))
	    imgSrc = "/vu/img/sign_warning.png";
	else if(msgType.equals("ERROR"))
	    imgSrc = "/vu/img/sign_error.png";
	if(imgSrc != null)
	    out.println("\t<div class='imgSpace'>\n\t\t<img src='" + imgSrc + 
			"' alt='" + msgType + "'>\n\t</div>"
	    );
    }

    String linkURL = (String)request.getAttribute("linkURL");
    if(linkURL == null)
	linkURL = request.getParameter("linkURL");
    String linkURL2 = (String)request.getAttribute("linkURL2");
    if(linkURL2 == null)
	linkURL2 = request.getParameter("linkURL2");
    String linkText = (String)request.getAttribute("linkText");
    if(linkText == null)
	linkText = request.getParameter("linkText");
    String linkTarget = (String)request.getAttribute("linkTarget");
    if(linkTarget == null)
	linkTarget = request.getParameter("linkTarget");
    String linkText2 = (String)request.getAttribute("linkText2");
    if(linkText2 == null)
	linkText2 = request.getParameter("linkText2");
    String linkTarget2 = (String)request.getAttribute("linkTarget2");
    if(linkTarget2 == null)
	linkTarget2 = request.getParameter("linkTarget2");
%>  <div class='msgToUser'>
<%  // Se comprueba si 'msg1' es nulo. En caso afirmativo se asume 
    // que se quiere mostrar en mensaje por defecto.
    String msg1 = (String)request.getAttribute("msg1");
    if(msg1 == null)
	msg1 = request.getParameter("msg1");
    String msg2 = (String)request.getAttribute("msg2");
    if(msg2 == null)
	msg2 = request.getParameter("msg2");

    if(msg1 == null)
	out.println(defaultMessage);
    else
	out.println("\t" + msg1); 
%>
    </div>
<%	if(msg2 != null) {
	    out.println("<div class='msgToAdmin'><code>");
	    out.println("\t\t#" + session.getId() + "<br>");
	    out.println("\t\t" + sdf.format(java.util.Calendar.getInstance().getTime()) + "<br>");
	    out.println("\n\t\t" + msg2);
	    out.println("</code></div>");
	}
	if(linkURL != null) {
            out.println("<div style='clear: both;'></div>");
	    out.println("\t<div class='linkSpace'" + 
                        (linkURL2 != null ? " style='float: left; width: 45%;'" : "style='float: left; width: 55%;'") + ">"
            );
	    out.println("\t\t<a href='" + linkURL + "'" +  
			(linkTarget != null ? " target='" + linkTarget + "'>" : ">") + 
                        "<div style='float: right;'><img src='/vu/img/button_right.png'></div>" + 
                        "<div style='float: right; background-image: url(/vu/img/button_middle.gif); height: 32px;'>" + 
			"<p style='margin-top: 8px;'>" + (linkText == null ? "Regresar" : linkText) + "</p></div>" +  
                        "<div style='float: right;'><img src='/vu/img/button_left.png'></div>" + 
                        "</a>"
            );
            //out.println("\t\t<button>" + linkText + "<img src='/vu/img/sign_warning.png'></button>");
	    out.println("\t</div>");
	}
	if(linkURL2 != null) {
	    out.println("\t<div class='linkSpace' style='float: right; width: 45%;'>");
	    out.println("\t\t<a href='" + linkURL2 + "'" + 
			(linkTarget2 != null ? " target='" + linkTarget2 + "'>" : ">") + 
                        "<div style='float: left;'><img src='/vu/img/button_left.png'></div>" + 
                        "<div style='float: left; background-image: url(/vu/img/button_middle.gif); height: 32px;'>" + 
			"<p style='margin-top: 8px;'>" + (linkText2 == null ? "Regresar" : linkText2) + "</p></div>" + 
                        "<div style='float: left;'><img src='/vu/img/button_right.png'></div>" + 
                        "</a>"
            );
	    out.println("\t</div>");
            out.println("\t<div style='clear: both;'></div>");
	}
%>
</div>
</body>
</html>
