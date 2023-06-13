//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v3.0.0 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2023.06.13 um 02:20:41 PM CEST 
//


package de.telefonica.talend.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für TicketResponseList complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TicketResponseList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ticketResponse" type="{http://server.talend.telefonica.de/}TicketResponse" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TicketResponseList", propOrder = {
    "ticketResponses"
})
@XmlRootElement(name = "ticketResponseList")
public class TicketResponseList
    implements Serializable
{

    private final static long serialVersionUID = -1L;
    @XmlElement(name = "ticketResponse")
    protected List<TicketResponse> ticketResponses;

    /**
     * Gets the value of the ticketResponses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the ticketResponses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTicketResponses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TicketResponse }
     * 
     * 
     */
    public List<TicketResponse> getTicketResponses() {
        if (ticketResponses == null) {
            ticketResponses = new ArrayList<TicketResponse>();
        }
        return this.ticketResponses;
    }

}
