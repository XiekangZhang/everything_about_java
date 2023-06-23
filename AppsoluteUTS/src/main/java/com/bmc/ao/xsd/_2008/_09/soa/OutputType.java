//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v3.0.0 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2023.06.23 um 11:22:45 AM CEST 
//


package com.bmc.ao.xsd._2008._09.soa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Content type for representing generic output types resulting from the execution of a process. This can be used by both the client and the server depending on the situation.
 * 
 * <p>Java-Klasse für OutputType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="OutputType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Parameter" type="{http://bmc.com/ao/xsd/2008/09/soa}ParameterType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute ref="{http://bmc.com/ao/xsd/2008/09/soa}type"/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OutputType", propOrder = {
    "parameters"
})
public class OutputType
    implements Serializable
{

    private final static long serialVersionUID = -1L;
    @XmlElement(name = "Parameter")
    protected List<ParameterType> parameters;
    @XmlAttribute(name = "type", namespace = "http://bmc.com/ao/xsd/2008/09/soa")
    protected String type;

    /**
     * Gets the value of the parameters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the parameters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParameterType }
     * 
     * 
     */
    public List<ParameterType> getParameters() {
        if (parameters == null) {
            parameters = new ArrayList<ParameterType>();
        }
        return this.parameters;
    }

    /**
     * This is a client-only attribute that allows clients the option of specifying the format and type of all output parameters for an executing process in lieu of the default xs:anyType that is used to represent output parameters. This attribute is inherited by all possible output parameters for a process if no additional sequence of output parameters are included with this. It is possible to use this attribute in combination with a set of explicit output parameters with differing value types in which case the client can expect a fine degree of control over specific output type coercion that it receives from the server. The server will do its best to coerce the return types from XML to the specified type. This can incur additional processing overhead on the server side and, if used incorrectly may produce undesirable results thus this attribute is only permitted in this manner when synchronously executing a process or when requesting the status of a single asynchronously executing process. This attribute is ignored by the server if used in operations that involve process graphs, which is not permitted.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        if (type == null) {
            return "xs:anyType";
        } else {
            return type;
        }
    }

    /**
     * Legt den Wert der type-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
