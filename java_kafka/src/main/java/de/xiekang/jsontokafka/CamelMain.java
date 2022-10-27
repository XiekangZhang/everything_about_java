package de.xiekang.jsontokafka;

import de.xiekang.jsontokafka.route.JSONRouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelMain {
    public static void main(String[] args) {
        CamelContext ctx = new DefaultCamelContext();
        JSONRouteBuilder jsonRouteBuilder = new JSONRouteBuilder();

        try {
            ctx.addRoutes(jsonRouteBuilder);
            ctx.start();
            Thread.sleep(24 * 60 * 60 * 1000);
            ctx.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
