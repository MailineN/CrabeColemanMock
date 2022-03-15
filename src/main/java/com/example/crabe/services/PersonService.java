package com.example.crabe.services;

import com.example.crabe.beans.Person;
import com.example.crabe.beans.Survey;
import com.example.crabe.controller.GetPersonController;
import com.example.crabe.controller.SurveyController;
import com.example.crabe.exceptions.DuplicateException;
import com.example.crabe.exceptions.NotFoundException;
import com.example.crabe.repository.PersonRepository;
import com.example.crabe.repository.SurveyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PersonService {
    static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    @Autowired
    SurveyService surveyService;

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    PersonRepository personRepository;
    
    public void addToSurvey(Long idsurvey, List<Person> units) {
        // check and retrieval of the survey
        Survey survey = surveyRepository.findById(idsurvey)
                .orElseThrow(() -> new NotFoundException("survey", idsurvey));
        LOGGER.info(survey.toString());
        for (Person unit : units) {
            try {
                addToSurveyIndiv(idsurvey,unit);
                LOGGER.info("Unit : " + unit.toString());
            } catch (DuplicateException e){
                e.getMessage();
            }
        }

    }

    public void addToSurveyIndiv(Long idsurvey, Person unit){
        // On va considérer ici qu'une personne participe à une enquete à la fois
        if (unit.getIdSurvey() != null){
            throw new DuplicateException("unit in survey", unit.getIdSurvey());
        } else {
            unit.setIdSurvey(idsurvey);
            unit = personRepository.save(unit);
        }
    }

    public List<Person> sample(Long sampleSize){
        long len = personRepository.count();
        List<Person> liste = personRepository.findAll();
        List<Person> copy = new ArrayList<Person>(liste);
        Collections.shuffle(copy);
        return sampleSize > copy.size() ? copy.subList(0, copy.size()) : copy.subList(0, Math.toIntExact(sampleSize));
    }
}

