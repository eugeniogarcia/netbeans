/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

/**
 * Main entry point.
 *
 * @author Leihta
 */
public class MainEntryPoint implements EntryPoint {
    /** 
     * Creates a new instance of MainEntryPoint
     */
    public MainEntryPoint() {
    }

    /** 
     * The entry point method, called automatically by loading a module
     * that declares an implementing class as an entry-point
     */
    public void onModuleLoad() {
        final Label label = new Label("Hello, GWT!!!");
        label.setStyleName("miEtiqueta");
        final Button button = new Button("Click me!");
        final Button button1 = new Button("Abre la invocación del servicio!");
        final org.yournamehere.client.sampleservice.GWTServiceUsageExample panel=new org.yournamehere.client.sampleservice.GWTServiceUsageExample();

        HorizontalPanel hPanel = new HorizontalPanel();
        VerticalPanel vPanel = new VerticalPanel();

        button1.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                panel.setVisible(!panel.isVisible());
            }
        });


        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                label.setVisible(!label.isVisible());
            }
        });

        //Especificamos un panel horizontal
        RootPanel.get().add(hPanel);
        RootPanel.get().add(vPanel);
        panel.setVisible(false);
        hPanel.add(button);
        hPanel.add(label);
        //Y un panel vertical
        vPanel.add(button1);
        vPanel.add(panel);
    }
}
