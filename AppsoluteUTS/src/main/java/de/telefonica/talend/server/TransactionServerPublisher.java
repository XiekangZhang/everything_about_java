package de.telefonica.talend.server;

import de.telefonica.talend.server.TicketInfo;
import jakarta.xml.ws.Endpoint;

public class TransactionServerPublisher {
    public static void main(String[] args) {
        System.out.println("Publish server on http://localhost:8040/services/appsolute");
        Endpoint.publish(
                "http://localhost:8040/services/appsolute",
                new TransactionServer()
        );
    }
}
