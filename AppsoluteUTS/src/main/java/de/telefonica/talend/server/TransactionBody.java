//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v3.0.0 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2023.06.26 um 02:16:28 PM CEST 
//


package de.telefonica.talend.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für TransactionBody complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TransactionBody"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="SupportGroupList" type="{http://server.talend.telefonica.de/}SupportGroup" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="TicketNumber" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionBody", propOrder = {
    "ticketNumbers",
    "supportGroupLists"
})
public class TransactionBody
    implements Serializable
{

    private final static long serialVersionUID = -1L;
    @XmlElement(name = "TicketNumber")
    protected List<String> ticketNumbers;
    @XmlElement(name = "SupportGroupList")
    protected List<String> supportGroupLists;

    /**
     * Gets the value of the ticketNumbers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the ticketNumbers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTicketNumbers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTicketNumbers() {
        if (ticketNumbers == null) {
            ticketNumbers = new ArrayList<String>();
        }
        return this.ticketNumbers;
    }

    /**
     * Gets the value of the supportGroupLists property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the supportGroupLists property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupportGroupLists().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSupportGroupLists() {
        if (supportGroupLists == null) {
            supportGroupLists = new ArrayList<String>();
        }
        return this.supportGroupLists;
    }

}
