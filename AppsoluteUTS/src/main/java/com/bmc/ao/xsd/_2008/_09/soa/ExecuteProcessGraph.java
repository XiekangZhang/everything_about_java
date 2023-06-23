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
 *         &lt;element name="gridName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="processGraph" type="{http://bmc.com/ao/xsd/2008/09/soa}ProcessGraphType"/&gt;
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
    "gridName",
    "processGraph"
})
@XmlRootElement(name = "executeProcessGraph")
public class ExecuteProcessGraph
    implements Serializable
{

    private final static long serialVersionUID = -1L;
    @XmlElement(required = true)
    protected String gridName;
    @XmlElement(required = true)
    protected ProcessGraphType processGraph;

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
     * Ruft den Wert der processGraph-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ProcessGraphType }
     *     
     */
    public ProcessGraphType getProcessGraph() {
        return processGraph;
    }

    /**
     * Legt den Wert der processGraph-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessGraphType }
     *     
     */
    public void setProcessGraph(ProcessGraphType value) {
        this.processGraph = value;
    }

}
