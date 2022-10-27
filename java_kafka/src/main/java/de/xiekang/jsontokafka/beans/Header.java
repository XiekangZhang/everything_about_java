package de.xiekang.jsontokafka.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class Header {
    @JsonProperty(value = "GtfsRealtimeVersion")
    private String version;

    @JsonProperty(value = "Incrementality")
    private String incrementality;

    @JsonProperty(value = "Timestamp")
    private Long timestamp;

    public Header() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIncrementality() {
        return incrementality;
    }

    public void setIncrementality(String incrementality) {
        this.incrementality = incrementality;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
