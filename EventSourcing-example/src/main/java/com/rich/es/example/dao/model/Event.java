package com.rich.es.example.dao.model;

public class Event {
    
    private long id;
    private String aggregatetype;
    private int dataversion;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getAggregatetype() {
        return aggregatetype;
    }
    public void setAggregatetype(String aggregatetype) {
        this.aggregatetype = aggregatetype;
    }
    public int getDataversion() {
        return dataversion;
    }
    public void setDataversion(int dataversion) {
        this.dataversion = dataversion;
    }
    
    @Override
    public String toString() {
        return "Event [id=" + id + ", aggregatetype=" + aggregatetype + ", dataversion=" + dataversion + "]";
    }
    
}
