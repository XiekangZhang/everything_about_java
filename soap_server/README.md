# SOAP Server
This tutorial is aimed to build and invoke a SOAP(simple object access protocol) 
service by using JAX-WS.

## Top-Down or Bottom-Up approach
* Top-Down: a WSDL --> java classes (using jaxws-maven-plugin or maven-jaxb2-plugin)
* Bottom-Up: java classes --> a WSDL

## Web Services Definition Language
WSDL is a contract definition of the available services. It is a specification of input/output
messages, and how to invoke the web service. 
### major elements of a WSDL document
```<definitions/> it defines name, the namespace etc.```\
```<type/> it defines data types used by the web service```\
``<message/> it defines input or output of a service method and the possible exceptions``\
``<portType><operation><input/><output/><fault/> ``
``<binding/> it provides protocol and data format details for each portType``
``<service><port/>``

## SOAP Message
optional SOAP header --> Required SOAP body --> Optional SOAP attachments (deliver like movie in bytes)