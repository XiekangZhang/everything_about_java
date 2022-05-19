package de.xk.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    public void marshal() throws JAXBException, IOException {
        Book book = new Book();
        book.setId(1L);
        book.setName("Book1");
        book.setAuthor("Author1");
        book.setDate(new Date());

        JAXBContext jaxbContext = JAXBContext.newInstance(Book.class);
        Marshaller mar = jaxbContext.createMarshaller();

        /**
         * Info: Supported Properties
         *
         */
        mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

}