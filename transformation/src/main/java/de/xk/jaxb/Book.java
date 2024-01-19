package de.xk.jaxb;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement(name = "book")
@XmlType(propOrder = {
        "id",
        "name",
        "date"
})
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

    public void setDate(Date date) {
        this.date = date;
    }
}
