package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Runner implements CommandLineRunner {
    private final Session session;
    @Bean
    public Session session (){return session;}
    public Runner(Driver driver, ConfigurableApplicationContext applicationContext) {
        this.session = driver.session();
    }
    @Override
    public void run(String... args) throws Exception {
    }
}
