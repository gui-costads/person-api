package com.person.personapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {
    private Long id;
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

    @Pattern(regexp = "\\d{5}-\\d{3}")
    @NotNull
    @NotBlank
    private String postalCode;

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
}
