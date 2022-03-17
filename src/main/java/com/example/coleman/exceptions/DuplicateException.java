package com.example.coleman.exceptions;

public class DuplicateException extends RuntimeException{
    public DuplicateException(String ressource, Long id) {
        super(String.format("'%s' already exists for values : '%s'", ressource, id));
    }
}
