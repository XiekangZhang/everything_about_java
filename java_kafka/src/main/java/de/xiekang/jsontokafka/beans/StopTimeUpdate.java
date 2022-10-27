package de.xiekang.jsontokafka.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StopTimeUpdate {
    @JsonProperty(value = "StopSequence")
    private int stopSequence;
    @JsonProperty(value = "StopId")
    private String stopId;
    @JsonProperty(value = "Departure")
    private Departure departure;
    @JsonProperty(value = "Arrival")
    private Arrival arrival;
    @JsonProperty(value = "ScheduleRelationship")
    private String scheduleRelationship = "Scheduled";

    public StopTimeUpdate() {
    }

    public int getStopSequence() {
        return stopSequence;
    }

    public void setStopSequence(int stopSequence) {
        this.stopSequence = stopSequence;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public Arrival getArrival() {
        return arrival;
    }

    public void setArrival(Arrival arrival) {
        this.arrival = arrival;
    }

    public String getScheduleRelationship() {
        return scheduleRelationship;
    }

    public void setScheduleRelationship(String scheduleRelationship) {
        this.scheduleRelationship = scheduleRelationship;
    }
}
