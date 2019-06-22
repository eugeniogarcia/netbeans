/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invocaejb;

import ejemploEJB.miEJBRemote;
import ejemploEJB.miLibro;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author e.garcia.san.martin
 */
public class InvocaEJB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NamingException {
        Context jndiContext = new InitialContext();
        ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/javaee6/ConnectionFactory");
        Queue queue = (Queue) jndiContext.lookup("jms/javaee6/Queue");

        try {

            // Creates the needed artifacts to connect to the queue
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);
            

            // Sends a text message to the queue
            TextMessage message = session.createTextMessage();
            message.setText("This is a text message sent at " + new Date());
            producer.send(message);
            System.out.println("\nMessage sent !");

            message.setText("This is a new text message sent at " + new Date());
            producer.send(message);
            System.out.println("\nMessage sent !");

            
            message.setText("And the final text message sent at " + new Date());
            producer.send(message);
            System.out.println("\nMessage sent !");

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Creates the needed artifacts to connect to the queue
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(queue);
            consumer.setMessageListener(new Listener());

            connection.start();

            /*
            // Loops to receive the messages
            System.out.println("\nInfinite loop. Waiting for a message...");
            while (true) {
                TextMessage message = (TextMessage) consumer.receive();
                System.out.println("Message received: " + message.getText());
            }
            */

            // Creates the needed artifacts to connect to the queue
            Connection connectionS = connectionFactory.createConnection();
            Session sessionS = connectionS.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = sessionS.createProducer(queue);
            

            // Sends a text message to the queue
            TextMessage messageS = sessionS.createTextMessage();
            messageS.setText("El mensaje se envió a las " + new Date());
            producer.send(messageS);
            System.out.println("\nMessage sent !");
            connectionS.close();
            
            Thread.sleep(10000);    
            
            connection.close();
           

        } catch (Exception e) {
            e.printStackTrace();
        }
 
        try
        {
        Properties props = new Properties();

        
//        props.setProperty("java.naming.factory.initial","com.sun.enterprise.naming.SerialInitContextFactory"); //Por defecto se inicializa una naming facotory (si la VM es J2EE)
//        props.setProperty("java.naming.factory.url.pkgs","com.sun.enterprise.naming");
//        props.setProperty("java.naming.factory.state","com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
//        props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost"); // Valores por defecto
//        props.setProperty("org.omg.CORBA.ORBInitialPort", "3700"); // Valores por defecto
//        InitialContext context = new InitialContext(props);
        
        InitialContext context = new InitialContext();

        miEJBRemote miEJB=null;
        String respuesta;
        try
        {
            miEJB=(miEJBRemote) context.lookup("java:global/miEJB/miEJB!ejemploEJB.miEJBRemote");
            respuesta=miEJB.holaAmigo("Eugenio");
        }
        catch(Exception ex)
        {
            System.out.print(ex.getMessage());
        }
        
        System.out.println("Crea tres libros");
        miLibro libro=new miLibro();
        libro.setDescripcion("descripción1");
        libro.setIsbn("descripción1");
        libro.setPrecio(100F);
        libro.setTitulo("titulo1");
        miEJB.creaLibro(libro);
        libro.setDescripcion("descripción2");
        libro.setIsbn("descripción2");
        libro.setPrecio(200F);
        libro.setTitulo("titulo2");
        miEJB.creaLibro(libro);
        libro.setDescripcion("descripción22");
        libro.setIsbn("descripción2");
        libro.setPrecio(220F);
        libro.setTitulo("titulo22");
        miEJB.creaLibro(libro);
        
        System.out.println("Busca el libro con descripocion descripcion2");
        miLibro libro1= miEJB.encuentraLibro("descripción2");
        System.out.println(libro1.toString());
        
        System.out.println("Recupera todos los libros");
        List<miLibro> lista= miEJB.recuperaLibros();
        
        for(miLibro lib:lista)
        {
            System.out.println(lib.toString());
        }

        System.out.println("Borra el libro con descripocion descripcion2");
        miEJB.borraLibro("descripción2");
        
        System.out.println("Recupera todos los libros");
        lista= miEJB.recuperaLibros();

        for(miLibro lib:lista)
        {
            System.out.println(lib.toString());
        }
        }
        catch(Exception ex)
        {
            System.out.print(ex.getMessage());
        }
    }
    
   
    public static class Listener implements MessageListener {


    public void onMessage(Message message) {
        try {
            System.out.println("Mensaje recibido: " + ((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    }
}

