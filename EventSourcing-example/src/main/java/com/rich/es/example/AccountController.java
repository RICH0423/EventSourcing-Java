package com.rich.es.example;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rich.es.monitoring.ApplicationAbnormalEvent;
import com.rich.es.monitoring.ApplicationStatus;
import com.rich.es.monitoring.ApplicationStatusProjection;

import akka.actor.ActorRef;

@Controller
@RequestMapping("/account")
public class AccountController {
    
    private static Logger log = LoggerFactory.getLogger(AccountController.class);

    @Resource(name = "eventStore")
    private ActorRef eventStore;

    @Resource(name = "applicationStatusProjection")
    private ActorRef applicationStatusProjection;
    
    @RequestMapping(value = "/createAccount", method = RequestMethod.GET)
    @ResponseBody
    public void sendAbnormalApplicationStatus() {
        log.info("Controlller accept create account command");
        eventStore.tell(new CreateAccountEvent(), null);

    }
}
