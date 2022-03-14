package com.example.crabe.services;

import com.example.crabe.controller.SurveyAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyServices {
    static final Logger LOGGER = LoggerFactory.getLogger(CampaignService.class);
    @Autowired
    SurveyAssemblerRepository campaignRepository;

}
