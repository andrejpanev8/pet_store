package com.example.ScopistoTechnical.model.DTOs;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AppUserDTO {
    public String firstName;
    public String lastName;
    public String email;
    public BigDecimal budget;
    List<PetDTO> pets;

    public AppUserDTO(String firstName, String lastName, String email, BigDecimal budget) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.budget = budget;
        this.pets = new ArrayList<>();
    }
}
