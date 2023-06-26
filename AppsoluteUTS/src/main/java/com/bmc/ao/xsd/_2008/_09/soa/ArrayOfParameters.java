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
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * A collection of generic parameters for any process.
 * 
 * <p>Java-Klasse für ArrayOfParameters complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfParameters"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Parameter" type="{http://bmc.com/ao/xsd/2008/09/soa}ParameterType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfParameters", propOrder = {
    "parameters"
})
public class ArrayOfParameters
    implements Serializable
{

    private final static long serialVersionUID = -1L;
    @XmlElement(name = "Parameter")
    protected List<ParameterType> parameters;

    /**
     * Gets the value of the parameters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the parameters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParameterType }
     * 
     * 
     */
    public List<ParameterType> getParameters() {
        if (parameters == null) {
            parameters = new ArrayList<ParameterType>();
        }
        return this.parameters;
    }

}
