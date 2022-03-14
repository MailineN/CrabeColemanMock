package com.example.crabe.exceptions;

public class DuplicateException extends RuntimeException{
    public DuplicateException(String ressource, String id) {
        super(String.format("'%s' already exists for values : '%s'", ressource, id));
    }
}
