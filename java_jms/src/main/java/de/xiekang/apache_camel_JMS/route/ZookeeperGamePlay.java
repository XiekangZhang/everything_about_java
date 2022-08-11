package de.xiekang.apache_camel_JMS.route;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * This script is about using ZooKeeper Master to control the running instance &
 * ZooKeeper Persistent Node to save the startDatum.
 * Acknowledge used: Client Acknowledge?
 *
 * @author xzhang
 */

/**
 * TODO: 1. Check why the message could not be read from pollenrich;
 * TODO: 2. How to create znode at runtime;
 * TODO: 3. Check the read function;
 * TODO: 4: Timer analysis
 */

public class ZookeeperGamePlay extends RouteBuilder {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss");

    public static final long period = 1000;

    @Override
    public void configure() throws Exception {
        // info: Consumer
        from("zookeeper-master:localhost:timer:repeatedTimer?period=" + period + "&repeatCount=-1&delay=0")
                .to("direct:getDate");

        from("direct:getDate")
                // todo: on possible to add try/exception?
                .pollEnrich("zookeeper://localhost:2181/lastRunDate?repeat=true&timeout=100", 100)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        try {

                            System.out.println("Starting get the date from existing persistent node");
                            System.out.println(exchange.getIn().getBody());
                            System.out.println(exchange.getIn().getHeaders());
                            byte[] b = (byte[]) exchange.getIn().getBody();
                            String body = b == null || b.length == 0 ? null : new String(b);
                            System.out.println("body: " + body);

                            String startDatum = body == null ? null : body.substring(body.indexOf(":"));
                            System.out.println("The latest job run date: " + startDatum);
                            // code for job running simulation
                            Thread.sleep(10000);
                            // we update the date
                            startDatum = simpleDateFormat.format(new Date());
                            System.out.println("startDatum: " + startDatum);
                            exchange.getIn().setBody("startDatum: " + startDatum);
                        } catch (Throwable t) {
                            t.printStackTrace();
                        }
                    }
                })
                .to("zookeeper://localhost:2181/lastRunDate?create=true&createMode=PERSISTENT");
    }
}
