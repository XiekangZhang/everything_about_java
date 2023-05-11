package de.xk.jaxb;

import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;

class EventTest {
    @Test
    public void marshal() throws JAXBException, IOException {
        Event event = new Event();
        event.setSource("bbbbbbbb");
        event.setDestination("cccccccc");

        JAXBContext jaxbContext = JAXBContext.newInstance(Event.class);
        Marshaller mar = jaxbContext.createMarshaller();

        /**
         * Info: Supported Properties
         *
         */
        mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(event, new File("bbbbbbb_event.xml"));
    }
}