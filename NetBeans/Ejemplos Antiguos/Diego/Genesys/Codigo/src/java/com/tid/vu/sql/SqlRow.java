package com.tid.vu.sql;

import java.math.BigDecimal;


public class SqlRow{
    
	protected	Object[]	pField = null;
	protected	int		nField = 0;
/**
 * Comentario de constructor SqlRow.
 */
public SqlRow(Object [] fields) 
{
	super();
	if (fields == null || fields.length==0) return;

	nField = fields.length;
	pField = new Object[nField];
	for (int i=0;i<nField;i++)
	{
		pField[i] = fields[i];
	}
	return;
}

/**
 * Obtiene campo i-ésimo
 * Fecha de creación: (10/07/2001 9:28:30)
 */
public Object getField(int i)
{
	if (i<nField && i>=0){
		if (pField[i]!=null && pField[i].getClass().equals(BigDecimal.class)){
			return new Long(((BigDecimal)pField[i]).longValue());
		}else{
			return pField[i]; 
		}
	}
	else					return null;
}

/**
 * Obtiene campo i-ésimo
 * Fecha de creación: (10/07/2001 9:28:30)
 */
public Object getFieldBigDec(int i)
{
	if (i<nField && i>=0){
			return pField[i];
	}
	else					return null;
}
/**
 * Devuelve tamaño de la fila (número de campos)
 * Fecha de creación: (10/07/2001 9:49:44)
 */
public int getSize()
{
	return nField;
}
}
