# ZooKeeper
This short tutorial is based on [ZooKeeper Homepage](https://zookeeper.apache.org/) and 
[ZooKeeper Baeldung](https://www.baeldung.com/java-zookeeper).

Apache ZooKeeper is a distributed coordination service which eases the development of distributed
applications. Nodes within ZooKeeper cluster store their data in a shared hierarchical namespace 
which is similar to a standard file system or a tree data structure. ZooKeeper was designed to store
coordination data: status information, configuration, location information, etc., so the data
stored at each node is usually small, in the byte to kilobyte range. 

## Design Goals
![ZooKeeper Design](https://zookeeper.apache.org/doc/r3.4.13/images/zkservice.jpg)

The server that make up the ZooKeeper service must all know about each other. 
They maintain an in-memory image of state, along with a transaction logs and snapshots in a 
persistent store. As long as a majority of the services are available, the ZooKeeper service 
will be available. 

Clients connect to a single ZooKeeper server. The client maintains a TCP connection through
which it sends requests, gets responses, gets watch events, and sends heart beats. 
If the TCP connection to the server breaks, the client will connect to a different server. 

### ZNode vs ephemeral nodes
- ephemeral nodes will be deleted after the session is closed.

## ZNode
ZooKeeper has a hierarchical namespace, much like a distributed file system where it stores 
coordination data like status information, coordination information, location information, etc. 
This information is stored on different nodes. 

Every node in a ZooKeeper tree is referred to as ZNode.

Note: In standalone mode, there's no replication so if ZooKeeper process fails, the service will
go down. 

To use a ZooKeeper service, an application must first instantiate an object of ZooKeeper class, 
which is the de.xiekang.zookeeper.main class of ZooKeeper client library. 

## Programming ZooKeeper
ZooKeeper applications are broken into two units. 
- maintains the connection
- monitors data

### Running Replicated ZooKeeper
A replicated group of servers in the same application is called a **quorum**, and in replicated
mode, all servers in the quorum have copies of the same configuration file. 

For replicated mode, a minimum of three servers are required, and it is strongly recommended that
you have an odd number of servers. 

#### zoo.cfg Example
- tickTime: It is used to do heartbeats and the minimum session timeout will be twice the tickTime.
- dataDir: the location to store the in-memory database snapshots and, unless specified otherwise,
the transaction log of updates to the database.
- clientPort: the port to listen for client connections.
- initLimit: It is used to limit the length of time the ZooKeeper servers in quorum have to connect
to a leader. 
- syncLimit: it limits how far out of date a server can be from a leader. 
- server.X: it lists the servers that make up the ZooKeeper service. 
  - server.1=zoo1:2888:3888 : a ZooKeeper server uses the former port to connect followers to the
leader; 3888 is for leader election. 