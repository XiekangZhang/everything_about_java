package de.xiekang.apache_camel_JMS.bean;

public class StartDatum {
    private long startDatum_HOST;
    private long startDatum_GUEST;

    public StartDatum() {
        // TODO document why this constructor is empty
    }

    public long getStartDatum_HOST() {
        return startDatum_HOST;
    }

    public void setStartDatum_HOST(long startDatum_HOST) {
        this.startDatum_HOST = startDatum_HOST;
    }

    public long getStartDatum_GUEST() {
        return startDatum_GUEST;
    }

    public void setStartDatum_GUEST(long startDatum_GUEST) {
        this.startDatum_GUEST = startDatum_GUEST;
    }

    @Override
    public String toString() {
        return "StartDatum{" +
                "startDatum_HOST=" + startDatum_HOST +
                ", startDatum_GUEST=" + startDatum_GUEST +
                '}';
    }
}
