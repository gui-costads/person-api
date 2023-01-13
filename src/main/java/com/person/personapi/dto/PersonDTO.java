package com.person.personapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDTO {

    private String name;
    private LocalDate birth;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }
}
