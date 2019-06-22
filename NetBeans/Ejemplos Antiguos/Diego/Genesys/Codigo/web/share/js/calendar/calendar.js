// {{{ docs <-- this is a VIM (text editor) text fold

/**
 * Popup Calendar v2.0.5
 *
 * Summary: Popup Calendar is a date selector script that can be associated with
 *          an image next to a text form element that requires a date. The calendar
 *          pops up, at which point a date can be selected, and it will close the
 *          calendar and pass the date down to the input field. It has customizable
 *          colors and full year/month navigation. It works on all browsers (Konqueror,
 *          IE, Netscape 4, Mozilla, Opera) and makes choosing dates in forms much more
 *          pleasant.
 *
 * Maintainer: Dan Allen <dan@mojavelinux.com>
 *
 * License: LGPL - however, if you use this library, please post to my forum where you
 *          use it so that I get a chance to see my baby in action.  If you are doing
 *          this for commercial work perhaps you could send me a few Starbucks Coffee
 *          gift dollars to encourage future developement (NOT REQUIRED).  E-mail me
 *          for and address.
 *
 * Homepage: http://www.mojavelinux.com/forum/viewtopic.php?t=6
 *
 * Freshmeat Project: http://freshmeat.net/projects/popupcalendar/?topic_id=92
 *
 * Updated: 2002/11/22
 *
 * Supported Browsers: Mozilla (Gecko), IE 5+, Konqueror, Opera 7, Netscape 4
 *
 * Usage: 
 * Bascially, you need to pay attention to the paths and make sure
 * that the function getCalendar is looking in the right place for calendar.html,
 * which is the parent frame of calendar_body.html.  
 * 
 * The colors are configured as an associative array in the parent window.  I
 * haven't had a chance to document this yet, but you should be able to see what I
 * am going for in the calendar.js file.  All you have to do when calling
 * getCalendar is specify the full object to that form element, such as
 * 
 * return getCalendar(document.formName.elementName);
 * 
 * You will need to put killCalendar() in the body to make it go away if it is still open
 * when the page changes.
**/

// }}}
// {{{ settings (Editable)

var calendarWindow = null;
var calendarColors = new Array();
calendarColors['bgColor'] = '#EFEFF7';
calendarColors['borderColor'] = '#00005A';
calendarColors['headerBgColor'] = '#00005A';
calendarColors['headerColor'] = '#C9C9E1';
calendarColors['dateBgColor'] = '#F4B44D';
calendarColors['dateColor'] = '#00005A';
calendarColors['dateHoverBgColor'] = '#FFFFFF';
calendarColors['dateHoverColor'] = '#C9C9E1';
var calendarMonths = new Array('Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre');
var calendarWeekdays = new Array('Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa', 'Do');
var calendarUseToday = true;
var calendarFechaAnterior = false;
var calendarFormat = 'd/m/y';
var calendarStartMonday = true;


// }}}
// {{{ getCalendar()

function getCalendar(docBase,in_dateField, dia, mes, anio, diaIni, mesIni, anioIni) 
{
    if (calendarWindow && !calendarWindow.closed) {
        alert('El calendario ya esta abierto...');
        try {
            calendarWindow.focus();
        }
        catch(e) {}
        
        return false;
    }		
	
    var cal_width = 215;
    var cal_height = 200;

    // IE needs less space to make this thing
    if ((document.all) && (navigator.userAgent.indexOf("Konqueror") == -1)) {
        cal_width = 210;
    }

    calendarTarget = in_dateField;
    //NUEVO CODIGO DIEGO
    calendarDiaTarget = dia;
    calendarMesTarget = mes;
    calendarAnioTarget = anio;    
    
    if (diaIni!=null && mesIni!=null && anioIni!=null){
    	calendarFechaAnterior = true;    	
    	calendarDiaIni = diaIni.value;
    	calendarMesIni = mesIni.value;
    	calendarAnioIni = anioIni.value;   	
    }
    
    
    //FIN NUEVO CODIGO DIEGO
    calendarWindow = window.open( docBase + '/calendar.html', 'dateSelectorPopup','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=0,dependent=no,width='+cal_width+',height='+cal_height);

    return false;
}

// }}}
// {{{ killCalendar()

function killCalendar() 
{		
    if (calendarWindow && !calendarWindow.closed) {
    		
        calendarWindow.close();
    }
}

// }}}
