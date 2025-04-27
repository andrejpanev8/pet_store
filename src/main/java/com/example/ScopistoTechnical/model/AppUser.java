package com.example.ScopistoTechnical.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data @Entity @NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @Embedded
    private Money budget;
    @OneToMany(mappedBy = "owner")
    private List<Pet> pets;

    public AppUser(String firstName, String lastName, String email, Double money){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.budget = new Money(BigDecimal.valueOf(money));
        pets = new ArrayList<>();
    }
}
