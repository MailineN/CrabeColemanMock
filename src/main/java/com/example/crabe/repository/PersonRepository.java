package com.example.crabe.repository;
import com.example.crabe.beans.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,String> { //Type person, id format : long
    Person findByName(String name);
    Person findByEmail(String email); //Autres critères à définir
}
