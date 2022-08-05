# Apache Camel & Java DSL

This is a tutorial about apache camel and Java DSL based on [Apache Camel basic](https://www.codeusingjava.com/camel/camel-intro).

## Domain Knowledge
1. Apache Camel:
   1. Apache Camel is a rule-based routing and mediation engine that
provides a Java object-based implementation of the Enterprise
Integration Patterns using an API (or declarative Java Domain
      Specific Language) to configure routing and mediation rules. 
2. Enterprise Applications and Enterprise Integration Patterns
   1. Enterprise application integration is the process of linking
such applications within a single organization together in order to 
simplify and automate business processes to the greatest extent possible,
while at the same time avoiding having to make sweeping changes
to the existing applications or data structures. 
   2. Mediation reduces complexity and provides a more flexible
approach by adding and using a tier between the systems. 

## Routes
1. In Camel all logic is written using Routes.
2. Using Routes we define the message flow and integration
logic for our application. 
3. A Route consists of Message Channel which is used for passing
messages from one endpoint to another. The end points of these message
channels either consume of produce messages.

## Apache Camel Components
1. Component act as an endpoint factory using which
we can interact with external systems.

## JMS Acknowledgement
Details information could be found at 
[JMS Details](https://www.novell.com/documentation/extend52/Docs/help/MP/jms/concepts/details.html)

Acknowledgement of a message means that the JMS provider must never 
deliver the message to the consumer in question again.
Normally, consumer applications should therefore acknowledge messages as quickly as possible, to minimize resource
consumption.
### Duplicates Allowed
The session acknowledges message lazily, which provides faster message processing, 
with the penalty that some duplicate messages may be delivered multiple times if JMS fails.
Only applications that are tolerant to message duplicates should use this acknowledgement mode. 
### Auto acknowledge
The session automatically acknowledges that a client has received a message
* Just before the call to a message consumer's receive or receiveNoWait return a message
* Right after the onMessage method returns successfully after invoking the consumer's MessageListener

If a JMS provider or the message consumer crashes while it is processing a message, this message is subject to either
re-delivery or loss when using the automatic acknowledgement mode. 

Synchronous receive: the message will be lost of JMS acknowledges it but crashed before receive returns the message 
to the consumer.

Asynchronous receive: a duplicate will happen if JMS crashes after onMessage completed but before the acknowledgement
was recorded.
### Client acknowledge
A consumer can group a number of messages and then invoke the acknowledge method of the Message to instruct the JMS
provider that the message and all other messages received up until this point have been consumed. 
It can use the recover method of the session to revert back to its last check point
### Transactional acknowledge
A transacted session is a related group of consumed and produced messages that are treated as a single work unit. 
A transaction can be either committed or rolled back. 

Commit: consumed messages are acknowledged, and the associated produced messages are sent.

Rollback: the produced messages are destroyed, and the consumed messages are recovered. 