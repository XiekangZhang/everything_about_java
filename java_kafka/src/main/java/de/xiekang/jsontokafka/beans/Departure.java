package de.xiekang.jsontokafka.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Departure {
    @JsonProperty(value = "Delay")
    private int delay;

    @JsonProperty(value = "Time")
    private Long time;

    public Departure() {
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return this == null ? null : "{Delay: " + this.delay + "; Time: " + this.time + "}";
    }
}
