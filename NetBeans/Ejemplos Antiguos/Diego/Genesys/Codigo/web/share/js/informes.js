       
         function enviar(){            
            //Concatenamos todos los elementos del formulario que son select multiples            
            if (document.formulario.SERVICIOS!=null){                
                document.formulario.SERVICIOSMULTIPLES.value = concatenarMultiple(document.formulario.SERVICIOS);
            }            
            if (document.formulario.ESPECTAS!=null){                
                document.formulario.ESPECTASMULTIPLES.value = concatenarMultiple(document.formulario.ESPECTAS);
            }            
            if (document.formulario.TTOS!=null){                
                document.formulario.TTOSMULTIPLES.value = concatenarMultiple(document.formulario.TTOS);
            }            
            if (document.formulario.GERE_ORIG!=null){                
                document.formulario.GERE_ORIGMULTIPLES.value = concatenarMultiple(document.formulario.GERE_ORIG);
            }            
            if (document.formulario.PROV_ORIG!=null){
                document.formulario.PROV_ORIGMULTIPLES.value = concatenarMultiple(document.formulario.PROV_ORIG);
            }            
            if (document.formulario.GERE_DEST!=null){                
                document.formulario.GERE_DESTMULTIPLES.value = concatenarMultiple(document.formulario.GERE_DEST);
            }            
            if (document.formulario.PROV_DEST!=null){                
                document.formulario.PROV_DESTMULTIPLES.value = concatenarMultiple(document.formulario.PROV_DEST);
            }            
            if (document.formulario.SUBSEGMENTO!=null){                
                document.formulario.SUBSEGMENTOSMULTIPLESENTRADA.value = concatenarMultiple(document.formulario.SUBSEGMENTO);
            }
            if (document.formulario.SEGMENTOS!=null){                
                document.formulario.SEGMENTOSMULTIPLESENTRADA.value = concatenarMultiple(document.formulario.SEGMENTOS);
            }            
            if (document.formulario.COD_ENC_ENTRADA!=null){
                document.formulario.COD_ENC_ENTRADAMULTIPLESENTRADA.value = concatenarMultiple(document.formulario.COD_ENC_ENTRADA);
            }
            if (document.formulario.SUGSEGMENTO_S!=null){                
                document.formulario.SUGSEGMENTO_SMULTIPLES.value = concatenarMultiple(document.formulario.SUGSEGMENTO_S);
            }
            if (document.formulario.SEGMENTOS_S!=null){                
                document.formulario.SEGMENTOS_SMULTIPLES.value = concatenarMultiple(document.formulario.SEGMENTOS_S);
            }
            if (document.formulario.COD_ENC_SALIDA!=null){                
                document.formulario.COD_ENC_SALIDAMULTIPLES.value = concatenarMultiple(document.formulario.COD_ENC_SALIDA);
            }
            if (document.formulario.COORD!=null){                
                document.formulario.COORDMULTIPLES.value = concatenarMultiple(document.formulario.COORD);
            }             
            if (document.formulario.GRUPATEN!=null){                
                document.formulario.GRUPATENMULTIPLES.value = concatenarMultiple(document.formulario.GRUPATEN);
            }  
            if (document.formulario.MODOATEN!=null){                
                document.formulario.MODOATENMULTIPLES.value = concatenarMultiple(document.formulario.MODOATEN);
            }              
            if (document.formulario.PERFILATEN!=null){                
                document.formulario.PERFILATENMULTIPLES.value = concatenarMultiple(document.formulario.PERFILATEN);
            }  
            if (document.formulario.IDIOMAATEN!=null){                
                document.formulario.IDIOMAATENMULTIPLES.value = concatenarMultiple(document.formulario.IDIOMAATEN);
            } 
            if (document.formulario.NODORED!=null){                
                document.formulario.NODOREDMULTIPLES.value = concatenarMultiple(document.formulario.NODORED);
            }                  
            if (document.formulario.TIPOSERVICIO!=null){                
                document.formulario.TIPOSERVICIOMULTIPLES.value = concatenarMultiple(document.formulario.TIPOSERVICIO);
            }            
            if (document.formulario.OFICINAS!=null){                
                document.formulario.OFICINASMULTIPLES.value = concatenarMultiple(document.formulario.OFICINAS);
            }
            if (document.formulario.ENRUTAMIENTO!=null){
                document.formulario.ENRUTAMIENTOMULTIPLES.value = concatenarMultiple(document.formulario.ENRUTAMIENTO);
            }
            if (validaFecha(document.formulario.FECHA_DIA_INI.value,document.formulario.FECHA_MES_INI.value,document.formulario.FECHA_ANO_INI.value)==false){
                alert("Fecha de inicio incorrecta");
                return;
            }  
            if (fechaInicioMenorfechaFin()){
                alert('La fecha de inicio debe ser menor a la fecha de fin');
                return;
            }                        
            if (validaFecha(document.formulario.FECHA_DIA_FIN.value,document.formulario.FECHA_MES_FIN.value,document.formulario.FECHA_ANO_FIN.value)==false){
                alert("Fecha de fin incorrecta");
                return;
            }           

            document.formulario.HORA_INI_DES.value = concatenarMultiple(document.formulario.HORA_INI);
            document.formulario.HORA_FIN_DES.value = concatenarMultiple(document.formulario.HORA_FIN);            
            document.formulario.submit();             
         }             
         
         function concatenarMultiple(objetoSelect, valor) 
         {
            var primero = true;
            var opt_selected = new String();
            if (objetoSelect[0].selected){
                return objetoSelect[0].title;
            }
            else{
                for (var i=0;i < objetoSelect.length;i++) 
                {               
                    if (objetoSelect[i].selected) 
                    {
                        if (primero){
                            opt_selected = "'"+objetoSelect[i].title+"'";
                            primero = false;
                        }else    
                            opt_selected = opt_selected+','+"'"+objetoSelect[i].title+"'";
                    } 
                }
                return opt_selected; 
            }
          } 
          
          
          
         function cambiaFoco(input){            
            if (input.name=="FECHA_DIA_INI"){                
                if (input.value.length==2)
                {
                    document.forms[0].FECHA_MES_INI.focus();
                }
            }else if (input.name=="FECHA_MES_INI"){
                if (input.value.length==2)
                {
                    document.forms[0].FECHA_ANO_INI.focus();
                }
            }else if (input.name=="FECHA_ANO_INI"){
                if (input.value.length==4)
                {
                    document.forms[0].FECHA_DIA_FIN.focus();
                }
            }else if (input.name=="FECHA_DIA_FIN"){
                if (input.value.length==2)
                {
                    document.forms[0].FECHA_MES_FIN.focus();
                }
            }else if (input.name=="FECHA_MES_FIN"){
                if (input.value.length==2)
                {
                    document.forms[0].FECHA_ANO_FIN.focus();
                }
            }
         }
         

                    
                  function cambiaDesglose()
                  {                  
                  if (document.formulario.DESGLOSE[0].selected){
                  document.getElementById('fechas').style.display = "block" ;
                  document.getElementById('semanas').style.display = "none" ;
                  document.getElementById('FECHA_DIA_INI').disabled=false;
                  document.getElementById('FECHA_MES_INI').disabled=false;
                  document.getElementById('FECHA_ANO_INI').disabled=false;
                  document.getElementById('FECHA_DIA_FIN').disabled=false;
                  document.getElementById('FECHA_MES_FIN').disabled=false;
                  document.getElementById('FECHA_ANO_FIN').disabled=false;                  
                  document.getElementById('HORA_INI').disabled=false;                                    
                  document.getElementById('HORA_FIN').disabled=false;                                                                       
                  }
                              
                  if (document.formulario.DESGLOSE[1].selected){
                  document.getElementById('fechas').style.display = "block" ;
                  document.getElementById('semanas').style.display = "none" ;
                  document.getElementById('FECHA_DIA_INI').disabled=false;
                  document.getElementById('FECHA_MES_INI').disabled=false;
                  document.getElementById('FECHA_ANO_INI').disabled=false;
                  document.getElementById('FECHA_DIA_FIN').disabled=false;
                  document.getElementById('FECHA_MES_FIN').disabled=false;
                  document.getElementById('FECHA_ANO_FIN').disabled=false;                  
                  document.getElementById('HORA_INI').disabled=true;                                    
                  document.getElementById('HORA_FIN').disabled=true;                                                        
                  }
             
                  if (document.formulario.DESGLOSE[2].selected){
                  document.getElementById('fechas').style.display = "none" ;
                  document.getElementById('semanas').style.display = "block" ;
                  }
                  
                  if (document.formulario.DESGLOSE[3].selected){
                  document.getElementById('fechas').style.display = "" ;
                  document.getElementById('semanas').style.display = "none" ;
                  document.getElementById('FECHA_DIA_INI').disabled=true;
                  document.getElementById('FECHA_MES_INI').disabled=false;
                  document.getElementById('FECHA_ANO_INI').disabled=false;
                  document.getElementById('FECHA_DIA_FIN').disabled=true;
                  document.getElementById('FECHA_MES_FIN').disabled=false;
                  document.getElementById('FECHA_ANO_FIN').disabled=false;                  
                  document.getElementById('HORA_INI').disabled=true;                                                    
                  document.getElementById('HORA_FIN').disabled=true;                                                        
                  }
                   

                  if (document.formulario.DESGLOSE[4].selected){
                  document.getElementById('fechas').style.display = "" ;
                  document.getElementById('semanas').style.display = "none" ;
                  document.getElementById('FECHA_DIA_INI').disabled=true;
                  document.getElementById('FECHA_MES_INI').disabled=true;
                  document.getElementById('FECHA_ANO_INI').disabled=false;
                  document.getElementById('FECHA_DIA_FIN').disabled=true;
                  document.getElementById('FECHA_MES_FIN').disabled=true;
                  document.getElementById('FECHA_ANO_FIN').disabled=false;                  
                  document.getElementById('HORA_INI').disabled=true;                                                     
                  document.getElementById('HORA_FIN').disabled=true;                                                        

                  }
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

   function LeapYear(intYear) 
   {
      if (intYear % 100 == 0) 
      {
         if (intYear % 400 == 0) { return true; }
      }
      else 
      {
         if ((intYear % 4) == 0) 
         { return true; 
         }
      }
         return false;
   }
   
   function fechaInicioMenorfechaFin(){        
        var fecha1 = parseInt(document.formulario.FECHA_ANO_INI.value+document.formulario.FECHA_MES_INI.value+document.formulario.FECHA_DIA_INI.value);
        var fecha2 = parseInt(document.formulario.FECHA_ANO_FIN.value+document.formulario.FECHA_MES_FIN.value+document.formulario.FECHA_DIA_FIN.value);                
        if (fecha1 >= fecha2) {
            return true;
        }
        return false;
   }
   
   //Metodo que validara que la fecha de inicio sea menor a la fecha de sistema, y que no pase de X dias si esta 
   //activado la seleccion de tramo horario
   function fechaInicioMenorAnumdias(fechaHoy,numDias){
        var fechaIni = new Date(document.formulario.FECHA_ANO_INI.value,document.formulario.FECHA_MES_INI.value-1,document.formulario.FECHA_DIA_INI.value);        
        var dFhoy = fechaHoy.toString().substring(6,8); 
        var mFhoy = fechaHoy.toString().substring(4,6);
        var aFhoy = fechaHoy.toString().substring(0,4);        
        var fecha = new Date(aFhoy,mFhoy-1,dFhoy);               
        if ((fecha-fechaIni)/86400000 > numDias){
            return true;
        }
        return false;
   }
