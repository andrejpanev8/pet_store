package com.example.ScopistoTechnical.model.pets;

import com.example.ScopistoTechnical.model.AppUser;
import com.example.ScopistoTechnical.model.Money;
import com.example.ScopistoTechnical.model.Pet;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("CAT")
@NoArgsConstructor
public class Cat extends Pet {

    public Cat(AppUser owner, String name, String description, LocalDate birthDate, BigDecimal money) {
        super(owner, name, description, birthDate, money);
    }

    @Override
    public void setPrice(Money price){
        Money catPrice = new Money(BigDecimal.valueOf(super.getAge()));
        super.setPrice(catPrice);
    }

    @Override
    public String makeSound(){
        return super.getOwner() != null ?
                "Meow, cat " + super.getName() + " has owner " + super.getOwner().getFirstName() : "";
    }
}
