package de.xiekang.jsontokafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.xiekang.jsontokafka.beans.TripMain;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class JsonToKafka {

    private final String bootstrapServer;
    private final String topic_name;
    private Set<String> keys;
    private final Path path = Paths.get("java_kafka/src/main/java/de/xiekang/jsontokafka/data/id.txt");


    public JsonToKafka(String bootstrapServer, String topic_name) {
        this.bootstrapServer = bootstrapServer;
        this.topic_name = topic_name;
    }

    public String getBootstrapServer() {
        return bootstrapServer;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public Set<String> getKeys() {
        return keys;
    }

    public void setKeys(Set<String> keys) {
        this.keys = keys;
    }

    public void consumeMessage() throws JsonProcessingException {
        this.keys = new HashSet<>();
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "grp_id");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");


        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        TopicPartition topicPartition = new TopicPartition(this.topic_name, 0);
        List<TopicPartition> partitionList = Arrays.asList(topicPartition);
        //consumer.subscribe(Arrays.asList(this.topic_name));
        consumer.assign(partitionList);
        consumer.seekToBeginning(partitionList);

        final Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Starting exit...");
                consumer.wakeup();
                try {
                    mainThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    ObjectMapper read = new ObjectMapper();
                    TripMain tripMain = read.readValue(record.value(), TripMain.class);
                    this.keys.add(tripMain.getId() + " " + tripMain.getTripUpdate().getTrip().getStartDate() + " " + tripMain.getTripUpdate().getTrip().getStartTime());
                    System.err.println("Value: " + record.value());
                }
            }
        } catch (WakeupException wakeupException) {
            //
        } finally {
            consumer.close();
        }
    }

    public void getKeysFromFile() {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            this.keys = reader.lines().collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateKeysFile(String keys) {
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            writer.write(keys);
            writer.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void producerMessage(String msg) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.getBootstrapServer());
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>(this.getTopic_name(), msg);
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

    //public static void main(String[] args) throws IOException {


        /*ObjectMapper objectMapper = new ObjectMapper();
        TripMainWrapper tripMainWrapper = objectMapper.readValue(new File("java_kafka/src/main/java/de/xiekang/jsontokafka/data/test.json"), TripMainWrapper.class);
        JsonToKafka jsonToKafka = new JsonToKafka("localhost:9092", "jsonmsg");
        jsonToKafka.consumeMessage();
        jsonToKafka.getKeys().forEach(System.out::println);
        tripMainWrapper.getEntity().forEach(
                tripMain -> {
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        if (!jsonToKafka.getKeys().contains(tripMain.getId() + " " + tripMain.getTripUpdate().getTrip().getStartDate() + " " + tripMain.getTripUpdate().getTrip().getStartTime())) {
                            System.out.println("Starting transfer...");
                            jsonToKafka.producerMessage(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tripMain));
                        } else {
                            System.out.println("Message is already existed...");
                        }
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
        );*/
    // }
}
