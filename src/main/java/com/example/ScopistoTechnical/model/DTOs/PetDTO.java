package com.example.ScopistoTechnical.model.DTOs;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
public class PetDTO {
    public String name;
    public String description;
    public LocalDate birthDate;
    public BigDecimal price;
}
