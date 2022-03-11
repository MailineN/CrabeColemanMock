package com.example.crabe;

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
public class PersonController {
    private final PersonRepository repository;
    private final PersonModelAssembler assembler;
    PersonController(PersonRepository repository, PersonModelAssembler assembler){

        this.repository = repository;
        this.assembler = assembler;
    }
    @GetMapping("/persons")
    CollectionModel<EntityModel<Person>> all() {

        List<EntityModel<Person>> persons = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(persons, linkTo(methodOn(PersonController.class).all()).withSelfRel());
    }

    @GetMapping("/persons/{id}")
    Person one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PostMapping("/persons")
    ResponseEntity<?> newPerson(@RequestBody Person person){
        EntityModel<Person> entityModel= assembler.toModel(repository.save(person));
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

}
