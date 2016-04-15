package com.rich.es.example;

import com.rich.es.config.eventstore.Aggregate;

import no.ks.eventstore2.Event;

public class CreateAccountEvent extends Event{

    private static final long serialVersionUID = 1L;

    @Override
    public String getAggregateType() {
        return Aggregate.ACCOUNT;
    }

    @Override
    public String getLogMessage() {
        return "Create Account event!";
    }

    @Override
    public String getAggregateRootId() {
        return "CreateAccountEvent";
    }

}
