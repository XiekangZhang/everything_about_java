package de.xiekang.jsontokafka.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Trip {
    @JsonProperty(value = "TripId")
    private String tripId;
    @JsonProperty(value = "RouteId")
    private String routeId;
    //TODO: Discuss
    @JsonProperty(value = "StartTime")
    private String startTime;
    @JsonProperty(value = "StartDate")
    private String startDate;
    @JsonProperty(value = "ScheduleRelationship")
    private String scheduleRelationship = "Scheduled";

    public Trip() {
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getScheduleRelationship() {
        return scheduleRelationship;
    }

    public void setScheduleRelationship(String scheduleRelationship) {
        this.scheduleRelationship = scheduleRelationship;
    }
}
