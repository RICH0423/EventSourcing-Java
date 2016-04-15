package com.rich.es.config.eventstore;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import no.ks.eventstore2.projection.ProjectionManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import com.rich.es.example.AccountProjection;
import com.rich.es.monitoring.ApplicationStatusProjection;

@Configuration
public class ProjectionManagerInitializer {

    @Resource(name = "eventStore")
    private ActorRef eventStore;

    @Autowired
    private ActorSystem actorSystem;

    @Resource(name = "projectionErrorListener")
    private ActorRef projectionErrorListener;

    @Bean(name = "projectionManager")
    public ActorRef initializeProjectionManager() {
        List<Props> props = new ArrayList<Props>();

        props.add(ApplicationStatusProjection.mkProps(eventStore));
        props.add(AccountProjection.mkProps(eventStore));
        
        return actorSystem.actorOf(ProjectionManager.mkProps(projectionErrorListener, props), "ProjectionManager");
    }
}
