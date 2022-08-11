package de.xiekang.apache_camel_JMS.route;

import de.xiekang.apache_camel_JMS.bean.StartDatum;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
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
    StartDatum startDatum = new StartDatum();

    public static final long period = 1000;

    @Override
    public void configure() throws Exception {
        // info: Consumer
        from("zookeeper-master:localhost:timer:repeatedTimer?period=" + period + "&repeatCount=3&delay=0")
                .to("direct:getDate");

        from("direct:getDate")
                // todo: on possible to add try/exception?
                .pollEnrich("zookeeper://localhost:2181/lastRunDate?repeat=true&timeout=100", 100)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        try {

                            System.out.println("Starting get the date from existing persistent node");

                            byte[] bytes = (byte[]) exchange.getIn().getBody();
                            if (bytes == null || bytes.length == 0) {
                                startDatum.setStartDatum_HOST(0L);
                                startDatum.setStartDatum_GUEST(0L);
                            } else {
                                ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
                                startDatum = (StartDatum) objectInputStream.readObject();
                                Thread.sleep(10000);
                                startDatum.setStartDatum_GUEST(new Date().getTime());
                                startDatum.setStartDatum_HOST(new Date().getTime());
                            }

                            System.out.println(startDatum.toString());

                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                            objectOutputStream.writeObject(startDatum);
                            objectOutputStream.flush();
                            byte[] bytes1 = byteArrayOutputStream.toByteArray();
                            objectOutputStream.close();
                            exchange.getIn().setBody(bytes1);
                            /*byte[] b = (byte[]) exchange.getIn().getBody();
                            String body = b == null || b.length == 0 ? null : new String(b);
                            System.out.println("body: " + body);

                            List<String> startDatums = body == null ? null: Arrays.asList(body.split("\n"));
                            String startDatum_HOST = body == null ? null: startDatums.get(0).substring(startDatums.get(0).indexOf(":") + 1);
                            String startDatum_GUEST = body == null ? null: startDatums.get(1).substring(startDatums.get(1).indexOf(":") + 1);
                            System.out.println("The latest host job run date: " + startDatum_HOST);
                            System.out.println("The latest guest job run date: " + startDatum_GUEST);
                            // code for job running simulation
                            // Thread.sleep(10000);
                            // we update the date
                            startDatum_HOST = simpleDateFormat.format(new Date());
                            startDatum_GUEST = simpleDateFormat.format(new Date());
                            exchange.getIn().setBody("startDatum_HOST: " + startDatum_HOST + "\nstartDatum_GUEST: " + startDatum_GUEST);*/
                        } catch (Throwable t) {
                            t.printStackTrace();
                        }
                    }
                })
                .to("zookeeper://localhost:2181/lastRunDate?create=true&createMode=PERSISTENT");
    }
}
