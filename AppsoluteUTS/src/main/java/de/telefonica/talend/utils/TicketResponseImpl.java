package de.telefonica.talend.utils;

import de.telefonica.talend.server.TicketResponse;
import de.telefonica.talend.server.TicketResponseList;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TicketResponseImpl extends TicketResponse {
    public TicketResponse ticketResponseInit(HashMap<String, Object> valueMap) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {

        TicketResponse ticketResponse = new TicketResponse();
        for (Map.Entry<String, Object> entry : valueMap.entrySet()) {
            String methodName = "set" + entry.getKey().toUpperCase().replace("_", "");
            System.out.println("Set method on the fly: " + methodName);

            if (entry.getValue() instanceof String) {
                Method method = ticketResponse.getClass().getMethod(methodName, String.class);
                method.invoke(ticketResponse, entry.getValue().toString());
            }
            if (entry.getValue() instanceof Date) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime((Date) entry.getValue());
                Method method = ticketResponse.getClass().getMethod(methodName, Calendar.class);
                method.invoke(ticketResponse, calendar);
            }
            // todo: error handling
        }
        return ticketResponse;
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, ParseException {
        TicketResponseImpl ticketResponseImpl = new TicketResponseImpl();
        HashMap<String, Object> map = new HashMap<>();
        map.put("CHANGE_REQUEST_ID", "test1");
        map.put("ACTUAL_END_DATE", new Date());
        TicketResponse ticketResponse = ticketResponseImpl.ticketResponseInit(map);
        System.out.println(ticketResponse.getCHANGEREQUESTID());
        System.out.println(ticketResponse.getACTUALENDDATE());
        TicketResponseList ticketResponseList = new TicketResponseList();
        ticketResponseList.getTicketResponses().add(new TicketResponseImpl().ticketResponseInit(map));
    }
}
