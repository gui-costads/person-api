package com.person.personapi.dto;

import java.time.LocalDate;

public class PersonCreateDTO {
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
