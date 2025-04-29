package com.example.ScopistoTechnical.service.impl;

import com.example.ScopistoTechnical.model.DTOs.PetDTO;
import com.example.ScopistoTechnical.model.Pet;
import com.example.ScopistoTechnical.repository.PetRepository;
import com.example.ScopistoTechnical.service.PetService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Optional<Pet> getPet(Long id) throws Exception {
        try{
            return petRepository.findById(id);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Pet> getAllPets() throws Exception {
        try{
            return petRepository.findAll();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Pet> saveAll(List<Pet> pets) throws Exception {
        try{
            return petRepository.saveAll(pets);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Optional<Pet> save(Pet pet) throws Exception {
        try{
            return Optional.of(petRepository.save(pet));
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
