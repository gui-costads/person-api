package com.person.personapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class PersonCreateDTO {
    @NotNull
    @NotBlank
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy", locale = "pt-BR")
    @NotNull
    @NotBlank
    @Past
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
