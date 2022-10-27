package de.xiekang.jsontokafka.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TripMainWrapper {
    @JsonProperty(value = "Header")
    @JsonIgnoreProperties
    private Header header;
    @JsonProperty(value = "Entity")
    private List<TripMain> entity;

    public TripMainWrapper() {
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<TripMain> getEntity() {
        return entity;
    }

    public void setEntity(List<TripMain> entity) {
        this.entity = entity;
    }
}
