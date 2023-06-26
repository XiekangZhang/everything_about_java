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
 * A detailed description of a process.
 * 
 * <p>Java-Klasse für ProcessDescriptionType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ProcessDescriptionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Peer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PeerLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Parameters" type="{http://bmc.com/ao/xsd/2008/09/soa}ParametersType" minOccurs="0"/&gt;
 *         &lt;element name="Predecessors" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Predecessor" type="{http://bmc.com/ao/xsd/2008/09/soa}ProcessDescriptionType"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessDescriptionType", propOrder = {
    "name",
    "peer",
    "peerLocation",
    "description",
    "parameters",
    "predecessors"
})
public class ProcessDescriptionType
    implements Serializable
{

    private final static long serialVersionUID = -1L;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Peer")
    protected String peer;
    @XmlElement(name = "PeerLocation")
    protected String peerLocation;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Parameters")
    protected ParametersType parameters;
    @XmlElement(name = "Predecessors")
    protected ProcessDescriptionType.Predecessors predecessors;

    /**
     * Ruft den Wert der name-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Legt den Wert der name-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Ruft den Wert der peer-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeer() {
        return peer;
    }

    /**
     * Legt den Wert der peer-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeer(String value) {
        this.peer = value;
    }

    /**
     * Ruft den Wert der peerLocation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeerLocation() {
        return peerLocation;
    }

    /**
     * Legt den Wert der peerLocation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeerLocation(String value) {
        this.peerLocation = value;
    }

    /**
     * Ruft den Wert der description-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Legt den Wert der description-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Ruft den Wert der parameters-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ParametersType }
     *     
     */
    public ParametersType getParameters() {
        return parameters;
    }

    /**
     * Legt den Wert der parameters-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ParametersType }
     *     
     */
    public void setParameters(ParametersType value) {
        this.parameters = value;
    }

    /**
     * Ruft den Wert der predecessors-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ProcessDescriptionType.Predecessors }
     *     
     */
    public ProcessDescriptionType.Predecessors getPredecessors() {
        return predecessors;
    }

    /**
     * Legt den Wert der predecessors-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessDescriptionType.Predecessors }
     *     
     */
    public void setPredecessors(ProcessDescriptionType.Predecessors value) {
        this.predecessors = value;
    }


    /**
     * <p>Java-Klasse für anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Predecessor" type="{http://bmc.com/ao/xsd/2008/09/soa}ProcessDescriptionType"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "predecessor"
    })
    public static class Predecessors
        implements Serializable
    {

        private final static long serialVersionUID = -1L;
        @XmlElement(name = "Predecessor", required = true)
        protected ProcessDescriptionType predecessor;

        /**
         * Ruft den Wert der predecessor-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link ProcessDescriptionType }
         *     
         */
        public ProcessDescriptionType getPredecessor() {
            return predecessor;
        }

        /**
         * Legt den Wert der predecessor-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link ProcessDescriptionType }
         *     
         */
        public void setPredecessor(ProcessDescriptionType value) {
            this.predecessor = value;
        }

    }

}
