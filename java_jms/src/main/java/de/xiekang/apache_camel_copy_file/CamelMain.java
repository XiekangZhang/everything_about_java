package de.xiekang.apache_camel_copy_file;

import de.xiekang.apache_camel_copy_file.route.FileRouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelMain {

    public static void main(String[] args) {
        CamelContext ctx = new DefaultCamelContext();
        FileRouteBuilder fileRouteBuilder = new FileRouteBuilder();

        try {
            ctx.addRoutes(fileRouteBuilder);
            ctx.start();
            Thread.sleep(5 * 60 * 1000);
            ctx.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
