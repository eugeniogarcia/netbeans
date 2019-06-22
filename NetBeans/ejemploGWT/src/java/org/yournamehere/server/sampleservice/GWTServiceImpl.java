/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.yournamehere.server.sampleservice;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.yournamehere.client.sampleservice.GWTService;

/**
 *
 * @author Leihta
 */
public class GWTServiceImpl extends RemoteServiceServlet implements GWTService {
   

    private Random randomizer = new Random();
    private static final long serialVersionUID = -15020842597334403L;
    private static List quotes = new ArrayList();
    static {
        quotes.add("No great thing is created suddenly - Epictetus");
        quotes.add("Well done is better than well said - Ben Franklin");
        quotes.add("No wind favors he who has no destined port - Montaigne");
        quotes.add("Sometimes even to live is an act of courage - Seneca");
        quotes.add("Know thyself - Socrates");
    }


    public String aleatorio(String mensaje) {
        return "Hola "+mensaje+" "+(String) quotes.get(randomizer.nextInt(4));
    }

        public boolean actualizaEmpleado(int ID) {
        try {
            new DB_Demo_Quries().query1(ID);
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }
        



}
