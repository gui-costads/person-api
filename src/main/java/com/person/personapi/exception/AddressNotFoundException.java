package com.person.personapi.exception;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(Long id) {
        super("Address not found by Id:" + id);
    }
}
