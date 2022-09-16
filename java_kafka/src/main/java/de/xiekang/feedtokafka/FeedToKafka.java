package de.xiekang.feedtokafka;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.apache.commons.cli.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.Properties;

public class FeedToKafka {

    public String getFeedSource(String url, String type) {
        StringWriter stringWriter = new StringWriter();
        stringWriter.append("{ \n");
        try {
            URL feedSource = new URL(url);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = null;
            if (type.equalsIgnoreCase("xml")) {
                XmlReader inputReader = new XmlReader(feedSource);
                feed = input.build(inputReader);
            }
            stringWriter.append("title: ").append(feed.getTitle()).append(" , \n");
            stringWriter.append("feed type: ").append(feed.getFeedType()).append(" , \n");
            stringWriter.append("link: ").append(feed.getLink()).append(" , \n");
        } catch (FeedException | IOException exception) {
            exception.printStackTrace();
        }
        stringWriter.append("}");
        return stringWriter.toString();
    }

    public void producerMessage(String msg, String bootstrapServer, String topic_name) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic_name, msg);

        producer.send(record, ((recordMetadata, e) -> {
            if (e == null) {
                System.err.println("Successfully received the details as: ");
                System.err.println("Topic: " + recordMetadata.topic());
                System.err.println("Partition: " + recordMetadata.partition());
                System.err.println("Offset: " + recordMetadata.offset());
                System.err.println("Timestamp: " + recordMetadata.timestamp());
            } else {
                System.err.println(e);
            }
        }));

        producer.flush();
        producer.close();
    }

    public static Options setArguments() {
        Options options = new Options();
        Option url = new Option("u", "URL", true, "RSS feed URL");
        url.setRequired(true);
        options.addOption(url);

        Option type = new Option("t", "TYPE", true, "Type of RSS feed");
        type.setRequired(false);
        options.addOption(type);

        Option bootstrapServer = new Option("s", "BOOSTRAP_SERVERS", true, "Kafka Server to connect to");
        bootstrapServer.setRequired(true);
        options.addOption(bootstrapServer);

        Option topic = new Option("t", "TOPIC", true, "Topic Name");
        topic.setRequired(false);
        options.addOption(topic);

        return options;
    }

    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;
        Options options = setArguments();
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }

        String url = cmd.getOptionValue("URL");
        String type = cmd.getOptionValue("TYPE") == null ? "xml": cmd.getOptionValue("TYPE");
        String server = cmd.getOptionValue("BOOSTRAP_SERVERS");
        String topic = cmd.getOptionValue("TOPIC") == null ? "rss_feed": cmd.getOptionValue("TOPIC");

        FeedToKafka feed = new FeedToKafka();
        String msg = feed.getFeedSource(url, type);
        feed.producerMessage(msg, server, topic);
    }
}
