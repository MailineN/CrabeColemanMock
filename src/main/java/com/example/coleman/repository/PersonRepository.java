package com.example.coleman.repository;
import com.example.coleman.beans.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {
    List<Person> findByIdSurvey(Long idSurvey);
    @Transactional
    @Modifying
    @Query("update Person p set p.idSurvey = ?1 where p.id = ?2")
    int updatePersonSurveyById(Long idSurvey, Long idPerson);
}
