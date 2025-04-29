package com.example.ScopistoTechnical.service;

import com.example.ScopistoTechnical.model.AppUser;
import com.example.ScopistoTechnical.model.Log;
import com.example.ScopistoTechnical.model.Pet;
import com.example.ScopistoTechnical.model.pets.Cat;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class ServiceIntegrationTest {

    @Autowired
    private PetService petService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private LogService logService;

    @Test
    void shouldAssignPetToOwner() throws Exception {
        AppUser owner = new AppUser("Jane", "Doe", "jane@example.com", BigDecimal.valueOf(100));
        ownerService.save(owner).orElseThrow();

        Pet pet = new Cat(null, "Mittens", "Lazy Cat", LocalDate.now().minusYears(2), BigDecimal.valueOf(10));
        petService.save(pet);

        owner.addPet(pet);
        ownerService.save(owner);

        List<Pet> pets = petService.getAllPets();
        assertThat(pets).isNotEmpty();
        assertThat(Objects.requireNonNull(pets.getFirst().getOwner()).getEmail()).isEqualTo("jane@example.com");
    }

    @Test
    void shouldSaveAndRetrievePet() throws Exception {
        Pet pet = new Cat(null, "Whiskers", "Fluffy cat", LocalDate.now().minusYears(3), BigDecimal.valueOf(20));
        petService.save(pet);

        List<Pet> pets = petService.getAllPets();
        assertThat(pets).isNotEmpty();
        assertThat(pets).extracting(Pet::getName).contains("Whiskers");
    }

    @Test
    void shouldSaveAndRetrieveOwner() throws Exception {
        AppUser owner = new AppUser("Tom", "Smith", "tom@example.com", BigDecimal.valueOf(200));
        ownerService.save(owner);

        List<AppUser> owners = ownerService.getAllOwners();
        assertThat(owners).isNotEmpty();
        assertThat(owners).extracting(AppUser::getEmail).contains("tom@example.com");
    }

    @Test
    void shouldAssignPetsToOwnersAutomatically() throws Exception {
        AppUser richOwner = new AppUser("Alice", "Rich", "alice@example.com", BigDecimal.valueOf(1000));
        AppUser poorOwner = new AppUser("Bob", "Poor", "bob@example.com", BigDecimal.valueOf(1));
        ownerService.saveAll(List.of(richOwner, poorOwner));

        Pet cat1 = new Cat(null, "Garfield", "Fat cat", LocalDate.now().minusYears(4), BigDecimal.ZERO);
        Pet cat2 = new Cat(null, "Tom", "Mischievous cat", LocalDate.now().minusYears(2), BigDecimal.ZERO);
        petService.saveAll(List.of(cat1, cat2));

        List<AppUser> updatedOwners = ownerService.buyPets();
        long totalAssigned = updatedOwners.stream().mapToLong(o -> o.getPets().size()).sum();

        assertThat(totalAssigned).isEqualTo(2);
        assertThat(petService.getAllPets()).allMatch(p -> p.getOwner() != null);
    }

    @Test
    void shouldLogBuyPetsExecution() throws Exception {
        Log log = new Log();
        log.setNumOfBuyers(2);
        log.setNumOfBrowsers(1);
        log.setDateOfExecution(LocalDate.now());

        logService.recordBuyExecution(log);
        List<Log> logs = logService.getAllLogs();

        assertThat(logs).isNotEmpty();
        assertThat(logs.getFirst().getDateOfExecution()).isEqualTo(LocalDate.now());
    }

}

