function sectionClick(sender) {
    var section = sender.getAttribute('name');    
    var open = (sender.open == 'true');    
    /*document.getElementById(section).style.display = (open ? 'none' : 'list-item');
    document.getElementById(section).style.listStyle = 'none outside';    */
    /*document.getElementById(section + '_img').src = '/vu/img/' + 
                                                    (open ? 'open' : 'close') + 
                                                    '_section.gif';*/
    sender.open = (open ? 'false' : 'true');

}

function expandMenuSection(sectionId) {    
    var index = 0;
    if(document.createEvent)
        index = 1;
    var section = document.getElementById(sectionId);        
    sectionClick(section);    
    var parentSection = section.parentNode.parentNode.parentNode;
    if(parentSection && parentSection.className == 'menuSection') {
        expandMenuSection(parentSection.childNodes[index].getAttribute('id'));
    }
}

function cambiarSeleccionado(sectionId) {    
    var section = document.getElementById(sectionId);    
    section.style.cssText = 'color: 000099; background-image: url(/vu/img/punto_act.gif);FONT-WEIGHT: bold;';

}
