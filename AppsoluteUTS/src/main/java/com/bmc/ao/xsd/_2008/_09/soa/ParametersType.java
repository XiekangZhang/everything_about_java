//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v3.0.0 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2023.06.26 um 02:16:28 PM CEST 
//


package com.bmc.ao.xsd._2008._09.soa;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Generic type for representing any given parameter to any given process.
 * 
 * <p>Java-Klasse für ParametersType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ParametersType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Input" type="{http://bmc.com/ao/xsd/2008/09/soa}ArrayOfParameters" minOccurs="0"/&gt;
 *         &lt;element name="Output" type="{http://bmc.com/ao/xsd/2008/09/soa}OutputType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParametersType", propOrder = {
    "input",
    "output"
})
public class ParametersType
    implements Serializable
{

    private final static long serialVersionUID = -1L;
    @XmlElement(name = "Input")
    protected ArrayOfParameters input;
    @XmlElement(name = "Output")
    protected OutputType output;

    /**
     * Ruft den Wert der input-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfParameters }
     *     
     */
    public ArrayOfParameters getInput() {
        return input;
    }

    /**
     * Legt den Wert der input-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfParameters }
     *     
     */
    public void setInput(ArrayOfParameters value) {
        this.input = value;
    }

    /**
     * Ruft den Wert der output-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link OutputType }
     *     
     */
    public OutputType getOutput() {
        return output;
    }

    /**
     * Legt den Wert der output-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link OutputType }
     *     
     */
    public void setOutput(OutputType value) {
        this.output = value;
    }

}
