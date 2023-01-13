package com.person.personapi.dto;

import com.person.personapi.model.Person;

public class AddressDTO {
    private String street;
    private String number;
    private String postalCode;
    private String city;
    private Person person;

    public AddressDTO() {
    }
    public AddressDTO(String street, String number, String postalCode, String city, Person person) {
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.city = city;
        this.person = person;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
