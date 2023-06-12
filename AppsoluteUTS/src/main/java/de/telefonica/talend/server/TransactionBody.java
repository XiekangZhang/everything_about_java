//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v3.0.0 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2023.06.12 um 02:00:59 PM CEST 
//


package de.telefonica.talend.server;

import java.io.Serializable;
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
 *       &lt;sequence&gt;
 *         &lt;element name="supportGroupList" type="{http://server.talend.telefonica.de/}SupportGroup"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionBody", propOrder = {
    "supportGroupList"
})
public class TransactionBody
    implements Serializable
{

    private final static long serialVersionUID = -1L;
    @XmlElement(required = true)
    protected SupportGroup supportGroupList;

    /**
     * Ruft den Wert der supportGroupList-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SupportGroup }
     *     
     */
    public SupportGroup getSupportGroupList() {
        return supportGroupList;
    }

    /**
     * Legt den Wert der supportGroupList-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SupportGroup }
     *     
     */
    public void setSupportGroupList(SupportGroup value) {
        this.supportGroupList = value;
    }

}
