package com.example.coleman.repository;

import com.example.coleman.beans.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey,Long> {
    Survey findByName(String name);

    @Transactional
    @Modifying
    @Query("update Survey s set s.state_survey = ?1 where s.id = ?2")
    int updateSurveyStateById(String state, Long idSurvey);
}
