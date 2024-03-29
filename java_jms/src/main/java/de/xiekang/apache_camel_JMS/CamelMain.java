package de.xiekang.apache_camel_JMS;

import de.xiekang.apache_camel_JMS.route.ZookeeperGamePlay;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;
public class CamelMain {

    // starting to count the number of messages in a queue
    // Variant 1 by using QueueBrowser
    // TODO: code with JMX

    public static void main(String[] args) {
        // info: transfer message
        try  {
            CamelContext ctx = new DefaultCamelContext();
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
            // warning: setting acknowledge mode by runtime
            // info: give me a sign if you read it.

            // ctx.addComponent("jmsComponent", JmsComponent.jmsComponentClientAcknowledge(connectionFactory));
            //JMSRouteBuilder jmsRouteBuilder = new JMSRouteBuilder();
            ZookeeperGamePlay zookeeperGamePlay = new ZookeeperGamePlay();
            ctx.addRoutes(zookeeperGamePlay);
            ctx.start();
            Thread.sleep(10 * 60 * 1000);
            ctx.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
