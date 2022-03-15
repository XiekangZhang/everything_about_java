package de.XK;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.XK.model.Book;
import de.XK.model.Catalog;

import javax.xml.bind.JAXB;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class JSON2XML {

    public static void main(String[] args) throws IOException {
        File xmlFile = new File("jaxb_tutorial/src/main/resources/xml/books.xml");
        Catalog catalog = JAXB.unmarshal(xmlFile, Catalog.class);

        // add two books
        /*Catalog catalog1 = new Catalog();
        Book book = new Book();
        book.setId("bk101");
        book.setTitle("Pride and Prejudice");
        book.setAuthor("Jane Austen");
        book.setGenre("Romance");
        book.setPrice(new BigDecimal("6.99"));
        book.setPubdate("2010-04-01");
        book.setDescription("\"It is a truth universally acknowledged, that a single man in possession of a good fortune must be in want of a wife.\" So begins Pride and Prejudice, Jane Austen's witty comedy of manners-one of the most popular novels of all time-that features splendidly civilized sparring between the proud Mr. Darcy and the prejudiced Elizabeth Bennet as they play out their spirited courtship in a series of eighteenth-century drawing-room intrigues.");
        System.out.println(book.getId());
        catalog1.getBooks().add(book);

        JAXB.marshal(catalog1, new File("jaxb_tutorial/src/main/resources/xml/out_book.xml"));*/

        /*BigDecimal cutoff = new BigDecimal("30.0");
        BigDecimal newPrice = new BigDecimal("0.90");
        System.out.println("old info: -------------------------------------------------");
        catalog.getBooks().stream().filter(book -> book.getPrice().compareTo(cutoff) > 0)
                .forEach(book -> JAXB.marshal(book, System.out));

        System.out.println("new info: -------------------------------------------------");
        catalog.getBooks().stream().filter(book -> book.getPrice().compareTo(cutoff) > 0)
                .forEach(book -> book.setPrice(book.getPrice().multiply(newPrice)));
        JAXB.marshal(catalog, System.out);*/

        // info: to json
        //ObjectMapper mapper = new ObjectMapper();
        //mapper.writeValue(System.out, catalog);

        // json to xml
        ObjectMapper mapper1 = new ObjectMapper();
        Catalog catalog1 = mapper1.readValue(new File("jaxb_tutorial/src/main/resources/json/books.json"), Catalog.class);
        JAXB.marshal(catalog1, System.out);
    }
}
