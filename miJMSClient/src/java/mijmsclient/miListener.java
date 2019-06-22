/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mijmsclient;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author Eugenio
 */
public class miListener implements MessageListener{
    

   public void onMessage(Message message) {
        try {
            System.out.println("Mensaje recibido: " + ((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
