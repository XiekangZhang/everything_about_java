package de.xiekang.apache_camel_JMS.route;

import de.xiekang.apache_camel_JMS.WhitespotAggregationStrategy;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.zookeepermaster.policy.MasterRoutePolicy;

import java.util.Date;

public class JMSRouteBuilder extends RouteBuilder {
    WhitespotAggregationStrategy whitespotAggregationStrategy = new WhitespotAggregationStrategy();

    @Override
    public void configure() throws Exception {
        //MasterRoutePolicy masterRoutePolicy = new MasterRoutePolicy();
       // from("zookeeper://localhost:2181/test")
        from("zookeeper-master:localhost:jmsComponent:queue:xiekang-input")
                //.setHeader("name", constant("this is only a test"))
                //.setHeader("time", constant(new Date()))
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Thread.sleep(10000);
                        System.err.println("I am active!");
                        System.err.println(exchange.getIn().getBody(String.class));
                    }
                })
                .log( String.valueOf(simple("${in.body}")));
        /*//initialize Timer
        from("timer://testTimer?period=2000&repeatCount=5")
                //msg = null
                .pollEnrich("jmsComponent:queue:xiekang-input?acknowledgementModeName=CLIENT_ACKNOWLEDGE", 100)
                .setHeader("type", constant("test1"))
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.err.println(new Date());
                    }
                })
                .to("jmsComponent:queue:xiekang-input");*/
                /*.choice()
                    .when(simple("${header.startDatum} == null && ${header.type} == null && ${header.DI_Job} == null"))
                        .setHeader("startDatum", constant("")) // info: startDatum will be updated after DI Job (latest successful run)
                        .setHeader("type", constant("bbbbb")) // info: acknowledge setting at runtime
                        .setHeader("DI_Job", constant("jobs")) // info: [quest job, host job, sync job]
                        .process(exchange -> System.out.println("Message Created!"))
                        .to("jmsComponent:queue:xiekang-input").endChoice()
                    .otherwise()
                .process(exchange -> System.out.println("Message found, putting message back!"))
                        .to("jmsComponent:queue:xiekang-input");*/
    }
}
