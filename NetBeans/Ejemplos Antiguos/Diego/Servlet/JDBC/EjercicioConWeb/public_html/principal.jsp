<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>principal</title>
  </head>
  <body><form action="acceso.do" method="post">
      <p>
        Usuario: 
        <input type="text" name="usuario"/>
      </p>
      <p>
        Password: 
        <input type="password" name="password"/>
      </p>
      <p>
        &nbsp;
      </p>
      <p>
        <input type="submit" value="Aceptar"/>
      </p>
    </form><form action="nuevousuario.do" method="post">
      <p>
        Si no es usuario, pulse para darse de alta.
      </p>
      <p>
        <input type="submit" value="Nuevo Usuario"/>
      </p>
    </form></body>
</html>