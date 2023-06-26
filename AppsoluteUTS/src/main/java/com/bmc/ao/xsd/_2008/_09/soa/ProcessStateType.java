//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v3.0.0 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2023.06.26 um 02:16:28 PM CEST 
//


package com.bmc.ao.xsd._2008._09.soa;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für ProcessStateType.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <pre>
 * &lt;simpleType name="ProcessStateType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="READY"/&gt;
 *     &lt;enumeration value="PENDING"/&gt;
 *     &lt;enumeration value="ASSIGNED"/&gt;
 *     &lt;enumeration value="IN_PROGRESS"/&gt;
 *     &lt;enumeration value="PAUSED"/&gt;
 *     &lt;enumeration value="COMPLETED"/&gt;
 *     &lt;enumeration value="COMPENSATED"/&gt;
 *     &lt;enumeration value="CANCELLED"/&gt;
 *     &lt;enumeration value="PENDING_REASSIGNMENT"/&gt;
 *     &lt;enumeration value="FAILED"/&gt;
 *     &lt;enumeration value="ABORTED"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ProcessStateType")
@XmlEnum
public enum ProcessStateType {

    READY,
    PENDING,
    ASSIGNED,
    IN_PROGRESS,
    PAUSED,
    COMPLETED,
    COMPENSATED,
    CANCELLED,
    PENDING_REASSIGNMENT,
    FAILED,
    ABORTED;

    public String value() {
        return name();
    }

    public static ProcessStateType fromValue(String v) {
        return valueOf(v);
    }

}
