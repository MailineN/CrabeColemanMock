package com.example.crabe.controller;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.crabe.beans.Person;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class GetPersonModelAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>> {
    @Override
    public EntityModel<Person> toModel(Person person){
        return EntityModel.of(person, //
                linkTo(methodOn(GetPersonController.class).one(person.getId())).withSelfRel(),
                linkTo(methodOn(GetPersonController.class).all()).withRel("persons"));
    }
}
