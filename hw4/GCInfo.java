package ru.otus.skuznets;

public class GCInfo {

    private String gcName;
    private long gcStartTime;
    private long gcEndTime;
    private long gcDuration;

    public GCInfo(String gcName, long gcStartTime, long gcEndTime, long gcDuration) {

        this.gcName = gcName;
        this.gcStartTime = gcStartTime;
        this.gcEndTime = gcEndTime;
        this.gcDuration = gcDuration;
    }
    @Override

    public String toString() {
        return "GCInfo{" + "gcName='" + gcName + '\'' + ", gcStartTime=" + gcStartTime + ", gcEndTime=" + gcEndTime + ", gcDuration=" + gcDuration + '}';
    }

}
