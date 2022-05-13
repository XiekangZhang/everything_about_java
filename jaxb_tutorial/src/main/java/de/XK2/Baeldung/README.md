# JAXB
JAXB provides a fast and convenient way to marshal (write) Java objects into XML
and unmarshal (read) XML into objects. 

JAXB uses Java annotations for augmenting the generated classes with additional information.
Adding such annotations to existing Java classes prepares them for the JAXB runtime.

## JAXB annotations
- @XmlRootElement: the name of the root XML element is derived from the class name, and
we can also specify the name of the root element of the XML using its name attribute
- @XmlType: define the order in which the fields are written in the XML file
- @XmlElement: define the actual XML element name that will be used
- @XmlAttribute: define the id field is mapped as an attribute instead of an element
- @XmlTransient: annotate fields that we don't want to be included in XML
- XmlAdapter to define a custom code to convert an unmappable class into something that 
JAXB can handle. @XmlJavaTypeAdapter annotation uses an adapter that extends the XmlAdapter class for 
custom marshalling

## JAXB-2 Maven Plugin
- Generating a Java Class from XSD