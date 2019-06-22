<?xml version="1.0" encoding="UTF-8" ?>

<!--
    Document   : Grafica.xsl
    Created on : 8 de marzo de 2007, 18:35
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
        			<fo:block space-before.optimum='5pt' text-align='center' vertical-align='middle' line-height='13pt' font-family='sans-serif'
                                        font-weight='bold' font-size='16pt' color='black'  text-decoration='underline'>Servicio Ventanilla Unica</fo:block>
        			<fo:block space-before.optimum='10pt' text-align='center' vertical-align='middle' line-height='13pt' font-family='sans-serif'
                                        font-weight='bold' font-size='12pt' color='black'>
					Graficas Seguimiento Atencion
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
                    Página <fo:page-number/> de <fo:page-number-citation ref-id='last-page'/>
		</fo:block>        		
            </fo:static-content>
            <fo:flow flow-name='xsl-region-body'>               
                <fo:table margin-top='2cm' border-collapse='collapse' table-layout='fixed' text-align='center' width='100%'>
                    <fo:table-column column-width='proportional-column-width(1)'/>
                    <fo:table-column column-width='proportional-column-width(1)'/>
                    <fo:table-column column-width='proportional-column-width(1)'/>
                <!--Aqui viene la generacion del informe como tal-->
                <fo:table-body table-layout='fixed' border-width='1pt' border-style='solid'>
                    <xsl:apply-templates select="fila" />                    
                </fo:table-body> 
                </fo:table>  
            </fo:flow>
        </fo:page-sequence>
    </fo:root>       
    </xsl:template>
    
    <xsl:template match="fila">
        <fo:table-row>
            <xsl:apply-templates select="territorio"/>
        </fo:table-row>
    </xsl:template>
    
    <xsl:template match="territorio">                              
       <fo:table-cell padding-left='1mm' border-width='0.3pt' border-style='solid'>
        <fo:block text-align='center' line-height='9pt' font-family='sans-serif' padding-top='5pt' font-size='6pt' color='black'>
            <fo:external-graphic content-height="15mm" scaling="uniform"> 
                <xsl:attribute name="src">url('<xsl:value-of select="text()"/>')</xsl:attribute> 
            </fo:external-graphic>            
        </fo:block>               
        </fo:table-cell>        
    </xsl:template>
</xsl:stylesheet>
      
