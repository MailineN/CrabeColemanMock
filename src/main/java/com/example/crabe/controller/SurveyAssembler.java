package com.example.crabe.controller;

import com.example.crabe.beans.Person;
import com.example.crabe.beans.Survey;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SurveyAssembler implements RepresentationModelAssembler<Survey, EntityModel<Survey>> {
    @Override
    // SurementOverkill pour ce qu'on veut faire utilisé que pour le Get
    public EntityModel<Survey> toModel(Survey survey){
        return EntityModel.of(survey,
                linkTo(methodOn(SurveyController.class).all()).withRel("surveys"));
    }
}
