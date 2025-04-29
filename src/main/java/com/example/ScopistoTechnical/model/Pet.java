package com.example.ScopistoTechnical.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Data @Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // One table for cats and dogs
@DiscriminatorColumn(name = "animal_type")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Pet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @Nullable
    @JoinColumn(name = "owner_id")
    private AppUser owner;
    private String name;
    private String description;
    private LocalDate birthDate;
    @Setter
    @Embedded
    private Money price;

    public Pet(){}

    public Pet(@Nullable AppUser owner, String name, String description, LocalDate birthDate, BigDecimal money) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.birthDate = birthDate;
        this.price = new Money(money);
    }

    @PrePersist
    @PreUpdate
    public void calculatePrice() {
        setPrice(this.price);
    }

    public int getAge(){
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public String makeSound(){
        return "";
    }
}
