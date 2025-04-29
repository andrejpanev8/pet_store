package com.example.ScopistoTechnical.controllers;

import com.example.ScopistoTechnical.model.AppUser;
import com.example.ScopistoTechnical.model.DTOs.AppUserDTO;
import com.example.ScopistoTechnical.model.Log;
import com.example.ScopistoTechnical.service.DataSeederService;
import com.example.ScopistoTechnical.service.LogService;
import com.example.ScopistoTechnical.service.OwnerService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping({"/api/owners", "/api"})
public class OwnersController {
    private final OwnerService ownerService;
    private final DataSeederService dataSeederService;
    private final LogService logService;

    @GetMapping(path = {"/list-users"})
    public ResponseEntity<List<AppUser>> listAllOwners() throws Exception {
        List<AppUser> owners = ownerService.getAllOwners();
        return ResponseEntity.ok(owners);
    }

    @PostMapping(path = {"/create-users"})
    public ResponseEntity<List<AppUser>> createOwners() throws Exception {
        return ResponseEntity.ok(dataSeederService.seedOwners());
    }

    @PostMapping(path = {"/buy"})
    public ResponseEntity<List<AppUser>> buy() throws Exception {
        List<AppUser> owners = ownerService.buyPets();

        int buyers = owners.size() - owners.stream().filter(owner-> owner.getPets().isEmpty()).toList().size();
        int browsers = owners.size() - buyers;
        Log log = new Log(buyers, browsers);
        logService.recordBuyExecution(log);

        return ResponseEntity.ok(owners);
    }
}
