package com.example.ScopistoTechnical.service.impl;

import com.example.ScopistoTechnical.model.AppUser;
import com.example.ScopistoTechnical.model.Pet;
import com.example.ScopistoTechnical.model.pets.Cat;
import com.example.ScopistoTechnical.model.pets.Dog;
import com.example.ScopistoTechnical.service.DataSeederService;
import com.example.ScopistoTechnical.service.OwnerService;
import com.example.ScopistoTechnical.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class DataSeederServiceImpl implements DataSeederService {
    private final OwnerService ownerService;
    private final PetService petService;

    @Override
    public List<AppUser> seedOwners() throws Exception{
        List<AppUser> owners = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AppUser owner = new AppUser("John " + i, "Doe " + i, "email" + i + "@email.com", BigDecimal.valueOf(i*i));
            owners.add(owner);
        }
        return ownerService.saveAll(owners);
    }

    @Override
    public List<Pet> seedPets() throws Exception{
        Random r = new Random();
        List<Pet> pets = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int maxYearsAgo = 15;
            LocalDate date = generateRandomDate(maxYearsAgo);
            int randomNumber = r.nextInt(10);

            Pet pet = (randomNumber % 2 == 0)
                    ? new Cat(null, "CatName" + i, "CatDesc" + i, date, BigDecimal.valueOf(Math.max(i, randomNumber - i)))
                    : new Dog(null, "DogName" + i, "DogDesc" + i, date, BigDecimal.valueOf(Math.max(i, randomNumber - i)), randomNumber);
            pets.add(pet);
        }
        return petService.saveAll(pets);
    }

    private LocalDate generateRandomDate(int maxYearsAgo) {
        Random random = new Random();
        int yearsAgo = 1 + random.nextInt(maxYearsAgo);
        LocalDate baseDate = LocalDate.now().minusYears(yearsAgo);

        int dayOfYear = 1 + random.nextInt(baseDate.lengthOfYear());
        return baseDate.withDayOfYear(dayOfYear);
    }
}
