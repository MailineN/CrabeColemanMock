package com.example.coleman.repository;

import com.example.coleman.beans.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey,Long> {
    Survey findByName(String name);
}
