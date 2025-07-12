/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.univlome.epl.jms.client1;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
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
public class JmsClientListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            this.envoyerFile();
            this.envoyerTopic();
        } catch (NamingException ex) {
            Logger.getLogger(JmsClientListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(JmsClientListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    
    private void envoyerFile() throws NamingException, JMSException {
        InitialContext context = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) context.lookup("jms/cf1");
        Destination dest = (Destination) context.lookup("jms/file1");
        
        try (JMSContext jmsContext = cf.createContext()) {
            JMSProducer producer = jmsContext.createProducer();
            TextMessage message = jmsContext.createTextMessage();
            message.setText("Bonjour, c'est mon premier message jms dans file1");
            producer.send(dest, message);
        }
    }
    
    private void envoyerTopic() throws NamingException, JMSException {
        InitialContext context = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) context.lookup("jms/cf1");
        Destination dest = (Destination) context.lookup("jms/topic1");
        
        try (JMSContext jmsContext = cf.createContext()) {
            JMSProducer producer = jmsContext.createProducer();
            TextMessage message = jmsContext.createTextMessage();
            message.setText("Bonjour, c'est mon premier message jms dans topic1");
            producer.send(dest, message);
        }
    }
    
}
