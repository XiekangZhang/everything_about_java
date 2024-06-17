package de.xiekang.log4j.official;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.util.HashMap;
import java.util.Map;

public class MyApp {
    private static final Logger logger = LogManager.getLogger(MyApp.class.getName());

    public static void main(final String... args) {
        // Set up a simple configuration that logs on the console.
        ThreadContext.put("_projectName", "INTEGRATIONLAYER");
        ThreadContext.put("jobName", "my_job");
        ThreadContext.put("jobVersion", "my_version");
        logger.info(ThreadContext.get("jobName"));
        logger.trace("Entering application.");
        Bar bar = new Bar();
        if (!bar.doIt()) {
            logger.error("Didn't do it");
        }
        logger.trace("Existing application.");


        //Map<String, Object> requestBody = new HashMap<>();
        //requestBody.put("projectName", "my_project");
        //requestBody.put("jobName", "my_job");
        //logger.info(new CustomMessage(requestBody));
    }
}