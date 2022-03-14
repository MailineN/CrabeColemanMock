package com.example.crabe.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String ressource, String id) {
        super(String.format("'%s' already exists for values : '%s'", ressource, id));
    }
}

