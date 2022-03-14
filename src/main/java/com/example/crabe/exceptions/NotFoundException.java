package com.example.crabe.exceptions;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(Long id) {
            super("La personne " + id + " n'existe pas");
        }
    }

