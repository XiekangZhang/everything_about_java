//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v3.0.0 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2023.06.23 um 11:22:45 AM CEST 
//


package de.telefonica.talend.server;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für metadata complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="metadata"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="query-executed" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="execution-milliseconds" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="row-count" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="column-count" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "metadata", propOrder = {
    "queryExecuted",
    "executionMilliseconds",
    "rowCount",
    "columnCount",
    "status"
})
public class Metadata
    implements Serializable
{

    private final static long serialVersionUID = -1L;
    @XmlElement(name = "query-executed", required = true)
    protected String queryExecuted;
    @XmlElement(name = "execution-milliseconds")
    protected long executionMilliseconds;
    @XmlElement(name = "row-count")
    protected int rowCount;
    @XmlElement(name = "column-count")
    protected int columnCount;
    @XmlElement(required = true)
    protected String status;

    /**
     * Ruft den Wert der queryExecuted-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryExecuted() {
        return queryExecuted;
    }

    /**
     * Legt den Wert der queryExecuted-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryExecuted(String value) {
        this.queryExecuted = value;
    }

    /**
     * Ruft den Wert der executionMilliseconds-Eigenschaft ab.
     * 
     */
    public long getExecutionMilliseconds() {
        return executionMilliseconds;
    }

    /**
     * Legt den Wert der executionMilliseconds-Eigenschaft fest.
     * 
     */
    public void setExecutionMilliseconds(long value) {
        this.executionMilliseconds = value;
    }

    /**
     * Ruft den Wert der rowCount-Eigenschaft ab.
     * 
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Legt den Wert der rowCount-Eigenschaft fest.
     * 
     */
    public void setRowCount(int value) {
        this.rowCount = value;
    }

    /**
     * Ruft den Wert der columnCount-Eigenschaft ab.
     * 
     */
    public int getColumnCount() {
        return columnCount;
    }

    /**
     * Legt den Wert der columnCount-Eigenschaft fest.
     * 
     */
    public void setColumnCount(int value) {
        this.columnCount = value;
    }

    /**
     * Ruft den Wert der status-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Legt den Wert der status-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
