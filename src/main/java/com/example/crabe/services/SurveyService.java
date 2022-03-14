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
        return surveyRepository.save(newSurvey);
    }

    public Survey findById(Long idSurvey) throws NotFoundException {
        return surveyRepository.findById(idSurvey)
                .orElseThrow(() -> new NotFoundException("survey ", idSurvey));
    }


}
