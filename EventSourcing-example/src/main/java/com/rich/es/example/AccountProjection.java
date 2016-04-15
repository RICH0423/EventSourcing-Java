package com.rich.es.example;

import com.rich.es.config.eventstore.Aggregate;

import akka.actor.ActorRef;
import akka.actor.Props;
import no.ks.eventstore2.Handler;
import no.ks.eventstore2.projection.Projection;
import no.ks.eventstore2.projection.Subscriber;

@Subscriber(Aggregate.ACCOUNT)
public class AccountProjection extends Projection{

    public AccountProjection(ActorRef eventStore) {
        super(eventStore);
    }
    
    public static Props mkProps(ActorRef eventstore) {
        return Props.create(AccountProjection.class, eventstore);
    }
    
    @Handler
    public void handleEvent(CreateAccountEvent event) {
        System.out.println("[Handle Create Account Event]");
        System.out.println("Event msg: " + event.getLogMessage());
        System.out.println("Event journalid: " + event.getJournalid());
    }

}
