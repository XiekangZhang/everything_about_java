package de.xiekang.route;

import de.telefonica.talend.server.SupportGroup;
import de.telefonica.talend.server.TicketInfo;
import de.telefonica.talend.server.TicketResponse;
import de.telefonica.talend.server.TicketResponseList;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfEndpoint;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AppsoluteRoute extends RouteBuilder {

    private CxfEndpoint getCxfEndpoint(String uri, boolean isProvider,
                                       boolean useAuthorization, boolean usePropagateSamlAP, String... token) {
        final org.apache.camel.component.cxf.CxfEndpoint cxfEndpoint =
                (org.apache.camel.component.cxf.CxfEndpoint) endpoint(uri);

        return cxfEndpoint;
    }

    @Override
    public void configure() throws Exception {
        CxfEndpoint endpoint = getCxfEndpoint(
                "cxf://" + "http://localhost:8040/services/appsolute" + "?dataFormat=PAYLOAD" + "&allowStreaming=false"
                        + "&wsdlURL=" + "C:/dev/workspace/AppsoluteCamel/src/main/resources/appsolute_talend.wsdl"
                        + "&serviceName=" + "{http://server.talend.telefonica.de/}TransactionServerService"
                        + "&endpointName=" + "{http://server.talend.telefonica.de/}TransactionServerPort",
                true, false, false, (String[]) null);

        from(endpoint).convertBodyTo(String.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println(exchange.getIn().getHeader("operationName"));
                        JAXBContext jaxbContext = JAXBContext.newInstance(TicketInfo.class);
                        TicketInfo ticketInfo = (TicketInfo) jaxbContext.createUnmarshaller().unmarshal(
                                new ByteArrayInputStream(exchange.getIn().getBody().toString().getBytes())
                        );
                        List<String> supportGroupList = ticketInfo.getTransactionBody().getSupportGroupList().getSupportGroups();
                        // todo: update the extracted group list in body
                        // todo: give the body further into DI job
                        System.out.println(exchange.getIn().getBody().toString());
                        System.out.println("found following support group list: (" + supportGroupList.stream().collect(Collectors.joining(", ")) + ")");

                        // placeholder for DI Job
                    }
                })
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        // Response
                        JAXBContext jaxbContext = JAXBContext.newInstance(TicketResponseList.class);

                        TicketResponseList ticketResponseList = new TicketResponseList();
                        TicketResponse ticketResponse = new TicketResponse();
                        ticketResponse.setCHANGEREQUESTID("1");
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(new Date());
                        ticketResponse.setACTUALSTARTDATE(calendar);
                        TicketResponse ticketResponse1 = new TicketResponse();
                        ticketResponse1.setCHANGEREQUESTID("2");
                        calendar.add(Calendar.HOUR, -3);
                        ticketResponse1.setACTUALENDDATE(calendar);

                        ticketResponseList.getTicketResponses().add(ticketResponse);
                        ticketResponseList.getTicketResponses().add(ticketResponse1);

                        Marshaller marshaller = jaxbContext.createMarshaller();
                        StringWriter stringWriter = new StringWriter();

                        marshaller.marshal(ticketResponseList, stringWriter);
                        System.out.println(stringWriter);
                        String response = stringWriter.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
                        exchange.getIn().setBody(response);
                    }
                })
                .to("mock:mock_server");
    }
}
