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
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


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
 *         &lt;element name="processContext" type="{http://bmc.com/ao/xsd/2008/09/soa}ProcessContextType"/&gt;
 *         &lt;element name="timeout" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "processContext",
    "timeout"
})
@XmlRootElement(name = "pauseProcess")
public class PauseProcess
    implements Serializable
{

    private final static long serialVersionUID = -1L;
    @XmlElement(required = true)
    protected ProcessContextType processContext;
    protected long timeout;

    /**
     * Ruft den Wert der processContext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ProcessContextType }
     *     
     */
    public ProcessContextType getProcessContext() {
        return processContext;
    }

    /**
     * Legt den Wert der processContext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessContextType }
     *     
     */
    public void setProcessContext(ProcessContextType value) {
        this.processContext = value;
    }

    /**
     * Ruft den Wert der timeout-Eigenschaft ab.
     * 
     */
    public long getTimeout() {
        return timeout;
    }

    /**
     * Legt den Wert der timeout-Eigenschaft fest.
     * 
     */
    public void setTimeout(long value) {
        this.timeout = value;
    }

}
