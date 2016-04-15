package com.rich.es.monitoring;

import org.joda.time.DateTime;

public class ApplicationStatus {

    private DateTime firstStart;
    private DateTime lastStart;
    private int startCounter;
    private String status;

    public DateTime getFirstStart() {
        return firstStart;
    }

    public void setFirstStart(DateTime firstStart) {
        this.firstStart = firstStart;
    }

    public DateTime getLastStart() {
        return lastStart;
    }

    public void setLastStart(DateTime lastStart) {
        this.lastStart = lastStart;
    }

    public int getStartCounter() {
        return startCounter;
    }

    public void setStartCounter(int startCounter) {
        this.startCounter = startCounter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
