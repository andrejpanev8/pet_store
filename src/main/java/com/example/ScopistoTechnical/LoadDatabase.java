package com.example.ScopistoTechnical;

import com.example.ScopistoTechnical.model.AppUser;
import com.example.ScopistoTechnical.model.Pet;
import com.example.ScopistoTechnical.model.pets.Cat;
import com.example.ScopistoTechnical.model.pets.Dog;
import com.example.ScopistoTechnical.service.OwnerService;
import com.example.ScopistoTechnical.service.PetService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component @AllArgsConstructor
public class LoadDatabase {
    private final OwnerService ownerService;
    private final PetService petService;

    @PostConstruct
    public void initData() throws Exception {
        List<AppUser> owners = new ArrayList<>();
        for (int i=0;i<10;i++){
            AppUser owner = new AppUser("John " + i, "Doe " + i, "email"+i+"@email.com", (double)i*i);
            owners.add(owner);
        }
        ownerService.saveAll(owners);

        Random r = new Random();
        List<Pet> pets = new ArrayList<>();
        for (int i=0;i<10;i++){
            int randomNumber = r.nextInt(10);
            LocalDate date = generateRandomDate(i+randomNumber);
            Pet pet = (randomNumber % 2 == 0)
                    ? new Cat(null, "CatName" + i, "CatDesc" + i, date, (double)Math.max(i, randomNumber-i))
                    : new Dog(null, "DogName" + i, "DogDesc" + i, date, (double)Math.max(i, randomNumber-i), randomNumber);
            pets.add(pet);
        }
        petService.saveAll(pets);
    }

    private LocalDate generateRandomDate(int yearsAgo) {
        Random random = new Random();

        int randomYears = random.nextInt(yearsAgo);

        LocalDate today = LocalDate.now();
        LocalDate randomDate = today.minusYears(randomYears);

        randomDate = randomDate.withDayOfYear(random.nextInt(randomDate.lengthOfYear()) + 1);

        return randomDate;
    }
}
