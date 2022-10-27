package de.xiekang.jsontokafka.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TripMain {
    @JsonProperty(value = "Id")
    private String id;
    @JsonProperty(value = "IsDeleted")
    private boolean deleted;
    @JsonProperty(value = "TripUpdate")
    private TripUpdate tripUpdate;

    public TripMain() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public TripUpdate getTripUpdate() {
        return tripUpdate;
    }

    public void setTripUpdate(TripUpdate tripUpdate) {
        this.tripUpdate = tripUpdate;
    }
}
