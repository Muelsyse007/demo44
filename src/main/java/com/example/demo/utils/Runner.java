package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Runner implements CommandLineRunner {
//    private final Session;
//
//    @Bean
//    public SessionFactory sessionFactory() {
//        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder()
//                .uri("127.0.0.1:7687")
//                .credentials("neo4j", "12345678")
//                .build();
//        return new SessionFactory(configuration, "com.example.demo");}


//    @Bean
//    public Session session (){return session;}
//    public Runner(Driver driver, ConfigurableApplicationContext applicationContext) {
//        this.session = driver.session();
//    }
//    @Override
    public void run(String... args) throws Exception {
    }
}
