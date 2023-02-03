package de.xiekang.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

class TestRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                    }
                })
                .toD("mock:${header.name}");
    }
}