package de.xiekang.apache_camel_JMS.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class JMSRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //initialize Timer
        //from("jmsComponent:queue:xiekang-input")
        from("timer://testTimer?period=2000")
                //msg = null
                .pollEnrich("jmsComponent:queue:xiekang-input", 100)
                //msg = [msg]
                .choice()
                    .when(simple("${header.startDatum} == null && ${header.type} == null && ${header.DI_Job} == null"))
                        .setHeader("startDatum", constant("")) // info: startDatum will be updated after DI Job (latest successful run)
                        .setHeader("type", constant("bbbbb")) // info: acknowledge setting at runtime
                        .setHeader("DI_Job", constant("jobs")) // info: [quest job, host job, sync job]
                        .process(exchange -> System.out.println("Message Created!"))
                        .to("jmsComponent:queue:xiekang-input").endChoice()
                    .otherwise()
                .process(exchange -> System.out.println("Message found, putting message back!"))
                        .to("jmsComponent:queue:xiekang-input");
    }
}
