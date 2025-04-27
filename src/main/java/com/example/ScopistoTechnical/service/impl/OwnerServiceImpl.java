package com.example.ScopistoTechnical.service.impl;

import com.example.ScopistoTechnical.model.AppUser;
import com.example.ScopistoTechnical.model.DTOs.AppUserDTO;
import com.example.ScopistoTechnical.repository.OwnerRepository;
import com.example.ScopistoTechnical.service.OwnerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Override
    public Optional<AppUser> getOwner(Long id) throws Exception {
        try{
            return ownerRepository.findById(id);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<AppUser> getAllOwners() throws Exception {
        try{
            return Optional.of(ownerRepository.findAll()).orElse(new ArrayList<>());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<AppUser> saveAll(List<AppUser> owners) throws Exception {
        try{
            return ownerRepository.saveAll(owners);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Optional<AppUser> save(AppUserDTO appUserDTO) throws Exception {
        try{
            AppUser appUser = new AppUser(
                    appUserDTO.firstName,
                    appUserDTO.lastName,
                    appUserDTO.email,
                    appUserDTO.budget
            );
            return Optional.of(ownerRepository.save(appUser));
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
