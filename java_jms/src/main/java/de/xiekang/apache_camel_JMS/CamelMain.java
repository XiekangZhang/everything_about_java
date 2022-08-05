package de.xiekang.apache_camel_JMS;

import de.xiekang.apache_camel_JMS.route.JMSRouteBuilder;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.*;
import java.util.Enumeration;

public class CamelMain {

    // starting to count the number of messages in a queue
    // Variant 1 by using QueueBrowser
    // TODO: code with JMX
    public int getQueueSize(Session session, Queue queue) {
        int count = 0;
        try {
            QueueBrowser browser = session.createBrowser(queue);
            Enumeration elems = browser.getEnumeration();
            while (elems.hasMoreElements()) {
                elems.nextElement();
                count++;
            }
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    public static void main(String[] args) {
        // info: transfer message
        try (CamelContext ctx = new DefaultCamelContext()) {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
            // warning: setting acknowledge mode by runtime
            // info: give me a sign if you read it.
            ctx.addComponent("jmsComponent", JmsComponent.jmsComponentClientAcknowledge(connectionFactory));
            JMSRouteBuilder jmsRouteBuilder = new JMSRouteBuilder();
            ctx.addRoutes(jmsRouteBuilder);
            ctx.start();
            Thread.sleep(5 * 60 * 1000);
            ctx.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
