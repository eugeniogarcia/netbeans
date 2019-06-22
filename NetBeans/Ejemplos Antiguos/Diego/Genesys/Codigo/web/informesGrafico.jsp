<%@page contentType="text/html"
        pageEncoding="Windows-1252"
%>
<jsp:useBean  id="htmlFactory"
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
        <script type="text/JavaScript"> 
         <!--
        function enviar(){            
            //Concatenamos todos los elementos del formulario que son select multiples
            if (!validaFecha(document.formulario.FECHA_DIA_INI.value, document.formulario.FECHA_MES_INI.value, document.formulario.FECHA_ANO_INI.value)){
                alert('La fecha introducida no es valida');
            }else{                
                document.formulario.OFICINASMULTIPLES.value = concatenarMultiple(document.formulario.OFICINAS);
                document.formulario.submit(); 
            }
         }            
         
         function concatenarMultiple(objetoSelect, valor) 
         { 
            var opt_selected = new String();             
            for (var i=0;i < objetoSelect.length;i++) 
            { 
               if (objetoSelect[i].selected) 
               {                  
                  opt_selected = opt_selected + objetoSelect[i].value + ';';                  
               } 
            }            
            return opt_selected; 
          }  
          
          function cambiaFecha(){
            document.getElementById('FECHA_DIA_INI').disabled=true;
          }
          
      function validaFecha(dia, mes, ano) {      
      if (isNaN(dia) || isNaN(mes) || isNaN(ano)) {      	 
            return false;
        }      
        if (mes>12 || mes<1) {
            return false;
        }
        if ((mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) && (dia > 31 || dia < 1)) {         
            return false;
        }
        if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && (dia > 30 || dia < 1)) {        
            return false;
        }
        if (mes == 2) {
            if (dia < 1) {            
                return false;
            }
            if (LeapYear(ano) == true) {
                if (dia > 29) {               
                    return false;
                }
            }else {
                if (dia > 28) {              
                return false;
                }
            }
        }
         return true;
      }
         --> 
         </script> 
        <title>Servicio Ventanilla Unica</title>
    </head>
    <body>  

<%@include file="/WEB-INF/jspf/appHeader.jspf" %>

 <div id='contenido' >  
     <form name='formulario' method="post" action="/vu/GenerarInforme">
         <input type=hidden name="section" value="<%=section%>">
         <input type=hidden name="tipoInforme" value="<%=request.getParameter("tipoInforme")%>">
         <input type=hidden name="FORMATO" value="IMAGEN">         
         <div class='workspace'>  
             <br>
             <div id='fechasG'>
                 <label>Tipo de Gr&aacute;fica: </label>&nbsp;
                 <INPUT type="radio" name="tipoGrafica" value="Diaria" CHECKED ONCHANGE="cambiaFecha()"><b>Diaria</b>
                 <INPUT type="radio" name="tipoGrafica" value="Mensual" ONCHANGE="cambiaFecha()"><b>Mensual</b>
                 <br/><br/>             
                 <input type=hidden name="section" value="<%=section%>">
                 <input type=hidden name="tipoInforme" value="<%=request.getParameter("tipoInforme")%>">  
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 <label for='txtFechaIniDia'>Fecha: </label>&nbsp;
                 <INPUT TYPE=TEXT SIZE=2 MAXLENGTH=2 NAME=FECHA_DIA_INI VALUE="" class='text' onkeypress="cambiaFoco(this)">&nbsp;/
                 <INPUT TYPE=TEXT SIZE=2 MAXLENGTH=2 NAME=FECHA_MES_INI VALUE="" class='text' onkeypress="cambiaFoco(this)">&nbsp;/
                 <INPUT TYPE=TEXT SIZE=4 MAXLENGTH=4 NAME=FECHA_ANO_INI VALUE="" class='text' onkeypress="cambiaFoco(this)">
                 <input type="hidden" name="txtFechaIni">
                 <A href="javascript:void(0);" onclick="return getCalendar( '/vu/share/js/calendar' ,document.formulario.txtFechaIni, document.formulario.FECHA_DIA_INI, document.formulario.FECHA_MES_INI, document.formulario.FECHA_ANO_INI);"> <IMG ALIGN="middle" border=0 src="/vu/img/calendar.gif" alt="Seleccione Fecha"></A>
                 
            </div> 
            <div class="parte1G">
                <label for='GERE_ORIG'>Oficinas</label>                    
                    </br>
                    <SELECT SIZE="15" NAME="OFICINAS" style="width:220px;">
                        <OPTION SELECTED value="Todos">Todos</OPTION>
                        <% out.println(htmlFactory.generarSelect("OFICINAS",false,true)); %>
                    </SELECT>
                    <input type="hidden" name="OFICINASMULTIPLES">  
                 <br/><br/><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 <input type="button" class='button'  align="center" value="Generar" onclick="enviar();"/>
            </div>
            

            <div id='dibujo' class="parte2G">
                <% if (request.getParameter("ficheroJPG")!=null){
                            if (request.getParameter("ficheroJPG").equalsIgnoreCase("NOHAY")){%>
                            <script>alert('NO HAY DATOS')</script>
                            <%}else{%>
                            <img src="<%=request.getParameter("ficheroJPG")%>">
                            <%}
                   }%>
             </div>

        </div>
     </form>    

<%@include file="/WEB-INF/jspf/mainMenu.jspf" %>
</div>     
</body>
</html>