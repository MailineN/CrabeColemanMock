package com.example.crabe.controller;

import com.example.crabe.beans.Person;
import com.example.crabe.exceptions.NotFoundException;
import com.example.crabe.repository.PersonRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class GetPersonController {
    private final PersonRepository repository;
    //private final GetPersonModelAssembler assembler;
    GetPersonController(PersonRepository repository){

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


}
