package com.example.crabe.controller;

import com.example.crabe.beans.Person;
import com.example.crabe.exceptions.NotFoundException;
import com.example.crabe.repository.PersonRepository;
import com.example.crabe.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
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
    List<Person> all() {
        return repository.findAll();
    }

    @GetMapping("/persons/{id}")
    Person one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("personne",id));
    }

    @PostMapping("/persons")
    Person newPerson(@RequestBody Person person){
        return repository.save(person);
    }

    @GetMapping(value = "/persons/sample/{sampleSize}", produces = "application/json")
    List<Person> sample(@PathVariable Long sampleSize){
        return personService.sample(sampleSize);
    }

}
