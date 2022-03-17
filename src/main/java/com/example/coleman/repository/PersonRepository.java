package com.example.coleman.repository;
import com.example.coleman.beans.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {
    List<Person> findByIdSurvey(Long idSurvey);
}
