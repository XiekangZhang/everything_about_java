package de.xiekang.route;

import de.telefonica.talend.server.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        JAXBContext jaxbContext = JAXBContext.newInstance(TicketResponseList.class);
        TicketResponseList ticketResponseList = new TicketResponseList();

        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setCHANGEREQUESTID("bbbbbb");
        ticketResponse.setCOORDINATORGROUP("bbbbbb");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        ticketResponse.setACTUALENDDATE(calendar);

        TicketResponse ticketResponse1 = new TicketResponse();
        ticketResponse1.setCHANGEREQUESTID("bbbbbbb2");
        ticketResponse1.setCOORDINATORGROUP("bbbbbb2");
        calendar.add(Calendar.MONTH, -2);
        ticketResponse1.setACTUALENDDATE(calendar);
        System.out.println(ticketResponse1.getACTUALENDDATE());

        ticketResponseList.getTicketResponses().add(ticketResponse);
        ticketResponseList.getTicketResponses().add(ticketResponse1);

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(ticketResponseList, new File("./src/test/resources/ticketInfoResponse.xml"));
    }

    // for talend test job
    @Test
    void AppsoluteToUTS() throws JAXBException {
        String request = "<ser:ticketInfo xmlns:ser=\"http://server.talend.telefonica.de/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "         <ser:transactionHeader>\n" +
                "               <ser:source>Appsolute</ser:source>\n" +
                "               <ser:destination>UTS</ser:destination>\n" +
                "               <ser:method>getINCSupportGroupDetails</ser:method>\n" +
                "            </ser:transactionHeader>\n" +
                "            <ser:transactionBody>\n" +
                "               <!--Zero or more repetitions:-->\n" +
                "               <ser:supportGroupList>\n" +
                "                  <ser:supportGroup>Abel_GU-00-Eingang</ser:supportGroup>\n" +
                "                  <!--ser:supportGroup>test1</ser:supportGroup-->\n" +
                "                  <!--ser:supportGroup>test2</ser:supportGroup-->\n" +
                "               </ser:supportGroupList>\n" +
                "            </ser:transactionBody>\n" +
                "      </ser:ticketInfo>";

        JAXBContext jaxbContext = JAXBContext.newInstance(TicketInfo.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        TicketInfo ticketInfo = (TicketInfo) unmarshaller.unmarshal(new ByteArrayInputStream(request.getBytes()));
        List<String> groupList = ticketInfo.getTransactionBody().getSupportGroupList().getSupportGroups();
        groupList = groupList.stream().map(group -> "\'" + group + "\'").collect(Collectors.toList());
        String groupListString = groupList.stream().collect(Collectors.joining(", "));
        System.out.println(groupListString);
    }

    // functionality test
    @Test
    void FunctionTest() {

    }
}