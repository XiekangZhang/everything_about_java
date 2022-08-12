# Introduction to Java Serialization

## Java's Native Serialization
This tutorial is based on [introduction to java serialization](https://www.baeldung.com/java-serialization).
Serialization is the conversion of the state of an object into a byte stream, 
which we can then save to a db or transfer over a network. 
Deserialization does the opposite. 

Custom serialization can be particularly useful when trying to serialize an object
that has some unserializable attributes. A guide could be found 
[Externalizable Interface](https://www.baeldung.com/java-externalizable).

Java Serialization Caveats
- Only objects marked Serializable can be persisted.
- When an object has a reference to another object, these objects must implement
the Serializable interface separately.
- Java serialization heavily uses I/O streams. It could result in resource leak.

## Google Gson
Gson is a java library that is used to serialize and deserialize java objects to
and from JSON representation. 

Gson features:
- can handle collections, generic types and nested classed
- custom serializer / deserializer possible
- it allows deserializing instances of classes for which the source code is not
accessible 
- use @Since annotation on newly added fields, and then we can use the __setVersion()__
method from GsonBuilder for versioning.

[more info about Gson serialization](https://www.baeldung.com/gson-serialization-guide) and
[more info about Gson deserialization](https://www.baeldung.com/gson-deserialization-guide)

## Jackson API
[Jackson Tutorial](https://www.baeldung.com/jackson)
- One of the greatest strengths of the Jackson library is the highly customizable
serialization and deserialization process

## YAML
using jackson for YAML. [jackson YAML](https://www.baeldung.com/jackson-yaml)

[*tbd*](https://www.baeldung.com/java-serialization-approaches) 