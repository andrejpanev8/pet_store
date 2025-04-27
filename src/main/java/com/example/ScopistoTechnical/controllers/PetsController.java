package com.example.ScopistoTechnical.controllers;

import com.example.ScopistoTechnical.model.Pet;
import com.example.ScopistoTechnical.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/pets")
public class PetsController {
    private final PetService petService;

    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() throws Exception {
        List<Pet> pets = petService.getAllPets();
        return ResponseEntity.ok(pets);
    }
}
