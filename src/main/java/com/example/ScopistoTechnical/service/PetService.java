package com.example.ScopistoTechnical.service;


import com.example.ScopistoTechnical.model.DTOs.PetDTO;
import com.example.ScopistoTechnical.model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {
    Optional<Pet> getPet(Long id) throws Exception;
    List<Pet> getAllPets() throws Exception;
    List<Pet> saveAll(List<Pet> pets) throws Exception;
    Optional<Pet> save(Pet pet) throws Exception;
}
