package de.xiekang.apache_camel_copy_file.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
/*
    info: Apache Camel for creating a route --> override configure from RouteBuilder
 */

// warning: camel context --> a runtime system to run and manage the routes
public class FileRouteBuilder extends RouteBuilder{
    @Override
    public void configure() throws Exception {
        from("file:/Users/xiekangzhang/Documents/CamelFile/input")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                    }
                })
                .to("file:/Users/xiekangzhang/Documents/CamelFile/output");
    }
}
