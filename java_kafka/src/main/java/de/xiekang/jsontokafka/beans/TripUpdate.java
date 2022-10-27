package de.xiekang.jsontokafka.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TripUpdate {
    @JsonProperty(value = "Trip")
    private Trip trip;
    @JsonProperty(value = "StopTimeUpdate")
    private List<StopTimeUpdate> stopTimeUpdates;

    public TripUpdate() {
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public List<StopTimeUpdate> getStopTimeUpdates() {
        return stopTimeUpdates;
    }

    public void setStopTimeUpdates(List<StopTimeUpdate> stopTimeUpdates) {
        this.stopTimeUpdates = stopTimeUpdates;
    }
}
