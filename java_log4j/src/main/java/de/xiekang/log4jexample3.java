package de.xiekang;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.rolling.DefaultRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.util.stream.IntStream;
import java.util.zip.Deflater;

public class log4jexample3 {
    private static ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
    private static LoggerContext ctx;

    private static Logger logger =  LogManager.getLogger("log4jexample3");

    private static CharArrayWriter writer = new CharArrayWriter();

    // INFO: this method is for Talend
    public static void updateRollingConfiguration() {
        LoggerContext loggerContext = ((org.apache.logging.log4j.core.Logger)logger).getContext();
        Configuration configuration = loggerContext.getConfiguration();
        System.out.println("before: " + configuration.getLoggers().entrySet());
        RollingFileAppender rollingFileAppender = RollingFileAppender.newBuilder().setName("mytest")
                .withFileName("target/rolling1.log")
                .withFilePattern("target/archive/rolling1-%d{MM-dd-yy}-%i.log.gz")
                .setLayout(PatternLayout.newBuilder().withPattern("%d [%t] %-5level: %msg%n").build())
                .withPolicy(SizeBasedTriggeringPolicy.createPolicy("2 kb"))
                .withStrategy(DefaultRolloverStrategy.newBuilder()
                        .withMax("5")
                        .withMin("1")
                        .withFileIndex("max")
                        .withCompressionLevelStr("0")
                        .build())
                .setConfiguration(configuration)
                .build();
        rollingFileAppender.start();
        configuration.addLoggerAppender(((org.apache.logging.log4j.core.Logger)logger), rollingFileAppender);
        loggerContext.updateLoggers(configuration);
        System.out.println("after: " + configuration.getLoggers().entrySet());
    }

    public static void createRollingConfiguration() {

        builder.setStatusLevel(Level.INFO);
        builder.setConfigurationName("RollingBuilder");

        // create a console appender
        AppenderComponentBuilder appenderComponentBuilder = builder.newAppender("Stdout", "CONSOLE").addAttribute("target", ConsoleAppender.Target.SYSTEM_ERR);
        appenderComponentBuilder.add(builder.newLayout("PatternLayout").addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable"));
        builder.add(appenderComponentBuilder);

        // create a rolling file appender
        LayoutComponentBuilder layoutBuilder = builder.newLayout("PatternLayout").addAttribute("pattern", "%d [%t] %-5level: %msg%n");
        ComponentBuilder triggeringPolicy = builder.newComponent("Policies").addComponent(builder.newComponent("CronTriggeringPolicy").addAttribute("schedule", "0 0 0 * * ?")).addComponent(builder.newComponent("SizeBasedTriggeringPolicy").addAttribute("size", "2 kb"));

        ComponentBuilder strategy = builder.newComponent("DefaultRolloverStrategy").addAttribute("max", "5").addAttribute("min", "1").addAttribute("fileIndex", "max").addAttribute("compressionLevel", Deflater.NO_COMPRESSION);

        appenderComponentBuilder = builder.newAppender("rolling", "RollingFile").addAttribute("fileName", "target/rolling.log").addAttribute("filePattern", "target/archive/rolling-%d{MM-dd-yy}-%i.log.gz").add(layoutBuilder).addComponent(triggeringPolicy).addComponent(strategy);


        builder.add(appenderComponentBuilder);

        // create the new logger
        builder.add(builder.newLogger(log4jexample3.class.getSimpleName(), Level.TRACE).add(builder.newAppenderRef("rolling")).add(builder.newAppenderRef("Stdout")).addAttribute("additivity", false));


        builder.add(builder.newRootLogger(Level.TRACE).add(builder.newAppenderRef("rolling")).add(builder.newAppenderRef("Stdout")));


        ctx = Configurator.initialize(builder.build());


    }

    public static ConfigurationBuilder<BuiltConfiguration> getBuilder() {
        return builder;
    }

    public static void main(String[] args) throws IOException {
        // version 1
        //createRollingConfiguration();
        //builder.writeXmlConfiguration(new FileOutputStream("target/bbb.xml"));
        //Configurator.reconfigure(getBuilder().build());
        //logger.getAppenders().forEach((key, value) -> System.err.println(key + ": " + value));

        // version 2
        updateRollingConfiguration();
        ((org.apache.logging.log4j.core.Logger)logger).getAppenders().forEach((key, value) -> System.err.println(key + ": " + value));
        IntStream.range(0, 1000).forEach(i -> {
            Runnable runnable = () -> logger.info("Logging info");
            Thread thread = new Thread(runnable);
            thread.start();
        });
    }
}

