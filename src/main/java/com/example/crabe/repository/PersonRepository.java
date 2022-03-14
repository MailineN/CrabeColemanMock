package com.example.crabe.repository;
import com.example.crabe.beans.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
