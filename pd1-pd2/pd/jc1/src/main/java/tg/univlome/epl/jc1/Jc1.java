/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package tg.univlome.epl.jc1;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSProducer;
import jakarta.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author robert-ubuntu
 */
public class Jc1 {

    public static void main(String[] args) throws NamingException {
        envoyerFile();
        envoyerTopic();
    }

    private static void envoyerFile() throws NamingException {
        InitialContext context = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) context.lookup("jms/cf1");
        Destination dest = (Destination) context.lookup("jms/file1");

        try (JMSContext jmsContext = cf.createContext("admin", "admin")) {
            JMSProducer producer = jmsContext.createProducer();
            TextMessage message = jmsContext.createTextMessage();
            message.setText("Cc bro , c'est mon premier message JMS dans file1");

            producer.send(dest, message);

        } catch (Exception e) {
        }
    }

    private static void envoyerTopic() throws NamingException {
        InitialContext context = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) context.lookup("jms/cf1");
        Destination dest = (Destination) context.lookup("jms/topic1");

        try (JMSContext jmsContext = cf.createContext("admin", "admin")) {
            JMSProducer producer = jmsContext.createProducer();
            TextMessage message = jmsContext.createTextMessage();
            message.setText("Cc bro , c'est mon premier message JMS dans topic1");

            producer.send(dest, message);

        } catch (Exception e) {
        }
    }
}
