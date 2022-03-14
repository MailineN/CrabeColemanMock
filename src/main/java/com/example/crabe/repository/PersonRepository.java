package com.example.crabe.repository;
import com.example.crabe.beans.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {
    List<Person> findByIdSurvey(Long idSurvey);
}
