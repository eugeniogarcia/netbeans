<%@page contentType="text/html" pageEncoding="Windows-1252"%>

<jsp:useBean    id='configuration'
		scope='session'
		class='com.tid.vu.Configuration'
/><jsp:useBean  id="logger"
		scope="session"
		class="com.tid.vu.Logger"
/><jsp:useBean  id="htmlFactory"
		scope="session"
		class="com.tid.vu.HTMLFactory"
/>
<%
    logger.setSessionID(session.getId());
    logger.println("Registro de sesión #" + session.getId() + " activado.", this);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>        
        <link  href="./share/css/core.css" rel="stylesheet" type="text/css">        
        <script LANGUAGE="JavaScript"
                SRC="/vu/share/js/common.js"
                type="text/javascript"
        ></script>    
        <SCRIPT LANGUAGE="javascript" 
                type="text/javascript" 
                SRC="/vu/share/js/calendar/calendar.js"> 
        </script>
        <script type="text/JavaScript"> 
         <!--
         var fechaHoy = <%=htmlFactory.returnFechaHoy()%>         
         var numDias = <%=htmlFactory.getNumDias()%>
         --> 
         </script>
        <SCRIPT LANGUAGE="javascript" 
                type="text/javascript" 
                SRC="/vu/share/js/informes.js"> 
        </script>
        <title>Servicio Ventanilla Unica</title>
    </head>
    <body onLoad="document.forms[0].FECHA_DIA_INI.focus()">  

<%@include file="/WEB-INF/jspf/appHeader.jspf" %>

    <div id='contenido' >  
         <form name='formulario' method="post" action="/vu/GenerarInforme">            
            <div id='desglose'>
             <label for='DESGLOSE'>Desglosado por: </label>
              <SELECT SIZE="1" NAME="DESGLOSE"  ONCHANGE="cambiaDesglose();" STYLE="width:175px;" >
                 <% out.println(htmlFactory.generarSelect(section,false,true)); %>
              </SELECT>
            <br/><br/>
            <label for='FORMATO'>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                                 Formato: </label>
                
                <INPUT type="radio" name="FORMATO" value="PDF" CHECKED><b>PDF</b>
                <INPUT type="radio" name="FORMATO" value="TEXTO"><b>TEXTO</b>
                    
            </div>
             
             <!-- aqui empieza lo del change del desglose -->

            
            <div id='fechas'>
                <div>
                    <input type=hidden name="section" value="<%=section%>">
                    <input type=hidden name="tipoInforme" value="<%=request.getParameter("tipoInforme")%>">  
                    <label for='txtFechaIniDia'>Fechas: </label>
                    <input type="hidden" name="txtFechaIni">
                    <INPUT TYPE=TEXT SIZE=2 MAXLENGTH=2 NAME=FECHA_DIA_INI VALUE="" class='text' onkeypress="cambiaFoco(this)">&nbsp;/
                    <INPUT TYPE=TEXT SIZE=2 MAXLENGTH=2 NAME=FECHA_MES_INI VALUE="" class='text' onkeypress="cambiaFoco(this)">&nbsp;/
                    <INPUT TYPE=TEXT SIZE=4 MAXLENGTH=4 NAME=FECHA_ANO_INI VALUE="" class='text' onkeypress="cambiaFoco(this)">&nbsp;
                        <A style="text-decoration: none;"  href="javascript:void(0);" onclick="return getCalendar('/vu/share/js/calendar' ,document.formulario.txtFechaIni, document.formulario.FECHA_DIA_INI, document.formulario.FECHA_MES_INI, document.formulario.FECHA_ANO_INI);">
                        <IMG ALIGN="middle" border=0 src="/vu/img/calendar.gif" alt="Seleccione Fecha Inicio">
                        </A>&nbsp;                    
                    <INPUT TYPE=TEXT SIZE=2 MAXLENGTH=2 NAME=FECHA_DIA_FIN VALUE="" class='text' onkeypress="cambiaFoco(this)">&nbsp;/
                    <INPUT TYPE=TEXT SIZE=2 MAXLENGTH=2 NAME=FECHA_MES_FIN VALUE="" class='text' onkeypress="cambiaFoco(this)">&nbsp;/
                    <INPUT TYPE=TEXT SIZE=4 MAXLENGTH=4 NAME=FECHA_ANO_FIN VALUE="" class='text'>                    
                        <A href="javascript:void(0);" onclick="return getCalendar('/vu/share/js/calendar' ,document.formulario.txtFechaIni, document.formulario.FECHA_DIA_FIN, document.formulario.FECHA_MES_FIN, document.formulario.FECHA_ANO_FIN, document.formulario.FECHA_DIA_INI, document.formulario.FECHA_MES_INI, document.formulario.FECHA_ANO_INI);"> 
                        <IMG ALIGN="middle" border=0 src="/vu/img/calendar.gif" alt="Seleccione Fecha Final">
                        </A>
                </div>
                <br/>
                <div>
                    <label for='HORA_INI'>&nbsp; &nbsp; &nbsp; 
                        Periodo Horario: &nbsp; 
                    </label>
                    <SELECT SIZE="1" NAME="HORA_INI">
                        <%out.println(htmlFactory.generarSelect("HORAINI",false,true)); %>                        
                    </SELECT> 
                    <input type="hidden" name="HORA_INI_DES" value="">
                    <B>-</B>
                    <SELECT SIZE="1" NAME="HORA_FIN">
                        <%out.println(htmlFactory.generarSelect("HORAFIN",false,true)); %>
                    </SELECT>                    
                    <input type="hidden" name="HORA_FIN_DES" value="">
                </div>                  
            </div>
            <div id='semanas' class='popup'>
                <div>
                    <label for='txtFechaIniDia'>Semanas: </label>
                    <INPUT TYPE=TEXT SIZE=2 MAXLENGTH=2 NAME=MES_INI VALUE="" class='text'>&nbsp;/
                    <input type=text size=4 name="ANIO_INI" value="<%=htmlFactory.getYear()%>" class='text'>
                    <INPUT TYPE=TEXT SIZE=2 MAXLENGTH=2 NAME=MES_FIN VALUE="" class='text'>&nbsp;/
                    <input type=text size=4 name="ANIO_FIN" value="<%=htmlFactory.getYear()%>" class='text'>
                </div>
            </div>
            
<!-- inicio botones -->            
            <div id='botones'>                 
                <input type="button" class='button' value="Generar" onclick="enviar();"/>
                <br/><br/>
                <input type="reset" class='button' value="Limpiar"/>
            </div>                
<!-- fin botones -->
            
            <div class='workspace'> 
                        <% if ((section.compareTo("intervenciones") == 0)||(section.compareTo("transferencias") == 0) 
                        ||(section.compareTo("tiempo_respuesta") == 0)||(section.compareTo("atencion_servicio") == 0)
                        ||(section.compareTo("intervencion_cliente") == 0)||(section.compareTo("general_intervenciones") == 0) 
                        ||(section.compareTo("navegacion_ivr") == 0)||(section.compareTo("bloques_ivr") == 0)
                        ) {
                        %>      
                        
                        &nbsp;<IMG src="img/Separador_Origen.jpg" >
                        <div id='columna1'>
                            <label for='GERE_ORIG'>Territorios</label>                    
                            </br>
                            <SELECT MULTIPLE SIZE="10" NAME="GERE_ORIG" style="width:170px;">
                                <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                                <% out.println(htmlFactory.generarSelect("TERRITORIOS",false,true)); %>
                            </SELECT>
                            <input type="hidden" name="GERE_ORIGMULTIPLES">                    
                        </div>
                        
                        <div id='columna2'>
                            <label for='PROV_ORIG'>Provincias</label>
                            <br/>
                            <SELECT MULTIPLE SIZE="10" NAME="PROV_ORIG" style="width:140px;">
                                <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                                <% out.println(htmlFactory.generarSelect("PROVINCIAS",false,true)); %>
                            </SELECT>
                            <input type="hidden" name="PROV_ORIGMULTIPLES">
                        </div>

                        <% } %>                                  
                        
                        <% if ((section.compareTo("intervenciones") == 0)||(section.compareTo("transferencias") == 0) 
                        ||(section.compareTo("tiempo_respuesta") == 0)||(section.compareTo("atencion_servicio") == 0)
                        ||(section.compareTo("intervencion_cliente") == 0)||(section.compareTo("general_intervenciones") == 0) 
                        ||(section.compareTo("navegacion_ivr") == 0)||(section.compareTo("bloques_ivr") == 0)
                        ) {
                        %>      
                        &nbsp;&nbsp;&nbsp;&nbsp;<IMG src="img/Separador_entrada.jpg" >
                        <div id='columna3'>
                            <label for='SEGMENTOS'>Segmentos</label>
                            <br/>
                            <SELECT MULTIPLE SIZE="4" NAME="SEGMENTOS" style='width:110px;'>
                                <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                                <% out.println(htmlFactory.generarSelect("SEGMENTOS",false,true)); %>
                            </SELECT> 
                            <input type="hidden" name="SEGMENTOSMULTIPLESENTRADA">                    
                            <br/>
                            <label for='AREAS'>Subsegmentos</label>                    
                            <br/>
                            <SELECT MULTIPLE SIZE="4" NAME="SUBSEGMENTO" style='width:110px;'>
                                <OPTION SELECTED value="Todos" title="Todos">Todas</OPTION>
                                <% out.println(htmlFactory.generarSelect("AREAS",false,true)); %>
                            </SELECT>
                            <input type="hidden" name="SUBSEGMENTOSMULTIPLESENTRADA">                    
                        </div>
                        <div id='columna4'>
                            <label for='COD_ENC_ENTRADA'>Cod. Encam.</label>
                            <br/>                    
                            <SELECT MULTIPLE SIZE="10" NAME="COD_ENC_ENTRADA" style='width:80px;'>
                                <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                                <% out.println(htmlFactory.generarSelect("ENCAMINADOR",false,true)); %>
                            </SELECT>
                            <input type="hidden" name="COD_ENC_ENTRADAMULTIPLESENTRADA">
                        </div>  
                        <% } %>   

                    <% if ((section.compareTo("bloques_ivr") != 0) && (section.compareTo("navegacion_ivr") != 0)
                    && (section.compareTo("familias") != 0) && (section.compareTo("tiempo_conexion") != 0)
                    && (section.compareTo("atencion_agente") != 0)
                    ) {
                    %>      
                    
                    <div id='columna1'>
                    &nbsp;<IMG src="img/Separador_Destino.jpg">
                        <BR><label for='GERE_DEST'>Territorios</label>
                        <BR>
                        <SELECT MULTIPLE SIZE="10" NAME="GERE_DEST" style="width:170px;">
                            <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                            <% out.println(htmlFactory.generarSelect("TERRITORIOS",false,true)); %>
                        </SELECT>
                        <input type="hidden" name="GERE_DESTMULTIPLES">
                    </div>
                    <div id='columna22'>
                        <label for='PROV_DEST'>Provincias</label>
                        <br/>
                        <SELECT MULTIPLE SIZE="10" NAME="PROV_DEST" style="width:140px;">
                            <OPTION SELECTED value="Todos" title="Todos">Todas</OPTION>
                            <% out.println(htmlFactory.generarSelect("PROVINCIAS",false,true)); %>
                        </SELECT>  
                        <input type="hidden" name="PROV_DESTMULTIPLES">
                    </div>
<%}%>               

                        <% if ((section.compareTo("intervenciones") == 0)||(section.compareTo("transferencias") == 0) 
                        ||(section.compareTo("tiempo_respuesta") == 0)||(section.compareTo("atencion_servicio") == 0)
                        ||(section.compareTo("intervencion_cliente") == 0)||(section.compareTo("general_intervenciones") == 0) 
                    ) {
                    %>      
                    
                    <div id='columna230'>
                    &nbsp;<IMG src="img/Separador_salida.jpg">
                    <br>
                                <label for='SEGMENTOS_S'>Segmentos</label>
                                <br/>
                                <SELECT MULTIPLE SIZE="4" NAME="SEGMENTOS_S" style='width:110px;'>
                                    <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                                    <% out.println(htmlFactory.generarSelect("SEGMENTOS",false,true)); %>
                                </SELECT> 
                                <input type="hidden" name="SEGMENTOS_SMULTIPLES">                    
                                <br/>
                            <label for='SUGSEGMENTO_S'>Subsegmento</label>                    
                            <br/>
                            <SELECT MULTIPLE SIZE="4" NAME="SUGSEGMENTO_S" style='width:110px;'>
                                <OPTION SELECTED value="Todos" title="Todos">Todas</OPTION>
                                <% out.println(htmlFactory.generarSelect("AREAS",false,true)); %>
                            </SELECT>
                            <input type="hidden" name="SUGSEGMENTO_SMULTIPLES">
                        </div>
                        
                        <div id='columna24'>
                            <label for='COD_ENC_SALIDA'>Cod. Encam.</label>
                            <br/>                    
                            <SELECT MULTIPLE SIZE="10" NAME="COD_ENC_SALIDA" style='width:80px;'>
                                <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                                <% out.println(htmlFactory.generarSelect("ENCAMINADOR",false,true)); %>
                            </SELECT>
                            <input type="hidden" name="COD_ENC_SALIDAMULTIPLES"> 
                        </div>
                        <% }%>
                        <% if ((section.compareTo("bloques_ivr") == 0) || (section.compareTo("navegacion_ivr") == 0)
                    ) {
                    %>      
                    <div>
                    <div id='columnaPlat'>
                        <br>
                       <label for='COORD'>Plataformas</label>                       
                       <br/>                    
                        <SELECT MULTIPLE SIZE="10" NAME="COORD" style="width:170px;">
                            <OPTION SELECTED value="Todos" title="Todos">Todas</OPTION>
                            <% out.println(htmlFactory.generarSelect("PLATAFORMAS",false,true)); %>
                        </SELECT>
                        <input type="hidden" name="COORDMULTIPLES">                          
                    </div>  
                     <div id='columnaOf'>
                        <label for='CENTROS'>Oficinas</label>
                       <br/>                    
                        <SELECT MULTIPLE SIZE="10" NAME="OFICINAS" style="width:140px;">
                            <OPTION SELECTED value="Todos" title="Todos">Todas</OPTION>
                            <% out.println(htmlFactory.generarSelect("OFICINAS",false,true)); %>
                        </SELECT>
                        <input type="hidden" name="OFICINASMULTIPLES">                          
                      </div>                
<div id='columna23'>  
    <br><br>
&nbsp;<IMG src="img/Separador_salida.jpg">
                        <br>
                        <label for='SEGMENTOS_S'>Segmentos</label>
                        <br/>
                        <SELECT MULTIPLE SIZE="4" NAME="SEGMENTOS_S" style='width:110px;'>
                            <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                            <% out.println(htmlFactory.generarSelect("SEGMENTOS",false,true)); %>
                        </SELECT> 
                        <input type="hidden" name="SEGMENTOS_SMULTIPLES">
                        <br/>
                        <label for='SUGSEGMENTO_S'>Subsegmentos</label>                    
                        <br/>
                        <SELECT MULTIPLE SIZE="4" NAME="SUGSEGMENTO_S" style='width:110px;'>
                            <OPTION SELECTED value="Todos" title="Todos">Todas</OPTION>
                            <% out.println(htmlFactory.generarSelect("AREAS",false,true)); %>
                        </SELECT>
                        <input type="hidden" name="SUGSEGMENTO_SMULTIPLES">
                    </div>
                    
                    <div id='columnaIvr'>
                        <br>
                        <label for='COD_ENC_SALIDA'>Cod. Encam.</label>
                        <br/>                    
                        <SELECT MULTIPLE SIZE="10" NAME="COD_ENC_SALIDA" style='width:80px;'>
                            <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                            <% out.println(htmlFactory.generarSelect("ENCAMINADOR",false,true)); %>
                        </SELECT>
                        <input type="hidden" name="COD_ENC_SALIDAMULTIPLES"> 
                    </div>
</div>
                      <%}%>
<div class='parte2'>            
                    <% if ((section.compareTo("intervenciones") == 0)||(section.compareTo("transferencias") == 0) 
                        ||(section.compareTo("tiempo_respuesta") == 0)||(section.compareTo("atencion_servicio") == 0)
                        ||(section.compareTo("intervencion_cliente") == 0)||(section.compareTo("general_intervenciones") == 0) 
                       ){ %> 

                    <div class="partesinIvr">                      
                    <label for='ESPECTAS'>Valor de la llamada</label>
                    <br/>
                    <SELECT MULTIPLE SIZE="5" NAME="ESPECTAS" style="width:200px;">
                        <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                        <% out.println(htmlFactory.generarSelect("ESPECTAS",false,true)); %>
                    </SELECT>
                    <input type="hidden" name="ESPECTASMULTIPLES">
                    
                    <label for='MODOATEN'>Modo Atenci&oacute;n</label>
                    <br/>
                    <SELECT MULTIPLE SIZE="5" NAME="MODOATEN" style='width:200px;'>
                        <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                        <% out.println(htmlFactory.generarSelect("MODOATEN",false,true)); %>
                    </SELECT>    
                    <input type="hidden" name="MODOATENMULTIPLES">
                    <label for='PERFILATEN'>Perfil Atenci&oacute;n</label>
                    <br/>
                    <SELECT MULTIPLE SIZE="5" NAME="PERFILATEN" style='width:200px;'>
                        <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                        <% out.println(htmlFactory.generarSelect("PERFILATEN",false,true)); %>
                    </SELECT>    
                    <input type="hidden" name="PERFILATENMULTIPLES">
                   
                    <br><br>
                    <label for='IDIOMAATEN'>Idioma Atenci&oacute;n</label>
                    
                    <SELECT MULTIPLE SIZE="5" NAME="IDIOMAATEN" style='width:200px;'>
                        <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                        <% out.println(htmlFactory.generarSelect("IDIATEN",false,true)); %>
                    </SELECT>    
                    <input type="hidden" name="IDIOMAATENMULTIPLES">
                    </div>
                    <% } %>                 
                <%if ((section.compareTo("navegacion_ivr") == 0) || (section.compareTo("bloques_ivr") == 0)
                    ){ %>
                    
                    <div>
                            <label for='ESPECTAS'>Valor de la llamada</label>
                    <br>
                    <SELECT MULTIPLE SIZE="5" NAME="ESPECTAS" style="width:200px;">
                        <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                        <% out.println(htmlFactory.generarSelect("ESPECTAS",false,true)); %>
                    </SELECT>
                    <input type="hidden" name="ESPECTASMULTIPLES">
                    <label for='MODOATEN'>Modo Atenci&oacute;n</label>
                    <br>
                    <SELECT MULTIPLE SIZE="5" NAME="MODOATEN" style='width:200px;'>
                        <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                        <% out.println(htmlFactory.generarSelect("MODOATEN",false,true)); %>
                    </SELECT>    
                    <input type="hidden" name="MODOATENMULTIPLES">
                    <label for='PERFILATEN'>Perfil Atenci&oacute;n</label>
                    <br/>
                    <SELECT MULTIPLE SIZE="5" NAME="PERFILATEN" style='width:200px;'>
                        <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                        <% out.println(htmlFactory.generarSelect("PERFILATEN",false,true)); %>
                    </SELECT>    
                    <input type="hidden" name="PERFILATENMULTIPLES">

                    <br><br>
                    <label for='IDIOMAATEN'>Idioma Atenci&oacute;n</label>
                    
                    <SELECT MULTIPLE SIZE="5" NAME="IDIOMAATEN" style='width:200px;'>
                        <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                        <% out.println(htmlFactory.generarSelect("IDIATEN",false,true)); %>
                    </SELECT>    
                    <input type="hidden" name="IDIOMAATENMULTIPLES">
                    </div>
                    <% }%>  
</div>
        
                <%if ((section.compareTo("familias") == 0) || (section.compareTo("atencion_agente") == 0) 
                        || (section.compareTo("tiempo_conexion") == 0)
                    ){%>
            <div class='parteFamilia'>
                    <div id='columnaVL'>
                    <label for='ESPECTAS'>Valor de la llamada</label>
                    <SELECT MULTIPLE SIZE="10" NAME="ESPECTAS" style="width:200px;">
                        <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                        <% out.println(htmlFactory.generarSelect("ESPECTAS",false,true)); %>
                    </SELECT>
                    <input type="hidden" name="ESPECTASMULTIPLES">
                    </div>
                    
                    <div id='columnaMA'>
                    <label for='MODOATEN'>Modo Atenci&oacute;n</label>
                    <SELECT MULTIPLE SIZE="10" NAME="MODOATEN" style='width:200px;'>
                        <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                        <% out.println(htmlFactory.generarSelect("MODOATEN",false,true)); %>
                    </SELECT>    
                    <input type="hidden" name="MODOATENMULTIPLES">
                    </div>
                    
                    <div id='columnaPA'>
                    <label for='PERFILATEN'>Perfil Atenci&oacute;n</label>
                    <SELECT MULTIPLE SIZE="10" NAME="PERFILATEN" style='width:200px;'>
                        <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                        <% out.println(htmlFactory.generarSelect("PERFILATEN",false,true)); %>
                    </SELECT>    
                    
                    <input type="hidden" name="PERFILATENMULTIPLES">
                    </div>
        </div>     <!-- FIN FILA 1- Fin primera estructura-->         
        <% }%>

<!--Inicio tercera fila-->

<div class='fila3'>
<% if ((section.compareTo("intervenciones") == 0)||(section.compareTo("transferencias") == 0) 
                        ||(section.compareTo("tiempo_respuesta") == 0)||(section.compareTo("atencion_servicio") == 0)
                        ||(section.compareTo("intervencion_cliente") == 0)||(section.compareTo("general_intervenciones") == 0) 
                        ) {
                            %>      
                 

                            <div id='columna1'>
                       <label for='COORD'>Plataformas</label>                       
                       <br/>                    
                        <SELECT MULTIPLE SIZE="9" NAME="COORD" style="width:170px;">
                            <OPTION SELECTED value="Todos" title="Todos">Todas</OPTION>
                            <% out.println(htmlFactory.generarSelect("PLATAFORMAS",false,true)); %>
                        </SELECT>
                        <input type="hidden" name="COORDMULTIPLES">                          
                    </div>                  
<%} %>  
 
<% if ((section.compareTo("familias") != 0) && (section.compareTo("atencion_agente") != 0)
       && (section.compareTo("bloques_ivr") != 0)&& (section.compareTo("navegacion_ivr") != 0)
       && (section.compareTo("tiempo_conexion") != 0)
               ){
               %>                        
                      <div id='columna2'>
                       <label for='CENTROS'>Oficinas</label>                       
                        <br/>
                        <SELECT MULTIPLE SIZE="9" NAME="OFICINAS" style="width:140px;">
                            <OPTION SELECTED value="Todos" title="Todos">Todas</OPTION>
                            <% out.println(htmlFactory.generarSelect("OFICINAS",false,true)); %>
                        </SELECT>
                        <input type="hidden" name="OFICINASMULTIPLES">                          
                      
                      </div>
<%} %>


<% if ((section.compareTo("tiempo_conexion") == 0)
               ){
               %>                        
                      <div id='columnaFa'>
                        <label for='CENTROS'>Oficinas</label>
                        <br/>
                        <SELECT MULTIPLE SIZE="10" NAME="OFICINAS" style="width:140px;">
                            <OPTION SELECTED value="Todos" title="Todos">Todas</OPTION>
                            <% out.println(htmlFactory.generarSelect("OFICINAS",false,true)); %>
                        </SELECT>
                        <input type="hidden" name="OFICINASMULTIPLES">                          
                      </div>
<%} %>

<% if ((section.compareTo("bloques_ivr") != 0) && (section.compareTo("familias") != 0) && (section.compareTo("atencion_agente") != 0)
               && (section.compareTo("navegacion_ivr") != 0)&&(section.compareTo("tiempo_conexion") != 0)
               && (section.compareTo("tiempo_conexion") != 0)
                            ) {
                            %>      
                        <div id="columna32">
                            <label for='PUESTOS'>Puestos</label>
                            &nbsp;
                            <INPUT TYPE=TEXT SIZE=67 MAXLENGTH=256 NAME="PUESTOS" class='text' VALUE="">
                         </div>
<%}%>

<% if ((section.compareTo("tiempo_conexion") == 0)
               ){
               %>                        

                        <div id="columnaPu">
                            <br>
                            <label for='PUESTOS'>Puestos</label>
                            &nbsp;
                            <br>
                            <INPUT TYPE=TEXT SIZE=80 MAXLENGTH=256 NAME="PUESTOS" class='text' VALUE="">
                         </div>
<%}%>
               
<% if ((section.compareTo("tiempo_conexion") != 0) && (section.compareTo("bloques_ivr") != 0)
&& (section.compareTo("navegacion_ivr") != 0) && (section.compareTo("familias") != 0)
&& (section.compareTo("atencion_agente") != 0)
){
               %>                        
                        <div id="columna32">
                        <label for='FAMILIAS'>Familias</label>                      
                        &nbsp;
                        <INPUT TYPE=TEXT SIZE=67 MAXLENGTH=256 NAME="FAMILIAS" class='text' VALUE="">
                        </div>
<%} %>


<% if ((section.compareTo("familias") == 0)||(section.compareTo("atencion_agente") == 0)
               ){
               %>                        
                        <div id="columnaFa">
                        <br/>
                        <label for='FAMILIAS'>Familias</label>                      
                        &nbsp;
                        <br>
                        <INPUT TYPE=TEXT SIZE=110 MAXLENGTH=256 NAME="FAMILIAS" class='text' VALUE="">
                        </div>
<%} %>

<% if ((section.compareTo("familias") != 0) && (section.compareTo("atencion_agente") != 0) 
    && (section.compareTo("tiempo_conexion") != 0) && (section.compareTo("bloques_ivr") != 0)
    && (section.compareTo("navegacion_ivr") != 0)
      ){
               %>                        
                        <div id="columna32">
                        <label for='AGENTES'>Agentes</label>                      
                        &nbsp;
                        <INPUT TYPE=TEXT SIZE=67 MAXLENGTH=256 NAME="AGENTES" class='text' VALUE="">                        
                        </div>    
<%} %>

</div>

                <!--Fin tercera fila-->
              <!--Inicio cuarta fila-->
<% if ((section.compareTo("bloques_ivr") != 0) && (section.compareTo("familias") != 0)
        && (section.compareTo("navegacion_ivr") != 0)&& (section.compareTo("tiempo_conexion") != 0)
        && (section.compareTo("atencion_agente") != 0)
                        ) {
                            %>      
<br><br>
                 <div class='fila3'>
                  <div id='columna1'>
<!-- Se agrego la opcion de Enrutamiento -->
                        <label for='TTOS'>Enrutamientos</label>
                        <BR/>
                        <SELECT MULTIPLE SIZE="9" NAME="ENRUTAMIENTO" style='width:170px;'>
                            <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                            <% out.println(htmlFactory.generarSelect("ENRUTAMIENTO",false,true)); %>
                        </SELECT>
                        <input type="hidden" name="ENRUTAMIENTOMULTIPLES">                         
                      </div>
<!-- FIN opcion de Enrutamiento -->
                    <div id='columna2'>
                    <label for='TTOS'>Tratamientos</label>
                        <BR/>
                        <SELECT MULTIPLE SIZE="9" NAME="TTOS" style='width:160px;'>
                            <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                            <% out.println(htmlFactory.generarSelect("TTOS",false,true)); %>
                        </SELECT>
                        <input type="hidden" name="TTOSMULTIPLES">                         
                    </div>
                    <div id='columna3'>
                        <label for='SERVICIOS'>Servicios</label>
                        <br/>
                        <SELECT MULTIPLE SIZE="9" NAME="SERVICIOS" style='width:200px;'>
                            <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                            <% out.println(htmlFactory.generarSelect("SERVICIOS",false,true)); %>
                        </SELECT> 
                        <input type="hidden" name="SERVICIOSMULTIPLES">
                    </div>
<!-- Se creo Tipo de Servicio -->
                    <div id='columna45'>
                        <label for='TIPOSERVICIO'> Tipo de Servicio </label>
                        <BR>
                        <SELECT MULTIPLE SIZE="9" NAME="TIPOSERVICIO" style='width:90px;' >
                            <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                            <% out.println(htmlFactory.generarSelect("TIPOSERVICIO",false,true)); %>
                        </SELECT>
                        <input type="hidden" name="TIPOSERVICIOMULTIPLES">
                    </div>
<!-- FIN Tipo de Servicio -->      
                    <div id='columna45'>
                        <label for='ELEMRED'>&nbsp; Nodos de Red</label>
                        <BR>
                        <SELECT MULTIPLE SIZE="9" NAME="NODORED" style='width:90px;'>
                            <OPTION SELECTED value="Todos" title="Todos">Todos</OPTION>
                            <% out.println(htmlFactory.generarSelect("ELEMRED",false,true)); %>
                        </SELECT>
                        <input type="hidden" name="NODOREDMULTIPLES">
                    </div>
                 </div> <!--Fin cuarta fila-->                    
                 <% } %>

 </div> <!-- FIN id='workspace' -->
</form>      
</div>   <!-- FIN id='contenido' -->
<%@include file="/WEB-INF/jspf/mainMenu.jspf" %>

</body>

</html>
