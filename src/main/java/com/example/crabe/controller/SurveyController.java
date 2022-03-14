package com.example.crabe.controller;

import com.example.crabe.beans.Person;
import com.example.crabe.beans.Survey;
import com.example.crabe.exceptions.DuplicateException;
import com.example.crabe.exceptions.NotFoundException;
import com.example.crabe.repository.SurveyRepository;
import com.example.crabe.services.PersonService;
import com.example.crabe.services.SurveyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class SurveyController {
    // Relatif a une enquete

    @Autowired
    SurveyService surveyService;
    @Autowired
    PersonService personService;

    private final SurveyRepository repository;
    // private final SurveyAssembler assembler;
    static final Logger LOGGER = LoggerFactory.getLogger(SurveyController.class);

    public SurveyController(SurveyRepository repository) {
        this.repository = repository;
        // this.assembler = assembler;
    }

    @GetMapping("/surveys")
    List<Survey> all(){
        return repository.findAll();
    }

    @PostMapping("/surveys")
    public Survey addSurvey(@RequestBody Survey newSurvey){
        LOGGER.info("Post request, add a survey");
        return surveyService.save(newSurvey);


    }

    @GetMapping(value = "/surveys/{idsurvey}", produces = "application/json")
    public Survey displayAsurvey(@PathVariable Long idsurvey) throws NotFoundException {
        LOGGER.info("Request GET with survey id : {}", idsurvey);
        return surveyService.findById(idsurvey);
    }

    @GetMapping("/surveys/{idsurvey}/units")
    public List<Person> allUnits(){
        // est ce que c'est vraiment utile ?
        return new ArrayList<>();
    }

    @PostMapping("/surveys/{idsurvey}/units")
    public void addToSurvey(@RequestBody List<Person> units, @PathVariable Long idsurvey){
        LOGGER.info("POST request to add unit to survey {}", idsurvey);
        try {
            surveyService.findById(idsurvey);
            personService.addToSurvey(idsurvey, units);

        } catch (NotFoundException e) {
            LOGGER.error("Error in request: campaign not found {}", idsurvey);
        } catch (DuplicateException e){
            LOGGER.error("Error in request: duplicate unit");
        }
    }
}
