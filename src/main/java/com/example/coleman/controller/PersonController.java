package com.example.coleman.controller;

import com.example.coleman.beans.Person;
import com.example.coleman.exceptions.NotFoundException;
import com.example.coleman.repository.PersonRepository;
import com.example.coleman.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class PersonController {
    @Autowired
    PersonService personService;
    static final Logger LOGGER = LoggerFactory.getLogger(SurveyController.class);
    private final PersonRepository repository;
    //private final GetPersonModelAssembler assembler;
    PersonController(PersonRepository repository){

        this.repository = repository;
        // this.assembler = assembler;
    }
    @GetMapping("/persons")
    ResponseEntity<List<Person>> all() {

        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/persons/{id}")
    Person one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("personne",id));
    }

    @PostMapping(value = "/persons",consumes = "application/json")
    ResponseEntity<Person> newPerson(@RequestBody Person person){

        return new ResponseEntity<Person>(repository.save(person),HttpStatus.CREATED);
    }

    @PatchMapping(value="/persons/{id}", consumes = "application/json")
    ResponseEntity updatePerson(@RequestBody Person newPerson, @PathVariable Long id){
        Person toUpdatePerson = repository.getById(id);
        toUpdatePerson.setId_survey(newPerson.getId_survey());
        LOGGER.info("Update survey ID "+ newPerson.getId_survey());
        repository.save(toUpdatePerson);
        return new ResponseEntity(HttpStatus.OK);
    }


}
