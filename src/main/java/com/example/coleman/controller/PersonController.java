package com.example.coleman.controller;

import com.example.coleman.beans.Person;
import com.example.coleman.exceptions.NotFoundException;
import com.example.coleman.repository.PersonRepository;
import com.example.coleman.services.PersonService;
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


}
