package com.example.coleman.controller;

import com.example.coleman.beans.Person;
import com.example.coleman.beans.Survey;
import com.example.coleman.exceptions.DuplicateException;
import com.example.coleman.exceptions.NotFoundException;
import com.example.coleman.repository.PersonRepository;
import com.example.coleman.repository.SurveyRepository;
import com.example.coleman.services.PersonService;
import com.example.coleman.services.SurveyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class SurveyController {
    // Relatif a une enquete

    @Autowired
    SurveyService surveyService;
    @Autowired
    PersonService personService;

    private final SurveyRepository repository;
    private final PersonRepository personRepository;
    // private final SurveyAssembler assembler;
    static final Logger LOGGER = LoggerFactory.getLogger(SurveyController.class);

    public SurveyController(SurveyRepository repository, PersonRepository personRepository) {
        this.repository = repository;
        // this.assembler = assembler;
        this.personRepository = personRepository;
    }

    @GetMapping("/surveys")
    ResponseEntity<List<Survey>> all(){
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping(value = "/surveys", consumes = "application/json")
    public ResponseEntity<Survey> addSurvey(@RequestBody Survey newSurvey){
        LOGGER.info("Post request, add a survey");
        return new ResponseEntity<Survey>(surveyService.save(newSurvey), HttpStatus.CREATED);

    }

    @GetMapping(value = "/surveys/id/{idsurvey}", produces = "application/json")
    public ResponseEntity<Survey> displayAsurvey(@PathVariable Long idsurvey) throws NotFoundException {
        LOGGER.info("Request GET with survey id : {}", idsurvey);
        return ResponseEntity.ok(surveyService.findById(idsurvey));
    }

    @GetMapping(value = "/surveys/name/{name}", produces = "application/json")
    public ResponseEntity<Survey> displaySurveyName(@PathVariable String name) throws NotFoundException {
        LOGGER.info("Request GET with survey name : {}", name);
        return ResponseEntity.ok(surveyService.findByName(name));
    }

    @GetMapping("/surveys/{idsurvey}/units")
    public ResponseEntity<List<Person>> allUnits(@PathVariable String idsurvey){
        // est ce que c'est vraiment utile ?
        return ResponseEntity.ok(personRepository.findByIdSurvey(Long.parseLong(idsurvey)));
    }

    @PostMapping(value = "/surveys/{idsurvey}/units", consumes = "application/json")
    public ResponseEntity addToSurvey(@RequestBody List<Person> units, @PathVariable String idsurvey){
        LOGGER.info("POST request to add unit to survey {}", idsurvey);
        Long idLongSurvey = Long.parseLong(idsurvey);
        LOGGER.info("id Survey : ",idLongSurvey);
        try {
            surveyService.findById(idLongSurvey);
            LOGGER.info("Enquête trouvée");
            int respUpdate = personService.addToSurvey(idLongSurvey, units);
            if (respUpdate ==1 ){
                return new ResponseEntity(HttpStatus.ACCEPTED);
            } else{
                LOGGER.info("UpdateFailed");
                return new ResponseEntity(HttpStatus.NOT_MODIFIED);
            }
        } catch (NotFoundException e) {
            LOGGER.error("Error in request: survey not found {}", idsurvey);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/survey/{idsurvey}/ready")
    public ResponseEntity surveySetStateReady(@PathVariable String idsurvey){
        Long idLongSurvey = Long.parseLong(idsurvey);
        LOGGER.info("id Survey : ",idLongSurvey);
        try {
            surveyService.findById(idLongSurvey);
            LOGGER.info("Enquête trouvée");
            // Update SurveyState
            int respUdate = repository.updateSurveyStateById("ready",idLongSurvey);
            if (respUdate ==1 ){
                return new ResponseEntity(HttpStatus.ACCEPTED);
            } else{
                LOGGER.info("UpdateFailed");
                return new ResponseEntity(HttpStatus.NOT_MODIFIED);
            }

        } catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
