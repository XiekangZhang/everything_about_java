//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v3.0.0 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2023.06.26 um 02:16:28 PM CEST 
//


package com.bmc.ao.xsd._2008._09.soa;

import java.io.Serializable;
import java.util.Calendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3._2001.xmlschema.Adapter1;


/**
 * Status information regarding a scheduled process instance.
 * 
 * <p>Java-Klasse für ProcessStatusType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ProcessStatusType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ProcessId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="PeerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Status" type="{http://bmc.com/ao/xsd/2008/09/soa}ProcessStateType"/&gt;
 *         &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
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
@XmlType(name = "ProcessStatusType", propOrder = {
    "processId",
    "name",
    "peerId",
    "status",
    "startTime",
    "output"
})
public class ProcessStatusType
    implements Serializable
{

    private final static long serialVersionUID = -1L;
    @XmlElement(name = "ProcessId", required = true)
    protected String processId;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "PeerId")
    protected String peerId;
    @XmlElement(name = "Status", required = true)
    @XmlSchemaType(name = "string")
    protected ProcessStateType status;
    @XmlElement(name = "StartTime", required = true, type = String.class, nillable = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar startTime;
    @XmlElement(name = "Output")
    protected OutputType output;

    /**
     * Ruft den Wert der processId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessId() {
        return processId;
    }

    /**
     * Legt den Wert der processId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessId(String value) {
        this.processId = value;
    }

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
     * Ruft den Wert der peerId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeerId() {
        return peerId;
    }

    /**
     * Legt den Wert der peerId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeerId(String value) {
        this.peerId = value;
    }

    /**
     * Ruft den Wert der status-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ProcessStateType }
     *     
     */
    public ProcessStateType getStatus() {
        return status;
    }

    /**
     * Legt den Wert der status-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessStateType }
     *     
     */
    public void setStatus(ProcessStateType value) {
        this.status = value;
    }

    /**
     * Ruft den Wert der startTime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getStartTime() {
        return startTime;
    }

    /**
     * Legt den Wert der startTime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartTime(Calendar value) {
        this.startTime = value;
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
