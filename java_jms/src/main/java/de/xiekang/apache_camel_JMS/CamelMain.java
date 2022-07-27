package de.xiekang.apache_camel_JMS;

import de.xiekang.apache_camel_JMS.route.JMSRouteBuilder;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;

public class CamelMain {

    public static void main(String[] args) {
        CamelContext ctx = new DefaultCamelContext();
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        ctx.addComponent("jmsComponent", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        JMSRouteBuilder jmsRouteBuilder = new JMSRouteBuilder();

        try {
            ctx.addRoutes(jmsRouteBuilder);
            ctx.start();
            Thread.sleep(5 * 60 * 1000);
            ctx.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
