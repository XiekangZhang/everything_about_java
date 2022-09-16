package de.xiekang.feedtokafka;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

// warning: https://www.baeldung.com/rome-rss
public class test {

    public void bbbbbb(String msg) {
        String bootstrapServers = "localhost:9092";
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // info: creating producer
        KafkaProducer<String, String> first_producer = new KafkaProducer<>(properties);

        // info: creating producer record --> a record is a message created by producer
        ProducerRecord<String, String> record = new ProducerRecord<>("rss_feed", msg);

        // info: sending message
        first_producer.send(record, (recordMetadata, e) -> {
            if (e == null) {
                System.err.println("Successfully received the details as: ");
                System.err.println("Topic: " + recordMetadata.topic());
                System.err.println("Partition: " + recordMetadata.partition());
                System.err.println("Offset: " + recordMetadata.offset());
                System.err.println("Timestamp: " + recordMetadata.timestamp());
            } else {
                System.err.println(e);
            }
        });//.get(); //.get() sending synchronous data forcefully
        first_producer.flush();
        first_producer.close();
    }

    public static void main(String[] args) throws IOException, FeedException {
        URL feedSource = new URL("https://feeds.howtogeek.com/howtogeek");
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedSource));
        System.err.println(feed.getTitle());

        test test = new test();
        test.bbbbbb(feed.getTitle());

    }
}