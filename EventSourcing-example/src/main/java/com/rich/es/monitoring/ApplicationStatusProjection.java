package com.rich.es.monitoring;

import static akka.pattern.Patterns.ask;
import static no.ks.eventstore2.projection.CallProjection.call;

import org.joda.time.DateTime;

import no.ks.eventstore2.Event;
import no.ks.eventstore2.Handler;
import no.ks.eventstore2.projection.Projection;
import no.ks.eventstore2.projection.Subscriber;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.Props;

import com.rich.es.config.eventstore.Aggregate;
import com.rich.es.util.Timeout;

@Subscriber(Aggregate.SYSTEM)
public class ApplicationStatusProjection extends Projection {

    private ApplicationStatus applicationStatus = new ApplicationStatus();

    public ApplicationStatusProjection(ActorRef eventStore) {
        super(eventStore);
    }

    public static Props mkProps(ActorRef eventstore) {
        return Props.create(ApplicationStatusProjection.class, eventstore);
    }

    @Handler
    public void handleEvent(ApplicationHasStartedEvent event) {
        updateFirstStart(event);
        updateLastStart(event);
        updateStartCounter();
        updateStatus("Normal");
    }
    
    @Handler
    public void handleAbnormalEvent(ApplicationAbnormalEvent event) {
        updateFirstStart(event);
        updateLastStart(event);
        updateStartCounter();
        updateStatus("Abnormal");
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public static ApplicationStatus askApplicationStatus(ActorRef applicationStatusProjection) {
        Future<Object> askApplicationStatus = ask(applicationStatusProjection, call("getApplicationStatus"), Timeout.THREE_SECONDS);
        try {
            return (ApplicationStatus) Await.result(askApplicationStatus, Duration.create(Timeout.THREE_SECONDS_STR));
        } catch (Exception e) {
            throw new RuntimeException("Could not retrieve application status", e);
        }
    }
    
    public static ApplicationStatus updateApplicationStatus(ActorRef applicationStatusProjection) {
        Future<Object> askApplicationStatus = ask(applicationStatusProjection, call("getApplicationStatus"), Timeout.THREE_SECONDS);
        try {
            return (ApplicationStatus) Await.result(askApplicationStatus, Duration.create(Timeout.THREE_SECONDS_STR));
        } catch (Exception e) {
            throw new RuntimeException("Could not retrieve application status", e);
        }
    }
    
    private void updateStatus(String status) {
        applicationStatus.setStatus(status);
    }

    private void updateStartCounter() {
        int startCounter = applicationStatus.getStartCounter() + 1;
        applicationStatus.setStartCounter(startCounter);
    }

    private void updateLastStart(Event event) {
        applicationStatus.setLastStart(event.getCreated());
    }

    private void updateFirstStart(Event event) {
        DateTime firstStart = applicationStatus.getFirstStart();
        if (firstStart == null) {
            applicationStatus.setFirstStart(event.getCreated());
        }
    }
}
