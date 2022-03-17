package com.example.coleman.services;

import com.example.coleman.beans.Survey;
import com.example.coleman.exceptions.NotFoundException;
import com.example.coleman.repository.SurveyRepository;
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
        return surveyRepository.save(newSurvey);
    }

    public Survey findById(Long idSurvey) throws NotFoundException {
        return surveyRepository.findById(idSurvey)
                .orElseThrow(() -> new NotFoundException("survey ", idSurvey));
    }


}
