package com.example.ScopistoTechnical.service;

import com.example.ScopistoTechnical.model.AppUser;
import com.example.ScopistoTechnical.model.Pet;

import java.util.List;

public interface DataSeederService {
    public List<AppUser> seedOwners() throws Exception;
    public List<Pet> seedPets() throws Exception;
}
