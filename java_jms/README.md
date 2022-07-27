# Apache Camel & Java DSL

This is a tutorial about apache camel and Java DSL based on https://www.codeusingjava.com/camel/camel-intro.

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