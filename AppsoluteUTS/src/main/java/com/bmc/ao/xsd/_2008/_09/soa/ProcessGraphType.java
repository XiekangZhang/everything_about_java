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
 * Represents an directed acyclic graph of processes that have predecessors that are expected to be executed prior to succeeding processings. This is useful for bulk-execution of processes is desirable as is the ability to schedule a graph of processes in a single web service invocation.
 * 
 * <p>Java-Klasse für ProcessGraphType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ProcessGraphType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ProcessGraph" type="{http://bmc.com/ao/xsd/2008/09/soa}ProcessDescriptionType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="concurrencyLimit" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="failurePolicy" type="{http://bmc.com/ao/xsd/2008/09/soa}PredecessorFailurePolicy" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessGraphType", propOrder = {
    "processGraphs"
})
public class ProcessGraphType
    implements Serializable
{

    private final static long serialVersionUID = -1L;
    @XmlElement(name = "ProcessGraph", required = true)
    protected List<ProcessDescriptionType> processGraphs;
    @XmlAttribute(name = "concurrencyLimit", required = true)
    protected long concurrencyLimit;
    @XmlAttribute(name = "failurePolicy")
    protected PredecessorFailurePolicy failurePolicy;

    /**
     * Gets the value of the processGraphs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the processGraphs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcessGraphs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProcessDescriptionType }
     * 
     * 
     */
    public List<ProcessDescriptionType> getProcessGraphs() {
        if (processGraphs == null) {
            processGraphs = new ArrayList<ProcessDescriptionType>();
        }
        return this.processGraphs;
    }

    /**
     * Ruft den Wert der concurrencyLimit-Eigenschaft ab.
     * 
     */
    public long getConcurrencyLimit() {
        return concurrencyLimit;
    }

    /**
     * Legt den Wert der concurrencyLimit-Eigenschaft fest.
     * 
     */
    public void setConcurrencyLimit(long value) {
        this.concurrencyLimit = value;
    }

    /**
     * Ruft den Wert der failurePolicy-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PredecessorFailurePolicy }
     *     
     */
    public PredecessorFailurePolicy getFailurePolicy() {
        return failurePolicy;
    }

    /**
     * Legt den Wert der failurePolicy-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PredecessorFailurePolicy }
     *     
     */
    public void setFailurePolicy(PredecessorFailurePolicy value) {
        this.failurePolicy = value;
    }

}
