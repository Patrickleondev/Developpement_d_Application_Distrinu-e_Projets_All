/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.univlome.epl.jms.client2;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.JMSProducer;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author jason-karl
 */
@WebListener
public class JmsClient2Listener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            this.lireFile();
            this.abonnerTopic();
        } catch (NamingException ex) {
            Logger.getLogger(JmsClient2Listener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(JmsClient2Listener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    
    private void lireFile() throws NamingException, JMSException {
        InitialContext context = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) context.lookup("jms/cf1");
        Destination dest = (Destination) context.lookup("jms/file1");
        
        try (JMSContext jmsContext = cf.createContext()) {
            JMSConsumer consumer = jmsContext.createConsumer(dest);
            Message message = consumer.receive();
            System.out.println("Message lu : " + message.getBody(String.class));
        }
        
    }
    
    private void abonnerTopic() throws NamingException, JMSException {
        InitialContext context = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) context.lookup("jms/cf1");
        Destination dest = (Destination) context.lookup("jms/topic1");
        
        try (JMSContext jmsContext = cf.createContext()) {
            JMSConsumer consumer = jmsContext.createConsumer(dest);
            Message message = consumer.receive();
            System.out.println("Message lu : " + message.getBody(String.class));
        }
    }
    
}
