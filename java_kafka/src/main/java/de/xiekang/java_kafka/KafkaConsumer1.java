package de.xiekang.java_kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumer1 {
    public static void main(String[] args) {
        String bootstrapServers = "localhost:9092";
        String grp_id = "1st_app";
        String topic = "myfirst";

        // info: consumer properties
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, grp_id);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // info: creating consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        // info: subscribing
        consumer.subscribe(Arrays.asList(topic));
        // info: polling
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.err.println("Key: " + record.key() + ", Value: " + record.value());
                System.err.println("Partition: " + record.partition() + ", Offset: " + record.offset());
            }
        }
    }
}
