# Kafka (based on Java 8)
This description or tutorial is based on [GCP Kafka](https://hevodata.com/learn/gcp-kafka-installation/)
and [Kafka Tutorial](https://www.javatpoint.com/apache-kafka).

Apache Kafka is a distributed Publish-Subscribe Messaging platform explicitly designed to handle
Real-time Streaming data. 

## What is Apache Kafka?
Apache Kafka was originally developed for Monitoring Activity Stream Data and Operational Metrics.
Apache Kafka is a Distributed Event Streaming Platform written in Java and Scala. It is a Publish-Subscribe
Messaging Solution used to create Real-Time Streaming Data Pipelines and applications that adapt to
the Data Streams. 

For more details please go through [Kafka official Documentation](https://kafka.apache.org/documentation/)

__Kafka Core concept:__
- Producer
  - write data to the cluster by using message keys
  - load balancing is done when the producer writes data to the kafka topic without specifying 
  any key (a round-robin manner -->  to each partition)
  - write data to the cluster by using acknowledgment: a confirmation of its data writes
    - acks=0: producer sends the data to the broker but does not wait for the acknowledgment.
        it could result data loss because without confirming that the data is successfully sent to
        the broker or may be the broker is down, it sends another one.
    - acks=1: producer will wait for the leader's acknowledgement. Limited data loss.
    - acks=all: the acknowledgment is done by both the leader and its followers. No data loss.
- Consumer
  - Consumer vs Consumer Groups \
![Kafka Consumer Groups](https://static.javatpoint.com/tutorial/kafka/images/apache-kafka-consumer-and-consumer-groups2.png)
![Kafka Consumer Groups2](https://static.javatpoint.com/tutorial/kafka/images/apache-kafka-consumer-and-consumer-groups3.png)
  - Read data from committed offset (could be understood as a bookmark)
  - Delivery semantics (defining when to commit)
    - at most once: the offsets are committed as soon as the consumer receives the message
    - at least once: the offsets are committed after the message has been processed
    - exactly once: the offsets can be achieved for Kafka to Kafka workflow only using the Streams API
- Broker: A (Kafka) Server that acts as an agent/broker for message exchange
  - A broker is a container that holds several topics with their multiple partitions
  - Kafka brokers are also known as Bootstrap brokers because connection with any one broker means
    connection with the entire cluster.
  - A broker does not contain whole data, but each broker in the cluster knows about all other
    brokers, partitions as well as topics
![Kafka Brokers Example](https://static.javatpoint.com/tutorial/kafka/images/kafka-topics-2.png)
- Cluster
  - MirrorMaker: it ensures a producer from one Kafka cluster producing a message, and a consumer 
    from another cluster reading the message.
![Multiple datacenter architecture](https://static.javatpoint.com/tutorial/kafka/images/apache-kafka-multiple-clusters.png)
- Topic
  - topics in Kafka are similar to tables in the database, but not containing all constraints
  - partitions 
  - offset value is used as a id to store the message into a partition of a topic
  - a replication factor is created for the topics contained in any particular broker
    - a same principle like Zookeeper is used --> find the leader and the rest are followers
![Topic Replication Factor](https://static.javatpoint.com/tutorial/kafka/images/kafka-topic-replication-1.png)
- Zookeeper \
![apache kafka architecture](https://static.javatpoint.com/tutorial/kafka/images/apache-kafka-architecture3.png)

__Kafka Core API's:__
- Producer API
- Consumer API
- Streams API
- Connector API

__Kafka disadvantages:__
- Message tweaking issues: it works well if the message does not need to change
- 

__Kafka Key Features:__
- Persistent messaging: Apache Kafka is built with __O(1)__ Disc Structures
- High Throughput: Kafka was designed to work with large amounts of data and support Millions
of Messages per Second
- Distributed event streaming platform: Apache Kafka facilitates Message Partitioning across
Kafka servers and distributing consumption over a cluster of consumer systems while ensuring
per-partition ordering semantics
- Real-time solutions: Messages created by producer threads should be instantly available to
consumer threads. This characteristic is essential in event-based systems like Complex Event
Processing. 

