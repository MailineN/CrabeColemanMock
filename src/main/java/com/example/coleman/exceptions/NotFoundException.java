package com.example.coleman.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String ressource, Long id) {
        super(String.format("'%s' already exists for values : '%s'", ressource, id));
    }
    // Overload
    public NotFoundException(String ressource) {
        super(String.format("'%s' already exists for values : '%s'", ressource,0.0));
    }
}

