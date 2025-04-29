package com.example.ScopistoTechnical.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data @Entity @NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @Embedded
    private Money budget;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets;

    public AppUser(String firstName, String lastName, String email, BigDecimal money){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.budget = new Money(money);
        pets = new ArrayList<>();
    }

    public void addPet(Pet pet){
        if (!pets.contains(pet)) {
            pets.add(pet);
            pet.setOwner(this);
        }
    }
}
