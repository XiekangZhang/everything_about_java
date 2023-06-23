//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v3.0.0 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2023.06.23 um 11:22:45 AM CEST 
//


package com.bmc.ao.xsd._2008._09.soa;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Common value content type that represents all possible parameter values for any process description or execution. Processes only support a very limited list of types shown by the choices below so callers must be able to process both. The default type is xs:anyType.
 * 
 * <p>Java-Klasse für ValueType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ValueType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="Text" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XmlDoc" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute ref="{http://bmc.com/ao/xsd/2008/09/soa}type"/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValueType", propOrder = {
    "xmlDoc",
    "text"
})
public class ValueType
    implements Serializable
{

    private final static long serialVersionUID = -1L;
    @XmlElement(name = "XmlDoc")
    protected Object xmlDoc;
    @XmlElement(name = "Text")
    protected String text;
    @XmlAttribute(name = "type", namespace = "http://bmc.com/ao/xsd/2008/09/soa")
    protected String type;

    /**
     * Ruft den Wert der xmlDoc-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getXmlDoc() {
        return xmlDoc;
    }

    /**
     * Legt den Wert der xmlDoc-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setXmlDoc(Object value) {
        this.xmlDoc = value;
    }

    /**
     * Ruft den Wert der text-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Legt den Wert der text-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * When used by the client, this attribute allows clients the option of explicitly specifying the format and type of the parameter that is being sent to the server in lieu of the default xs:anyType. The server will do its best to coerce the type from the format its provided into the format that the process requires. This can incur additional processing overhead on the server side and, if used incorrectly may produce undesirable results thus this attribute is only permitted in this manner when synchronously executing a process or when requesting the status of a single asynchronously executing process. This attribute is ignored by the server if used in operations that involve process graphs, which is not permitted. When used by the server, this attribute indicates to clients the actual type/format of the data represented by this value.
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
