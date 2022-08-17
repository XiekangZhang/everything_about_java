package de.xiekang.karaf_tutorial;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class HelloWorld implements BundleActivator {
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Hello World!");
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Goodbye World!");
    }
}
