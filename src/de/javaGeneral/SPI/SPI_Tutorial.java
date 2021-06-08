package de.javaGeneral.SPI;

import java.util.Arrays;
import java.util.Optional;
import java.util.ServiceLoader;

/**
 * The API: use to achieve a goal
 * The SPI: extend and implement to achieve a goal
 * <p>
 * Service Provider Interface: An interface or abstract class that defines the contract for the service provider
 * implementation classes
 * <p>
 * Service Providers: The implementation classes that actually provides the services
 * <p>
 * SPI Configuration File: A special file that provides the logic to look for the services implementations. The file
 * name must be present in the META-INF/services directory.
 * <p>
 * ServiceLoader: The Java SPI main class that is used to load the services for a service for a service provider
 * interface.
 */
public class SPI_Tutorial {
    public static void main(String[] args) {
        ServiceLoader<MessageServiceProvider> serviceProviders = ServiceLoader.load(MessageServiceProvider.class);
        for (MessageServiceProvider serviceProvider : serviceProviders) {
            serviceProvider.sendMessage("Hello");
        }

        serviceProviders.forEach(messageServiceProvider -> messageServiceProvider.sendMessage("Have a nice Day!"));

        System.out.println(Arrays.asList(serviceProviders).stream().count());
    }
}
