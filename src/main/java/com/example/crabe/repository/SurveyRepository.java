package com.example.crabe.repository;

import com.example.crabe.beans.Person;
import com.example.crabe.beans.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey,String> {
    Survey findByName(String name);
}
