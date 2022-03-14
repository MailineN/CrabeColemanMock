package com.example.crabe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> { //Type person, id format : long
    Person findByName(String name);
    Person findByEmail(String email); //Autres critères à définir
}
