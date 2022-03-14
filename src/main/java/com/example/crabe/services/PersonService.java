package com.example.crabe.services;

import com.example.crabe.beans.Person;
import com.example.crabe.beans.Survey;
import com.example.crabe.exceptions.DuplicateException;
import com.example.crabe.exceptions.NotFoundException;
import com.example.crabe.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    SurveyService surveyService;

    @Autowired
    SurveyRepository surveyRepository;
    
    public void addToSurvey(Long idsurvey, List<Person> units) {
        // check and retrieval of the survey
        Survey survey = surveyRepository.findById(String.valueOf(idsurvey))
                .orElseThrow(() -> new NotFoundException("survey", idsurvey));

        for (Person unit : units) {
            try {
                addToSurveyIndiv(idsurvey,unit);
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
        }
    }
}
