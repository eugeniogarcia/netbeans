/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miMDB;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;

/**
 *
 * @author Eugenio
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "miCola"),
    //@ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "durable"),
    @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "importeOrden > 4")
})
public class miMDB implements MessageListener {
    
    public miMDB() {
    }
    
    @Resource(lookup  = "miFactoriaConexionesTopic")
    private TopicConnectionFactory topicConnectionFactory;
    @Resource(lookup  = "miTopico")
    private Topic topico;
    
    @Override
    public void onMessage(Message message) {
        TextMessage msg = (TextMessage) message;
        try {
            System.out.println("Mensaje recibido: " + msg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        
        //Ahora envia por el topic
        try {
            Connection connection = topicConnectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(topico);

            // Sends a text message to the topic
            TextMessage respuesta = session.createTextMessage();
            respuesta.setText("Recibi un mensaje. "+msg.getText());
            producer.send(respuesta);
            respuesta.acknowledge();

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @PostConstruct
    private void initConnection() {
        System.out.println("######## Post Construct miMDB");
    }

    @PreDestroy
    private void closeConnection() {
        System.out.println("######## Pre Destroy miMDB");
    }
}
