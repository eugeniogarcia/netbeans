<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="/webapp/B_basado_proyecto" prefix="B_basado_proyecto"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>pruebas</title>
  </head>
  <body><p>
      <B_basado_proyecto:E1/>
    </p><p>
      &nbsp;
    </p><p>
     <B_basado_proyecto:E2 nombre="<%=request.getParameter(\"nombre\")%>"/>
    </p><p>
      <B_basado_proyecto:S1 condicion="false">
        Condicion cumplida
      </B_basado_proyecto:S1>
    </p><p>
      <B_basado_proyecto:TablaProf cabecera="cabecera" cuerpo="cuerpo"
                                   ncolumnas="3" nfilas="3"/>
    </p></body>
</html>