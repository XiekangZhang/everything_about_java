//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v3.0.0 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2023.06.13 um 02:20:41 PM CEST 
//


package de.telefonica.talend.server;

import java.io.Serializable;
import java.util.Calendar;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3._2001.xmlschema.Adapter1;


/**
 * <p>Java-Klasse für TicketResponse complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TicketResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CHANGE_REQUEST_ID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="COORDINATOR_GROUP" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CHANGE_LOCATION" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SERVICE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CI_ID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CI_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="LOCATION_CI" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="TEMPLATE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SUMMARY" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="NOTES" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CRQCLASS" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CHANGE_REASON" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="TARGET_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="IMPACT" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="URGENCY" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="PRIORITY" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="RISK_LEVEL" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="STATUS_REASON" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="MANAGER_GROUP" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="AFFECTED_CUSTOMERS" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SCHEDULED_START_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="ACTUAL_START_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="SCHEDULED_END_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="ACTUAL_END_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="COMPLETED_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="EARLIEST_START_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="REQUESTED_START_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="REQUESTED_END_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="REQUESTED_SA_BEGIN_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="REQUESTED_SA_END_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="DURATION_MINUTES" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="SUBMIT_DATE" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="LAST_MODIFIED_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="IMPLEMENTOR_COMPANY" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="IMPLEMENTOR_ORGANIZATION" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="IMPLEMENTOR_GROUP" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CUSTOMER_COMPANY" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="FAILURE_REASON" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CLOSED_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="REQUESTOR_COMPANY" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SITE_ID_A" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SITE_ID_B" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SITE_Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SERVICE_AFFECTING" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="IS_CHANGE_SUCCESSFUL" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="RELEASE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="RELEASE_TEMPLATE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TicketResponse", propOrder = {
    "changerequestid",
    "coordinatorgroup",
    "changelocation",
    "service",
    "ciid",
    "ciname",
    "locationci",
    "template",
    "summary",
    "notes",
    "crqclass",
    "changereason",
    "targetdate",
    "impact",
    "urgency",
    "priority",
    "risklevel",
    "status",
    "statusreason",
    "managergroup",
    "affectedcustomers",
    "scheduledstartdate",
    "actualstartdate",
    "scheduledenddate",
    "actualenddate",
    "completeddate",
    "earlieststartdate",
    "requestedstartdate",
    "requestedenddate",
    "requestedsabegindate",
    "requestedsaenddate",
    "durationminutes",
    "submitdate",
    "lastmodifieddate",
    "implementorcompany",
    "implementororganization",
    "implementorgroup",
    "customercompany",
    "failurereason",
    "closeddate",
    "requestorcompany",
    "siteida",
    "siteidb",
    "siteName",
    "serviceaffecting",
    "ischangesuccessful",
    "releaseid",
    "releasetemplate"
})
public class TicketResponse
    implements Serializable
{

    private final static long serialVersionUID = -1L;
    @XmlElement(name = "CHANGE_REQUEST_ID", required = true)
    protected String changerequestid;
    @XmlElement(name = "COORDINATOR_GROUP", required = true)
    protected String coordinatorgroup;
    @XmlElement(name = "CHANGE_LOCATION", required = true)
    protected String changelocation;
    @XmlElement(name = "SERVICE", required = true)
    protected String service;
    @XmlElement(name = "CI_ID", required = true)
    protected String ciid;
    @XmlElement(name = "CI_NAME", required = true)
    protected String ciname;
    @XmlElement(name = "LOCATION_CI", required = true)
    protected String locationci;
    @XmlElement(name = "TEMPLATE", required = true)
    protected String template;
    @XmlElement(name = "SUMMARY", required = true)
    protected String summary;
    @XmlElement(name = "NOTES", required = true)
    protected String notes;
    @XmlElement(name = "CRQCLASS", required = true)
    protected String crqclass;
    @XmlElement(name = "CHANGE_REASON", required = true)
    protected String changereason;
    @XmlElement(name = "TARGET_DATE", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar targetdate;
    @XmlElement(name = "IMPACT", required = true)
    protected String impact;
    @XmlElement(name = "URGENCY", required = true)
    protected String urgency;
    @XmlElement(name = "PRIORITY", required = true)
    protected String priority;
    @XmlElement(name = "RISK_LEVEL", required = true)
    protected String risklevel;
    @XmlElement(name = "STATUS", required = true)
    protected String status;
    @XmlElement(name = "STATUS_REASON", required = true)
    protected String statusreason;
    @XmlElement(name = "MANAGER_GROUP", required = true)
    protected String managergroup;
    @XmlElement(name = "AFFECTED_CUSTOMERS", required = true)
    protected String affectedcustomers;
    @XmlElement(name = "SCHEDULED_START_DATE", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar scheduledstartdate;
    @XmlElement(name = "ACTUAL_START_DATE", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar actualstartdate;
    @XmlElement(name = "SCHEDULED_END_DATE", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar scheduledenddate;
    @XmlElement(name = "ACTUAL_END_DATE", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar actualenddate;
    @XmlElement(name = "COMPLETED_DATE", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar completeddate;
    @XmlElement(name = "EARLIEST_START_DATE", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar earlieststartdate;
    @XmlElement(name = "REQUESTED_START_DATE", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar requestedstartdate;
    @XmlElement(name = "REQUESTED_END_DATE", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar requestedenddate;
    @XmlElement(name = "REQUESTED_SA_BEGIN_DATE", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar requestedsabegindate;
    @XmlElement(name = "REQUESTED_SA_END_DATE", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar requestedsaenddate;
    @XmlElement(name = "DURATION_MINUTES")
    protected int durationminutes;
    @XmlElement(name = "SUBMIT_DATE", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar submitdate;
    @XmlElement(name = "LAST_MODIFIED_DATE", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar lastmodifieddate;
    @XmlElement(name = "IMPLEMENTOR_COMPANY", required = true)
    protected String implementorcompany;
    @XmlElement(name = "IMPLEMENTOR_ORGANIZATION", required = true)
    protected String implementororganization;
    @XmlElement(name = "IMPLEMENTOR_GROUP", required = true)
    protected String implementorgroup;
    @XmlElement(name = "CUSTOMER_COMPANY", required = true)
    protected String customercompany;
    @XmlElement(name = "FAILURE_REASON", required = true)
    protected String failurereason;
    @XmlElement(name = "CLOSED_DATE", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar closeddate;
    @XmlElement(name = "REQUESTOR_COMPANY", required = true)
    protected String requestorcompany;
    @XmlElement(name = "SITE_ID_A", required = true)
    protected String siteida;
    @XmlElement(name = "SITE_ID_B", required = true)
    protected String siteidb;
    @XmlElement(name = "SITE_Name", required = true)
    protected String siteName;
    @XmlElement(name = "SERVICE_AFFECTING", required = true)
    protected String serviceaffecting;
    @XmlElement(name = "IS_CHANGE_SUCCESSFUL", required = true)
    protected String ischangesuccessful;
    @XmlElement(name = "RELEASE_ID", required = true)
    protected String releaseid;
    @XmlElement(name = "RELEASE_TEMPLATE", required = true)
    protected String releasetemplate;

    /**
     * Ruft den Wert der changerequestid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCHANGEREQUESTID() {
        return changerequestid;
    }

    /**
     * Legt den Wert der changerequestid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCHANGEREQUESTID(String value) {
        this.changerequestid = value;
    }

    /**
     * Ruft den Wert der coordinatorgroup-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOORDINATORGROUP() {
        return coordinatorgroup;
    }

    /**
     * Legt den Wert der coordinatorgroup-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOORDINATORGROUP(String value) {
        this.coordinatorgroup = value;
    }

    /**
     * Ruft den Wert der changelocation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCHANGELOCATION() {
        return changelocation;
    }

    /**
     * Legt den Wert der changelocation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCHANGELOCATION(String value) {
        this.changelocation = value;
    }

    /**
     * Ruft den Wert der service-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSERVICE() {
        return service;
    }

    /**
     * Legt den Wert der service-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSERVICE(String value) {
        this.service = value;
    }

    /**
     * Ruft den Wert der ciid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCIID() {
        return ciid;
    }

    /**
     * Legt den Wert der ciid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCIID(String value) {
        this.ciid = value;
    }

    /**
     * Ruft den Wert der ciname-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCINAME() {
        return ciname;
    }

    /**
     * Legt den Wert der ciname-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCINAME(String value) {
        this.ciname = value;
    }

    /**
     * Ruft den Wert der locationci-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLOCATIONCI() {
        return locationci;
    }

    /**
     * Legt den Wert der locationci-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLOCATIONCI(String value) {
        this.locationci = value;
    }

    /**
     * Ruft den Wert der template-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTEMPLATE() {
        return template;
    }

    /**
     * Legt den Wert der template-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTEMPLATE(String value) {
        this.template = value;
    }

    /**
     * Ruft den Wert der summary-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSUMMARY() {
        return summary;
    }

    /**
     * Legt den Wert der summary-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSUMMARY(String value) {
        this.summary = value;
    }

    /**
     * Ruft den Wert der notes-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNOTES() {
        return notes;
    }

    /**
     * Legt den Wert der notes-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNOTES(String value) {
        this.notes = value;
    }

    /**
     * Ruft den Wert der crqclass-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCRQCLASS() {
        return crqclass;
    }

    /**
     * Legt den Wert der crqclass-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCRQCLASS(String value) {
        this.crqclass = value;
    }

    /**
     * Ruft den Wert der changereason-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCHANGEREASON() {
        return changereason;
    }

    /**
     * Legt den Wert der changereason-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCHANGEREASON(String value) {
        this.changereason = value;
    }

    /**
     * Ruft den Wert der targetdate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getTARGETDATE() {
        return targetdate;
    }

    /**
     * Legt den Wert der targetdate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTARGETDATE(Calendar value) {
        this.targetdate = value;
    }

    /**
     * Ruft den Wert der impact-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMPACT() {
        return impact;
    }

    /**
     * Legt den Wert der impact-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMPACT(String value) {
        this.impact = value;
    }

    /**
     * Ruft den Wert der urgency-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURGENCY() {
        return urgency;
    }

    /**
     * Legt den Wert der urgency-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURGENCY(String value) {
        this.urgency = value;
    }

    /**
     * Ruft den Wert der priority-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRIORITY() {
        return priority;
    }

    /**
     * Legt den Wert der priority-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRIORITY(String value) {
        this.priority = value;
    }

    /**
     * Ruft den Wert der risklevel-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRISKLEVEL() {
        return risklevel;
    }

    /**
     * Legt den Wert der risklevel-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRISKLEVEL(String value) {
        this.risklevel = value;
    }

    /**
     * Ruft den Wert der status-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTATUS() {
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
    public void setSTATUS(String value) {
        this.status = value;
    }

    /**
     * Ruft den Wert der statusreason-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTATUSREASON() {
        return statusreason;
    }

    /**
     * Legt den Wert der statusreason-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTATUSREASON(String value) {
        this.statusreason = value;
    }

    /**
     * Ruft den Wert der managergroup-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMANAGERGROUP() {
        return managergroup;
    }

    /**
     * Legt den Wert der managergroup-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMANAGERGROUP(String value) {
        this.managergroup = value;
    }

    /**
     * Ruft den Wert der affectedcustomers-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAFFECTEDCUSTOMERS() {
        return affectedcustomers;
    }

    /**
     * Legt den Wert der affectedcustomers-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAFFECTEDCUSTOMERS(String value) {
        this.affectedcustomers = value;
    }

    /**
     * Ruft den Wert der scheduledstartdate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getSCHEDULEDSTARTDATE() {
        return scheduledstartdate;
    }

    /**
     * Legt den Wert der scheduledstartdate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSCHEDULEDSTARTDATE(Calendar value) {
        this.scheduledstartdate = value;
    }

    /**
     * Ruft den Wert der actualstartdate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getACTUALSTARTDATE() {
        return actualstartdate;
    }

    /**
     * Legt den Wert der actualstartdate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACTUALSTARTDATE(Calendar value) {
        this.actualstartdate = value;
    }

    /**
     * Ruft den Wert der scheduledenddate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getSCHEDULEDENDDATE() {
        return scheduledenddate;
    }

    /**
     * Legt den Wert der scheduledenddate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSCHEDULEDENDDATE(Calendar value) {
        this.scheduledenddate = value;
    }

    /**
     * Ruft den Wert der actualenddate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getACTUALENDDATE() {
        return actualenddate;
    }

    /**
     * Legt den Wert der actualenddate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACTUALENDDATE(Calendar value) {
        this.actualenddate = value;
    }

    /**
     * Ruft den Wert der completeddate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getCOMPLETEDDATE() {
        return completeddate;
    }

    /**
     * Legt den Wert der completeddate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOMPLETEDDATE(Calendar value) {
        this.completeddate = value;
    }

    /**
     * Ruft den Wert der earlieststartdate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getEARLIESTSTARTDATE() {
        return earlieststartdate;
    }

    /**
     * Legt den Wert der earlieststartdate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEARLIESTSTARTDATE(Calendar value) {
        this.earlieststartdate = value;
    }

    /**
     * Ruft den Wert der requestedstartdate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getREQUESTEDSTARTDATE() {
        return requestedstartdate;
    }

    /**
     * Legt den Wert der requestedstartdate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREQUESTEDSTARTDATE(Calendar value) {
        this.requestedstartdate = value;
    }

    /**
     * Ruft den Wert der requestedenddate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getREQUESTEDENDDATE() {
        return requestedenddate;
    }

    /**
     * Legt den Wert der requestedenddate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREQUESTEDENDDATE(Calendar value) {
        this.requestedenddate = value;
    }

    /**
     * Ruft den Wert der requestedsabegindate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getREQUESTEDSABEGINDATE() {
        return requestedsabegindate;
    }

    /**
     * Legt den Wert der requestedsabegindate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREQUESTEDSABEGINDATE(Calendar value) {
        this.requestedsabegindate = value;
    }

    /**
     * Ruft den Wert der requestedsaenddate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getREQUESTEDSAENDDATE() {
        return requestedsaenddate;
    }

    /**
     * Legt den Wert der requestedsaenddate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREQUESTEDSAENDDATE(Calendar value) {
        this.requestedsaenddate = value;
    }

    /**
     * Ruft den Wert der durationminutes-Eigenschaft ab.
     * 
     */
    public int getDURATIONMINUTES() {
        return durationminutes;
    }

    /**
     * Legt den Wert der durationminutes-Eigenschaft fest.
     * 
     */
    public void setDURATIONMINUTES(int value) {
        this.durationminutes = value;
    }

    /**
     * Ruft den Wert der submitdate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSUBMITDATE() {
        return submitdate;
    }

    /**
     * Legt den Wert der submitdate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSUBMITDATE(XMLGregorianCalendar value) {
        this.submitdate = value;
    }

    /**
     * Ruft den Wert der lastmodifieddate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getLASTMODIFIEDDATE() {
        return lastmodifieddate;
    }

    /**
     * Legt den Wert der lastmodifieddate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLASTMODIFIEDDATE(Calendar value) {
        this.lastmodifieddate = value;
    }

    /**
     * Ruft den Wert der implementorcompany-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMPLEMENTORCOMPANY() {
        return implementorcompany;
    }

    /**
     * Legt den Wert der implementorcompany-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMPLEMENTORCOMPANY(String value) {
        this.implementorcompany = value;
    }

    /**
     * Ruft den Wert der implementororganization-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMPLEMENTORORGANIZATION() {
        return implementororganization;
    }

    /**
     * Legt den Wert der implementororganization-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMPLEMENTORORGANIZATION(String value) {
        this.implementororganization = value;
    }

    /**
     * Ruft den Wert der implementorgroup-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMPLEMENTORGROUP() {
        return implementorgroup;
    }

    /**
     * Legt den Wert der implementorgroup-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMPLEMENTORGROUP(String value) {
        this.implementorgroup = value;
    }

    /**
     * Ruft den Wert der customercompany-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCUSTOMERCOMPANY() {
        return customercompany;
    }

    /**
     * Legt den Wert der customercompany-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCUSTOMERCOMPANY(String value) {
        this.customercompany = value;
    }

    /**
     * Ruft den Wert der failurereason-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFAILUREREASON() {
        return failurereason;
    }

    /**
     * Legt den Wert der failurereason-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFAILUREREASON(String value) {
        this.failurereason = value;
    }

    /**
     * Ruft den Wert der closeddate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getCLOSEDDATE() {
        return closeddate;
    }

    /**
     * Legt den Wert der closeddate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCLOSEDDATE(Calendar value) {
        this.closeddate = value;
    }

    /**
     * Ruft den Wert der requestorcompany-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREQUESTORCOMPANY() {
        return requestorcompany;
    }

    /**
     * Legt den Wert der requestorcompany-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREQUESTORCOMPANY(String value) {
        this.requestorcompany = value;
    }

    /**
     * Ruft den Wert der siteida-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSITEIDA() {
        return siteida;
    }

    /**
     * Legt den Wert der siteida-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSITEIDA(String value) {
        this.siteida = value;
    }

    /**
     * Ruft den Wert der siteidb-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSITEIDB() {
        return siteidb;
    }

    /**
     * Legt den Wert der siteidb-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSITEIDB(String value) {
        this.siteidb = value;
    }

    /**
     * Ruft den Wert der siteName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSITEName() {
        return siteName;
    }

    /**
     * Legt den Wert der siteName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSITEName(String value) {
        this.siteName = value;
    }

    /**
     * Ruft den Wert der serviceaffecting-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSERVICEAFFECTING() {
        return serviceaffecting;
    }

    /**
     * Legt den Wert der serviceaffecting-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSERVICEAFFECTING(String value) {
        this.serviceaffecting = value;
    }

    /**
     * Ruft den Wert der ischangesuccessful-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getISCHANGESUCCESSFUL() {
        return ischangesuccessful;
    }

    /**
     * Legt den Wert der ischangesuccessful-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setISCHANGESUCCESSFUL(String value) {
        this.ischangesuccessful = value;
    }

    /**
     * Ruft den Wert der releaseid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRELEASEID() {
        return releaseid;
    }

    /**
     * Legt den Wert der releaseid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRELEASEID(String value) {
        this.releaseid = value;
    }

    /**
     * Ruft den Wert der releasetemplate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRELEASETEMPLATE() {
        return releasetemplate;
    }

    /**
     * Legt den Wert der releasetemplate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRELEASETEMPLATE(String value) {
        this.releasetemplate = value;
    }

}
