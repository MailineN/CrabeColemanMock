package com.example.crabe;

import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoaderExample {
    private static final Logger log = LoggerFactory.getLogger(LoaderExample.class);

    @Bean
    CommandLineRunner initDatabase(PersonRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Person("Mi", "Mi","abc@abc.com", "0102030405")));
            log.info("Preloading " + repository.save(new Person("Mo", "Mo","abc@abc.com","0102030405")));
        };
    }
}
