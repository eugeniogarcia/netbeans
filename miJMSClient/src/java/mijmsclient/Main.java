/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mijmsclient;

import java.util.Date;
import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author Eugenio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Properties props = new Properties();
            props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
            props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            //Direccion del servicio de directorio
            props.setProperty("org.omg.CORBA.ORBInitialPort","3700");
            props.setProperty("org.omg.CORBA.ORBInitialHost","localhost");
        
            Context jndiContext = new InitialContext(props);

            // Looks up the administered objects
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("miFactoriaConexionesColas");
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
            
            String body;
            message = session.createTextMessage();
            for (int i = 0; i < 10; i++) {
                message.setIntProperty("importeOrden", i);
                body = "Este es un mensaje con importeOrden "  + i;
                System.out.println(body);
                message.setText(body);
                producer.send(message);
                message.acknowledge();
            }
            
            connection.close();
            
            //Ahora escucha
            TopicConnectionFactory topicoConnectionFactory = (TopicConnectionFactory) jndiContext.lookup("miFactoriaConexionesTopic");
            Topic topico = (Topic) jndiContext.lookup("miTopico");
            connection = topicoConnectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //String selector = "importeOrden between 3 and 5";
            //MessageConsumer consumer = session.createConsumer(topico, selector);
            MessageConsumer consumer = session.createConsumer(topico);
            consumer.setMessageListener(new miListener());
            connection.start();
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}
