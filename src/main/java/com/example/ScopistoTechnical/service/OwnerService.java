package com.example.ScopistoTechnical.service;

import com.example.ScopistoTechnical.model.AppUser;
import com.example.ScopistoTechnical.model.DTOs.AppUserDTO;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    Optional<AppUser> getOwner(Long id) throws Exception;
    List<AppUser> getAllOwners() throws Exception;
    List<AppUser> saveAll(List<AppUser> owners) throws Exception;
    Optional<AppUser> save(AppUserDTO appUserDTO) throws Exception;
}
