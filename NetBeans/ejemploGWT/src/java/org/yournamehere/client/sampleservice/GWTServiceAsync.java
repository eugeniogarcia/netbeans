/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.yournamehere.client.sampleservice;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author Leihta
 */
public interface GWTServiceAsync {
    //El retorno es void
    //El callback proporcionará el tipo de retorno. Cuando se crea el callback se utiliza un template que admite el tipo de retorno como argumento
    public void aleatorio(String mensaje,AsyncCallback<String> callback);
    public void actualizaEmpleado(int ID, AsyncCallback<java.lang.Boolean> asyncCallback);
}
