package de.XK.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.XK.adapter.LocalDateAdapter;
import de.XK.serializer.LocalDateDeserializer;
import de.XK.serializer.LocalDateSerializer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class Book {
    // info: XmlAttribute: which attribute should have been serialized
    @XmlAttribute
    private String id;
    private String author;
    private String title;
    private String genre;
    private BigDecimal price;

    // info: mapping publish_date from xml to pubdate in java object
    @XmlElement(name = "publish_date")
    /*@XmlJavaTypeAdapter(LocalDateAdapter.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate pubdate;*/
    private String pubdate;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    /*@JsonProperty("publish_date")
    public LocalDate getPubdate() {
        return pubdate;
    }

    @JsonProperty("publish_date")
    public void setPubdate(LocalDate pubdate) {
        this.pubdate = pubdate;
    }*/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
