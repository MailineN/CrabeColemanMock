package com.example.crabe;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(Long id) {
            super("La personne " + id + " n'existe pas");
        }
    }

