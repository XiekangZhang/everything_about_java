//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v3.0.0 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2023.06.23 um 11:22:45 AM CEST 
//


package com.bmc.ao.xsd._2008._09.soa;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für CancelPolicy.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <pre>
 * &lt;simpleType name="CancelPolicy"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="WITH_COMPENSATION"/&gt;
 *     &lt;enumeration value="WITHOUT_COMPENSATION"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CancelPolicy")
@XmlEnum
public enum CancelPolicy {

    WITH_COMPENSATION,
    WITHOUT_COMPENSATION;

    public String value() {
        return name();
    }

    public static CancelPolicy fromValue(String v) {
        return valueOf(v);
    }

}
