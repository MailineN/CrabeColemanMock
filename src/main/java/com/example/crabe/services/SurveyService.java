package com.example.crabe.services;

import com.example.crabe.beans.Survey;
import com.example.crabe.exceptions.DuplicateException;
import com.example.crabe.exceptions.NotFoundException;
import com.example.crabe.repository.SurveyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {
    static final Logger LOGGER = LoggerFactory.getLogger(SurveyService.class);
    @Autowired
    SurveyRepository surveyRepository;

    public Survey save(Survey newSurvey){
        Long id = newSurvey.getId();
        if (!surveyRepository.existsById(String.valueOf(id))){
            return surveyRepository.save(newSurvey);
        } else {
            throw new DuplicateException("Survey",id);
        }
    }

    public Survey findById(Long idSurvey) throws NotFoundException {
        return surveyRepository.findById(String.valueOf(idSurvey))
                .orElseThrow(() -> new NotFoundException("survey ", idSurvey));
    }
}
