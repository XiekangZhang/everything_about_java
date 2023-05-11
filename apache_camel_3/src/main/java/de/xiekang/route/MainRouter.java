package de.xiekang.route;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;


public class MainRouter {
    public static void main(String[] args) {
        CamelContext context = new DefaultCamelContext();
        TestRouter router = new TestRouter();

        try {
            context.addRoutes(router);
            context.start();
            Thread.sleep(5 * 60 * 1000);
            context.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
