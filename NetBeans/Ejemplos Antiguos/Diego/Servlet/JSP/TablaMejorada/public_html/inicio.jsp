<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>inicio</title>
  </head>
  <body><form action="insertarDatos.do" method="post">
      <p>
        Nombre: &nbsp;&nbsp;
        <input type="text" name="nombre"/>
      </p>
      <p>
        Apellidos: 
        <input type="text" name="apellidos"/>
      </p>
      <p>
        Edad: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="text" name="edad" maxlength="3" size="3"/>
      </p>
      <p>
        &nbsp;
      </p>
      <p>
        <input type="submit" value="Aceptar"/>
      </p>
    </form><form action="mostrar.do" method="post">
      <input type="submit" value="Mostrar Datos Insertados"/>
    </form></body>
</html>