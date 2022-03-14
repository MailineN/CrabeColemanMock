package com.example.crabe;

import com.example.crabe.beans.Person;
import com.example.crabe.repository.PersonRepository;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


@Configuration
public class LoaderExample {
    private static final Logger log = LoggerFactory.getLogger(LoaderExample.class);

    @Bean
    CommandLineRunner initDatabase(PersonRepository repository) {
        return args -> {
            log.info("PRELOADING : DONE" );
        };
    }
}
