package de.telefonica.talend.server;

import de.telefonica.talend.utils.Utils;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

class TicketInfoTest {

    @Test
    void TicketInfoUnmarshalTest() throws JAXBException {
        TicketInfo ticketInfo = new TicketInfo();
        TransactionHeader transactionHeader = new TransactionHeader();
        TransactionBody transactionBody = new TransactionBody();

        transactionHeader.setSource("Appsolute");
        transactionHeader.setDestination("UTS");
        transactionHeader.setModule("IncidentWorkInfo");
        transactionHeader.setCommand("requestRead");

        transactionBody.getTicketNumbers().add("INC0001");
        transactionBody.getTicketNumbers().add("INC0002");

        ticketInfo.setTransactionHeader(transactionHeader);
        ticketInfo.setTransactionBody(transactionBody);

        JAXBContext jaxbContext = JAXBContext.newInstance(TicketInfo.class);
        jaxbContext.createMarshaller().marshal(ticketInfo, new File("C:\\dev\\workspace\\AppsoluteUTS\\src\\test\\resources\\ticketInfo.xml"));
    }

    @Test
    void TicketInfoMarshalTest() throws JAXBException, IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException {
        String request =
                "      <soa:executeProcess xmlns:soa=\"http://bmc.com/ao/xsd/2008/09/soa\">\n" +
                        "         <soa:gridName>UTSE2EGRID</soa:gridName>\n" +
                        "         <soa:processName>:UTS_GWS_Read:getIncidentDetailsForINC</soa:processName>\n" +
                        "         <soa:parameters>\n" +
                        "            <soa:Input>\n" +
                        "               <soa:Parameter>\n" +
                        "                  <soa:Name>soapRequest</soa:Name>\n" +
                        "                  <soa:Value>\n" +
                        "                     <soa:XmlDoc type=\"xs:anyType\">\n" +
                        "                        <TicketInfo>\n" +
                        "                           <TransactionHeader>\n" +
                        "                              <Source>Appsolute</Source>\n" +
                        "                              <Destination>UTS</Destination>\n" +
                        "                              <Module>IncidentWorkInfo</Module>\n" +
                        "                              <Command>RequestRead</Command>\n" +
                        "                           </TransactionHeader>\n" +
                        "                           <TransactionBody>\n" +
                        "                                  <TicketNumber>INC000001761835</TicketNumber>\n" +
                        "                                  <TicketNumber>INC000001761839</TicketNumber>\n" +
                        "                           </TransactionBody>\n" +
                        "                        </TicketInfo>\n" +
                        "                     </soa:XmlDoc>\n" +
                        "                  </soa:Value>\n" +
                        "               </soa:Parameter>\n" +
                        "            </soa:Input>\n" +
                        "         </soa:parameters>\n" +
                        "      </soa:executeProcess>\n";

        // 1st: read whole request
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new ByteArrayInputStream(request.getBytes()));
        String test = Utils.parseXML(document, "TicketInfo");

        // 2nd: initialization
        //String test = Utils.parseXML(document, "TicketInfo");
        JAXBContext jaxbContext = JAXBContext.newInstance(TicketInfo.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        TicketInfo ticketInfo = (TicketInfo) unmarshaller.unmarshal(new ByteArrayInputStream(test.getBytes()));
        ticketInfo.getTransactionBody().getTicketNumbers().forEach(System.out::println);
    }

}