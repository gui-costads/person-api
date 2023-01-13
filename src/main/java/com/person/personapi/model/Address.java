package com.person.personapi.model;

import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id", nullable = false)
    private Long id;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "numer", nullable = false)
    private String number;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "postalCode", nullable = false)
    private String postalCode;
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    public Address() {
    }

    public Address(Long id, String street, String number, String city, String postalCode, Person person) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = city;
        this.postalCode = postalCode;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
