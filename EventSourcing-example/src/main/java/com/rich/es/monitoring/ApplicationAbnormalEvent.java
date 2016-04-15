package com.rich.es.monitoring;

import com.rich.es.config.eventstore.Aggregate;

import no.ks.eventstore2.Event;

public class ApplicationAbnormalEvent extends Event {
    private static final long serialVersionUID = 1L;

    @Override
    public String getAggregateType() {
        return Aggregate.SYSTEM;
    }

    @Override
    public String getLogMessage() {
        return "Application has error!";
    }

    @Override
    public String getAggregateRootId() {
        return "ApplicationHasAbnormalEvent";
    }

}
