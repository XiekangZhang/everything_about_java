//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v3.0.0 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2023.06.13 um 02:20:41 PM CEST 
//


package de.telefonica.talend.server;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für TicketInfo complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TicketInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="transactionHeader" type="{http://server.talend.telefonica.de/}TransactionHeader"/&gt;
 *         &lt;element name="transactionBody" type="{http://server.talend.telefonica.de/}TransactionBody"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TicketInfo", propOrder = {
    "transactionHeader",
    "transactionBody"
})
@XmlRootElement(name = "ticketInfo")
public class TicketInfo
    implements Serializable
{

    private final static long serialVersionUID = -1L;
    @XmlElement(required = true)
    protected TransactionHeader transactionHeader;
    @XmlElement(required = true)
    protected TransactionBody transactionBody;

    /**
     * Ruft den Wert der transactionHeader-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TransactionHeader }
     *     
     */
    public TransactionHeader getTransactionHeader() {
        return transactionHeader;
    }

    /**
     * Legt den Wert der transactionHeader-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionHeader }
     *     
     */
    public void setTransactionHeader(TransactionHeader value) {
        this.transactionHeader = value;
    }

    /**
     * Ruft den Wert der transactionBody-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TransactionBody }
     *     
     */
    public TransactionBody getTransactionBody() {
        return transactionBody;
    }

    /**
     * Legt den Wert der transactionBody-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionBody }
     *     
     */
    public void setTransactionBody(TransactionBody value) {
        this.transactionBody = value;
    }

}
