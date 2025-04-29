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
@DiscriminatorValue("DOG")
@NoArgsConstructor
public class Dog extends Pet {
    private int rating;

    public Dog(AppUser owner, String name, String description, LocalDate birthDate, BigDecimal money, int rating) {
        super(owner, name, description, birthDate, money);
        this.rating = rating;
    }

    @Override
    public void setPrice(Money money){
        Money dogPrice = new Money(BigDecimal.valueOf(super.getAge()+this.rating));
        super.setPrice(dogPrice);
    }

    @Override
    public String makeSound(){
        return super.getOwner() != null ?
                "Woof, dog " + super.getName() + " has owner " + super.getOwner().getFirstName() : "";
    }
}

