//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v3.0.0 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2023.06.12 um 02:00:59 PM CEST 
//


package de.telefonica.talend.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


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
 *         &lt;element name="CHANGE_REQUEST_ID" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="COORDINATOR_GROUP" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CHANGE_LOCATION" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SERVICE" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CI_ID" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CI_NAME" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="LOCATION_CI" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="TEMPLATE" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SUMMARY" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="NOTES" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CRQCLASS" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CHANGE_REASON" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="TARGET_DATE" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="IMPACT" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="URGENCY" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="PRIORITY" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="RISK_LEVEL" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="STATUS_REASON" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="MANAGER_GROUP" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="AFFECTED_CUSTOMERS" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SCHEDULED_START_DATE" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ACTUAL_START_DATE" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SCHEDULED_END_DATE" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ACTUAL_END_DATE" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="COMPLETED_DATE" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="EARLIEST_START_DATE" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="REQUESTED_START_DATE" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="REQUESTED_END_DATE" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="REQUESTED_SA_BEGIN_DATE" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="REQUESTED_SA_END_DATE" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="DURATION_MINUTES" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SUBMIT_DATE" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="LAST_MODIFIED_DATE" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="IMPLEMENTOR_COMPANY" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="IMPLEMENTOR_ORGANIZATION" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="IMPLEMENTOR_GROUP" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CUSTOMER_COMPANY" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="FAILURE_REASON" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CLOSED_DATE" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="REQUESTOR_COMPANY" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SITE_ID_A" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SITE_ID_B" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SITE_Name" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SERVICE_AFFECTING" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="IS_CHANGE_SUCCESSFUL" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="RELEASE_ID" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="RELEASE_TEMPLATE" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "changerequestids",
    "coordinatorgroups",
    "changelocations",
    "services",
    "ciids",
    "cinames",
    "locationcis",
    "templates",
    "summaries",
    "notes",
    "crqclasses",
    "changereasons",
    "targetdates",
    "impacts",
    "urgencies",
    "priorities",
    "risklevels",
    "statuses",
    "statusreasons",
    "managergroups",
    "affectedcustomers",
    "scheduledstartdates",
    "actualstartdates",
    "scheduledenddates",
    "actualenddates",
    "completeddates",
    "earlieststartdates",
    "requestedstartdates",
    "requestedenddates",
    "requestedsabegindates",
    "requestedsaenddates",
    "durationminutes",
    "submitdates",
    "lastmodifieddates",
    "implementorcompanies",
    "implementororganizations",
    "implementorgroups",
    "customercompanies",
    "failurereasons",
    "closeddates",
    "requestorcompanies",
    "siteidas",
    "siteidbs",
    "siteNames",
    "serviceaffectings",
    "ischangesuccessfuls",
    "releaseids",
    "releasetemplates"
})
@XmlRootElement(name = "ticketResponse")
public class TicketResponse
    implements Serializable
{

    private final static long serialVersionUID = -1L;
    @XmlElement(name = "CHANGE_REQUEST_ID")
    protected List<String> changerequestids;
    @XmlElement(name = "COORDINATOR_GROUP")
    protected List<String> coordinatorgroups;
    @XmlElement(name = "CHANGE_LOCATION")
    protected List<String> changelocations;
    @XmlElement(name = "SERVICE")
    protected List<String> services;
    @XmlElement(name = "CI_ID")
    protected List<String> ciids;
    @XmlElement(name = "CI_NAME")
    protected List<String> cinames;
    @XmlElement(name = "LOCATION_CI")
    protected List<String> locationcis;
    @XmlElement(name = "TEMPLATE")
    protected List<String> templates;
    @XmlElement(name = "SUMMARY")
    protected List<String> summaries;
    @XmlElement(name = "NOTES")
    protected List<String> notes;
    @XmlElement(name = "CRQCLASS")
    protected List<String> crqclasses;
    @XmlElement(name = "CHANGE_REASON")
    protected List<String> changereasons;
    @XmlElement(name = "TARGET_DATE")
    @XmlSchemaType(name = "date")
    protected List<XMLGregorianCalendar> targetdates;
    @XmlElement(name = "IMPACT")
    protected List<String> impacts;
    @XmlElement(name = "URGENCY")
    protected List<String> urgencies;
    @XmlElement(name = "PRIORITY")
    protected List<String> priorities;
    @XmlElement(name = "RISK_LEVEL")
    protected List<String> risklevels;
    @XmlElement(name = "STATUS")
    protected List<String> statuses;
    @XmlElement(name = "STATUS_REASON")
    protected List<String> statusreasons;
    @XmlElement(name = "MANAGER_GROUP")
    protected List<String> managergroups;
    @XmlElement(name = "AFFECTED_CUSTOMERS")
    protected List<String> affectedcustomers;
    @XmlElement(name = "SCHEDULED_START_DATE")
    @XmlSchemaType(name = "date")
    protected List<XMLGregorianCalendar> scheduledstartdates;
    @XmlElement(name = "ACTUAL_START_DATE")
    @XmlSchemaType(name = "date")
    protected List<XMLGregorianCalendar> actualstartdates;
    @XmlElement(name = "SCHEDULED_END_DATE")
    @XmlSchemaType(name = "date")
    protected List<XMLGregorianCalendar> scheduledenddates;
    @XmlElement(name = "ACTUAL_END_DATE")
    @XmlSchemaType(name = "date")
    protected List<XMLGregorianCalendar> actualenddates;
    @XmlElement(name = "COMPLETED_DATE")
    @XmlSchemaType(name = "date")
    protected List<XMLGregorianCalendar> completeddates;
    @XmlElement(name = "EARLIEST_START_DATE")
    @XmlSchemaType(name = "date")
    protected List<XMLGregorianCalendar> earlieststartdates;
    @XmlElement(name = "REQUESTED_START_DATE")
    @XmlSchemaType(name = "date")
    protected List<XMLGregorianCalendar> requestedstartdates;
    @XmlElement(name = "REQUESTED_END_DATE")
    @XmlSchemaType(name = "date")
    protected List<XMLGregorianCalendar> requestedenddates;
    @XmlElement(name = "REQUESTED_SA_BEGIN_DATE")
    @XmlSchemaType(name = "date")
    protected List<XMLGregorianCalendar> requestedsabegindates;
    @XmlElement(name = "REQUESTED_SA_END_DATE")
    @XmlSchemaType(name = "date")
    protected List<XMLGregorianCalendar> requestedsaenddates;
    @XmlElement(name = "DURATION_MINUTES", type = Integer.class)
    protected List<Integer> durationminutes;
    @XmlElement(name = "SUBMIT_DATE")
    @XmlSchemaType(name = "date")
    protected List<XMLGregorianCalendar> submitdates;
    @XmlElement(name = "LAST_MODIFIED_DATE")
    @XmlSchemaType(name = "date")
    protected List<XMLGregorianCalendar> lastmodifieddates;
    @XmlElement(name = "IMPLEMENTOR_COMPANY")
    protected List<String> implementorcompanies;
    @XmlElement(name = "IMPLEMENTOR_ORGANIZATION")
    protected List<String> implementororganizations;
    @XmlElement(name = "IMPLEMENTOR_GROUP")
    protected List<String> implementorgroups;
    @XmlElement(name = "CUSTOMER_COMPANY")
    protected List<String> customercompanies;
    @XmlElement(name = "FAILURE_REASON")
    protected List<String> failurereasons;
    @XmlElement(name = "CLOSED_DATE")
    @XmlSchemaType(name = "date")
    protected List<XMLGregorianCalendar> closeddates;
    @XmlElement(name = "REQUESTOR_COMPANY")
    protected List<String> requestorcompanies;
    @XmlElement(name = "SITE_ID_A")
    protected List<String> siteidas;
    @XmlElement(name = "SITE_ID_B")
    protected List<String> siteidbs;
    @XmlElement(name = "SITE_Name")
    protected List<String> siteNames;
    @XmlElement(name = "SERVICE_AFFECTING")
    protected List<String> serviceaffectings;
    @XmlElement(name = "IS_CHANGE_SUCCESSFUL")
    protected List<String> ischangesuccessfuls;
    @XmlElement(name = "RELEASE_ID")
    protected List<String> releaseids;
    @XmlElement(name = "RELEASE_TEMPLATE")
    protected List<String> releasetemplates;

    /**
     * Gets the value of the changerequestids property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the changerequestids property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCHANGEREQUESTIDS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCHANGEREQUESTIDS() {
        if (changerequestids == null) {
            changerequestids = new ArrayList<String>();
        }
        return this.changerequestids;
    }

    /**
     * Gets the value of the coordinatorgroups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the coordinatorgroups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCOORDINATORGROUPS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCOORDINATORGROUPS() {
        if (coordinatorgroups == null) {
            coordinatorgroups = new ArrayList<String>();
        }
        return this.coordinatorgroups;
    }

    /**
     * Gets the value of the changelocations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the changelocations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCHANGELOCATIONS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCHANGELOCATIONS() {
        if (changelocations == null) {
            changelocations = new ArrayList<String>();
        }
        return this.changelocations;
    }

    /**
     * Gets the value of the services property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the services property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSERVICES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSERVICES() {
        if (services == null) {
            services = new ArrayList<String>();
        }
        return this.services;
    }

    /**
     * Gets the value of the ciids property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the ciids property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCIIDS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCIIDS() {
        if (ciids == null) {
            ciids = new ArrayList<String>();
        }
        return this.ciids;
    }

    /**
     * Gets the value of the cinames property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the cinames property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCINAMES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCINAMES() {
        if (cinames == null) {
            cinames = new ArrayList<String>();
        }
        return this.cinames;
    }

    /**
     * Gets the value of the locationcis property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the locationcis property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLOCATIONCIS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLOCATIONCIS() {
        if (locationcis == null) {
            locationcis = new ArrayList<String>();
        }
        return this.locationcis;
    }

    /**
     * Gets the value of the templates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the templates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTEMPLATES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTEMPLATES() {
        if (templates == null) {
            templates = new ArrayList<String>();
        }
        return this.templates;
    }

    /**
     * Gets the value of the summaries property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the summaries property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSUMMARIES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSUMMARIES() {
        if (summaries == null) {
            summaries = new ArrayList<String>();
        }
        return this.summaries;
    }

    /**
     * Gets the value of the notes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the notes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNOTES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNOTES() {
        if (notes == null) {
            notes = new ArrayList<String>();
        }
        return this.notes;
    }

    /**
     * Gets the value of the crqclasses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the crqclasses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCRQCLASSES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCRQCLASSES() {
        if (crqclasses == null) {
            crqclasses = new ArrayList<String>();
        }
        return this.crqclasses;
    }

    /**
     * Gets the value of the changereasons property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the changereasons property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCHANGEREASONS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCHANGEREASONS() {
        if (changereasons == null) {
            changereasons = new ArrayList<String>();
        }
        return this.changereasons;
    }

    /**
     * Gets the value of the targetdates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the targetdates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTARGETDATES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getTARGETDATES() {
        if (targetdates == null) {
            targetdates = new ArrayList<XMLGregorianCalendar>();
        }
        return this.targetdates;
    }

    /**
     * Gets the value of the impacts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the impacts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIMPACTS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIMPACTS() {
        if (impacts == null) {
            impacts = new ArrayList<String>();
        }
        return this.impacts;
    }

    /**
     * Gets the value of the urgencies property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the urgencies property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getURGENCIES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getURGENCIES() {
        if (urgencies == null) {
            urgencies = new ArrayList<String>();
        }
        return this.urgencies;
    }

    /**
     * Gets the value of the priorities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the priorities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPRIORITIES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPRIORITIES() {
        if (priorities == null) {
            priorities = new ArrayList<String>();
        }
        return this.priorities;
    }

    /**
     * Gets the value of the risklevels property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the risklevels property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRISKLEVELS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRISKLEVELS() {
        if (risklevels == null) {
            risklevels = new ArrayList<String>();
        }
        return this.risklevels;
    }

    /**
     * Gets the value of the statuses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the statuses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSTATUSES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSTATUSES() {
        if (statuses == null) {
            statuses = new ArrayList<String>();
        }
        return this.statuses;
    }

    /**
     * Gets the value of the statusreasons property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the statusreasons property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSTATUSREASONS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSTATUSREASONS() {
        if (statusreasons == null) {
            statusreasons = new ArrayList<String>();
        }
        return this.statusreasons;
    }

    /**
     * Gets the value of the managergroups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the managergroups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMANAGERGROUPS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMANAGERGROUPS() {
        if (managergroups == null) {
            managergroups = new ArrayList<String>();
        }
        return this.managergroups;
    }

    /**
     * Gets the value of the affectedcustomers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the affectedcustomers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAFFECTEDCUSTOMERS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAFFECTEDCUSTOMERS() {
        if (affectedcustomers == null) {
            affectedcustomers = new ArrayList<String>();
        }
        return this.affectedcustomers;
    }

    /**
     * Gets the value of the scheduledstartdates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the scheduledstartdates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSCHEDULEDSTARTDATES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getSCHEDULEDSTARTDATES() {
        if (scheduledstartdates == null) {
            scheduledstartdates = new ArrayList<XMLGregorianCalendar>();
        }
        return this.scheduledstartdates;
    }

    /**
     * Gets the value of the actualstartdates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the actualstartdates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getACTUALSTARTDATES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getACTUALSTARTDATES() {
        if (actualstartdates == null) {
            actualstartdates = new ArrayList<XMLGregorianCalendar>();
        }
        return this.actualstartdates;
    }

    /**
     * Gets the value of the scheduledenddates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the scheduledenddates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSCHEDULEDENDDATES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getSCHEDULEDENDDATES() {
        if (scheduledenddates == null) {
            scheduledenddates = new ArrayList<XMLGregorianCalendar>();
        }
        return this.scheduledenddates;
    }

    /**
     * Gets the value of the actualenddates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the actualenddates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getACTUALENDDATES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getACTUALENDDATES() {
        if (actualenddates == null) {
            actualenddates = new ArrayList<XMLGregorianCalendar>();
        }
        return this.actualenddates;
    }

    /**
     * Gets the value of the completeddates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the completeddates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCOMPLETEDDATES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getCOMPLETEDDATES() {
        if (completeddates == null) {
            completeddates = new ArrayList<XMLGregorianCalendar>();
        }
        return this.completeddates;
    }

    /**
     * Gets the value of the earlieststartdates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the earlieststartdates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEARLIESTSTARTDATES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getEARLIESTSTARTDATES() {
        if (earlieststartdates == null) {
            earlieststartdates = new ArrayList<XMLGregorianCalendar>();
        }
        return this.earlieststartdates;
    }

    /**
     * Gets the value of the requestedstartdates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the requestedstartdates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getREQUESTEDSTARTDATES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getREQUESTEDSTARTDATES() {
        if (requestedstartdates == null) {
            requestedstartdates = new ArrayList<XMLGregorianCalendar>();
        }
        return this.requestedstartdates;
    }

    /**
     * Gets the value of the requestedenddates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the requestedenddates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getREQUESTEDENDDATES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getREQUESTEDENDDATES() {
        if (requestedenddates == null) {
            requestedenddates = new ArrayList<XMLGregorianCalendar>();
        }
        return this.requestedenddates;
    }

    /**
     * Gets the value of the requestedsabegindates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the requestedsabegindates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getREQUESTEDSABEGINDATES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getREQUESTEDSABEGINDATES() {
        if (requestedsabegindates == null) {
            requestedsabegindates = new ArrayList<XMLGregorianCalendar>();
        }
        return this.requestedsabegindates;
    }

    /**
     * Gets the value of the requestedsaenddates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the requestedsaenddates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getREQUESTEDSAENDDATES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getREQUESTEDSAENDDATES() {
        if (requestedsaenddates == null) {
            requestedsaenddates = new ArrayList<XMLGregorianCalendar>();
        }
        return this.requestedsaenddates;
    }

    /**
     * Gets the value of the durationminutes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the durationminutes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDURATIONMINUTES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getDURATIONMINUTES() {
        if (durationminutes == null) {
            durationminutes = new ArrayList<Integer>();
        }
        return this.durationminutes;
    }

    /**
     * Gets the value of the submitdates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the submitdates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSUBMITDATES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getSUBMITDATES() {
        if (submitdates == null) {
            submitdates = new ArrayList<XMLGregorianCalendar>();
        }
        return this.submitdates;
    }

    /**
     * Gets the value of the lastmodifieddates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the lastmodifieddates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLASTMODIFIEDDATES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getLASTMODIFIEDDATES() {
        if (lastmodifieddates == null) {
            lastmodifieddates = new ArrayList<XMLGregorianCalendar>();
        }
        return this.lastmodifieddates;
    }

    /**
     * Gets the value of the implementorcompanies property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the implementorcompanies property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIMPLEMENTORCOMPANIES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIMPLEMENTORCOMPANIES() {
        if (implementorcompanies == null) {
            implementorcompanies = new ArrayList<String>();
        }
        return this.implementorcompanies;
    }

    /**
     * Gets the value of the implementororganizations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the implementororganizations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIMPLEMENTORORGANIZATIONS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIMPLEMENTORORGANIZATIONS() {
        if (implementororganizations == null) {
            implementororganizations = new ArrayList<String>();
        }
        return this.implementororganizations;
    }

    /**
     * Gets the value of the implementorgroups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the implementorgroups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIMPLEMENTORGROUPS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIMPLEMENTORGROUPS() {
        if (implementorgroups == null) {
            implementorgroups = new ArrayList<String>();
        }
        return this.implementorgroups;
    }

    /**
     * Gets the value of the customercompanies property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the customercompanies property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCUSTOMERCOMPANIES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCUSTOMERCOMPANIES() {
        if (customercompanies == null) {
            customercompanies = new ArrayList<String>();
        }
        return this.customercompanies;
    }

    /**
     * Gets the value of the failurereasons property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the failurereasons property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFAILUREREASONS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFAILUREREASONS() {
        if (failurereasons == null) {
            failurereasons = new ArrayList<String>();
        }
        return this.failurereasons;
    }

    /**
     * Gets the value of the closeddates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the closeddates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCLOSEDDATES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getCLOSEDDATES() {
        if (closeddates == null) {
            closeddates = new ArrayList<XMLGregorianCalendar>();
        }
        return this.closeddates;
    }

    /**
     * Gets the value of the requestorcompanies property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the requestorcompanies property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getREQUESTORCOMPANIES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getREQUESTORCOMPANIES() {
        if (requestorcompanies == null) {
            requestorcompanies = new ArrayList<String>();
        }
        return this.requestorcompanies;
    }

    /**
     * Gets the value of the siteidas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the siteidas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSITEIDAS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSITEIDAS() {
        if (siteidas == null) {
            siteidas = new ArrayList<String>();
        }
        return this.siteidas;
    }

    /**
     * Gets the value of the siteidbs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the siteidbs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSITEIDBS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSITEIDBS() {
        if (siteidbs == null) {
            siteidbs = new ArrayList<String>();
        }
        return this.siteidbs;
    }

    /**
     * Gets the value of the siteNames property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the siteNames property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSITENames().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSITENames() {
        if (siteNames == null) {
            siteNames = new ArrayList<String>();
        }
        return this.siteNames;
    }

    /**
     * Gets the value of the serviceaffectings property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the serviceaffectings property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSERVICEAFFECTINGS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSERVICEAFFECTINGS() {
        if (serviceaffectings == null) {
            serviceaffectings = new ArrayList<String>();
        }
        return this.serviceaffectings;
    }

    /**
     * Gets the value of the ischangesuccessfuls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the ischangesuccessfuls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getISCHANGESUCCESSFULS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getISCHANGESUCCESSFULS() {
        if (ischangesuccessfuls == null) {
            ischangesuccessfuls = new ArrayList<String>();
        }
        return this.ischangesuccessfuls;
    }

    /**
     * Gets the value of the releaseids property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the releaseids property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRELEASEIDS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRELEASEIDS() {
        if (releaseids == null) {
            releaseids = new ArrayList<String>();
        }
        return this.releaseids;
    }

    /**
     * Gets the value of the releasetemplates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the releasetemplates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRELEASETEMPLATES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRELEASETEMPLATES() {
        if (releasetemplates == null) {
            releasetemplates = new ArrayList<String>();
        }
        return this.releasetemplates;
    }

}
