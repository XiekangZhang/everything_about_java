package de.XK2.Baeldung;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class main {

    public void marshal() throws JAXBException, IOException {
        Book book = new Book();
        book.setId(1L);
        book.setName("Book1");
        book.setAuthor("Author1");
        book.setDate(new Date());

        JAXBContext context = JAXBContext.newInstance(Book.class); // entry point to JAXB API
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(book, new File("./book.xml"));
    }

    public Book unmarschall() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Book.class);
        return (Book) context.createUnmarshaller().unmarshal(new FileReader("./book.xml"));
    }

    public static void main(String[] args) throws JAXBException, IOException {
        main main = new main();
        main.marshal();
        Book book = main.unmarschall();
        System.out.printf("[id=%s. name=%s, author=%s, date=%s]", book.getId(), book.getName(),
                book.getAuthor(), book.getDate());
    }
}
