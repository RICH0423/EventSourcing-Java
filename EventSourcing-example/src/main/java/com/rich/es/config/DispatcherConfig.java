package com.rich.es.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.rich.es.example.dao.EventDao;
import com.rich.es.example.dao.EventDaoImpl;

@Configuration
@ComponentScan("com.rich.es")
@EnableWebMvc
public class DispatcherConfig extends WebMvcConfigurerAdapter {
    

}
