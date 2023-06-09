package de.xiekang;

import de.xiekang.route.AppsoluteRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class AppsoluteRouteRun {
    public static void main(String[] args) {
        CamelContext context = new DefaultCamelContext();
        AppsoluteRoute appsoluteRoute = new AppsoluteRoute();

        try {
            context.addRoutes(appsoluteRoute);
            context.start();
            Thread.sleep(5 * 60 * 1000);
            context.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
