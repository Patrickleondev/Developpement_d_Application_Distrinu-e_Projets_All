/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tg.univlome.epl.jc2;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author robert-ubuntu
 */
public class Jc2 {

    public static void main(String[] args) throws NamingException, JMSException {
        lireFile();
        abonnerTopic();
    }
    
    private static void lireFile() throws NamingException, JMSException {
        InitialContext context = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) context.lookup("jms/cf1");
        Destination dest = (Destination) context.lookup("jms/file1");

        try (JMSContext jmsContext = cf.createContext("admin", "admin")) {
            JMSConsumer consomer = jmsContext.createConsumer(dest);
            Message message = consomer.receive();
            System.out.println(" Message lu :" + message.getBody(String.class));
        }
    }

    private static void abonnerTopic() throws NamingException, JMSException {
        InitialContext context = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) context.lookup("jms/cf1");
        Destination dest = (Destination) context.lookup("jms/topic1");

        try (JMSContext jmsContext = cf.createContext("admin", "admin")) {
            JMSConsumer consomer = jmsContext.createConsumer(dest);
            Message message = consomer.receive();
            System.out.println(" Message lu :" + message.getBody(String.class));
        }
    }
}
