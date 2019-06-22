<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<jsp:useBean id="libro" class="modelo.Libro" scope="session"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>datosLibro</title>
  </head>
  <body><div align="center">
      <p>
        <strong>DATOS DEL LIBRO BUSCADO</strong>
      </p><p align="left">
        TITULO: 
        <jsp:getProperty name="libro" property="titulo"/>
      </p><p align="left">
        AUTOR:<jsp:getProperty name="libro" property="autor"/>
      </p><p align="left">
        NACIONALIDAD:<jsp:getProperty name="libro" property="nacionalidad"/>
      </p><p align="left">
        NUMERO PAGINAS:<jsp:getProperty name="libro" property="numeroPaginas"/>
      </p><p align="left">
        EDITORIAL:<jsp:getProperty name="libro" property="editorial"/>
      </p>
    </div></body>
</html>