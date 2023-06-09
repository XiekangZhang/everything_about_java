package de.telefonica.talend.server;

import de.telefonica.talend.server.SupportGroup;
import de.telefonica.talend.server.TicketInfo;
import de.telefonica.talend.server.TransactionBody;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public class TransactionServer {
    /**@WebMethod
    public String getTicketInfo(TicketInfo ticketInfo) {
        TransactionBody transactionBody = ticketInfo.getTransactionBody();
        List<SupportGroup> supportGroups = transactionBody.getSupportGroupLists();
        StringBuilder stringBuilder = new StringBuilder();
        for (SupportGroup supportGroup: supportGroups) {
            stringBuilder.append(supportGroup.getSupportGroup());
        }
        return stringBuilder.toString();
    }**/
}
