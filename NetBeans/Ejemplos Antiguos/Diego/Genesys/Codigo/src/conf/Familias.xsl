<?xml version="1.0" encoding="UTF-8" ?>

<!--
    Document   : Familias.xsl
    Created on : 7 de marzo de 2007, 12:24
    Author     : t610908
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output method="html"/>  
    <xsl:template match="datosTabla">
        <fo:root xmlns:fo='http://www.w3.org/1999/XSL/Format'>
 	<fo:layout-master-set>
   	<fo:simple-page-master master-name='A4-landscape' margin-right='0.5cm' margin-left='0.5cm' margin-bottom='1cm' margin-top='1cm' page-height="21cm" page-width="29.7cm">
            <fo:region-before display-align='after' region-name='header' extent='25mm' margin-right='5mm' margin-left='0.5cm'/>
            <fo:region-after region-name='footer' extent='15mm'/>
            <fo:region-start extent='8mm'/>
            <fo:region-end extent='8mm'/>
            <fo:region-body margin-top='25mm' margin-left='0.1cm' margin-right='5mm' margin-bottom='10mm'/>
        </fo:simple-page-master>
        </fo:layout-master-set>
        <fo:page-sequence initial-page-number='1' master-reference='A4-landscape'>
            <fo:static-content flow-name='header'>
                <fo:block space-before.optimum='2pt' text-align='right' line-height='13pt' font-family='sans-serif' font-weight='normal' font-size='7pt' font-style='italic' color='black'>
                    Fecha de impresión: <xsl:value-of select="fechaImpresion"/>
                </fo:block>
                <fo:table margin-right='2in div 12' table-layout='fixed' width='100%'>
                    <fo:table-column column-width='proportional-column-width(1)'/>
                    <fo:table-column column-width='proportional-column-width(2)'/>
                    <fo:table-column column-width='proportional-column-width(1)'/>
                    <fo:table-body border-width='1pt' border-style='solid'>
                        <fo:table-row>
                            <fo:table-cell display-align="center">
        			<fo:block text-align='left'>
                            	<fo:external-graphic content-height="15mm" scaling="uniform"> 
                                    <xsl:attribute name="src">url('<xsl:value-of select="imagen1"/>')</xsl:attribute> 
                                </fo:external-graphic>
        			</fo:block>
                            </fo:table-cell>
                            <fo:table-cell display-align="center">
        			<fo:block space-before.optimum='10pt' text-align='center' vertical-align='middle' line-height='13pt' font-family='sans-serif'
                                        font-weight='bold' font-size='24pt' color='black'>
					Informe de Familias
        			</fo:block>
                            </fo:table-cell>
                            <fo:table-cell display-align="center">
        			<fo:block text-align='right'>
				<fo:external-graphic content-height="15mm" scaling="uniform"> 
                                    <xsl:attribute name="src">url('<xsl:value-of select="imagen2"/>')</xsl:attribute> 
                                </fo:external-graphic>
        			</fo:block>
                            </fo:table-cell>
        		</fo:table-row>
                    </fo:table-body>
                 </fo:table>
            </fo:static-content>
            <fo:static-content flow-name='footer'>
                <fo:block line-height='8pt' font-size='7pt' text-align='end' font-style='italic'>
                    Página <fo:page-number/> de <fo:page-number-citation ref-id="last-page"/>
		</fo:block>        		
            </fo:static-content>
            <fo:flow flow-name='xsl-region-body'>
                <!--Para los parametros de seleccion-->
                <fo:table margin-right='2in div 12' table-layout='fixed' width='100%' space-after.optimum='0.8cm'>
        		<fo:table-column column-width='proportional-column-width(0.5)'/>
                        <fo:table-column column-width='proportional-column-width(1)'/>
        		<fo:table-column column-width='proportional-column-width(1)'/>
                        <fo:table-column column-width='proportional-column-width(1)'/>
                        <fo:table-column column-width='proportional-column-width(1)'/>
                        <fo:table-column column-width='proportional-column-width(1.5)'/>
                    <fo:table-body>
                        <xsl:apply-templates select="seleccion/filaSeleccion" />
                    </fo:table-body> 
                </fo:table>
                <!--Fin parametros de seleccion-->
                <fo:table margin-top='2cm' border-collapse='collapse' table-layout='fixed' text-align='center' width='100%'>
        	<fo:table-column column-width='1.8cm'/>        			
                <fo:table-column column-width='1.0cm'/>
                <fo:table-column column-width='1.0cm'/>
                <fo:table-column column-width='1.0cm'/>
                <fo:table-column column-width='1.0cm'/>
                <fo:table-column column-width='1.0cm'/>
                <fo:table-column column-width='1.0cm'/>
                <fo:table-column column-width='1.0cm'/>
                <fo:table-column column-width='1.0cm'/>
                <fo:table-column column-width='1.0cm'/>
                <fo:table-column column-width='1.0cm'/>
                <fo:table-column column-width='1.0cm'/>
                <fo:table-column column-width='1.0cm'/>
                <fo:table-column column-width='1.0cm'/>
                <fo:table-column column-width='1.0cm'/>
                <fo:table-column column-width='1.0cm'/>
                <fo:table-column column-width='1.0cm'/>
                <fo:table-column column-width='1.5cm'/>
                <fo:table-column column-width='1.5cm'/>
                <fo:table-column column-width='1.5cm'/>
                <fo:table-column column-width='1.5cm'/>
                <!--Cabecera de la tabla, esto cambia con cada informe-->
                <fo:table-header background-color='#e6e6e6'>
                    <fo:table-row border-start-style='solid' border-end-style='solid' border-before-style='solid' border-width='0.3pt'>
                        <fo:table-cell background-color='#ffffff'>
        		</fo:table-cell>	
        		<fo:table-cell border-style='solid' border-width='1pt' number-columns-spanned='10' display-align="center">
                            <fo:block text-align='center' line-height='13pt' font-family='sans-serif' font-weight='bold' font-size='7pt' color='black'>
                                 INTERVENCIONES AGENTE
                            </fo:block>
                        </fo:table-cell>
        		<fo:table-cell border-style='solid' border-width='1pt' number-columns-spanned='6' display-align="center">
                            <fo:block text-align='center' line-height='13pt' font-family='sans-serif' font-weight='bold' font-size='7pt' color='black'>
                                 TIEMPO DE ATENCIÓN
                            </fo:block>
        		</fo:table-cell>
        		<fo:table-cell border-style='solid' border-width='1pt' number-rows-spanned='3' display-align="center">
                            <fo:block text-align='center' line-height='13pt' font-family='sans-serif' font-weight='bold' font-size='7pt' color='black'>
                                Tiempo de reposo
                            </fo:block>
        		</fo:table-cell>
        		<fo:table-cell border-style='solid' border-width='1pt' number-rows-spanned='3' display-align="center">
                            <fo:block text-align='center' line-height='13pt' font-family='sans-serif' font-weight='bold' font-size='7pt' color='black'>
                                 Tiempo efectivo de conexión
                            </fo:block>
        		</fo:table-cell>
        		<fo:table-cell border-style='solid' border-width='1pt' number-rows-spanned='3' display-align="center">
                            <fo:block text-align='center' line-height='13pt' font-family='sans-serif' font-weight='bold' font-size='7pt' color='black'>
                                Tiempo de pausa
                            </fo:block>
        		</fo:table-cell>
        		<fo:table-cell border-style='solid' border-width='1pt' number-rows-spanned='3' display-align="center">
                            <fo:block text-align='center' line-height='13pt' font-family='sans-serif' font-weight='bold' font-size='7pt' color='black'>
                                Número de pausas
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                    <fo:table-row border-start-style='solid' border-end-style='solid' border-before-style='solid' border-width='0.3pt'>
                        <fo:table-cell border-style='solid' display-align="center" border-width='1pt' number-rows-spanned='2'>
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' text-decoration='underline' font-weight='bold' font-size='7pt' color='black'>
                                <xsl:value-of select="desglose"/>
                            </fo:block>
                        </fo:table-cell>
        		<fo:table-cell border-style='solid' display-align="center" border-width='1pt' number-columns-spanned='5'>
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                                Asignaciones automáticas
                            </fo:block>
        		</fo:table-cell> 
        		<fo:table-cell border-style='solid' display-align="center" border-width='1pt' number-columns-spanned='5'>
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                                Asignaciones de agente
                            </fo:block>
			</fo:table-cell>
			<fo:table-cell border-style='solid' display-align="center" border-width='1pt' number-columns-spanned='3'>
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                                Asignaciones automáticas
                            </fo:block>
			</fo:table-cell>
			<fo:table-cell border-style='solid' display-align="center" border-width='1pt' number-columns-spanned='3'>
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                                Asignaciones de agente
                            </fo:block>
			</fo:table-cell>
                     </fo:table-row>
                     <fo:table-row border-start-style='solid' border-end-style='solid' border-before-style='solid' border-width='0.3pt'>
                        <fo:table-cell border-style='solid' display-align="center" border-width='1pt' >
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                                Recib
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border-style='solid' display-align="center" border-width='1pt'>
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                                Atend
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border-style='solid' display-align="center" border-width='1pt'>
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                                Transf
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border-style='solid' display-align="center" border-width='1pt'>
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                                Difer
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border-style='solid' display-align="center" border-width='1pt'>
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                                Lib Op
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border-style='solid' display-align="center" border-width='1pt'>
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                                Recib
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border-style='solid' display-align="center" border-width='1pt'>
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                                Atend
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border-style='solid' display-align="center" border-width='1pt'>
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                                Transf
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border-style='solid' display-align="center" border-width='1pt'>
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                                Difer
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border-style='solid' display-align="center" border-width='1pt'>
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                                Lib Op
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border-style='solid' display-align="center" border-width='1pt'>
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                                Total
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border-style='solid' display-align="center" border-width='1pt'>
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                                Medio(s)
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border-style='solid' display-align="center" border-width='1pt'>
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                                Maximo
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border-style='solid' display-align="center" border-width='1pt'>
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                                Total
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border-style='solid' display-align="center" border-width='1pt'>
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                                Medio(s)
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border-style='solid' display-align="center" border-width='1pt'>
                            <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                                Maximo
                            </fo:block>
                        </fo:table-cell>
                        </fo:table-row>   
                    </fo:table-header>
                    <!--Fin cabecera, el resto del documento es igual para todos los informes-->
                    <!--Para el pie de la tabla, los totales-->
                    <fo:table-footer border-style='solid' border-width='1pt' >
                         <xsl:apply-templates select="tabla/total" />
                    </fo:table-footer>
                    <!--Aqui viene la generacion del informe como tal-->                    
                    <fo:table-body table-layout='fixed' border-left-width='1pt' border-left-style='solid'  border-right-width='1pt' border-right-style='solid'
                            border-top-width='1pt' border-top-style='solid'>
                        <xsl:apply-templates select="tabla/fila" />
                    </fo:table-body>                    
                  </fo:table>               
                </fo:flow>
            </fo:page-sequence>
         </fo:root>
      </xsl:template>
      
      <!--Template para la seleccion de parametros elegidos para el informe-->
      <xsl:template match="filaSeleccion">
         <fo:table-row>
             <xsl:apply-templates select="parametro"/>
         </fo:table-row>
      </xsl:template>
      
      <xsl:template match="parametro">
          <fo:table-cell></fo:table-cell>
          <fo:table-cell display-align="center">
            <fo:block space-before.optimum='2pt' text-align='right' line-height='9pt' 
                        font-family='sans-serif' font-weight='bold' font-size='7pt' color='black'>
               <xsl:value-of select="nombre"/>
            </fo:block>
          </fo:table-cell>          
          <fo:table-cell display-align="center">
            <fo:block space-before.optimum='2pt' text-align='left' line-height='9pt' 
        		font-family='sans-serif' font-weight='normal' font-size='7pt' color='black'>
               <xsl:value-of select="valor"/>
            </fo:block>
          </fo:table-cell>
      </xsl:template>
      <!--Fin de template-->      
      <xsl:template match="fila">                    
         <fo:table-row> 
            <xsl:apply-templates select="columna"/>
         </fo:table-row>
      </xsl:template>
      
      <xsl:template match="total">
          <fo:table-row> 
            <xsl:apply-templates select="columna"/>
         </fo:table-row>
      </xsl:template>
      
      <xsl:template match="columna">
          <fo:table-cell display-align="center" border-left-width='0.3pt' border-left-style='solid'>
               <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
                    <xsl:value-of select="text()"/>
               </fo:block>               
           </fo:table-cell>
      </xsl:template>      
 </xsl:stylesheet>