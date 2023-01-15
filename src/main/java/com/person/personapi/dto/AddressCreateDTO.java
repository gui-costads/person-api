package com.person.personapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AddressCreateDTO {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String street;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 20)
    private String number;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String city;
    @NotNull
    @NotBlank
    @Pattern(regexp = "\\d{5}-\\d{3}")
    private String postalCode;

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
}
