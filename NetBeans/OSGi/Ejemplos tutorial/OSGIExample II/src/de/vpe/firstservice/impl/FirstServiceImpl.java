/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.vpe.firstservice.impl;

//Paquete con la interfaz del Servicio
import de.vpe.firstservice.FirstService;
//Para manejar y formatear fechas
import java.util.Date;
import java.text.DateFormat;

/**
 *
 * @author Leihta
 */
public class FirstServiceImpl implements FirstService {

    public String getFormattedDate(Date date) {
        return DateFormat.getDateInstance(DateFormat.SHORT).format(date);

    }


}
