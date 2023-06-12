package de.xiekang.route;

import de.telefonica.talend.server.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppsoluteRouteTest {

    @Test
    void marshal() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(TicketInfo.class);

        // supportgroup
        SupportGroup supportGroup = new SupportGroup();
        supportGroup.getSupportGroups().add("Test1");
        supportGroup.getSupportGroups().add("Test2");

        // body
        TransactionBody transactionBody = new TransactionBody();
        transactionBody.setSupportGroupList(supportGroup);

        TicketInfo ticketInfo = new TicketInfo();
        TransactionHeader transactionHeader = new TransactionHeader();
        transactionHeader.setSource("Appsolute");
        transactionHeader.setDestination("UTS");
        transactionHeader.setMethod("getINCSupportGroupDetails");

        ticketInfo.setTransactionHeader(transactionHeader);
        ticketInfo.setTransactionBody(transactionBody);

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(ticketInfo, new File("./src/test/resources/ticketInfo.xml"));
    }

    @Test
    void marshall1() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(TicketResponse.class);

    }
}