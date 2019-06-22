<%-- 
    Document   : index
    Created on : 27-mar-2010, 20:28:04
    Author     : Eugenio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="../CalculatorClientApp/clienteWS">
            <input id="i" name ="i" value="" type="text" size="30"/>
            <input id="j" name ="j" value="" type="text" size="30"/>
            <button type="submit">Suma</button>
        </form>
    </body>
</html>
