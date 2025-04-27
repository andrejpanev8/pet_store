package com.example.ScopistoTechnical.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Data @Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // One table for cats and dogs
@DiscriminatorColumn(name = "animal_type")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @Nullable
    private AppUser owner;
    private String name;
    private String description;
    private LocalDate birthDate;
    @Embedded
    private Money price;

    public Pet(){}

    public Pet(@Nullable AppUser owner, String name, String description, LocalDate birthDate, Double money) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.birthDate = birthDate;
        this.price = new Money(BigDecimal.valueOf(money));
    }

    @PrePersist
    @PreUpdate
    public void calculatePrice() {
        setPrice(this.price);
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public int getAge(){
        return Period.between(LocalDate.now(), birthDate).getYears();
    }
}
