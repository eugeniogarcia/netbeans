package org.beginningee6.book.chapter13.jms.ex01;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Date;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 6 with Glassfish
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
public class Sender {

    // ======================================
    // =           Public Methods           =
    // ======================================

    public static void main(String[] args) {

        try {
            // Gets the JNDI context
            Context jndiContext = new InitialContext();

            // Looks up the administered objects
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("miFactoriaConexionesGenerica");
            Queue queue = (Queue) jndiContext.lookup("miCola");

            // Creates the needed artifacts to connect to the queue
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);

            // Sends a text message to the queue
            TextMessage message = session.createTextMessage();
            message.setText("Este es un mensaje de texto enviado a las " + new Date());
            producer.send(message);
            System.out.println("\nMensaje enviado !");

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}