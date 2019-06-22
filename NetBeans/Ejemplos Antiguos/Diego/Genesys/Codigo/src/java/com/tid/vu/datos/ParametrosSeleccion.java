/*
 * ParametrosSeleccion.java
 *
 * Created on 12 de febrero de 2007, 11:56
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.tid.vu.datos;

import java.sql.Date;

/**
 *
 * @author t610908
 */
public class ParametrosSeleccion {
    
    //Atributo para la el parametro fechas
    private String fechaInicio;
    private String fechaFinalizacion;
    //Atribudo para el paramtero Periodo Horario
    private String horaInicial;
    private String horaFinal;
    private String horaInicialDescr;
    private String horaFinalDescr;
    //Atributos usados para el desglose por semana
    private String mesInicial;
    private String mesFinal;
    private String anioInicial;
    private String anioFinal; 
    private String diaInicial;
    private String diaFinal;
    //Atributo para el parametro Servicio
    private String servicio;
    //Atribudo para el paramtero Provincia Origen
    private String provinciaOrigen;
    //Atributo para el parametro Provincia Destino
    private String provinciaDestino;
    //Atributo para el parametro Valor de la LLamada
    private String valorLlamada;
    //Atributo para el modo de llamada
    private String modoLlamada;
    //Atributo para el Perfil de atencion
    private String perfilAtencion;
    //Atributo para el idioma de atencion
    private String idiomaAtencion;    
    //Atributo para el parametro Tratamiento
    private String tratamiento;
    //Atributo para el parametro SubSegmento de Entrada
    private String subSegmentoEntrada;
    //Atributo para el parametro Segmento de Entrada
    private String segmentoEntrada;
     //Atributo para el parametro codigo encaminador de Entrada
    private String cod_encaminadorEntrada;
    //Atribudo para el subsegmento de Salida
    private String subSegmentoSalida;
    //Atributo para el parametro Segmento
    private String segmentoSalida;
     //Atributo para el parametro codigo encaminador
    private String cod_encaminadorSalida;
    //Atributo para el parametro Plataforma
    private String plataforma;
    //Atributo para el parametro Territorio Origen
    private String territorioOrigen;
    //Atributo para el parametro Territorio Destino
    private String territorioDestino;
    //Atributo para el parametro Oficinas
    private String oficinas;
    //Atrubuto para el parametro puesto
    private String puesto;    
    //Atributo para el parametro grupo de atencion
    private String grupoAtencion;    
    //Atributo para el parametro familia
    private String familia;
    //Atributo para el parametro agentes
    private String agentes;
    //Atributo para el parametros enrutamiento
    private String enrutamiento;
    //Atributo para el nodo de red
    private String nodoRed;
    //Atributo para tipo de servicio
    private String tipoServicio;   
   
    
    //Atributo para saber el tipo de informe a generar
    private String tipoInforme;
    private String desglose;
    
    //Atributo para la fecha, (no son datos de la BBDD).
    private String fechaImpresion;  
    
    private boolean todoTramo;
   
    
       
    /**
     * ParametrosSeleccion: Constructor de la clase ParametrosSeleccion, que recoge
     * los parametros elegidos para la construccion del informe requerido.
     */
    public ParametrosSeleccion() {        
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(String fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getProvinciaOrigen() {
        return provinciaOrigen;
    }

    public void setProvinciaOrigen(String provinciaOrigen) {
        this.provinciaOrigen = provinciaOrigen;
    }

    public String getProvinciaDestino() {
        return provinciaDestino;
    }

    public void setProvinciaDestino(String provinciaDestino) {
        this.provinciaDestino = provinciaDestino;
    }

    public void setValorLlamada(String valorLlamada) {
        this.valorLlamada = valorLlamada;
    }

    public String getValorLlamada() {
        return valorLlamada;
    }
    
    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }
   
    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getTerritorioOrigen() {
        return territorioOrigen;
    }

    public void setTerritorioOrigen(String territorio) {
        this.territorioOrigen = territorio;
    }

    public void setTerritorioDestino(String territorioDestino) {
        this.territorioDestino = territorioDestino;
    }

    public String getTerritorioDestino() {
        return territorioDestino;
    }   


    public String getOficinas() {
        return oficinas;
    }

    public void setOficinas(String oficinas) {
        this.oficinas = oficinas;
    }   

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }   

    public String getGrupoAtencion() {
        return grupoAtencion;
    }

    public void setGrupoAtencion(String grupoAtencion) {
        this.grupoAtencion = grupoAtencion;
    }   
   
    
    public String toString() {
       return "fechaInicio:"+ fechaInicio + 
               " fechaFinalizacion:"+ fechaFinalizacion+
               " horaInicial:"+ horaInicial+
               " horaFinal:"+horaFinal+
               " mesInicial:"+mesInicial+
               " mesFinal:"+mesFinal+
               " anioInicial:"+anioInicial+
               " anioFinal:"+anioFinal+
               " servicio:"+servicio+
               " provinciaOrigen:"+provinciaOrigen+
               " provinciaDestino:"+provinciaDestino+
               " valorLlamada:"+valorLlamada+
               " modoLlamada:"+modoLlamada+
               " perfilAtencion:"+perfilAtencion+
               " idiomaAtencion:"+idiomaAtencion+
               " tratamiento:"+tratamiento+
               " subSegmentoEntrada:"+subSegmentoEntrada+
               " segmentoEntrada:"+segmentoEntrada+
               " cod_encaminadorEntrada:"+cod_encaminadorEntrada+
               " subSegmentoSalida:"+subSegmentoSalida+
               " segmentoSalida:"+segmentoSalida+
               " cod_encaminadorSalida:"+cod_encaminadorSalida+
               " plataforma:"+plataforma+
               " territorioOrigen:"+territorioOrigen+
               " territorioDestino:"+territorioDestino+
               " oficinas:"+oficinas+              
               " puesto:"+puesto+
               " grupoAtencion:"+grupoAtencion+              
               " familia:"+familia+
               " agentes:"+agentes+
               " enrutamiento:"+enrutamiento+
               " nodoRed:"+nodoRed; 
    }
    
    /*public String toString() {
        return "FechaInicio: "+ fechaInicio+" fechaFinalizacion: "+fechaFinalizacion+
                " horaInicial: "+horaInicial+" horaFinal: "+horaFinal+
                " servicio: "+servicio+" provinciaOrigen: "+provinciaOrigen+
                " provinciaDestino: "+provinciaDestino+" grupoEspecialista: "+grupoEspecialista+               
                " territorio: "+territorio+" centroOperacion: "+centroOperacion+
                " tema: "+tema+" puesto: "+puesto+
                " tipoLlamada: "+tipoLlamada+" grupoAtencion: "+grupoAtencion+
                " elementoRed: "+elementoRed;
                 
    } */   
    
    public void setInforme(String informe){
        this.tipoInforme = informe;
    }
    
    public String getTipoInforme(){
        return this.tipoInforme;
    }
    
    public String getDesglose(){
        return this.desglose;
    }
    
    public void setDesglose(String desglose){
        this.desglose = desglose;
    }
    
    public void setFechaImpresion(String fechaImpresion){
        this.fechaImpresion = fechaImpresion;
    }
    
    public String getFechaImpresion(){
        return this.fechaImpresion;
    }    

    public void setTodoTramo(boolean todoTramo) {
        this.todoTramo = todoTramo;
    }

    public boolean isTodoTramo() {
        return todoTramo;
    }  

    public String getAnioFinal() {
        return anioFinal;
    }

    public String getAnioInicial() {
        return anioInicial;
    }

    public String getMesFinal() {
        return mesFinal;
    }

    public String getMesInicial() {
        return mesInicial;
    }

    public void setAnioFinal(String anioFinal) {
        this.anioFinal = anioFinal;
    }

    public void setAnioInicial(String anioInicial) {
        this.anioInicial = anioInicial;
    }

    public void setMesFinal(String mesFinal) {
        this.mesFinal = mesFinal;
    }

    public void setMesInicial(String mesInicial) {
        this.mesInicial = mesInicial;
    }

    public String getCod_encaminadorEntrada() {
        return cod_encaminadorEntrada;
    }

    public String getCod_encaminadorSalida() {
        return cod_encaminadorSalida;
    }

    public String getSegmentoEntrada() {
        return segmentoEntrada;
    }

    public String getSegmentoSalida() {
        return segmentoSalida;
    }

    public String getSubSegmentoEntrada() {
        return subSegmentoEntrada;
    }

    public String getSubSegmentoSalida() {
        return subSegmentoSalida;
    }

    public void setCod_encaminadorEntrada(String cod_encaminadorEntrada) {
        this.cod_encaminadorEntrada = cod_encaminadorEntrada;
    }

    public void setCod_encaminadorSalida(String cod_encaminadorSalida) {
        this.cod_encaminadorSalida = cod_encaminadorSalida;
    }

    public void setSegmentoEntrada(String segmentoEntrada) {
        this.segmentoEntrada = segmentoEntrada;
    }

    public void setSegmentoSalida(String segmentoSalida) {
        this.segmentoSalida = segmentoSalida;
    }

    public void setSubSegmentoEntrada(String subSegmentoEntrada) {
        this.subSegmentoEntrada = subSegmentoEntrada;
    }

    public void setSubSegmentoSalida(String subSegmentoSalida) {
        this.subSegmentoSalida = subSegmentoSalida;
    }

    public String getIdiomaAtencion() {
        return idiomaAtencion;
    }

    public String getModoLlamada() {
        return modoLlamada;
    }

    public String getPerfilAtencion() {
        return perfilAtencion;
    }

    public void setIdiomaAtencion(String idiomaAtencion) {
        this.idiomaAtencion = idiomaAtencion;
    }

    public void setModoLlamada(String modoLlamada) {
        this.modoLlamada = modoLlamada;
    }

    public void setPerfilAtencion(String perfilAtencion) {
        this.perfilAtencion = perfilAtencion;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getAgentes() {
        return agentes;
    }

    public void setAgentes(String agentes) {
        this.agentes = agentes;
    }

    public String getEnrutamiento() {
        return enrutamiento;
    }

    public void setEnrutamiento(String enrutamiento) {
        this.enrutamiento = enrutamiento;
    }

    public String getNodoRed() {
        return nodoRed;
    }

    public void setNodoRed(String nodoRed) {
        this.nodoRed = nodoRed;
    }

    public String getDiaFinal() {
        return diaFinal;
    }

    public String getDiaInicial() {
        return diaInicial;
    }

    public void setDiaFinal(String diaFinal) {
        this.diaFinal = diaFinal;
    }

    public void setDiaInicial(String diaInicial) {
        this.diaInicial = diaInicial;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getHoraInicialDescr() {
        return horaInicialDescr;
    }

    public String getHoraFinalDescr() {
        return horaFinalDescr;
    }

    public void setHoraFinalDescr(String horaFinalDescr) {
        this.horaFinalDescr = horaFinalDescr;
    }

    public void setHoraInicialDescr(String horaInicialDescr) {
        this.horaInicialDescr = horaInicialDescr;
    }
}
