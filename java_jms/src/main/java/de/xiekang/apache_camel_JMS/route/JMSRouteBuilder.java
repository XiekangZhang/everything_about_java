package de.xiekang.apache_camel_JMS.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class JMSRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jmsComponent:queue:xiekang-input").
                process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println(exchange.getIn().getBody());
                    }
                }).to("jmsComponent:queue:xiekang-output");
    }
}
