//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v3.0.0 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2023.06.13 um 02:20:41 PM CEST 
//


package de.telefonica.talend.server;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.telefonica.talend.server package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.telefonica.talend.server
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TicketInfo }
     * 
     */
    public TicketInfo createTicketInfo() {
        return new TicketInfo();
    }

    /**
     * Create an instance of {@link TransactionHeader }
     * 
     */
    public TransactionHeader createTransactionHeader() {
        return new TransactionHeader();
    }

    /**
     * Create an instance of {@link TransactionBody }
     * 
     */
    public TransactionBody createTransactionBody() {
        return new TransactionBody();
    }

    /**
     * Create an instance of {@link TicketResponseList }
     * 
     */
    public TicketResponseList createTicketResponseList() {
        return new TicketResponseList();
    }

    /**
     * Create an instance of {@link TicketResponse }
     * 
     */
    public TicketResponse createTicketResponse() {
        return new TicketResponse();
    }

    /**
     * Create an instance of {@link SupportGroup }
     * 
     */
    public SupportGroup createSupportGroup() {
        return new SupportGroup();
    }

}
