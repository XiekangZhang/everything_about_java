package de.XK2.Baeldung;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement(name = "book")
@XmlType(propOrder = {"id", "name", "date"})
public class Book {
    private Long id;
    private String name;
    private String author;
    private Date date;

    public Book() {
    }

    public Long getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "title")
    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    @XmlTransient
    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setDate(Date date) {
        this.date = date;
    }
}
