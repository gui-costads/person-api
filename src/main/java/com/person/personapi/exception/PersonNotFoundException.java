package com.person.personapi.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(Long id) {
        super("Person not found by Id:" + id);
    }
}
