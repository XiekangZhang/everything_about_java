//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v3.0.0 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2023.06.26 um 02:16:28 PM CEST 
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
 * Contextual information regarding an asynchronously executed process. This should contain everything that is necessary to pause, resume, cancel and query status on a process.
 * 
 * <p>Java-Klasse für ProcessContextType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ProcessContextType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ProcessName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="JobId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SubProcesses" type="{http://bmc.com/ao/xsd/2008/09/soa}ProcessContextType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="gridName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute ref="{http://bmc.com/ao/xsd/2008/09/soa}type"/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessContextType", propOrder = {
    "processName",
    "jobId",
    "subProcesses"
})
public class ProcessContextType
    implements Serializable
{

    private final static long serialVersionUID = -1L;
    @XmlElement(name = "ProcessName")
    protected String processName;
    @XmlElement(name = "JobId", required = true)
    protected String jobId;
    @XmlElement(name = "SubProcesses")
    protected List<ProcessContextType> subProcesses;
    @XmlAttribute(name = "gridName", required = true)
    protected String gridName;
    @XmlAttribute(name = "type", namespace = "http://bmc.com/ao/xsd/2008/09/soa")
    protected String type;

    /**
     * Ruft den Wert der processName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessName() {
        return processName;
    }

    /**
     * Legt den Wert der processName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessName(String value) {
        this.processName = value;
    }

    /**
     * Ruft den Wert der jobId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * Legt den Wert der jobId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobId(String value) {
        this.jobId = value;
    }

    /**
     * Gets the value of the subProcesses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the subProcesses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubProcesses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProcessContextType }
     * 
     * 
     */
    public List<ProcessContextType> getSubProcesses() {
        if (subProcesses == null) {
            subProcesses = new ArrayList<ProcessContextType>();
        }
        return this.subProcesses;
    }

    /**
     * Ruft den Wert der gridName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGridName() {
        return gridName;
    }

    /**
     * Legt den Wert der gridName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGridName(String value) {
        this.gridName = value;
    }

    /**
     * This optional attribute controls type coercion for all output types associated with the process that this context represents. The client may set this attribute when it requests the status of a process to be one of the choices available and the web service applies this choice to every output parameter generated by the associated process. This makes it possible for the client to ask for output in a form other than its default form to possibly simplify processing on the client side. Note that since this is an optional attribute, clients should not expect the server to set this attribute on instances of this type that are sent to clients. This is purely for the clients benefit only. Also note that this is a global setting that is applied to every output value generated by a process (a process may generate more than one) thus its possible for the client to misuse this attribute and have it corrupt the outputs. For example, if this attribute were set to xs:string and the output parameters for the process was an XML document, the content of the resulting document would be lost. So use this attribute only when absolutely necessary.
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
