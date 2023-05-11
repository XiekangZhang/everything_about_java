package de.xiekang;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.stream.IntStream;

public class log4jexample2 {
    private static final Logger logger = (Logger) LogManager.getLogger(log4jexample2.class);

    public static void main(String[] args) {
        IntStream.range(0, 5).forEach(i -> {
            Runnable runnable = () -> logger.info("Logging info");
            Thread thread = new Thread(runnable);
            thread.start();
        });

    }
}
