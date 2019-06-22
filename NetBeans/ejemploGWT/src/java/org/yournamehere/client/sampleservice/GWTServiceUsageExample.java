/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.yournamehere.client.sampleservice;

import com.google.gwt.core.client.GWT;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * Example class using the GWTService service.
 *
 * @author Leihta
 */
public class GWTServiceUsageExample extends VerticalPanel {
    private Label lblServerReply = new Label();
    private TextBox txtUserInput = new TextBox();
    private Button btnSend = new Button("Send to server");

    private Label lblServerReplySQL = new Label();
    private TextBox txtUserInputSQL = new TextBox();
    private Button btnSendSQL = new Button("Send to server");
    
    public GWTServiceUsageExample() {
        add(new Label("Input your text: "));
        add(txtUserInput);
        add(btnSend);
        add(lblServerReply);    
        
        add(new Label("Input your ID: "));
        add(txtUserInputSQL);
        add(btnSendSQL);
        add(lblServerReplySQL);

        // Create an asynchronous callback to handle the result.
        //EL callback es un template. Al template se le especifica el tipo de retorno del serviio
        final AsyncCallback<String> callback = new AsyncCallback<String>() {
            public void onSuccess(String result) {
                lblServerReply.setText(result);
            }

            public void onFailure(Throwable caught) {
                lblServerReply.setText("Communication failed");
            }
        };

        // Listen for the button clicks
        btnSend.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
                // Make remote call. Control flow will continue immediately and later
                // 'callback' will be invoked when the RPC completes.
                getService().aleatorio(txtUserInput.getText(), callback);
            }
        });
        
        // Create an asynchronous callback to handle the result.
        //EL callback es un template. Al template se le especifica el tipo de retorno del serviio
        final AsyncCallback<Boolean> callbackSQL = new AsyncCallback<Boolean>() {
            public void onSuccess(Boolean  result) {
                if(result)
                    lblServerReplySQL.setText("Actualizado");
                else
                    lblServerReplySQL.setText("NO Actualizado");
            }

            public void onFailure(Throwable caught) {
                lblServerReplySQL.setText("Communication failed");
            }
        };

        btnSendSQL.addClickHandler(new ClickHandler() {
           public void onClick(ClickEvent event) {
                // Make remote call. Control flow will continue immediately and later
                // 'callback' will be invoked when the RPC completes.
               lblServerReplySQL.setText("");
                getService().actualizaEmpleado(Integer.parseInt(txtUserInputSQL.getText()),callbackSQL);
            }
        });


    }
    
    public static GWTServiceAsync getService() {
        // Create the client proxy. Note that although you are creating the
        // service interface proper, you cast the result to the asynchronous
        // version of the interface. The cast is always safe because the
        // generated proxy implements the asynchronous interface automatically.

        return GWT.create(GWTService.class);
    }
}
