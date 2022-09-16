package de.xiekang.java_kafka;


import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaProducer1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String bootstrapServers = "localhost:9092";
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // info: creating producer
        KafkaProducer<String, String> first_producer = new KafkaProducer<>(properties);

        // info: creating producer record --> a record is a message created by producer
        ProducerRecord<String, String> record = new ProducerRecord<>("myfirst", "Hey from Intellij part 2");

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
}
