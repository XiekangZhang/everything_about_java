package de.xiekang;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.rolling.DefaultRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.message.SimpleMessage;

import java.util.zip.Deflater;

public class log4jexample {
    private static RollingFileAppender raf;
    private static Logger logger = (Logger) LogManager.getLogger(log4jexample.class);


    public static void createAppender() {
        String pattern = "%d [%t] %-5level: %msg%n%throwable";
        String fileLogName = "logs/rolling.log";
        String filePattern = "rolling-%d{MM-dd-yy}.log.gz";

        String hourly = "0 0 0/1 1/1 * ? *";
        String daily = "0 0 12 1/1 * ? *";

        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        //LoggerContext ctx = logger.getContext();
        Configuration config = ctx.getConfiguration();

        DefaultRolloverStrategy strategy = DefaultRolloverStrategy.newBuilder()
                .withMax("7")
                .withMin("1")
                .withFileIndex("max")
                .withConfig(config)
                .withCompressionLevelStr(Deflater.NO_COMPRESSION + "")
                .build();

        PatternLayout layout = PatternLayout.newBuilder().withConfiguration(config).withPattern(pattern).build();

        RollingFileAppender.Builder builder = RollingFileAppender.newBuilder();
        builder.setName("bbbbb");
        builder.withFileName(fileLogName);
        builder.withFilePattern(filePattern);
        builder.withPolicy(SizeBasedTriggeringPolicy.createPolicy("10 kb"));
        //builder.withPolicy(CronTriggeringPolicy.createPolicy(config, Boolean.TRUE.toString(), daily));
        builder.withStrategy(strategy);
        builder.setLayout(layout);
        builder.setConfiguration(config);


        raf = builder.build();
        raf.start();
        //config.addAppender(raf);
        logger.getContext().getConfiguration().addLoggerAppender(logger, raf);
        ctx.updateLoggers();
    }

    public static void main(String[] args) {
        createAppender();
        logger.info("Hello World");
        logger.debug("Hello World");
        System.out.println(raf.getFileName());






        //for (int i = 0; i < 100; i++) {
        //    logger.info("bbbbbbbbb" + i);
        //    raf.append(asLogEvent("This is a debug message: {}"+ i, Level.DEBUG));
        //}
    }

    private static LogEvent asLogEvent(String message, Level level) {
        return new Log4jLogEvent.Builder().setLoggerName("logger").setMarker(null)
                .setLevel(level)
                //.setMessage(new SimpleMessage(logger.).build();
                .setMessage(new SimpleMessage(message)).setTimeMillis(System.currentTimeMillis()).build();
    }
}
